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
package org.redhelix.server.action.op.discover;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.olingo.commons.api.data.ContextURL;
import org.apache.olingo.commons.api.data.Parameter;
import org.apache.olingo.commons.api.edm.Edm;
import org.apache.olingo.commons.api.edm.EdmAction;
import org.apache.olingo.commons.api.edm.EdmBindingTarget;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpHeader;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.ODataLibraryException;
import org.apache.olingo.server.api.ODataRequest;
import org.apache.olingo.server.api.ODataResponse;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.deserializer.DeserializerException;
import org.apache.olingo.server.api.prefer.Preferences.Return;
import org.apache.olingo.server.api.prefer.PreferencesApplied;
import org.apache.olingo.server.api.processor.ActionEntityProcessor;
import org.apache.olingo.server.api.serializer.EntitySerializerOptions;
import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriInfoResource;
import org.apache.olingo.server.api.uri.UriResource;
import org.apache.olingo.server.api.uri.UriResourceAction;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;
import org.apache.olingo.server.api.uri.UriResourceFunction;
import org.apache.olingo.server.api.uri.UriResourceNavigation;
import static org.apache.olingo.server.core.serializer.utils.ContentTypeHelper.isODataMetadataNone;
import org.redhelix.server.data.DataProvider;
import org.redhelix.server.data.EntityActionResult;

