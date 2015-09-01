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

import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntityRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientProperty;
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
abstract class AbstractRedfishJsonReader
{
    private final ClientEntity entity;
    private final boolean      isResponseValid;
            

    protected AbstractRedfishJsonReader( final RedHxServerConnectionContext ctx,
            final RedHxServiceRootIdEum                                     serviceRootId,
            final String                                                    linkToResource )
            throws URISyntaxException,
                   RedHxHttpResponseException
    {
        final ODataEntityRequest<ClientEntity>    req      = ctx.getEntityRequest(linkToResource);
        final ODataRetrieveResponse<ClientEntity> response = req.execute();

        this.isResponseValid = response.getStatusCode() == HttpURLConnection.HTTP_OK;

        if (isResponseValid)
        {
            entity = response.getBody();
        }
        else
        {
            throw new RedHxHttpResponseException( serviceRootId,
                    response.getStatusCode(),
                    "Can not read "+ serviceRootId+" "+linkToResource);
        }
    }

    private AbstractRedfishJsonReader( )
    {
        this.entity          = null;
        this.isResponseValid = false;
    }

    protected String getOptionalProperty( String propName )
    {
        final ClientProperty clientProp = entity.getProperty(propName);
        final String         retVal;

        if (clientProp != null)
        {
            retVal = clientProp.getValue().toString();
        }
        else
        {
            retVal = null;
        }

        return retVal;
    }

   

    protected boolean isHttpResponseValid( )
    {
        return isResponseValid;
    }
}
