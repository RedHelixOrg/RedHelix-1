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

import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;

import org.redhelix.core.chassis.RedHxChassisCollection;

/**
 *
 * <br><br>
 * Git SHA: $Id$
 *
 * @since RedHelix Version HELIX_VERSION_TAG // Do not change this line.
 * @author Hank Bruning
 *
 */
final class ChassisCollectionReader
{
    ChassisCollectionReader( RedHxServerConnectionContext ctx ) {}

//  public ClientEntity getCar(int key)
//  {
//      final URI carEntityURI
//                = client.newURIBuilder(SERVICE_ROOT).appendEntitySetSegment(ENTITY_SET_CHASSIS).appendKeySegment(key).build();
//      final ODataRetrieveResponse<ClientEntity> car = client.getRetrieveRequestFactory().getEntityRequest(carEntityURI).execute();
//
//      return car.getBody();
//  }
    static RedHxChassisCollection readChassisCollection( RedHxServerConnectionContext ctx )
    {

//      final URI carEntityURI
//                = client.newURIBuilder(SERVICE_ROOT).appendEntitySetSegment(ENTITY_SET_CHASSIS).appendKeySegment(key).build();
        final ODataRetrieveResponse<ClientEntity> chassisCollectionResponse = ctx.getChassisEntityRequest().execute();

        if (chassisCollectionResponse.getStatusCode() == 200)
        {
            ClientEntity entity = chassisCollectionResponse.getBody();

            System.out.println("HFB5: got chassis entity " + entity);
        }
        else
        {
            System.out.println("HFB5: failed  to get chassis entity. error  " + chassisCollectionResponse.getStatusCode());
        }

        return null;
    }
}
