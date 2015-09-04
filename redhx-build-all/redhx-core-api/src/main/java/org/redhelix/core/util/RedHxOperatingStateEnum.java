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
package org.redhelix.core.util;

/**
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public enum RedHxOperatingStateEnum
{
    ABSENT("Absent", "This function or resource is not present or not detected"),
    DISABLED("Disabled", "This function or resource has been disabled"),
    ENABLED("Enabled", "This function or resource has been enabled"),
    INTEST("InTest", "This function or resource is undergoing testing"),
    STANDBY_OFFLINE("StandbyOffline", "This function or resource is enabled, but awaiting an external action to activate it"),
    STANDBY_SPARE(
            "StandbySpare",
            "This function or resource is part of a redundancy set and is awaiting a failover or other external action to activate it."),
    STARTING("Starting", "This function or resource is starting");

    private final String desc;
    private final String jsonKeyword;

    private RedHxOperatingStateEnum(String jsonKeyword,
                                String desc)
    {
        this.jsonKeyword = jsonKeyword;
        this.desc = desc;
    }

    public String getDescription()
    {
        return desc;
    }

    /**
     * convert from the RedFish JSON keyword into the enumeration. The JSON keyswords are found in the Redfish file Resource.json.
     *
     * @param jsonKeyword the keyword to lookup.
     * @return null if the argument is not a valid Redfish JSON keyword otherwise the enumeration.
     */
    public static RedHxOperatingStateEnum getInstance(String jsonKeyword)
    {
        RedHxOperatingStateEnum retVal = null;

        for (RedHxOperatingStateEnum tmp : values())
        {
            if (tmp.jsonKeyword.equals(jsonKeyword))
            {
                retVal = tmp;

                break;
            }
        }

        return retVal;
    }
}
