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
package org.redhelix.server.main.computer.system;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.apache.olingo.commons.api.edm.provider.CsdlPropertyRef;

import org.redhelix.server.main.edm.RedHxAbstractEdmProvider;

import java.util.Arrays;
import java.util.Collections;

/**
 *
 * Create the Entity Data Model for the RedHelix Computer System service JSON messages.
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public final class RedHxComputerSystemServiceEdmProvider extends RedHxAbstractEdmProvider {

  /**
   * Entity Types Names and is singular.
   */
  private static final String ET_COMPUTER_SYSTEM_NAME = "computerSystem";

  public RedHxComputerSystemServiceEdmProvider() {
    super(ET_COMPUTER_SYSTEM_NAME);
  }

  @Override
  public CsdlEntityType getEntityType() {

    // create EntityType properties
    CsdlProperty id = new CsdlProperty().setName("CID")
        .setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
    CsdlProperty name = new CsdlProperty().setName("CName")
        .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
    CsdlProperty description = new CsdlProperty().setName("CDescription")
        .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

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
}
