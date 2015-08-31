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
import org.apache.olingo.client.api.domain.ClientAnnotation;
import org.apache.olingo.client.api.domain.ClientComplexValue;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientProperty;
import org.apache.olingo.client.api.domain.ClientValue;
import org.redhelix.core.chassis.RedHxChassisCollection;
import org.redhelix.core.chassis.RedHxChassisParseException;
import org.redhelix.core.service.root.RedHxServiceRootIdEum;
import org.redhelix.core.util.RedHxHttpResponseException;

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

    ChassisCollectionReader(RedHxServerConnectionContext ctx)
    {
    }

    static RedHxChassisCollection readChassisCollection(RedHxServerConnectionContext ctx)
            throws RedHxChassisParseException, RedHxHttpResponseException
    {

        final ODataRetrieveResponse<ClientEntity> chassisCollectionResponse = ctx.getChassisEntityRequest().execute();

        if (chassisCollectionResponse.getStatusCode() == 200)
        {
            ClientEntity entity = chassisCollectionResponse.getBody();

            ClientProperty chassisProperty = entity.getProperty("Members");

            //       System.out.println("HFB5: pvalue "+chassisProperty);
            for (ClientValue chassisValue : chassisProperty.getCollectionValue())
            {

                ClientComplexValue cplx = chassisValue.asComplex();
                if (cplx != null)
                {
                    ClientAnnotation anno = cplx.getAnnotations().get(0);
                    if (anno.getTerm().equals("odata.id"))
                    {
                        String chassisUrl = anno.getValue().toString();

                        System.out.println("HFB5: chassis=" + chassisUrl);
                    }
                    else
                    {
                        throw new RedHxChassisParseException("");
                    }
                }
                else
                {
                    throw new RedHxChassisParseException("");
                }

            }

        }
        else
        {
            throw new RedHxHttpResponseException(RedHxServiceRootIdEum.CHASSIS, chassisCollectionResponse.getStatusCode(), "Can not read Chassis Collection.");

        }

        return null;
    }
}
