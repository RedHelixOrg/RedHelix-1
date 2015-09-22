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
import java.util.Collections;
import java.util.List;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAbstractEdmProvider;
import org.apache.olingo.commons.api.edm.provider.CsdlAction;
import org.apache.olingo.commons.api.edm.provider.CsdlActionImport;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainer;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityContainerInfo;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlEnumType;
import org.apache.olingo.commons.api.edm.provider.CsdlSchema;
import org.apache.olingo.commons.api.ex.ODataException;
import org.redhelix.server.action.RedHxEdmActionProvider;
import org.redhelix.server.action.op.discover.RedHxDiscoverSystemEdmProvider;
import org.redhelix.server.message.edm.RedHxEdmEntityProvider;
import org.redhelix.server.message.op.chassis.RedHxChassisServiceEdmProvider;

/**
 *
 * Create the Entity Data Model for the RedHelix JSON messages. RedHelix entitys are added to the
 * data model by adding them to the static list in {@link #createEdmList() }. Each entry in the
 * liust implements the {@link org.redhelix.server.message.edm.RedHxEdmEntityProvider} class which
 * is a package public class that describes each of the major services provided by RedHelix.
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public final class RedHxServiceEdmProvider extends CsdlAbstractEdmProvider {

  public static final String RED_HELIX_SCHEMA_ORG_NAMESPACE = "RedHelixOData";

  /**
   * This tag is used to seperate the schema name space from other RedHelixOData schemas. For
   * example there may be a schema of RedHelixOData.admin. The name moon arbitray. This was written
   * on a night with a full moon.
   */
  private static final String SCHEMA_SUFFIX_NAME = "moon";
  public static final String SCHEMA_NAME_SPACE =
      RED_HELIX_SCHEMA_ORG_NAMESPACE + "." + SCHEMA_SUFFIX_NAME;

  // EDM Container
  private static final String CONTAINER_NAME = "DefaultContainer";
  private static final FullQualifiedName CONTAINER =
      new FullQualifiedName(SCHEMA_NAME_SPACE, CONTAINER_NAME);
  private static final List<RedHxEdmEntityProvider> EDM_ENTITY_PROVIDER_LIST = createEdmList();
  private static final List<RedHxEdmActionProvider> EDM_ACTION_PROVIDER_LIST = createActionList();
  protected static final List<CsdlAction> EMPTY_ACTION_LIST =
      Collections.unmodifiableList(new ArrayList<CsdlAction>());

  public RedHxServiceEdmProvider() {}

  @Override
  public List<CsdlAction> getActions(FullQualifiedName actionName) throws ODataException {
    List<CsdlAction> list = null;

    /**
     * todo change this lookup to a map.get();
     */
    for (RedHxEdmActionProvider edmProvider : EDM_ACTION_PROVIDER_LIST) {
      list = edmProvider.getActionList(actionName);

      if (!list.isEmpty()) {
        break;
      }
    }

    if (list == null) {
      list = EMPTY_ACTION_LIST;
    }

    return list;
  }

  @Override
  public CsdlEntityContainer getEntityContainer() {

    // create EntityContainer
    CsdlEntityContainer entityContainer = new CsdlEntityContainer();

    entityContainer.setName(CONTAINER_NAME);

    // create EntitySets
    List<CsdlEntitySet> entitySetList = new ArrayList<>();

    for (RedHxEdmEntityProvider edmProvider : EDM_ENTITY_PROVIDER_LIST) {
      entitySetList.add(edmProvider.getEntitySet());
    }

    /**
     * add entities.
     */
    entityContainer.setEntitySets(entitySetList);

    /**
     * add actions
     */
    List<CsdlActionImport> actionImportsList = new ArrayList<>();

    for (RedHxEdmActionProvider edmProvider : EDM_ACTION_PROVIDER_LIST) {
      actionImportsList.addAll(edmProvider.getActionImportList());
    }

    System.out.println("HFB5: adding " + actionImportsList.size() + " actions to container.");
    entityContainer.setActionImports(actionImportsList);

    return entityContainer;
  }

  @Override
  public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName) {
    CsdlEntityContainerInfo entityContainerInfo = null;

    if ((entityContainerName == null) || entityContainerName.equals(CONTAINER)) {
      entityContainerInfo = new CsdlEntityContainerInfo();
      entityContainerInfo.setContainerName(CONTAINER);

    }

    return entityContainerInfo;
  }

  @Override
  public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName) {
    CsdlEntitySet entitySet = null;

    if (entityContainer.equals(CONTAINER)) {
      for (RedHxEdmEntityProvider edmProvider : EDM_ENTITY_PROVIDER_LIST) {
        if (entitySetName.equals(edmProvider.getEntitySetName())) {
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
  public CsdlEntityType getEntityType(FullQualifiedName entityTypeName) {
    CsdlEntityType entityType = null;

    for (RedHxEdmEntityProvider edmProvider : EDM_ENTITY_PROVIDER_LIST) {
      if (entityTypeName.equals(edmProvider.getFqdName())) {
        entityType = edmProvider.getEntityType();

        break;
      }
    }

    return entityType;
  }

  @Override
  public List<CsdlSchema> getSchemas() {

    // create Schema
    CsdlSchema schema = new CsdlSchema();

    schema.setNamespace(SCHEMA_NAME_SPACE);

    /*
     * set all enum type.
     */
    List<CsdlEnumType> enumTypesList = new ArrayList<>();

    for (RedHxEdmEntityProvider edmProvider : EDM_ENTITY_PROVIDER_LIST) {
      enumTypesList.addAll(edmProvider.getEnumTypeList());
    }

    // // get an enums defind by the actions.
    // for (RedHxEdmActionProvider edmProvider : EDM_ACTION_PROVIDER_LIST)
    // {
    // enumTypesList.addAll(edmProvider.getEnumTypeList());
    // }
    // schema.setEnumTypes(enumTypesList);

    /*
     * set Entity types
     */
    List<CsdlEntityType> entityTypes = new ArrayList<>();

    for (RedHxEdmEntityProvider edmProvider : EDM_ENTITY_PROVIDER_LIST) {
      entityTypes.add(edmProvider.getEntityType());
    }

    schema.setEntityTypes(entityTypes);

    /*
     * set Actions
     */
    List<CsdlAction> actionList = new ArrayList<>();

    for (RedHxEdmActionProvider edmProvider : EDM_ACTION_PROVIDER_LIST) {
      actionList.addAll(edmProvider.getActionList());
    }

    System.out.println("HFB5: added " + actionList.size() + " actions.");
    schema.setActions(actionList);
    /**
     * set the entity container
     */
    schema.setEntityContainer(getEntityContainer());

    // finally
    List<CsdlSchema> schemas = new ArrayList<>();

    schemas.add(schema);

    return schemas;
  }

  private static List<RedHxEdmActionProvider> createActionList() {
    List<RedHxEdmActionProvider> list = new ArrayList<>();

    list.add(new RedHxDiscoverSystemEdmProvider());
    return list;
  }

  private static List<RedHxEdmEntityProvider> createEdmList() {
    List<RedHxEdmEntityProvider> list = new ArrayList<>();

    /*
     * aranged in alph order by RedHx name.
     */
    list.add(new RedHxChassisServiceEdmProvider());
    // list.add(new RedHxComputerSystemServiceEdmProvider());

    return list;
  }
}
