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
 *
 */
package org.redhelix.redhx.server.db;

import java.net.URI;
import java.util.Map;
import java.util.UUID;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.ODataRequest;
import org.apache.olingo.client.api.communication.request.invoke.ODataInvokeRequest;
import org.apache.olingo.client.api.communication.response.ODataInvokeResponse;
import org.apache.olingo.client.api.domain.ClientInvokeResult;
import org.apache.olingo.client.api.domain.ClientValue;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.http.HttpHeader;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;

/**
 *
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public abstract class RedHexlixAbstractServiceTest
{

    private static TJWSEmbeddedJaxrsServer embeddedServer;
    private final ODataClient client;
    private final UUID cookie;
    private final String serviceUrl;

    protected RedHexlixAbstractServiceTest()
    {
        client = ODataClientFactory.getClient();
        serviceUrl = "http://localhost:" + RedHelixTestServerHelper.TCP_PORT_NUMBER + RedHelixTestServerHelper.HTTP_URL;
        cookie = UUID.randomUUID();
    }

    @AfterClass
    public static void afterClass()
            throws Exception
    {
        embeddedServer.stop();
    }

    @BeforeClass
    public static void beforeClass()
            throws Exception
    {
        embeddedServer = RedHelixTestServerHelper.startServer();
    }

    /**
     * Call and action in the OData RESTFull server. The action changes a state in the OData Server.
     *
     * @param <T>
     * @param name
     * @param resultRef
     * @param parameters
     * @param returnMinimal
     * @return
     */
    protected <T extends ClientInvokeResult> ODataInvokeResponse<T> callAction(final String name,
                                                                               final Class<T> resultRef,
                                                                               final Map<String, ClientValue> parameters,
                                                                               final boolean returnMinimal)
    {
        final URI actionURI = client.newURIBuilder(serviceUrl).appendActionCallSegment(name).build();
        ODataInvokeRequest<T> request = client.getInvokeRequestFactory().getActionInvokeRequest(actionURI,
                                                                                                resultRef,
                                                                                                parameters);

        if (returnMinimal)
        {
            request.setPrefer(client.newPreferences().returnMinimal());
        }

        // We can re-use the session since our actions don't (yet?!) modify existing data.
        setCookieHeader(request);

        final ODataInvokeResponse<T> response = request.execute();

        if (returnMinimal)
        {
            assertEquals(HttpStatusCode.NO_CONTENT.getStatusCode(),
                         response.getStatusCode());
            assertEquals("return=minimal",
                         response.getHeader(HttpHeader.PREFERENCE_APPLIED).iterator().next());
        }

        return response;
    }

    protected ODataClient getODataClient()
    {
        return client;
    }

    protected String getServiceUrl()
    {
        return serviceUrl;
    }

    protected void setCookieHeader(ODataRequest request)
    {
        request.addCustomHeader(HttpHeader.COOKIE,
                                cookie.toString());
    }
}
