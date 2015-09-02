/*
 * Copyright 2015 JBlade LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */



package org.redhelix.core.computer.system.id;

import org.redhelix.core.util.RedHxAbstractStringProperty;

/**
 *
 * <br><br>Git SHA: $Id$
 *
 * @since RedHelix Version HELIX_VERSION_TAG // Do not change this line.
 * @author Hank Bruning
 *
 */
public class RedHxComputerManufacturerName
        extends RedHxAbstractStringProperty
{
    /**
     * The maximum number of characters allowed in a manufacturer name.
     */
    public final static short MAX_CHAR_COUNT_REDH_DEFINED = 50;    // arbitrary

    /**
     * @param maxCharCount
     * @param propName
     */
    public RedHxComputerManufacturerName( int maxCharCount,
            String                            propName )
    {
        super(maxCharCount,
              propName);
    }

    public String getManufacturerName( )
    {
        return super.getValue();
    }
}
