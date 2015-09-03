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



package org.redhelix.core.computer.system.power.limit;

/**
 *
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public enum RedHxPowerLimitExceptionEnum
{
    HARD_POWER_OFF("Turn the power off immediately when the limit is exceeded."),
    LOG_EVENT_ONLY("Log an event when the limit is exceeded, but take no further action."),
    NO_ACTION("Take no action when the limit is exceeded."),
    OEM("Take an OEM-defined action.");

    private final String desc;

    private RedHxPowerLimitExceptionEnum( final String desc )
    {
        this.desc = desc;
    }

    public String getDescription( )
    {
        return desc;
    }
}
