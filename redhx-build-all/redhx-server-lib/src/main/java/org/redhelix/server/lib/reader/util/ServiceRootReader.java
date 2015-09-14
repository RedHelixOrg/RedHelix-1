/*
 * Copyright 2015 JBlade LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License
 */
package org.redhelix.server.lib.reader.util;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.domain.ClientProperty;
import org.redhelix.core.root.RedHxServiceRootIdImpl;
import org.redhelix.core.root.RedHxServiceRootLocatorImpl;
import org.redhelix.core.service.root.RedHxServiceRootId;
import org.redhelix.core.service.root.RedHxServiceRootIdEum;
import org.redhelix.core.service.root.RedHxServiceRootLocator;
import org.redhelix.core.service.root.RedHxTcpProtocolTypeEnum;
import static org.redhelix.core.service.root.RedHxTcpProtocolTypeEnum.HTTPS;
import org.redhelix.core.util.RedHxHttpResponseException;
import org.redhelix.core.util.RedHxRedfishProtocolVersionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * read the Redfish server root. It contains the paths to all other Redfish data available from the
 * Redfish server.
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 */
class ServiceRootReader {

  private static final String ENTITY_SET_NAME = "odata/"; // from the Redfish specification.

  /**
   * each KEY coresponds to a Redfish schema and each schema has a JSON file provided by Redfish
   */
  private static final String KEY_OEM = "Oem";
  private static final String KEY_REDFISH_SERVICE = "Service";
  private static final String KEY_ACCOUNT_SERVICE = "AccountService";
  private static final String KEY_CHASSIS = "Chassis";
  private static final String KEY_EVENT_SERVICE = "EventService";
  private static final String KEY_JSON_SCHEMA = "JsonSchemas";
  private static final String KEY_MANAGERS = "Managers";
  private static final String KEY_REGISTRIES = "Registries";
  private static final String KEY_SESSIONS = "Sessions";
  private static final String KEY_SESSION_SERVICE = "SessionService";
  private static final String KEY_SYSTEMS = "Systems";
  private static final String KEY_TASKS = "Tasks";
  private static final Map<String, RedHxServiceRootIdEum> ODATA_STRING_TO_ENUM_MAP =
      createStringToIdMap();

  private ServiceRootReader() {}

