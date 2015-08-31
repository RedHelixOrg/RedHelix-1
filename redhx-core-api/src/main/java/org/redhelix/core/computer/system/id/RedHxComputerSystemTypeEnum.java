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

/**
 *
 * <br><br>Git SHA: $Id$
 *
 * @since RedHelix Version HELIX_VERSION_TAG // Do not change this line.
 * @author Hank Bruning
 *
 */
public enum RedHxComputerSystemTypeEnum
{
    OS("An operating system instance"),
    PHYSICAL("A computer system"),
    PHYSICALLY_PARTITIONED("A hardware-based partition of a computer system"),
    VIRTUAL("A virtual machine instance running on this system"),
    VIRTUALLY_PARTITIONED("A virtual or software-based partition of a computer system");

    private final String desc;

    private RedHxComputerSystemTypeEnum( String desc )
    {
        this.desc = desc;
    }

    public String getDescription( )
    {
        return desc;
    }
}
