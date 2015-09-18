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
package org.redhelix.server.main;

import java.util.ArrayList;
import java.util.List;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainerInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlEnumType;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;
import org.redhelix.server.message.edm.RedHxEdmProvider;
import org.redhelix.server.message.op.chassis.RedHxChassisServiceEdmProvider;
import org.redhelix.server.message.op.computer.system.RedHxComputerSystemServiceEdmProvider;
import org.redhelix.server.message.op.discover.RedHxDiscoverSystemEdmProvider;

/**
 *
 * Create the Entity Data Model for the RedHelix JSON messages. RedHelix entitys are added to the data model by adding them to the static
 * list in {@link #createEdmList() }. Each entry in the liust implements the {@link org.redhelix.server.message.edm.RedHxEdmProvider} class
 * which is a package public class that describes each of the major services provided by RedHelix.
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public final class RedHxServiceEdmProvider extends CsdlAbstractEdmProvider
{

    private static final String RED_HELIX_SCHEMA_ORG_NAMESPACE = "RedHelix.OData";

    /**
     * This tag is used to seperate the schema name space from other RedHelix.OData schemas. For example there may be a schema of
     * RedHelix.OData.admin. The name moon arbitray. This was written on a night with a full moon.
     */
    private static final String SCHEMA_SUFFIX_NAME = "moon";
    public static final String SCHEMA_NAME_SPACE
                               = RED_HELIX_SCHEMA_ORG_NAMESPACE + "." + SCHEMA_SUFFIX_NAME;

    // EDM Container
    private static final String CONTAINER_NAME = "Container";
    private static final FullQualifiedName CONTAINER
                                           = new FullQualifiedName(SCHEMA_NAME_SPACE, CONTAINER_NAME);
    private static final List<RedHxEdmProvider> EDM_PROVIDER_LIST = createEdmList();

    public RedHxServiceEdmProvider()
    {
    }

    @Override
    public CsdlEntityContainer getEntityContainer()
    {

        // create EntitySets
        List<CsdlEntitySet> entitySets = new ArrayList<>();

        for (RedHxEdmProvider edmProvider : EDM_PROVIDER_LIST)
        {
            entitySets.add(edmProvider.getEntitySet());
        }

        // create EntityContainer
        CsdlEntityContainer entityContainer = new CsdlEntityContainer();

        entityContainer.setName(CONTAINER_NAME);
        entityContainer.setEntitySets(entitySets);

        System.out.println("HFB5: got entity container." + entityContainer);
        return entityContainer;
    }

    @Override
    public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName)
    {
        CsdlEntityContainerInfo entityContainerInfo = null;

        System.out.println("HFB5: looking for  entityContainerName=" + entityContainerName);
        if ((entityContainerName == null) || entityContainerName.equals(CONTAINER))
        {
            entityContainerInfo = new CsdlEntityContainerInfo();
            entityContainerInfo.setContainerName(CONTAINER);
        }

        System.out.println("HFB5: got entity container info " + entityContainerInfo);
        return entityContainerInfo;
    }

    @Override
    public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName)
    {
        CsdlEntitySet entitySet = null;

        if (entityContainer.equals(CONTAINER))
        {
            for (RedHxEdmProvider edmProvider : EDM_PROVIDER_LIST)
            {
                if (entitySetName.equals(edmProvider.getEntitySetName()))
                {
                    entitySet = new CsdlEntitySet();
                    entitySet.setName(edmProvider.getEntitySetName());
                    entitySet.setType(edmProvider.getFqdName());

                    break;
                }
            }
        }

        return entitySet;
    }

    @Override
    public CsdlEntityType getEntityType(FullQualifiedName entityTypeName)
    {
        CsdlEntityType entityType = null;

        for (RedHxEdmProvider edmProvider : EDM_PROVIDER_LIST)
        {
            if (entityTypeName.equals(edmProvider.getFqdName()))
            {
                entityType = edmProvider.getEntityType();

                break;
            }
        }

        return entityType;
    }

    @Override
    public List<CsdlSchema> getSchemas()
    {

        // create Schema
        CsdlSchema schema = new CsdlSchema();

        schema.setNamespace(SCHEMA_NAME_SPACE);

        /*
     * set all enum type.
         */
        List<CsdlEnumType> enumTypesList = new ArrayList<>();

        for (RedHxEdmProvider edmProvider : EDM_PROVIDER_LIST)
        {
            enumTypesList.addAll(edmProvider.getEnumTypeList());
        }

        schema.setEnumTypes(enumTypesList);

        /*
     *
         */
        List<CsdlEntityType> entityTypes = new ArrayList<>();

        for (RedHxEdmProvider edmProvider : EDM_PROVIDER_LIST)
        {
            entityTypes.add(edmProvider.getEntityType());
        }

        schema.setEntityTypes(entityTypes);

        // add EntityContainer
        schema.setEntityContainer(getEntityContainer());

        // finally
        List<CsdlSchema> schemas = new ArrayList<>();

        schemas.add(schema);

        return schemas;
    }

    private static List<RedHxEdmProvider> createEdmList()
    {
        List<RedHxEdmProvider> list = new ArrayList<>();

        /*
     * aranged in alph order by RedHx name.
         */
        list.add(new RedHxChassisServiceEdmProvider());
        list.add(new RedHxComputerSystemServiceEdmProvider());
        list.add(new RedHxDiscoverSystemEdmProvider());

        return list;
    }
}
