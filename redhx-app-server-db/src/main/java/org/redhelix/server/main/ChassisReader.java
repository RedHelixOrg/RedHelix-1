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



package org.redhelix.server.main;

import java.net.URISyntaxException;
import java.util.List;
import org.redhelix.core.chassis.RedHxChassis;
import org.redhelix.core.chassis.RedHxChassisBuilder;
import org.redhelix.core.chassis.id.RedHxChassisManufacturerName;
import org.redhelix.core.chassis.id.RedHxChassisSerialNumber;
import org.redhelix.core.service.root.RedHxServiceRootIdEum;
import org.redhelix.core.util.RedHxHttpResponseException;

/**
 *
 * <br><br>
 * Git SHA: $Id$
 *
 * @since RedHelix Version HELIX_VERSION_TAG // Do not change this line.
 * @author Hank Bruning
 *
 */
final class ChassisReader
        extends AbstractRedfishJsonReader
{
    private final static String JSON_ANOTATION_KEY_POWER      = "Power";
    private final static String JSON_ANOTATION_KEY_THERMAL    = "Thermal";
    private final static String JSON_ANOTATION_LOG_SERVICES   = "LogServices";
    private final static String JSON_KEY_ACTIONS              = "Actions";
    private final static String JSON_KEY_ASSET_TAG            = "AssetTag";
    private final static String JSON_KEY_CHASSIS_TYPE         = "ChassisType";        // required by the JSON schema
    private final static String JSON_KEY_DESCRIPTION          = "Description";
    private final static String JSON_KEY_ID                   = "Id";
    private final static String JSON_KEY_INDICATOR_LED        = "IndicatorLED";
    private final static String JSON_KEY_LOG_SERVICES         = "LogServices";
    private final static String JSON_KEY_MANUFACTURER         = "Manufacturer";
    private final static String JSON_KEY_MODEL                = "Model";
    private final static String JSON_KEY_NAME                 = "Name";
    private final static String JSON_KEY_PART_NUMBER          = "PartNumber";
    private final static String JSON_KEY_POWER                = "Power";
    private final static String JSON_KEY_SERIAL_NUMBER        = "SerialNumber";
    private final static String JSON_KEY_SKU                  = "SKU";
    private final static String JSON_KEY_STATUS               = "Status";
    private final static String JSON_LINK_KEY_COMPUTER_SYSTEM = "ComputerSystems";    // array of links
    private final static String JSON_LINK_KEY_CONTAINED_BY    = "ContainedBy";        // single link
    private final static String JSON_LINK_KEY_CONTAINS        = "Contains";           // array of links
    private final static String JSON_LINK_KEY_COOLED_BY       = "CooledBy";           // array of links
    private final static String JSON_LINK_KEY_MANAGED_BY      = "ManagedBy";          // single link
    private final static String JSON_LINK_KEY_POWERED_BY      = "PoweredBy";          // array of links
    private final static String JSON_SUB_KEY_STATUS_HEALTH    = "Health";
    private final static String JSON_SUB_KEY_STATUS_STATE     = "State";

    ChassisReader( RedHxServerConnectionContext ctx,
                   String                       chassisLink )
            throws URISyntaxException,
                   RedHxHttpResponseException
    {
        super(ctx,
              RedHxServiceRootIdEum.CHASSIS,
              chassisLink);
    }


    /**
     *
     * @param ctx
     * @param chassisLink
     * @return
     * @throws RedHxHttpResponseException
     * @throws URISyntaxException
     */
    RedHxChassis readChassis( )
            throws RedHxHttpResponseException,
                   URISyntaxException
    {
        final RedHxChassis chassis;

        System.out.println("HFB5: HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");

        RedHxChassisManufacturerName manufacturerName = null;
        RedHxChassisSerialNumber     serialNumber     = null;
        RedHxChassisBuilder          builder          = new RedHxChassisBuilder(manufacturerName,
                serialNumber);
        String                       tmpStr;

        /*
         * read anotations
         */
        tmpStr = getAnnotation(JSON_ANOTATION_KEY_POWER);
        tmpStr = getAnnotation(JSON_ANOTATION_KEY_THERMAL);
        tmpStr = getAnnotation(JSON_ANOTATION_LOG_SERVICES);

        /*
         * read values with two keys
         */
        tmpStr = getComplexValue(JSON_KEY_STATUS, JSON_SUB_KEY_STATUS_HEALTH);
        tmpStr = getComplexValue(JSON_KEY_STATUS, JSON_SUB_KEY_STATUS_STATE);

        /*
         * read properties
         */
        tmpStr = getOptionalProperty(JSON_KEY_ACTIONS);
        tmpStr = getOptionalProperty(JSON_KEY_ASSET_TAG);
        tmpStr = getOptionalProperty(JSON_KEY_CHASSIS_TYPE);
        tmpStr = getOptionalProperty(JSON_KEY_DESCRIPTION);
        tmpStr = getOptionalProperty(JSON_KEY_ID);
        tmpStr = getOptionalProperty(JSON_KEY_INDICATOR_LED);
        tmpStr = getOptionalProperty(JSON_KEY_LOG_SERVICES);
        tmpStr = getOptionalProperty(JSON_KEY_MANUFACTURER);
        tmpStr = getOptionalProperty(JSON_KEY_MODEL);
        tmpStr = getOptionalProperty(JSON_KEY_NAME);
        tmpStr = getOptionalProperty(JSON_KEY_PART_NUMBER);
        tmpStr = getOptionalProperty(JSON_KEY_POWER);
        tmpStr = getOptionalProperty(JSON_KEY_SERIAL_NUMBER);
        tmpStr = getOptionalProperty(JSON_KEY_SKU);

        /*
         * read single links
         */
        tmpStr = getLinkSingle(JSON_LINK_KEY_CONTAINED_BY);

        /*
         * read lists of links.
         */
        List<String> computerSystemList = getLinkArray(JSON_LINK_KEY_COMPUTER_SYSTEM);
        List<String> systemManagerList  = getLinkArray(JSON_LINK_KEY_MANAGED_BY);
        List<String> containsList       = getLinkArray(JSON_LINK_KEY_CONTAINS);
        List<String> cooledByList       = getLinkArray(JSON_LINK_KEY_COOLED_BY);
        List<String> poweredByList      = getLinkArray(JSON_LINK_KEY_POWERED_BY);

        //
        chassis = builder.getInstance();

        return chassis;
    }

}
