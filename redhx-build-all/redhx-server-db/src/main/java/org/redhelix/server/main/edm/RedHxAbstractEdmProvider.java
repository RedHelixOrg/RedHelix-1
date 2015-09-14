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
package org.redhelix.server.main.edm;

import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlEntitySet;

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
  private final FullQualifiedName fqdName;

  /**
   * create provider where the entity set name has a "s" added to the singularName.
   *
   * @param singularName
   */
  protected RedHxAbstractEdmProvider(String singularName) {
    this.fqdName = new FullQualifiedName(NAMESPACE, singularName);
    this.entitySetName = singularName + "s";
  }

  /**
   * create a provider where the entity set name has an arbritray value. This is rarely used and is
   * used in cases like "chassis" where the singluar and plural are spelled identically.
   *
   * @param singularName
   * @param entitySetName
   */
  protected RedHxAbstractEdmProvider(String singularName, String entitySetName) {
    this.fqdName = new FullQualifiedName(NAMESPACE, singularName);
    this.entitySetName = entitySetName;
  }

  private RedHxAbstractEdmProvider() {
    this.fqdName = null;
    this.entitySetName = null;
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
  public FullQualifiedName getFqdName() {
    return fqdName;
  }
}
