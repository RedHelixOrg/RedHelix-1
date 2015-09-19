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
package org.redhelix.server.action;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

/**
 *
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public abstract class RedHxAbstractEdmActionProvider
        implements RedHxEdmActionProvider
{

    // Primitive Type Names

    public static final FullQualifiedName nameBinary = EdmPrimitiveTypeKind.Binary.getFullQualifiedName();
    public static final FullQualifiedName nameBoolean = EdmPrimitiveTypeKind.Boolean.getFullQualifiedName();
    public static final FullQualifiedName nameByte = EdmPrimitiveTypeKind.Byte.getFullQualifiedName();

    public static final FullQualifiedName nameDate = EdmPrimitiveTypeKind.Date.getFullQualifiedName();
    public static final FullQualifiedName nameDateTimeOffset
                                          = EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName();

    public static final FullQualifiedName nameDecimal = EdmPrimitiveTypeKind.Decimal.getFullQualifiedName();
    public static final FullQualifiedName nameDouble = EdmPrimitiveTypeKind.Double.getFullQualifiedName();
    public static final FullQualifiedName nameDuration = EdmPrimitiveTypeKind.Duration.getFullQualifiedName();

    public static final FullQualifiedName nameGuid = EdmPrimitiveTypeKind.Guid.getFullQualifiedName();
    public static final FullQualifiedName nameInt16 = EdmPrimitiveTypeKind.Int16.getFullQualifiedName();
    public static final FullQualifiedName nameInt32 = EdmPrimitiveTypeKind.Int32.getFullQualifiedName();
    public static final FullQualifiedName nameInt64 = EdmPrimitiveTypeKind.Int64.getFullQualifiedName();

    public static final FullQualifiedName nameSByte = EdmPrimitiveTypeKind.SByte.getFullQualifiedName();
    public static final FullQualifiedName nameSingle = EdmPrimitiveTypeKind.Single.getFullQualifiedName();

    public static final FullQualifiedName nameString = EdmPrimitiveTypeKind.String.getFullQualifiedName();
    public static final FullQualifiedName nameTimeOfDay = EdmPrimitiveTypeKind.TimeOfDay.getFullQualifiedName();

   // private final FullQualifiedName actionFqn;
//    protected RedHxAbstractEdmActionProvider(String actionName)
//    {
//        actionFqn = new FullQualifiedName(super.getFqdName().getFullQualifiedNameAsString(),
//                                 actionName);
//    }
    protected RedHxAbstractEdmActionProvider()
    {
        //   this.actionFqn = null;
    }
}
