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

import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.codec.binary.Base64;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntityRequest;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.format.ODataFormat;
import org.redhelix.core.service.root.RedHxServiceRootIdEum;
import org.redhelix.core.service.root.RedHxServiceRootLocator;
import org.redhelix.core.service.root.RedHxTcpProtocolTypeEnum;
import org.redhelix.core.util.RedHxHttpResponseException;
import org.redhelix.core.util.RedHxRedfishProtocolVersionEnum;
import org.redhelix.core.util.RedHxUriPath;

/**
 * the communication parameters used to contact a single Redfish OData provider. After this class
 * has been created the next method to call is {@link #openConnection() }.
 * <p>
 * To create this class the version of the Redfish protocol must be specified. This implies prior
 * knowledge of the Redfish server before a TCP connection is opened to it.. The Redfish
 * specification v1.0, Section 8.4.3 M-SEARCH Response mandates that the reply to the discovery
 * message must include the Redfish version supported by the server.
 * </p>
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public final class RedHxServerConnectionContext {

  private final ODataClient client;
  private final String hostName;
  private final RedHxTcpProtocolTypeEnum httpProtocol;
  private final String password;
  private final RedHxRedfishProtocolVersionEnum redfishProtocolVersion;
  private final int tcpPortNumber;
  private final String userName;
  private RedHxServiceRootLocator serviceRootLocator;

  /**
   * Create the context for the connection to the Redfish server with basic HTTP authentication.
   * Creating the class does not require a free TCP port number on the local host.
   *
   * @param redfishProtocolVersion the Redfish protocol version to used. See this class's
   *        description for the reason this shall not be null.
   * @param httpProtocol the type of TCP connection to use. This shall not be null.
   * @param hostName the hostname of the Redfish server. This shall not be null.
   * @param tcpPortNumber the TCP port number in the hostName argument to connect to.
   * @param userName the user name used for HTTPs basic authorization. This shall not be null.
   * @param password the password for the userName for HTTPs basic authorization. This shall not be
   *        null.
   * @throws RedHxHttpResponseException
   * @throws URISyntaxException
   */
  public RedHxServerConnectionContext(final RedHxRedfishProtocolVersionEnum redfishProtocolVersion,
      final RedHxTcpProtocolTypeEnum httpProtocol, final String hostName, final int tcpPortNumber,
      final String userName, final String password) {
    this.redfishProtocolVersion = redfishProtocolVersion;
    this.httpProtocol = httpProtocol;
    this.hostName = hostName;
    this.tcpPortNumber = tcpPortNumber;
    this.userName = userName;
    this.password = password;

    //
    this.client = ODataClientFactory.getClient();
    this.client.getConfiguration().setDefaultPubFormat(ODataFormat.JSON_NO_METADATA);
  }

  /**
   * Create the context for the connection to the Redfish server with no HTTP authentication.
   * Creating the class does not require a free TCP port number on the local host.
   *
   * @param redfishProtocolVersion the Redfish protocol version to used. See this class's
   *        description for the reason this shall not be null.
   * @param httpProtocol the type of TCP connection to use. This shall not be null.
   * @param hostName the hostname of the Redfish server. This shall not be null.
   * @param tcpPortNumber the TCP port number in the hostName argument to connect to.
   */
  public RedHxServerConnectionContext(final RedHxRedfishProtocolVersionEnum redfishProtocolVersion,
      final RedHxTcpProtocolTypeEnum httpProtocol, final String hostName, final int tcpPortNumber) {
    this.redfishProtocolVersion = redfishProtocolVersion;
    this.httpProtocol = httpProtocol;
    this.hostName = hostName;
    this.tcpPortNumber = tcpPortNumber;
    this.userName = null;
    this.password = null;

    //
    this.client = ODataClientFactory.getClient();
    this.client.getConfiguration().setDefaultPubFormat(ODataFormat.JSON_NO_METADATA);
  }

  private RedHxServerConnectionContext() {
    this.redfishProtocolVersion = null;
    this.httpProtocol = null;
    this.hostName = null;
    this.tcpPortNumber = -1;
    this.userName = null;
    this.password = null;

    //
    this.client = null;
    this.serviceRootLocator = null;
  }

  /**
   * get an OData entity request for a Redfish Chassis. The user name an password created when the
   * connection was opened will be used.
   *
   * @return
   */
  public ODataEntityRequest<ClientEntity> getChassisEntityRequest() {
    URI chassisUri = serviceRootLocator.getUri(RedHxServiceRootIdEum.CHASSIS);
    ODataEntityRequest<ClientEntity> req =
        client.getRetrieveRequestFactory().getEntityRequest(chassisUri);

    if (userName != null) {
      req = setServerAuth(req);
    }

    return req;
  }

  /**
   * get an OData entity request for a Redfish resource. The user name an password created when the
   * connection was opened will be used.
   *
   * @param pathToResource
   * @return
   * @throws URISyntaxException
   */
  public ODataEntityRequest<ClientEntity> getEntityRequest(RedHxUriPath pathToResource)
      throws URISyntaxException {
    URI myUri = serviceRootLocator.getUri(pathToResource.getValue());
    ODataEntityRequest<ClientEntity> req =
        client.getRetrieveRequestFactory().getEntityRequest(myUri);

    if (userName != null) {
      req = setServerAuth(req);
    }

    return req;
  }

  public RedHxRedfishProtocolVersionEnum getRedfishProtocolVersion() {
    return redfishProtocolVersion;
  }

  /**
   * open a connection to a Redfish server. The Redfish specification does not require HTTP
   * authentication or authorization when it is created so that the top level services, Chassis,
   * Computer System, etc can be validated that they are present.
   *
   * @throws URISyntaxException
   * @throws RedHxHttpResponseException
   */
  public void openConnection() throws URISyntaxException, RedHxHttpResponseException {
    serviceRootLocator = ServiceRootReader.getServiceRootLocator(client, httpProtocol, hostName,
        tcpPortNumber, RedHxRedfishProtocolVersionEnum.VERSION_1);
  }

  /**
   * Add to the HTTP request basic server authorization.
   *
   * @param req the request to add.
   * @return
   */
  private ODataEntityRequest<ClientEntity> setServerAuth(ODataEntityRequest<ClientEntity> req) {
    String authorization = "Basic ";

    authorization += new String(Base64.encodeBase64((userName + ":" + password).getBytes()));
    req.addCustomHeader("Authorization", authorization);

    return req;
  }
}
