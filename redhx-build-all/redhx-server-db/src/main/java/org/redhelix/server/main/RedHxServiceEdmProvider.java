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

import java.util.ArrayList;
import java.util.List;
import org.apache.olingo.commons.api.ODataException;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainerInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;
import org.redhelix.server.main.chassis.RedHxChassisServiceEdmProvider;
import org.redhelix.server.main.chassis.computer.system.RedHxComputerSystemServiceEdmProvider;
import org.redhelix.server.main.edm.RedHxEdmProvider;

/**
 *
 * Create the Entity Data Model for the RedHelix JSON messages. RedHelix entitys are added to the data model by adding them to the static
 * list in {@link #createEdmList() }. Each entry in the liust implements the {@link  org.redhelix.server.main.edm.RedHxEdmProvider} class
 * which is a package public class that describes each of the major services provided by RedHelix.
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public final class RedHxServiceEdmProvider
        extends CsdlAbstractEdmProvider
{

    // EDM Container
    private static final String CONTAINER_NAME = "Container";
    private static final FullQualifiedName CONTAINER = new FullQualifiedName(RedHxEdmProvider.NAMESPACE,
                                                                             CONTAINER_NAME);
    private static final List<RedHxEdmProvider> EDM_PROVIDER_LIST = createEdmList();

    public RedHxServiceEdmProvider()
    {
    }

    @Override
    public CsdlEntityContainer getEntityContainer()
            throws ODataException
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

        return entityContainer;
    }

    @Override
    public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName)
            throws ODataException
    {
        CsdlEntityContainerInfo entityContainerInfo = null;
        // This method is invoked when displaying the Service Document at e.g. http://localhost:8080/RedHelix.svc/
        if ((entityContainerName == null) || entityContainerName.equals(CONTAINER))
        {
            entityContainerInfo = new CsdlEntityContainerInfo();

            entityContainerInfo.setContainerName(CONTAINER);

        }

        return entityContainerInfo;
    }

    @Override
    public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer,
                                      String entitySetName)
            throws ODataException
    {
        CsdlEntitySet entitySet = null;

        if (entityContainer.equals(CONTAINER))
        {

            System.out.println("HFB5: looking for " + entityContainer + ", entitySetName=" + entitySetName);
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
            throws ODataException
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
            throws ODataException
    {

        // create Schema
        CsdlSchema schema = new CsdlSchema();

        schema.setNamespace(RedHxEdmProvider.NAMESPACE);

        // add EntityTypes
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

        list.add(new RedHxChassisServiceEdmProvider());
        list.add(new RedHxComputerSystemServiceEdmProvider());

        return list;
    }
}
