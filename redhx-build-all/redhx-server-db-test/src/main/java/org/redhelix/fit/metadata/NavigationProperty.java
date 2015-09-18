/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.redhelix.fit.metadata;

public class NavigationProperty extends AbstractMetadataElement {

  private final String name;

  // -----------------------
  // just for v3
  // -----------------------
  private String releationship;

  private String toRole;
  // -----------------------

  private String type;

  private String target;

  private boolean entitySet;

  public NavigationProperty(final String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getReleationship() {
    return releationship;
  }

  public void setReleationship(final String releationship) {
    this.releationship = releationship;
  }

  public String getToRole() {
    return toRole;
  }

  public void setToRole(final String toRole) {
    this.toRole = toRole;
  }

  public String getType() {
    return type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(final String target) {
    this.target = target;
  }

  public boolean isEntitySet() {
    return entitySet;
  }

  public void setFeed(final boolean entitySet) {
    this.entitySet = entitySet;
  }
}
