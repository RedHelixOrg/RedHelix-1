/*
 * Copyright 2015 JBlade LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package org.redhelix.server.main;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.domain.ClientProperty;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.format.ODataFormat;
import org.redhelix.core.service.root.RedHxServiceRootIdEum;
import org.redhelix.core.util.RedHxServiceRootLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * <br><br>Git SHA: $Id$
 *
 * @since RedHelix Version HELIX_VERSION_TAG // Do not change this line.
 * @author Hank Bruning
 *
 */
class ServiceRootReader
{

    private static final String ENTITY_SET_CHASSIS = "odata";
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
    private static final String SERVICE_ROOT = "http://localhost:9080/mockup1/redfish/v1/";
    private static final String KEY_TASKS = "Tasks";
    private static final Map<String, RedHxServiceRootIdEum> ODATA_STRING_TO_ENUM_MAP = createStringToIdMap();
    private final ODataClient client;

    public enum TcpProtocol
    {
        HTTP,
        HTTPS
    }
    private final Logger logger = LoggerFactory.getLogger(ServiceRootReader.class);

    ServiceRootReader()
    {
        client = ODataClientFactory.getClient();
        client.getConfiguration().setDefaultPubFormat(ODataFormat.JSON_NO_METADATA);    // ContentType.JSON_NO_METADATA);
    }

    public ClientEntity getCar(int key)
    {
        final URI carEntityURI
                  = client.newURIBuilder(SERVICE_ROOT).appendEntitySetSegment(ENTITY_SET_CHASSIS).appendKeySegment(key).build();
        final ODataRetrieveResponse<ClientEntity> car = client.getRetrieveRequestFactory().getEntityRequest(carEntityURI).execute();

        return car.getBody();
    }

    public RedHxServiceRootLocator getServiceRootLocator(TcpProtocol protocol,
                                                         String hostName,
                                                         int tcpPortNumber)
            throws URISyntaxException
    {
        final URI carsEntitySetURI = client.newURIBuilder(SERVICE_ROOT).appendEntitySetSegment(ENTITY_SET_CHASSIS).build();

        client.getRetrieveRequestFactory();

        final ODataRetrieveResponse<ClientEntitySet> chassisEntitySetResponse
                                                     = client.getRetrieveRequestFactory().getEntitySetRequest(carsEntitySetURI).execute();
        final List<ClientEntity> list;
        final String tcpProtocolStr;

        switch (protocol)
        {
            case HTTP:
                tcpProtocolStr = "http";

                break;
            case HTTPS:
                tcpProtocolStr = "https";

                break;
            default:
                throw new IllegalArgumentException("Argument \"potocol\" contains an unknown value of " + protocol);
        }

        RedHxServiceRootLocator locator = null;

        if (chassisEntitySetResponse.getStatusCode() == 200)
        {
            list = chassisEntitySetResponse.getBody().getEntities();

            Map<RedHxServiceRootIdEum, URI> idToUriMap = new HashMap<>();

            for (final ClientEntity entity : list)
            {
                for (final ClientProperty prop : entity.getProperties())
                {
                    final String nameKey = prop.getName();

                    if (nameKey.equals("name"))
                    {
                        final String serviceRootName = prop.getValue().toString();
                        final ClientProperty urlProp = entity.getProperty("url");
                        final String urlString = urlProp.getValue().toString();
                        final RedHxServiceRootIdEum serviceId = ODATA_STRING_TO_ENUM_MAP.get(serviceRootName);

                        if (serviceId != null)
                        {
                            final URI serviceUri = new URI(tcpProtocolStr,
                                                           null,
                                                           hostName,
                                                           tcpPortNumber,
                                                           urlString,
                                                           null,
                                                           null);

                            idToUriMap.put(serviceId,
                                           serviceUri);

                        }
                        else
                        {
                            String msg = "Can not find service for " + serviceRootName + " set by host " + hostName + ":"
                                    + tcpPortNumber;
                            logger.error(msg);
                            throw new IllegalArgumentException();
                        }
                    }
                }

                locator = new RedHxServiceRootLocator(idToUriMap);
            }
        }
        else
        {
            logger.info("error retriving JSON message from " + hostName + ":" + tcpPortNumber + ". HTML error is " + chassisEntitySetResponse.getStatusCode());
            list = null;
        }

        return locator;
    }

    private static Map<String, RedHxServiceRootIdEum> createStringToIdMap()
    {
        Map<String, RedHxServiceRootIdEum> map = new HashMap<>();;

        map.put(KEY_ACCOUNT_SERVICE,
                RedHxServiceRootIdEum.ACCOUNT_SERVICE);
        map.put(KEY_CHASSIS,
                RedHxServiceRootIdEum.CHASSIS);
        map.put(KEY_EVENT_SERVICE,
                RedHxServiceRootIdEum.EVENT_SERVICE);
        map.put(KEY_JSON_SCHEMA,
                RedHxServiceRootIdEum.JSON_SCHEMAS);
        map.put(KEY_MANAGERS,
                RedHxServiceRootIdEum.MANAGERS);
        map.put(KEY_OEM,
                RedHxServiceRootIdEum.OEM);
        map.put(KEY_REDFISH_SERVICE,
                RedHxServiceRootIdEum.REDFISH_SERVICE);
        map.put(KEY_REGISTRIES,
                RedHxServiceRootIdEum.REGISTRIES);
        map.put(KEY_SESSIONS,
                RedHxServiceRootIdEum.SESSIONS_ACTIVE);
        map.put(KEY_SESSION_SERVICE,
                RedHxServiceRootIdEum.SESSION_SERVICE);
        map.put(KEY_SYSTEMS,
                RedHxServiceRootIdEum.SYSTEMS);
        map.put(KEY_TASKS,
                RedHxServiceRootIdEum.TASK_SERIVCE);

        return map;
    }

//
//  public Edm getMetaDocument()
//  {
//      final ODataRetrieveResponse<Edm> response = client.getRetrieveRequestFactory().getMetadataRequest(SERVICE_ROOT).execute();
//
//      return response.getBody();
//  }
}
