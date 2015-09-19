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
package org.redhelix.server.action.op.discover;

import java.util.ArrayList;
import java.util.List;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.CsdlAction;
import org.apache.olingo.commons.api.edm.provider.CsdlParameter;
import org.redhelix.server.action.RedHxAbstractEdmActionProvider;
import org.redhelix.server.main.RedHxServiceEdmProvider;

/**
 *
 * Create the Entity Data Model for the RedHelix Discover System service JSON messages. The Discover System message configures how Redfish
 * servers are discovered.
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public final class RedHxDiscoverSystemEdmProvider
        extends RedHxAbstractEdmActionProvider
{

    /**
     * Entity Types Names and is singular.
     */
    static final String ET_DISCOVER_SYSTEM_NAME = "discoverSystem";
    public static final FullQualifiedName nameETTwoBase = new FullQualifiedName(RedHxServiceEdmProvider.SCHEMA_NAME_SPACE,
                                                                                "RedHxDiscoveryParam1");

    // private static final String HTTP_PROTOCOL_NAME_TYPE = "TcpProtocol";
    private final List<CsdlAction> actionList;

    // private final FullQualifiedName DISCOVER_FQN;
    // private final FullQualifiedName tcpProctocolFqn;
    public RedHxDiscoverSystemEdmProvider()
    {

//      super(ET_DISCOVER_SYSTEM_NAME);
//      DISCOVER_FQN = new FullQualifiedName(super.getFqdName().getFullQualifiedNameAsString(),
//                                           ET_DISCOVER_SYSTEM_NAME);
//      tcpProctocolFqn = addEnumType(HTTP_PROTOCOL_NAME_TYPE, true, "HTTP", "HTTPS");
        // actionList = new ArrayList<>();
        actionList = createActionList();
    }

    @Override
    public List<CsdlAction> getActionList()
    {
        return actionList;
    }

    @Override
    public List<CsdlAction> getActions(FullQualifiedName actionName)
    {
        List<CsdlAction> list = new ArrayList<>();

        for (CsdlAction action : actionList)
        {
            if (action.getName().equals(actionName.getName()))
            {
                list.add(action);
            }
        }

        System.out.println("HFB5: looking up action name=" + actionName.getFullQualifiedNameAsString() + " found " + list.size()
                + " actions.");

        return list;
    }

    private static List<CsdlAction> createActionList()
    {
        List<CsdlAction> actionList = new ArrayList<>();

        // actionList=     Collections.singletonList(
        CsdlAction action = new CsdlAction().setName(ET_DISCOVER_SYSTEM_NAME);
        List<CsdlParameter> paramList = new ArrayList<>();
        CsdlParameter parm = new CsdlParameter().setName("ParameterInt16");

        parm.setType(EdmPrimitiveTypeKind.Int16.getFullQualifiedName());

        // parm.setReturnType(new CsdlReturnType().setType(nameETTwoBase));
        action.setParameters(paramList);

        actionList.add(action);

        return actionList;

        /*
         *  return OData.newInstance().createDeserializer(CONTENT_TYPE_JSON)
         *   .actionParameters(new ByteArrayInputStream(input.getBytes()),
         *       edm.getBoundAction(new FullQualifiedName("Namespace1_Alias", actionName),
         *           new FullQualifiedName("Namespace1_Alias", typeName), false)).getActionParameters();
         */
    }

//
//  @Override
//  public CsdlEntityType getEntityType()
//  {
//
//      // create EntityType properties
//      CsdlProperty hostName = new CsdlProperty().setName("HostName").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
//      CsdlProperty ipV4 = new CsdlProperty().setName("IPv4").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
//      CsdlProperty ipV6Low = new CsdlProperty().setName("IPv6Low").setType(EdmPrimitiveTypeKind.Int64.getFullQualifiedName());
//      CsdlProperty ipV6High = new CsdlProperty().setName("IPv6High").setType(EdmPrimitiveTypeKind.Int64.getFullQualifiedName());
//
//      //
//      CsdlProperty tcpPortNumber = new CsdlProperty().setName("TcpPortNumber").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
//      CsdlProperty tcpProtocol = new CsdlProperty().setName(HTTP_PROTOCOL_NAME_TYPE).setType(tcpProctocolFqn);
//      CsdlProperty httpUserName = new CsdlProperty().setName("HttpUserName").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
//      CsdlProperty httpPassword = new CsdlProperty().setName("HttpPassword").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
//
//      // configure EntityType
//      CsdlEntityType entityType = new CsdlEntityType();
//
//      entityType.setName(ET_DISCOVER_SYSTEM_NAME);
//      entityType.setProperties(Arrays.asList(hostName, ipV4, ipV6Low, ipV6High, tcpPortNumber, tcpProtocol, httpUserName, httpPassword));
//
//      // entityType.setKey(Collections.singletonList(propertyRef));
//      return entityType;
//  }
}
