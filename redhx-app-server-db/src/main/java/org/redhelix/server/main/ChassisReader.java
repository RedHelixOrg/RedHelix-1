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
import java.util.List;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.domain.ClientProperty;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.edm.Edm;
import org.apache.olingo.commons.api.format.ODataFormat;

/**
 * 
 * Git SHA: $Id$
 * 
 * @since RedHelix Version HELIX_VERSION_TAG // Do not change this line.
 * @author Hank Bruning
 *
 */
class ChassisReader
{

    private static final String ENTITY_SET_CHASSIS = "odata";

    private static final String KEY_SYSTEMS = "Systems";
    private static final String KEY_CHASSIS = "Chassis";
    private static final String KEY_MANAGERS = "Managers";
    private static final String KEY_TASKS = "Tasks";
    private static final String KEY_SESSION_SERVICE = "SessionService";
    private static final String KEY_ACCOUNT_SERVICE = "AccountService";
    private static final String KEY_EVENT_SERVICE = "EventService";
    private static final String KEY_REGISTRIES = "Registries";
    private static final String KEY_JSON_SCHEMA = "JsonSchemas";

    private static final String SERVICE_ROOT = "http://localhost:9080/mockup1/redfish/v1/";

    private final ODataClient client;

    ChassisReader()
    {
        client = ODataClientFactory.getClient();
        client.getConfiguration().setDefaultPubFormat( ODataFormat.JSON_NO_METADATA  );//ContentType.JSON_NO_METADATA);
    }

    public List<ClientEntity> getAllChassis()
    {
        final URI carsEntitySetURI = client.newURIBuilder(SERVICE_ROOT).appendEntitySetSegment(ENTITY_SET_CHASSIS).build();
        client.getRetrieveRequestFactory();

        final ODataRetrieveResponse<ClientEntitySet> chassisEntitySetResponse = client.getRetrieveRequestFactory()
                .getEntitySetRequest(carsEntitySetURI).execute();
        List<ClientEntity> list;
        if (chassisEntitySetResponse.getStatusCode() == 200)
        {
            list = chassisEntitySetResponse.getBody().getEntities();

            for (final ClientEntity entity : list)
            {
                for (final ClientProperty prop : entity.getProperties())
                {
                    final String nameKey = prop.getName();
                    if (nameKey.equals("name"))
                    {
                        final ClientProperty urlProp = entity.getProperty("url");
                        System.out.println("HFB15: name=" + prop.getValue() + " urlProp=" + urlProp.getValue());

                    }
                    // System.out.println("HFB15: prop=" + prop.getName() + ", "
                    // + prop.getValue());

                }
            }
        }
        else
        {
            System.out.println("HFB15: error retriving chassis. " + chassisEntitySetResponse.getStatusCode());
            list = null;

        }

        return list;
    }

    public ClientEntity getCar(int key)
    {
        final URI carEntityURI = client.newURIBuilder(SERVICE_ROOT).appendEntitySetSegment(ENTITY_SET_CHASSIS).appendKeySegment(key)
                .build();
        final ODataRetrieveResponse<ClientEntity> car = client.getRetrieveRequestFactory().getEntityRequest(carEntityURI).execute();

        return car.getBody();
    }

    public Edm getMetaDocument()
    {
        final ODataRetrieveResponse<Edm> response = client.getRetrieveRequestFactory().getMetadataRequest(SERVICE_ROOT).execute();

        return response.getBody();
    }
}
