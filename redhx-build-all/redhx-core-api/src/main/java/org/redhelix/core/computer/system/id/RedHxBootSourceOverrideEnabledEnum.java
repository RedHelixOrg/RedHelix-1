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
 */



package org.redhelix.core.computer.system.id;

/**
 *
 *
 *
 * @since RedHelix Version 0.1 It will be replaced when checked in to the master branch
 * @author Hank Bruning
 *
 */
public enum RedHxBootSourceOverrideEnabledEnum
{
    CONTINUOUS("The system will boot to the target specified in the BootSourceOverrideTarget until this property is set to Disabled."),
    DISABLED("The system will boot as normal"),
    ONCE("ON ITS NEXT BOOT CYCLE, THE SYSTEM WILL BOOT (ONE TIME) TO THE BOOT SOURCE OVERRIDE TARGET.");

    private final String desc;

    private RedHxBootSourceOverrideEnabledEnum( String desc )
    {
        this.desc = desc;
    }

    public String getDescription( )
    {
        return desc;
    }
}
