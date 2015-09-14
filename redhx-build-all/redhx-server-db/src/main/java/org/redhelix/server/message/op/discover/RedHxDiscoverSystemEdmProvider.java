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
package org.redhelix.server.message.op.discover;

import java.util.Arrays;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.provider.CsdlEntityType;
import org.apache.olingo.commons.api.edm.provider.CsdlProperty;
import org.redhelix.server.message.edm.RedHxAbstractEdmProvider;

/**
 *
 * Create the Entity Data Model for the RedHelix Discover System service JSON messages. The Discover
 * System message configures how Redfish servers are discovered.
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public final class RedHxDiscoverSystemEdmProvider extends RedHxAbstractEdmProvider {

  /**
   * Entity Types Names and is singular.
   */
  private static final String ET_DISCOVER_SYSTEM_NAME = "discoverSystem";

  public RedHxDiscoverSystemEdmProvider() {
    super(ET_DISCOVER_SYSTEM_NAME);
  }

  @Override
  public CsdlEntityType getEntityType() {

    // create EntityType properties
    CsdlProperty hostName = new CsdlProperty().setName("HostName")
        .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
    CsdlProperty ipV4 = new CsdlProperty().setName("IPv4")
        .setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
    CsdlProperty ipV6Low = new CsdlProperty().setName("IPv6Low")
        .setType(EdmPrimitiveTypeKind.Int64.getFullQualifiedName());
    CsdlProperty ipV6High = new CsdlProperty().setName("IPv6High")
        .setType(EdmPrimitiveTypeKind.Int64.getFullQualifiedName());

    //
    CsdlProperty tcpPortNumber = new CsdlProperty().setName("TcpPortNumber")
        .setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
    CsdlProperty tcpProtocol = new CsdlProperty().setName("TcpProtocol")
        .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
    CsdlProperty httpUserName = new CsdlProperty().setName("HttpUserName")
        .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
    CsdlProperty httpPassword = new CsdlProperty().setName("HttpPassword")
        .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

    // configure EntityType
    CsdlEntityType entityType = new CsdlEntityType();

    entityType.setName(ET_DISCOVER_SYSTEM_NAME);
    entityType.setProperties(Arrays.asList(hostName, ipV4, ipV6Low, ipV6High, tcpPortNumber,
        tcpProtocol, httpUserName, httpPassword));

    // entityType.setKey(Collections.singletonList(propertyRef));
    return entityType;
  }
}
