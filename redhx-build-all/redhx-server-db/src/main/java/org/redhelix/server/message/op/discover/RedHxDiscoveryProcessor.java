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
package org.redhelix.server.message.op.discover;

import java.util.List;
import org.apache.olingo.commons.api.data.ContextURL;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.format.ODataFormat;
import org.apache.olingo.commons.api.http.HttpHeader;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.processor.EntityCollectionProcessor;
import org.apache.olingo.server.api.serializer.EntityCollectionSerializerOptions;
import org.apache.olingo.server.api.serializer.ODataSerializer;
import org.apache.olingo.server.api.serializer.SerializerException;
import org.apache.olingo.server.api.serializer.SerializerResult;
import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriResource;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;

/**
 *
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public final class RedHxDiscoveryProcessor
        implements EntityCollectionProcessor
{

    private OData odata;
    private ServiceMetadata serviceMetadata;

    @Override
    public void init(OData odata,
                     ServiceMetadata serviceMetadata)
    {
        this.odata = odata;
        this.serviceMetadata = serviceMetadata;
    }

    @Override
    public void readEntityCollection(ODataRequest request,
                                     ODataResponse response,
                                     UriInfo uriInfo,
                                     ContentType responseFormat)
            throws ODataApplicationException,
                   SerializerException
    {

        List<UriResource> resourcePaths = uriInfo.getUriResourceParts();
        UriResourceEntitySet uriResourceEntitySet = (UriResourceEntitySet) resourcePaths.get(0);    // in

        EdmEntitySet edmEntitySet = uriResourceEntitySet.getEntitySet();
        System.out.println("HFB5: received discovery request msg =" + request);
        // 2nd: fetch the data from backend for this requested EntitySetName
        // it has to be delivered as EntitySet object
        EntityCollection entitySet = getData(edmEntitySet);

        // 3rd: create a serializer based on the requested format (json)
        ODataFormat format = ODataFormat.fromContentType(responseFormat);
        ODataSerializer serializer = odata.createSerializer(format);

        // 4th: Now serialize the content: transform from the EntitySet object to InputStream
        EdmEntityType edmEntityType = edmEntitySet.getEntityType();
        ContextURL contextUrl = ContextURL.with().entitySet(edmEntitySet).build();
        EntityCollectionSerializerOptions opts = EntityCollectionSerializerOptions.with().contextURL(contextUrl).build();
        SerializerResult serializedContent = serializer.entityCollection(serviceMetadata,
                                                                         edmEntityType,
                                                                         entitySet,
                                                                         opts);

        // Finally: configure the response object: set the body, headers and status code
        response.setContent(serializedContent.getContent());
        response.setStatusCode(HttpStatusCode.OK.getStatusCode());
        response.setHeader(HttpHeader.CONTENT_TYPE,
                           responseFormat.toContentTypeString());
    }

    private EntityCollection getData(EdmEntitySet edmEntitySet)
    {
        EntityCollection entityCollection = new EntityCollection();

        // check for which EdmEntitySet the data is requested
        if (RedHxDiscoverSystemEdmProvider.ET_DISCOVER_SYSTEM_NAME.equals(edmEntitySet.getName()))
        {

            System.out.println("HFB5: received discovery msg =");
        }

        return entityCollection;
    }
}
