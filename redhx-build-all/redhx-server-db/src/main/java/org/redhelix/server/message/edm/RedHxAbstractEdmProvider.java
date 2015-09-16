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
package org.redhelix.server.message.edm;

import java.util.ArrayList;
import java.util.List;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;
import org.apache.olingo.commons.api.edm.provider.CsdlEnumMember;
import org.apache.olingo.commons.api.edm.provider.CsdlEnumType;
import org.redhelix.server.main.RedHxServiceEdmProvider;

/**
 *
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public abstract class RedHxAbstractEdmProvider implements RedHxEdmProvider {

  /**
   * the entity set name. This is usually plural.
   */
  private final String entitySetName;
  private final List<CsdlEnumType> enumTypeList;
  private final FullQualifiedName fqdName;
  private final FullQualifiedName schemaFullyQualifiedNameSpace;

  /**
   * create provider where the entity set name has a "s" added to the singularName.
   *
   * @param singularName
   */
  protected RedHxAbstractEdmProvider(String singularName) {
    this.schemaFullyQualifiedNameSpace =
        new FullQualifiedName(RedHxServiceEdmProvider.SCHEMA_NAME_SPACE);
    this.fqdName = new FullQualifiedName(
        schemaFullyQualifiedNameSpace.getFullQualifiedNameAsString(), singularName);
    this.entitySetName = singularName + "s";
    this.enumTypeList = new ArrayList<>();
  }

  /**
   * create a provider where the entity set name has an arbritray value. This is rarely used and is
   * used in cases like "chassis" where the singluar and plural are spelled identically.
   *
   * @param singularName
   * @param entitySetName
   */
  protected RedHxAbstractEdmProvider(String singularName, String entitySetName) {
    this.schemaFullyQualifiedNameSpace =
        new FullQualifiedName(RedHxServiceEdmProvider.SCHEMA_NAME_SPACE);
    this.fqdName = new FullQualifiedName(
        schemaFullyQualifiedNameSpace.getFullQualifiedNameAsString(), singularName);
    this.entitySetName = entitySetName;
    this.enumTypeList = new ArrayList<>();
  }

  private RedHxAbstractEdmProvider() {
    this.schemaFullyQualifiedNameSpace = null;
    this.fqdName = null;
    this.entitySetName = null;
    this.enumTypeList = null;
  }

  @Override
  public CsdlEntitySet getEntitySet() {
    CsdlEntitySet entitySet = new CsdlEntitySet();

    entitySet.setName(entitySetName);
    entitySet.setType(getFqdName());

    return entitySet;
  }

  @Override
  public String getEntitySetName() {
    return entitySetName;
  }

  @Override
  public List<CsdlEnumType> getEnumTypeList() {
    return enumTypeList;
  }

  @Override
  public FullQualifiedName getFqdName() {
    return fqdName;
  }

  /**
   * create an enum with upto 0xffff values. This may be larger than a Java VM may accept. The Java
   * Language Specication is silent on what the largest number of enums can be.
   *
   * @param enumTypeName
   * @param enumValues
   * @return the full qualified name of the enumeration. This value would be used by the caller when
   *         it needs to define the enum
   */
  protected FullQualifiedName addEnumType(String enumTypeName, String... enumValues) {
    List<CsdlEnumMember> list = new ArrayList<>();
    EdmPrimitiveTypeKind primitiveTypeKind = EdmPrimitiveTypeKind.SByte;
    int i = 0;

    for (String currentName : enumValues) {
      CsdlEnumMember cem = new CsdlEnumMember().setName(currentName);

      cem.setValue(i + "");
      list.add(cem);
      ++i;

      if ((i >= 255) && (i < 0xffff)) {
        primitiveTypeKind = EdmPrimitiveTypeKind.Int16;
      } else if (i >= 0xffff) {
        primitiveTypeKind = EdmPrimitiveTypeKind.Int32;
      }
    }

    final CsdlEnumType enumProvider = new CsdlEnumType().setName(enumTypeName).setMembers(list) // .setFlags(true)
        .setUnderlyingType(primitiveTypeKind.getFullQualifiedName());

    enumTypeList.add(enumProvider);

    FullQualifiedName fqn =
        new FullQualifiedName(RedHxServiceEdmProvider.SCHEMA_NAME_SPACE, enumTypeName);

    return fqn;
  }

  protected FullQualifiedName getSchemaFullyQualifiedNameSpace() {
    return schemaFullyQualifiedNameSpace;
  }
}