  /**
   * read from a URL the Redfish services available on the host.
   *
   * @param ctx the context of the server to read from.
   * @param httpProtocol the type of http protocol to use with each of the Redffish services. This
   *        value is not used for the Root Service because the Redfish specification mandates that
   *        it uses http only.
   * @param hostName the name of the host to read from.
   * @param tcpPortNumber the TCP pprt number to read the service from.
   * @param redfishProtocolVersion the redfish protocol version.
   * @return the locator containing all Redfish services available on the host:tcp port number
   * @throws URISyntaxException
   */
  public static RedHxServiceRootLocator getServiceRootLocator(final ODataClient client,
      final RedHxTcpProtocolTypeEnum httpProtocol, final String hostName, final int tcpPortNumber,
      final RedHxRedfishProtocolVersionEnum redfishProtocolVersion)
          throws URISyntaxException, RedHxHttpResponseException {
    RedHxServiceRootId serviceRoot =
        new RedHxServiceRootIdImpl(httpProtocol, hostName, tcpPortNumber, redfishProtocolVersion);
    final String serviceRootStr = serviceRoot.getServiceRootString();
    final URI redfishEntitySetURI =
        client.newURIBuilder(serviceRootStr).appendEntitySetSegment(ENTITY_SET_NAME).build();

    client.getConfiguration().setGzipCompression(true);
    client.getRetrieveRequestFactory();

    final ODataRetrieveResponse<ClientEntitySet> serviceRootResponse =
        client.getRetrieveRequestFactory().getEntitySetRequest(redfishEntitySetURI).execute();
    final List<ClientEntity> list;
    final String tcpProtocolStr;

    switch (httpProtocol) {
      case HTTP:
        tcpProtocolStr = "http";

        break;
      case HTTPS:
        tcpProtocolStr = "https";

        break;
      default:
        throw new IllegalArgumentException(
            "Argument \"httpProtocol\" contains an unknown value of " + httpProtocol);
    }

    RedHxServiceRootLocator locator = null;
    final Logger logger = LoggerFactory.getLogger(ServiceRootReader.class);

    if (serviceRootResponse.getStatusCode() == HttpURLConnection.HTTP_OK) {
      list = serviceRootResponse.getBody().getEntities();

      /**
       * Use a treemap so when the items are printed out in toString they are always in the same
       * order.
       */
      Map<RedHxServiceRootIdEum, URI> idToUriMap = new TreeMap<>();

      for (final ClientEntity entity : list) {
        for (final ClientProperty prop : entity.getProperties()) {
          final String nameKey = prop.getName();

          if (nameKey.equals("name")) {
            final String serviceRootName = prop.getValue().toString();
            final ClientProperty urlProp = entity.getProperty("url");
            final String urlString = urlProp.getValue().toString();
            final RedHxServiceRootIdEum serviceId = ODATA_STRING_TO_ENUM_MAP.get(serviceRootName);

            if (serviceId != null) {
              final URI serviceUri =
                  new URI(tcpProtocolStr, null, hostName, tcpPortNumber, urlString, null, null);

              idToUriMap.put(serviceId, serviceUri);
            } else {
              String msg = "Can not find service for " + serviceRootName + " set by host "
                  + hostName + ":" + tcpPortNumber;

              logger.error(msg);

              throw new IllegalArgumentException();
            }
          }
        }

        locator = new RedHxServiceRootLocatorImpl(serviceRoot, tcpProtocolStr, hostName,
            tcpPortNumber, idToUriMap);
      }
    } else {
      logger.info("error retriving JSON message from " + hostName + ":" + tcpPortNumber
          + ". HTML error is " + serviceRootResponse.getStatusCode());

      throw new RedHxHttpResponseException(RedHxServiceRootIdEum.REDFISH_SERVICE,
          serviceRootResponse.getStatusCode(), "Can not read The service root.");
    }

    return locator;
  }

  private static Map<String, RedHxServiceRootIdEum> createStringToIdMap() {
    Map<String, RedHxServiceRootIdEum> map = new HashMap<>();;

    map.put(KEY_ACCOUNT_SERVICE, RedHxServiceRootIdEum.ACCOUNT_SERVICE);
    map.put(KEY_CHASSIS, RedHxServiceRootIdEum.CHASSIS);
    map.put(KEY_EVENT_SERVICE, RedHxServiceRootIdEum.EVENT_SERVICE);
    map.put(KEY_JSON_SCHEMA, RedHxServiceRootIdEum.JSON_SCHEMAS);
    map.put(KEY_MANAGERS, RedHxServiceRootIdEum.MANAGERS);
    map.put(KEY_OEM, RedHxServiceRootIdEum.OEM);
    map.put(KEY_REDFISH_SERVICE, RedHxServiceRootIdEum.REDFISH_SERVICE);
    map.put(KEY_REGISTRIES, RedHxServiceRootIdEum.REGISTRIES);
    map.put(KEY_SESSIONS, RedHxServiceRootIdEum.SESSIONS_ACTIVE);
    map.put(KEY_SESSION_SERVICE, RedHxServiceRootIdEum.SESSION_SERVICE);
    map.put(KEY_SYSTEMS, RedHxServiceRootIdEum.COMPUTER_SYSTEMS);
    map.put(KEY_TASKS, RedHxServiceRootIdEum.TASK_SERIVCE);

    return map;
  }

  //
  // public Edm getMetaDocument()
  // {
  // final ODataRetrieveResponse<Edm> response =
  // client.getRetrieveRequestFactory().getMetadataRequest(SERVICE_ROOT).execute();
  //
  // return response.getBody();
  // }
}
