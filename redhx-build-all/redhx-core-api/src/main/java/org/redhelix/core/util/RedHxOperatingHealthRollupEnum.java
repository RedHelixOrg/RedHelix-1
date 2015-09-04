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
 * the Health State of the resource and its dependent resources();
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 */
public enum RedHxOperatingHealthRollupEnum
{
    OK("OK", "Normal."),
    WARNING("Warning", "A condition exists that requires attention."),
    CRITICAL("Critical", "A critical condition exists that requires immediate attention.");

    private final String desc;
    private final String jsonKeyword;

    private RedHxOperatingHealthRollupEnum( String jsonKeyword,
            String                                 desc )
    {
        this.jsonKeyword = jsonKeyword;
        this.desc        = desc;
    }

    public String getDescription( )
    {
        return desc;
    }

    /**
     * convert from the RedFish JSON keyword into the enumeration. The JSON keyswords are found in the Redfish file Resource.json.
     *
     * @param jsonKeyword the keyword to lookup.
     * @return null if the argument is not a valid Redfish JSON keyword otherwise the enumeration.
     */
    public static RedHxOperatingHealthRollupEnum getInstance( String jsonKeyword )
    {
        RedHxOperatingHealthRollupEnum retVal = null;

        for (RedHxOperatingHealthRollupEnum tmp : values())
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