/**
 *
 * process an OData Action message requesting the addition of an IP address or subnet to the database of Redfish systems managed by
 * RedHelix.
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public final class RedHxDiscoveryProcessor
        implements ActionEntityProcessor    // ,

//ActionComplexProcessor
{

    private final DataProvider dataProvider;

    // if (RedHxDiscoverSystemEdmProvider.ET_DISCOVER_SYSTEM_NAME.equals(edmEntitySet.getName()))
    private OData odata;
    private ServiceMetadata serviceMetadata;

    public RedHxDiscoveryProcessor(final OData odata,
                                   final Edm edm)
    {
        this.dataProvider = new DataProvider(odata,
                                             edm);
    }

    private RedHxDiscoveryProcessor()
    {
        this.dataProvider = null;
    }

    @Override
    public void init(final OData odata,
                     final ServiceMetadata serviceMetadata)
    {
        this.odata = odata;
        this.serviceMetadata = serviceMetadata;
    }

    @Override
    public void processActionEntity(final ODataRequest request,
                                    final ODataResponse response,
                                    final UriInfo uriInfo,
                                    final ContentType requestFormat,
                                    final ContentType responseFormat)
            throws ODataApplicationException,
                   ODataLibraryException
    {

        // from Olingo source of TechnicalProcessor.blockBoundActions(uriInfo);
        final EdmAction action
                        = ((UriResourceAction) uriInfo.asUriInfoResource().getUriResourceParts().get(0)).getAction();
        final EdmEntitySet edmEntitySet = getEdmEntitySet(uriInfo.asUriInfoResource());
        final EdmEntityType type = (EdmEntityType) action.getReturnType().getType();
        final Map<String, Parameter> parameters = readParameters(action,
                                                                 request.getBody(),
                                                                 requestFormat);
        final EntityActionResult entityResult = dataProvider.processActionEntity(action.getName(),
                                                                                 parameters);

        if ((entityResult == null) || (entityResult.getEntity() == null))
        {
            if (action.getReturnType().isNullable())
            {
                response.setStatusCode(HttpStatusCode.NO_CONTENT.getStatusCode());
            }
            else
            {
                // Not nullable return type so we have to give back a 500
                throw new ODataApplicationException("The action could not be executed.",
                                                    HttpStatusCode.INTERNAL_SERVER_ERROR.getStatusCode(),
                                                    Locale.ROOT);
            }
        }
        else
        {
            final Return returnPreference = odata.createPreferences(request.getHeaders(HttpHeader.PREFER)).getReturn();

            if ((returnPreference == null) || (returnPreference == Return.REPRESENTATION))
            {
                response.setContent(odata.createSerializer(responseFormat).entity(serviceMetadata, type, entityResult.getEntity(),
                                                                                  EntitySerializerOptions.with().contextURL(isODataMetadataNone(responseFormat)
                                                                                          ? null
                                                                                          : getContextUrl(edmEntitySet, type, true)).build()).getContent());
                response.setHeader(HttpHeader.CONTENT_TYPE,
                                   responseFormat.toContentTypeString());
                response.setStatusCode((entityResult.isCreated()
                        ? HttpStatusCode.CREATED
                        : HttpStatusCode.OK).getStatusCode());
            }
            else
            {
                response.setStatusCode(HttpStatusCode.NO_CONTENT.getStatusCode());
            }

            if (returnPreference != null)
            {
                response.setHeader(HttpHeader.PREFERENCE_APPLIED,
                                   PreferencesApplied.with().returnRepresentation(returnPreference).build().toValueString());
            }

            if (entityResult.isCreated())
            {
                final String location = request.getRawBaseUri() + '/' + odata.createUriHelper().buildCanonicalURL(edmEntitySet,
                                                                                                                  entityResult.getEntity());

                response.setHeader(HttpHeader.LOCATION,
                                   location);

                if (returnPreference == Return.MINIMAL)
                {
                    response.setHeader(HttpHeader.ODATA_ENTITY_ID,
                                       location);
                }
            }

            if (entityResult.getEntity().getETag() != null)
            {
                response.setHeader(HttpHeader.ETAG,
                                   entityResult.getEntity().getETag());
            }
        }
    }

    private void blockTypeFilters(final UriResource uriResource)
            throws ODataApplicationException
    {
        if (((uriResource instanceof UriResourceEntitySet)
                && (((UriResourceEntitySet) uriResource).getTypeFilterOnCollection() != null
                || ((UriResourceEntitySet) uriResource).getTypeFilterOnEntry()
                != null)) || ((uriResource instanceof UriResourceFunction)
                && (((UriResourceFunction) uriResource).getTypeFilterOnCollection() != null
                || ((UriResourceFunction) uriResource).getTypeFilterOnEntry()
                != null)) || ((uriResource instanceof UriResourceNavigation)
                && (((UriResourceNavigation) uriResource).getTypeFilterOnCollection() != null
                || ((UriResourceNavigation) uriResource).getTypeFilterOnEntry() != null)))
        {
            throw new ODataApplicationException("Type filters are not supported.",
                                                HttpStatusCode.NOT_IMPLEMENTED.getStatusCode(),
                                                Locale.ROOT);
        }
    }

    private static void checkRequestFormat(final ContentType requestFormat)
            throws ODataApplicationException
    {
        if (requestFormat == null)
        {
            throw new ODataApplicationException("The content type has not been set in the request.",
                                                HttpStatusCode.BAD_REQUEST.getStatusCode(),
                                                Locale.ROOT);
        }
    }

    private static ContextURL getContextUrl(final EdmEntitySet entitySet,
                                            final EdmEntityType entityType,
                                            final boolean isSingleEntity)
            throws ODataLibraryException
    {
        ContextURL.Builder builder = ContextURL.with();

        builder = (entitySet == null)
                ? isSingleEntity
                        ? builder.type(entityType)
                        : builder.asCollection().type(entityType)
                : builder.entitySet(entitySet);
        builder = builder.suffix((isSingleEntity && (entitySet != null))
                ? ContextURL.Suffix.ENTITY
                : null);

        return builder.build();
    }

    // @Override
    // public void processActionEntity( ODataRequest request,
    // ODataResponse response,
    // UriInfo uriInfo,
    // ContentType requestFormat,
    // ContentType responseFormat )
    // throws ODataApplicationException,
    // ODataLibraryException
    // {
    //
    //// 1st retrieve the requested EntitySet from the uriInfo (representation of the parsed URI)
    // final List<UriResource> resourcePaths = uriInfo.getUriResourceParts();
    //
    //// in our example, the first segment is the EntitySet
    // final UriResourceEntitySet uriResourceEntitySet = (UriResourceEntitySet) resourcePaths.get(0);
    // final EdmEntitySet edmEntitySet = uriResourceEntitySet.getEntitySet();
    //
    // System.out.println("HFB5: discover request=" + request.getRawBaseUri());
    // System.out.println("HFB5: uriInfo resource parts=" + uriInfo.getUriResourceParts());
    // System.out.println("HFB5: uriInfo uriInfo=" + uriInfo);
    // System.out.println("HFB5: uriInfo uriInfo filter=" + uriInfo.getFilterOption());
    // System.out.println("HFB5: uriInfo uriInfo expand=" + uriInfo.getExpandOption());
    // System.out.println("HFB5: uriInfo uriInfo expand=" + uriInfo.getSearchOption());
    // System.out.println("HFB5: uriInfo uriInfo expand=" + uriInfo.getFragment());
    //
    //// 2nd: fetch the data from backend for this requested EntitySetName
    //// it has to be delivered as EntitySet object
    // final EntityCollection entitySet = null; // storage.readEntitySetData(edmEntitySet);
    //
    //// 3rd: create a serializer based on the requested format (json)
    // final ODataSerializer serializer = odata.createSerializer(responseFormat);
    //
    //// and serialize the content: transform from the EntitySet object to InputStream
    // final EdmEntityType edmEntityType = edmEntitySet.getEntityType();
    // final ContextURL contextUrl = ContextURL.with().entitySet(edmEntitySet).build();
    // final String id = request.getRawBaseUri() + "/" + edmEntitySet.getName();
    // final EntityCollectionSerializerOptions opts =
    // EntityCollectionSerializerOptions.with().id(id).contextURL(contextUrl).build();
    // final SerializerResult serializedContent = serializer.entityCollection(serviceMetadata,
    // edmEntityType,
    // entitySet,
    // opts);
    //
    //// Finally: configure the response object: set the body, headers and status code
    // response.setContent(serializedContent.getContent());
    // response.setStatusCode(HttpStatusCode.OK.getStatusCode());
    // response.setHeader(HttpHeader.CONTENT_TYPE,
    // responseFormat.toContentTypeString());
    // }
    private EdmEntitySet getEdmEntitySet(final UriInfoResource uriInfo)
            throws ODataApplicationException
    {
        EdmEntitySet entitySet = null;
        final List<UriResource> resourcePaths = uriInfo.getUriResourceParts();

        // First must be an entity, an entity collection, a function import, or an action import.
        blockTypeFilters(resourcePaths.get(0));

        if (resourcePaths.get(0) instanceof UriResourceEntitySet)
        {
            entitySet = ((UriResourceEntitySet) resourcePaths.get(0)).getEntitySet();
        }
        else if (resourcePaths.get(0) instanceof UriResourceFunction)
        {
            entitySet = ((UriResourceFunction) resourcePaths.get(0)).getFunctionImport().getReturnedEntitySet();
        }
        else if (resourcePaths.get(0) instanceof UriResourceAction)
        {
            entitySet = ((UriResourceAction) resourcePaths.get(0)).getActionImport().getReturnedEntitySet();
        }
        else
        {
            throw new ODataApplicationException("Invalid resource type.",
                                                HttpStatusCode.NOT_IMPLEMENTED.getStatusCode(),
                                                Locale.ROOT);
        }

        int navigationCount = 0;

        while ((entitySet != null) && (++navigationCount < resourcePaths.size())
                && (resourcePaths.get(navigationCount) instanceof UriResourceNavigation))
        {
            final UriResourceNavigation uriResourceNavigation = (UriResourceNavigation) resourcePaths.get(navigationCount);

            blockTypeFilters(uriResourceNavigation);

            if (uriResourceNavigation.getProperty().containsTarget())
            {
                throw new ODataApplicationException("Containment navigation is not supported.",
                                                    HttpStatusCode.NOT_IMPLEMENTED.getStatusCode(),
                                                    Locale.ROOT);
            }

            final EdmBindingTarget target = entitySet.getRelatedBindingTarget(uriResourceNavigation.getProperty().getName());

            if (target instanceof EdmEntitySet)
            {
                entitySet = (EdmEntitySet) target;
            }
            else
            {
                throw new ODataApplicationException("Singletons are not supported.",
                                                    HttpStatusCode.NOT_IMPLEMENTED.getStatusCode(),
                                                    Locale.ROOT);
            }
        }

        return entitySet;
    }

    private Map<String, Parameter> readParameters(final EdmAction action,
                                                  final InputStream body,
                                                  final ContentType requestFormat)
            throws ODataApplicationException,
                   DeserializerException
    {
        if (action.getParameterNames().size() - (action.isBound()
                ? 1
                : 0) > 0)
        {
            checkRequestFormat(requestFormat);

            return odata.createDeserializer(requestFormat).actionParameters(body,
                                                                            action).getActionParameters();
        }

        return Collections.<String, Parameter>emptyMap();
    }
}
