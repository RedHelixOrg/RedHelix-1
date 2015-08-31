/*
 * Copyright 2015 JBlade LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 *
 */



package org.redhelix.server.main;

import org.apache.olingo.client.api.communication.request.retrieve.ODataEntityRequest;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.format.ODataFormat;

import org.redhelix.core.service.root.RedHxServiceRootIdEum;
import org.redhelix.core.service.root.RedHxServiceRootLocator;
import org.redhelix.core.service.root.RedHxTcpProtocolTypeEnum;
import org.redhelix.core.util.RedHxHttpResponseException;
import org.redhelix.core.util.RedHxRedfishProtocolVersionEnum;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * the communication parameters used to contact a single Redfish OData provider.
 * <br><br>
 * Git SHA: $Id$
 *
 * @since RedHelix Version HELIX_VERSION_TAG // Do not change this line.
 * @author Hank Bruning
 *
 */
public final class RedHxServerConnectionContext
{
    private final ODataClient                     client;
    private final RedHxRedfishProtocolVersionEnum redfishProtocolVersion;
    private RedHxServiceRootLocator               serviceRootLocator;

    RedHxServerConnectionContext( final RedHxRedfishProtocolVersionEnum redfishProtocolVersion )
    {
        this.redfishProtocolVersion = redfishProtocolVersion;
        this.client                 = ODataClientFactory.getClient();
        this.client.getConfiguration().setDefaultPubFormat(ODataFormat.JSON_NO_METADATA);    // ContentType.JSON_NO_METADATA);
    }

    private RedHxServerConnectionContext( )
    {
        this.redfishProtocolVersion = null;
        this.client                 = null;
    }

    public ODataEntityRequest<ClientEntity> getChassisEntityRequest( )
    {
        URI                              chassisUri = serviceRootLocator.getUri(RedHxServiceRootIdEum.CHASSIS);
        ODataEntityRequest<ClientEntity> req        = client.getRetrieveRequestFactory().getEntityRequest(chassisUri);

        return req;
    }

    public RedHxRedfishProtocolVersionEnum getRedfishProtocolVersion( )
    {
        return redfishProtocolVersion;
    }

    public void openConnection( final RedHxTcpProtocolTypeEnum httpProtocol,
                                final String                   hostName,
                                final int                      tcpPortNumber,
                                final String                   servicePrefix )
            throws URISyntaxException,
                   RedHxHttpResponseException
    {
        serviceRootLocator = ServiceRootReader.getServiceRootLocator(client, httpProtocol, hostName, tcpPortNumber, servicePrefix,
                RedHxRedfishProtocolVersionEnum.VERSION_1);
    }
}
