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
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.ODataServiceDocumentRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientServiceDocument;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.format.ContentType;

/**
 * 
 * @author Hank Bruning Date: $Date$ Git SHA: $Id$
 * @since RedHelix Version 0.0.1
 *
 */
public class RedMatrixServerDb
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "error");

        final ChassisReader chassisReader = new ChassisReader();

        final List<ClientEntity> list = chassisReader.getAllChassis();
        System.out.println("HFB15: The enitity list contained " + list.size() + " entitys");

        if (list != null)
        {
            for (final ClientEntity entity : list)
            {

                System.out.println("HFB15: ######################");

            }
        }
    }

    private void bad()
    {
        // System.setProperty("org.slf4j.simpleLogger.defaultLogLevel",
        // "error");

        final ODataClient client = ODataClientFactory.getClient();
        client.getConfiguration().setDefaultPubFormat(ContentType.APPLICATION_JSON);

        final String serviceRoot = "http://localhost:9080/mockup1/redfish/v1"; /// Chassis/1";

        final ODataServiceDocumentRequest req = client.getRetrieveRequestFactory().getServiceDocumentRequest(serviceRoot);

        final ODataRetrieveResponse<ClientServiceDocument> res = req.execute();

        if (res.getStatusCode() == 200)
        {
            System.out.println("HFB15: res=" + res.getContentType());

            final ClientServiceDocument serviceDocument = res.getBody();

            final Collection<String> entitySetNames = serviceDocument.getEntitySetNames();
            final Map<String, URI> entitySets = serviceDocument.getEntitySets();
            final Map<String, URI> singletons = serviceDocument.getSingletons();
            final Map<String, URI> functionImports = serviceDocument.getFunctionImports();
            // URI productsUri = serviceDocument.getEntitySetURI("Products");
            System.out.println("HFB15: ");
        }
        else
        {
            System.out.println("HFB15: unable to read index.html from server. " + res.getStatusCode());

        }

    }

}
