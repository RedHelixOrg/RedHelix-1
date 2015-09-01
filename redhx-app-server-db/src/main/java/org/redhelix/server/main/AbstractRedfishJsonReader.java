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
import java.util.List;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntityRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientAnnotation;
import org.apache.olingo.client.api.domain.ClientCollectionValue;
import org.apache.olingo.client.api.domain.ClientComplexValue;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientProperty;
import org.apache.olingo.client.api.domain.ClientValue;
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
    private static final String ODATA_ID_FLAG = "odata.id";
    private final ClientEntity  entity;
    private final boolean       isResponseValid;

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
            throw new RedHxHttpResponseException(serviceRootId,
                    response.getStatusCode(),
                    "Can not read " + serviceRootId + " " + linkToResource);
        }
    }

    private AbstractRedfishJsonReader( )
    {
        this.entity          = null;
        this.isResponseValid = false;
    }

    protected String getAnnotation( String propName )
    {
        final ClientProperty clientProp = entity.getProperty(propName);
        String               retVal;

        if (clientProp != null)
        {
            retVal = null;

            ClientComplexValue cplx = clientProp.getComplexValue();

            for (int i = 0; i < cplx.getAnnotations().size(); ++i)
            {
                ClientAnnotation anno = cplx.getAnnotations().get(i);

                if (anno.getTerm().equals(ODATA_ID_FLAG))
                {
                    retVal = anno.getValue().toString();

                    break;
                }
            }
        }
        else
        {
            retVal = null;
        }

        return retVal;
    }

    protected String getComplexValue( String propName,
                                      String propName2 )
    {
        final ClientProperty clientProp = entity.getProperty(propName);
        String               retVal;

        if (clientProp != null)
        {
            ClientComplexValue cplx = clientProp.getComplexValue();

            /*
             *   I would expect this code that is commented out should work but does not. It only gets the first element(the propName2 im the complex type
             *   final ClientProperty subProp = cplx.get(propName2);
             *   retVal = subProp.getValue().toString();
             */
            retVal = (String) cplx.asJavaMap().get(propName2);
        }
        else
        {
            retVal = null;
        }

        return retVal;
    }

    protected String getLinkArray( String propName )
    {
        final ClientProperty clientProp = entity.getProperty("Links");
        String               retVal;

        if (clientProp != null)
        {
            retVal = null;

            ClientComplexValue cplx  = clientProp.getComplexValue();
            ClientProperty     prop2 = cplx.get(propName);

            if ((prop2 != null) && prop2.hasCollectionValue())
            {
                /*
                 * this may not be the best way to get the link but it works
                 */
                ClientCollectionValue<ClientValue> collection2 = prop2.getCollectionValue();

                for (ClientValue cv : collection2)
                {
                    List<ClientAnnotation> annotationList = cv.asComplex().getAnnotations();

                    if (annotationList.size() == 1)
                    {
                        ClientAnnotation anno = annotationList.get(0);

                        if (anno.getTerm().equals(ODATA_ID_FLAG))
                        {
                            retVal = anno.getValue().toString();

                            break;
                        }
                    }
                }
            }
        }
        else
        {
            retVal = null;
        }

        return retVal;
    }

    protected String getLinkSingle( String propName )
    {
        final ClientProperty clientProp = entity.getProperty("Links");
        String               retVal;

        if (clientProp != null)
        {
            retVal = null;

            ClientComplexValue cplx  = clientProp.getComplexValue();
            ClientProperty     prop2 = cplx.get(propName);

            if ((prop2 != null) &&!prop2.hasCollectionValue())
            {
                List<ClientAnnotation> annotationList = prop2.getComplexValue().asComplex().getAnnotations();

                if (annotationList.size() == 1)
                {
                    ClientAnnotation anno = annotationList.get(0);

                    if (anno.getTerm().equals(ODATA_ID_FLAG))
                    {
                        retVal = anno.getValue().toString();
                    }
                }
            }
        }
        else
        {
            retVal = null;
        }

        return retVal;
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
