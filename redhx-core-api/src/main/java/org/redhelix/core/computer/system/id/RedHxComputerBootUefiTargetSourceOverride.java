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
 * The Uefi Device Path of the device to boot from when
 * BootSourceOverrideSupported is UefiTarget.
 * <br><br>Git SHA: $Id$
 *
 * @since RedHelix Version HELIX_VERSION_TAG // Do not change this line.
 * @author Hank Bruning
 *
 */
public class RedHxComputerBootUefiTargetSourceOverride
        extends RedHxAbstractStringProperty
{
    /**
     * The maximum number of characters in a UEFI boot path.
     */
    public static final short MAX_CHAR_COUNT_REDH_DEFINED = 200;

    /**
     * @param path
     */
    public RedHxComputerBootUefiTargetSourceOverride( String path )
    {
        super(MAX_CHAR_COUNT_REDH_DEFINED,
              path);
    }

    public String getBootPath( )
    {
        return super.getValue();
    }
}
