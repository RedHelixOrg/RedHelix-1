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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.olingo.commons.api.ODataException;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainerInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;

/**
 *
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public final class RedHxServiceEdmProvider
        extends CsdlAbstractEdmProvider
{

    // Service Namespace

    private static final String NAMESPACE = "OData.Demo";

    // EDM Container
    private static final String CONTAINER_NAME = "Container";
    private static final FullQualifiedName CONTAINER = new FullQualifiedName(NAMESPACE,
                                                                             CONTAINER_NAME);

    // Entity Types Names and is singluar
    private static final String ET_CHASSIS_NAME = "chassis";
    private static final String ET_COMPUTER_SYSTEM_NAME = "computerSystem";

    /*
     *  This singluar.
     */
    private static final FullQualifiedName ET_CHASSIS_FQN = new FullQualifiedName(NAMESPACE,
                                                                                  ET_CHASSIS_NAME);
    private static final FullQualifiedName ET_COMPUTER_SYSTEM_FQN = new FullQualifiedName(NAMESPACE,
                                                                                          ET_COMPUTER_SYSTEM_NAME);

    // Entity Set Names and is plural
    public static final String ES_CHASSISX_NAME = "chassisx";
    public static final String ES_COMPUTER_SYSTEMS_NAME = "computerSystems";

    @Override
    public CsdlEntityContainer getEntityContainer()
            throws ODataException
    {

        // create EntitySets
        List<CsdlEntitySet> entitySets = new ArrayList<>();

        entitySets.add(getEntitySet(CONTAINER, ES_CHASSISX_NAME));
        entitySets.add(getEntitySet(CONTAINER, ES_COMPUTER_SYSTEMS_NAME));

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

        // This method is invoked when displaying the Service Document at e.g. http://localhost:8080/RedHelix.svc/
        if ((entityContainerName == null) || entityContainerName.equals(CONTAINER))
        {
            CsdlEntityContainerInfo entityContainerInfo = new CsdlEntityContainerInfo();

            entityContainerInfo.setContainerName(CONTAINER);

            return entityContainerInfo;
        }

        return null;
    }

    @Override
    public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer,
                                      String entitySetName)
            throws ODataException
    {
        if (entityContainer.equals(CONTAINER))
        {
            if (entitySetName.equals(ES_CHASSISX_NAME))
            {
                CsdlEntitySet entitySet = new CsdlEntitySet();

                entitySet.setName(ES_CHASSISX_NAME);
                entitySet.setType(ET_CHASSIS_FQN);

                return entitySet;
            }
            else if (entitySetName.equals(ES_COMPUTER_SYSTEMS_NAME))
            {
                CsdlEntitySet entitySet = new CsdlEntitySet();

                entitySet.setName(ES_COMPUTER_SYSTEMS_NAME);
                entitySet.setType(ET_COMPUTER_SYSTEM_FQN);

                return entitySet;
            }
        }

        return null;
    }

    @Override
    public CsdlEntityType getEntityType(FullQualifiedName entityTypeName)
            throws ODataException
    {

        // this method is called for one of the EntityTypes that are configured in the Schema
        if (entityTypeName.equals(ET_CHASSIS_FQN))
        {
            // create EntityType properties
            CsdlProperty id = new CsdlProperty().setName("ID").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty name = new CsdlProperty().setName("Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
            CsdlProperty description
                         = new CsdlProperty().setName("Description").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

            // create CsdlPropertyRef for Key element
            CsdlPropertyRef propertyRef = new CsdlPropertyRef();

            propertyRef.setName("ID");

            // configure EntityType
            CsdlEntityType entityType = new CsdlEntityType();

            entityType.setName(ET_CHASSIS_NAME);
            entityType.setProperties(Arrays.asList(id, name, description));
            entityType.setKey(Collections.singletonList(propertyRef));

            return entityType;
        }
        else if (entityTypeName.equals(ET_COMPUTER_SYSTEM_FQN))
        {
            // create EntityType properties
            CsdlProperty id = new CsdlProperty().setName("CID").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty name = new CsdlProperty().setName("CName").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
            CsdlProperty description
                         = new CsdlProperty().setName("CDescription").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

            // create CsdlPropertyRef for Key element
            CsdlPropertyRef propertyRef = new CsdlPropertyRef();

            propertyRef.setName("ID");

            // configure EntityType
            CsdlEntityType entityType = new CsdlEntityType();

            entityType.setName(ET_COMPUTER_SYSTEM_NAME);
            entityType.setProperties(Arrays.asList(id, name, description));
            entityType.setKey(Collections.singletonList(propertyRef));

            return entityType;
        }

        return null;
    }

    @Override
    public List<CsdlSchema> getSchemas()
            throws ODataException
    {

        // create Schema
        CsdlSchema schema = new CsdlSchema();

        schema.setNamespace(NAMESPACE);

        // add EntityTypes
        List<CsdlEntityType> entityTypes = new ArrayList<>();

        entityTypes.add(getEntityType(ET_CHASSIS_FQN));
        entityTypes.add(getEntityType(ET_COMPUTER_SYSTEM_FQN));
        schema.setEntityTypes(entityTypes);

        // add EntityContainer
        schema.setEntityContainer(getEntityContainer());

        // finally
        List<CsdlSchema> schemas = new ArrayList<>();

        schemas.add(schema);

        return schemas;
    }
}
