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
package org.redhelix.server.message.op.chassis;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.olingo.commons.api.data.ContextURL;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.format.ContentType;
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
import org.apache.olingo.server.api.uri.queryoption.SelectOption;

/**
 *
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public final class RedHxChassisCollectionProcessor
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

        // 1st: retrieve the requested EntitySet from the uriInfo (representation of the parsed URI)
        List<UriResource> resourcePaths = uriInfo.getUriResourceParts();

        // in our example, the first segment is the EntitySet
        UriResourceEntitySet uriResourceEntitySet = (UriResourceEntitySet) resourcePaths.get(0);
        EdmEntitySet edmEntitySet = uriResourceEntitySet.getEntitySet();

        // 2nd: fetch the data from backend for this requested EntitySetName and deliver as EntitySet
        EntityCollection entityCollection = getData(edmEntitySet);
        EntityCollection modifiedEntityCollection = new EntityCollection();
        List<Entity> modifiedEntityList = new ArrayList<Entity>();

        modifiedEntityList.addAll(entityCollection.getEntities());

//      // 3rd: Apply system query option
//      // The system query options have to be applied in a defined order
//      // 3.1.) $filter
//      modifiedEntityList = applyFilterQueryOption(modifiedEntityList, uriInfo.getFilterOption());
//
//      // 3.2.) $orderby
//      modifiedEntityList = applyOrderQueryOption(modifiedEntityList, uriInfo.getOrderByOption());
//
//      // 3.3.) $count
//      modifiedEntityList = applyCountQueryOption(modifiedEntityCollection, modifiedEntityList, uriInfo.getCountOption());
//
//      // 3.4.) $skip
//      modifiedEntityList = applySkipQueryOption(modifiedEntityList, uriInfo.getSkipOption());
//
//      // 3.5.) $top
//      modifiedEntityList = applyTopQueryOption(modifiedEntityList, uriInfo.getTopOption());
//
//      // 3.6.) Server driven paging (not part of this tutorial)
//      // 3.7.) $expand
//      modifiedEntityList = applyExpandQueryOption(modifiedEntityList, edmEntitySet, uriInfo.getExpandOption());
        // 3.8.) $select
        SelectOption selectOption = uriInfo.getSelectOption();

        // Set the (may) modified entityList to the new entity collection
        modifiedEntityCollection.getEntities().addAll(modifiedEntityList);

        // 4th: create a serializer based on the requested format (json)
        ODataSerializer serializer = odata.createSerializer(responseFormat);

        // we need the property names of the $select, in order to build the context URL
        EdmEntityType edmEntityType = edmEntitySet.getEntityType();
        String selectList = odata.createUriHelper().buildContextURLSelectList(edmEntityType,
                                                                              uriInfo.getExpandOption(),
                                                                              selectOption);
        ContextURL contextUrl = ContextURL.with().entitySet(edmEntitySet).selectList(selectList).build();

        // adding the selectOption to the serializerOpts will actually tell the lib to do the job
        final String id = request.getRawBaseUri() + "/" + edmEntitySet.getName();
        EntityCollectionSerializerOptions opts
                                          = EntityCollectionSerializerOptions.with().contextURL(contextUrl).count(uriInfo.getCountOption()).select(selectOption)
                .expand(uriInfo.getExpandOption()).id(id).build();

        // and serialize the content: transform from the EntitySet object to InputStream
        SerializerResult serializerResult = serializer.entityCollection(serviceMetadata,
                                                                        edmEntityType,
                                                                        modifiedEntityCollection,
                                                                        opts);
        InputStream serializedContent = serializerResult.getContent();

        // 5th: configure the response object: set the body, headers and status code
        response.setContent(serializedContent);
        response.setStatusCode(HttpStatusCode.OK.getStatusCode());
        response.setHeader(HttpHeader.CONTENT_TYPE,
                           responseFormat.toContentTypeString());
    }

    // private OData odata;
    // private ServiceMetadata serviceMetadata;
    //
    // @Override
    // public void init(OData odata, ServiceMetadata serviceMetadata) {
    // this.odata = odata;
    // this.serviceMetadata = serviceMetadata;
    // }
    //
    // @Override
    // public void readEntityCollection(ODataRequest request, ODataResponse response, UriInfo uriInfo,
    // ContentType responseFormat) throws ODataApplicationException, SerializerException {
    //
    //// The filter RedHxFilterHardware will work with request.getRawQueryPath()
    //// 1st we have retrieve the requested EntitySet from the uriInfo object (representation of the
    //// parsed service URI)
    // List<UriResource> resourcePaths = uriInfo.getUriResourceParts();
    // UriResourceEntitySet uriResourceEntitySet = (UriResourceEntitySet) resourcePaths.get(0); // in
    //
    //// our
    //// example,
    //// the
    //// first
    //// segment
    //// is
    //// the
    //// EntitySet
    // EdmEntitySet edmEntitySet = uriResourceEntitySet.getEntitySet();
    //
    //// 2nd: fetch the data from backend for this requested EntitySetName
    //// it has to be delivered as EntitySet object
    // EntityCollection entitySet = getData(edmEntitySet);
    //
    //// 3rd: create a serializer based on the requested format (json)
    // ODataFormat format = ODataFormat.fromContentType(responseFormat);
    // ODataSerializer serializer = odata.createSerializer(format);
    //
    //// 4th: Now serialize the content: transform from the EntitySet object to InputStream
    // EdmEntityType edmEntityType = edmEntitySet.getEntityType();
    // ContextURL contextUrl = ContextURL.with().entitySet(edmEntitySet).build();
    // EntityCollectionSerializerOptions opts =
    // EntityCollectionSerializerOptions.with().contextURL(contextUrl).build();
    // SerializerResult serializedContent =
    // serializer.entityCollection(serviceMetadata, edmEntityType, entitySet, opts);
    //
    //// Finally: configure the response object: set the body, headers and status code
    // response.setContent(serializedContent.getContent());
    // response.setStatusCode(HttpStatusCode.OK.getStatusCode());
    // response.setHeader(HttpHeader.CONTENT_TYPE, responseFormat.toContentTypeString());
    // }
    //
    private EntityCollection getData(EdmEntitySet edmEntitySet)
    {
        EntityCollection entityCollection = new EntityCollection();

        // check for which EdmEntitySet the data is requested
        if (RedHxChassisServiceEdmProvider.ET_CHASSIS_NAME.equals(edmEntitySet.getName()))
        {
            List<Entity> entityList = entityCollection.getEntities();

            // add some sample product entities
//            entityList.add(new Entity().addProperty(new Property(null,
//                    "ID",
//                    ValueType.PRIMITIVE,
//                    1)).addProperty(new Property(null,
//                    "Name",
//                    ValueType.PRIMITIVE,
//                    "Notebook Basic 15")).addProperty(new Property(null,
//                    "Description",
//                    ValueType.PRIMITIVE,
//                    "Notebook Basic, 1.7GHz - 15 XGA - 1024MB DDR2 SDRAM - 40GB")));
//            entityList.add(new Entity().addProperty(new Property(null,
//                    "ID",
//                    ValueType.PRIMITIVE,
//                    2)).addProperty(new Property(null,
//                    "Name",
//                    ValueType.PRIMITIVE,
//                    "1UMTS PDA")).addProperty(new Property(null,
//                    "Description",
//                    ValueType.PRIMITIVE,
//                    "Ultrafast 3G UMTS/HSDPA Pocket PC, supports GSM network")));
//            entityList.add(new Entity().addProperty(new Property(null,
//                    "ID",
//                    ValueType.PRIMITIVE,
//                    3)).addProperty(new Property(null,
//                    "Name",
//                    ValueType.PRIMITIVE,
//                    "Ergo Screen")).addProperty(new Property(null,
//                    "Description",
//                    ValueType.PRIMITIVE,
//                    "17 Optimum Resolution 1024 x 768 @ 85Hz, resolution 1280 x 960")));
        }

        return entityCollection;
    }
}
