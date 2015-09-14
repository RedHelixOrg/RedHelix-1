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
package org.redhelix.server.main.chassis;

import java.util.Arrays;
import java.util.Collections;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;
import org.redhelix.server.main.edm.RedHxAbstractEdmProvider;

/**
 *
 * Create the Entity Data Model for the Chassis service RedHelix JSON messages.
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public final class RedHxChassisServiceEdmProvider extends RedHxAbstractEdmProvider {

  // Entity Types Names and is singluar
  private static final String ET_CHASSIS_NAME = "chassis";

  /*
   * Entity Set Names and is plural. Because the plural of chassis is the exact same spelling a x
   * has been added to the plural form and super's constructor is used with two arguments. This no
   * normal and if a the Entity type name can end with a "s" the prefered super construtor with
   * single argument is perfered.
   */
  static final String ES_CHASSISX_NAME = "chassisx";

  public RedHxChassisServiceEdmProvider() {
    super(ET_CHASSIS_NAME, ES_CHASSISX_NAME);
  }

  /**
   *
   *
   * @return
   */
  @Override
  public CsdlEntityType getEntityType() {
    // create EntityType properties
    CsdlProperty id =
        new CsdlProperty().setName("ID").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
    CsdlProperty name = new CsdlProperty().setName("Name")
        .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
    CsdlProperty description = new CsdlProperty().setName("Description")
        .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

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
}
