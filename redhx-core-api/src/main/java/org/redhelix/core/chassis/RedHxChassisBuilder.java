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



package org.redhelix.core.chassis;

import org.redhelix.core.action.RedHxActionGroup;
import org.redhelix.core.action.RedHxActionProperties;
import org.redhelix.core.chassis.id.RedHxChassisAssetTag;
import org.redhelix.core.chassis.id.RedHxChassisDescription;
import org.redhelix.core.chassis.id.RedHxChassisId;
import org.redhelix.core.chassis.id.RedHxChassisManufacturerName;
import org.redhelix.core.chassis.id.RedHxChassisModelNumber;
import org.redhelix.core.chassis.id.RedHxChassisName;
import org.redhelix.core.chassis.id.RedHxChassisPartNumber;
import org.redhelix.core.chassis.id.RedHxChassisSerialNumber;
import org.redhelix.core.chassis.id.RedHxChassisSKU;
import org.redhelix.core.util.RedHxIndicatorLedStateEnum;
import org.redhelix.core.util.RedHxOperatingHealthEnum;
import org.redhelix.core.util.RedHxOperatingState;
import org.redhelix.core.util.RedHxUriPath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * <br><br>
 * Git SHA: $Id$
 *
 * @since RedHelix Version HELIX_VERSION_TAG // Do not change this line.
 * @author Hank Bruning
 *
 */
public class RedHxChassisBuilder
{
    private static final Map<String, RedHxChassisTypeEnum>       CHASSIS_STRING_TO_TYPE_MAP = createChassisTypeMap();
    private static final Map<String, RedHxIndicatorLedStateEnum> LED_STRING_TO_TYPE_MAP     = createLedTypeMap();
    private final RedHxChassisTypeEnum                           chassisType;
    private RedHxActionGroup                                     actionGroup;
    private RedHxChassisAssetTag                                 assetTag;
    private RedHxChassisDescription                              chassisDescription;
    private RedHxChassisId                                       chassisId;
    private RedHxChassisName                                     chassisName;
    private List<RedHxUriPath>                                   computerSystemUriPathList;
    private RedHxUriPath                                         containedByUriPath;
    private List<RedHxUriPath>                                   containsList;
    private List<RedHxUriPath>                                   cooledByUriPathList;
    private RedHxIndicatorLedStateEnum                           ledState;
    private RedHxUriPath                                         logServicesUriPath;
    private RedHxChassisManufacturerName                         manufacturerName;
    private RedHxChassisModelNumber                              modelNumber;
    private RedHxOperatingHealthEnum                             operatingHealth;
    private RedHxOperatingState                                  operatingState;
    private RedHxChassisPartNumber                               partNumber;
    private List<RedHxUriPath>                                   poweredByList;
    private RedHxUriPath                                         powerUriPath;
    private RedHxChassisSerialNumber                             serialNumber;
    private RedHxChassisSKU                                      sku;
    private List<RedHxUriPath>                                   systemManagerUriPathList;
    private RedHxUriPath                                         thermalUriPath;

    public RedHxChassisBuilder( RedHxChassisTypeEnum chassisType )
    {
        this.chassisType      = chassisType;
        this.serialNumber     = serialNumber;
        this.manufacturerName = manufacturerName;
    }

    /**
     * convert from a JSON string to the chassis type enumeration.
     *
     * @param value the JSON string
     * @return null if the argument is not a valid JSON chassis type otherwise the enumerated chassis tyoe.
     */
    public static RedHxChassisTypeEnum convertChassisType( String value )
    {
        return CHASSIS_STRING_TO_TYPE_MAP.get(value);
    }

    public RedHxChassis getInstance( )
    {
        return new ChassisImpl(modelNumber,
                               partNumber,
                               sku,
                               serialNumber,
                               chassisType,
                               manufacturerName,
                               ledState,
                               assetTag);
    }

    public void setActions( Set<RedHxActionProperties> actionSet )
    {
        actionGroup = new RedHxActionGroup(actionSet);
    }

    public void setAssetTag( String value )
    {
        assetTag = new RedHxChassisAssetTag(value);
    }

    public void setChassisDescription( String value )
    {
        chassisDescription = new RedHxChassisDescription(value);
    }

    public void setChassisId( String value )
    {
        chassisId = new RedHxChassisId(value);
    }

    public void setChassisModelName( String value )
    {
        modelNumber = new RedHxChassisModelNumber(value);
    }

    public void setChassisName( String value )
    {
        chassisName = new RedHxChassisName(value);
    }

    public void setComputerSystemList( List<String> valueList )
    {
        List<RedHxUriPath> list = new ArrayList<>();

        for (String str : valueList)
        {
            list.add(new RedHxUriPath(str));
        }

        Collections.sort(list);
        computerSystemUriPathList = Collections.unmodifiableList(list);
    }

    public void setContainedByLink( String value )
    {
        containedByUriPath = new RedHxUriPath(value);
    }

    public void setContainsList( List<String> valueList )
    {
        List<RedHxUriPath> list = new ArrayList<>();

        for (String str : valueList)
        {
            list.add(new RedHxUriPath(str));
        }

        Collections.sort(list);
        containsList = Collections.unmodifiableList(list);
    }

    public void setCooledByList( List<String> valueList )
    {
        List<RedHxUriPath> list = new ArrayList<>();

        for (String str : valueList)
        {
            list.add(new RedHxUriPath(str));
        }

        Collections.sort(list);
        cooledByUriPathList = Collections.unmodifiableList(list);
    }

    public void setIndicatorLed( String value )
    {
        ledState = LED_STRING_TO_TYPE_MAP.get(value);

        if (ledState == null)
        {
            throw new IllegalArgumentException("Invalid argument value containing \"" + value
                                               + "\". It is not one of the JSON defined Indicator LED types.");
        }
    }

    public void setManufacturerName( String value )
    {
        this.manufacturerName = new RedHxChassisManufacturerName(value);
    }

    public void setModelNumber( String value )
    {
        modelNumber = new RedHxChassisModelNumber(value);
    }

    public void setOperatingState( String value )
    {
        RedHxOperatingState tmpState = RedHxOperatingState.getInstance(value);

        if (tmpState == null)
        {
            throw new IllegalArgumentException("Invalid argument value containing \"" + value
                                               + "\". It is not one of the JSON defined \"State\" found in the file Resource.json.");
        }

        operatingState = tmpState;
    }

    public void setPartNumber( String value )
    {
        partNumber = new RedHxChassisPartNumber(value);
    }

    public void setPathToLogServices( String value )
    {
        logServicesUriPath = new RedHxUriPath(value);
    }

    public void setPathToPower( String value )
    {
        powerUriPath = new RedHxUriPath(value);
    }

    public void setPathToThermal( String value )
    {
        thermalUriPath = new RedHxUriPath(value);
    }

    public void setPoweredByList( List<String> valueList )
    {
        List<RedHxUriPath> list = new ArrayList<>();

        for (String str : valueList)
        {
            list.add(new RedHxUriPath(str));
        }

        Collections.sort(list);
        poweredByList = Collections.unmodifiableList(list);
    }

    public void setSerialNumber( String value )
    {
        this.serialNumber = new RedHxChassisSerialNumber(value);
    }

    public void setSku( String value )
    {
        sku = new RedHxChassisSKU(value);
    }

    public void setStatusHealth( String value )
    {
        RedHxOperatingHealthEnum tmpHealth = RedHxOperatingHealthEnum.getInstance(value);

        if (tmpHealth == null)
        {
            throw new IllegalArgumentException("Invalid argument value containing \"" + value
                                               + "\". It is not one of the JSON defined \"Health\" found in the file Resource.json.");
        }

        operatingHealth = tmpHealth;
    }

    public void setSystemManagerList( List<String> valueList )
    {
        List<RedHxUriPath> list = new ArrayList<>();

        for (String str : valueList)
        {
            list.add(new RedHxUriPath(str));
        }

        Collections.sort(list);
        systemManagerUriPathList = Collections.unmodifiableList(list);
    }

    private static final Map<String, RedHxChassisTypeEnum> createChassisTypeMap( )
    {
        Map<String, RedHxChassisTypeEnum> map = new HashMap<>();

        map.put("Blade",
                RedHxChassisTypeEnum.BLADE);
        map.put("Card",
                RedHxChassisTypeEnum.CARD);
        map.put("Cartridge",
                RedHxChassisTypeEnum.CARTRIDGE);
        map.put("Component",
                RedHxChassisTypeEnum.COMPONENT);
        map.put("Drawer",
                RedHxChassisTypeEnum.DRAWER);
        map.put("Enclosure",
                RedHxChassisTypeEnum.ENCLOSURE);
        map.put("Expansion",
                RedHxChassisTypeEnum.EXPANSION);
        map.put("Module",
                RedHxChassisTypeEnum.MODULE);
        map.put("Other",
                RedHxChassisTypeEnum.OTHER);
        map.put("Pod",
                RedHxChassisTypeEnum.POD);
        map.put("Rack",
                RedHxChassisTypeEnum.RACK);
        map.put("RackMount",
                RedHxChassisTypeEnum.RACKMOUNT);
        map.put("Row",
                RedHxChassisTypeEnum.ROW);
        map.put("Shelf",
                RedHxChassisTypeEnum.SHELF);
        map.put("Sidecar",
                RedHxChassisTypeEnum.SIDECAR);
        map.put("Sled",
                RedHxChassisTypeEnum.SLED);
        map.put("StandAlone",
                RedHxChassisTypeEnum.STANDALONE);
        map.put("Zone",
                RedHxChassisTypeEnum.ZONE);

        return map;
    }

    private static final Map<String, RedHxIndicatorLedStateEnum> createLedTypeMap( )
    {
        final Map<String, RedHxIndicatorLedStateEnum> map = new HashMap<>();

        map.put("Unknown",
                RedHxIndicatorLedStateEnum.UNKNOWN);
        map.put("Lit",
                RedHxIndicatorLedStateEnum.LIT);
        map.put("Blinking",
                RedHxIndicatorLedStateEnum.BLINKING);
        map.put("Off",
                RedHxIndicatorLedStateEnum.OFF);

        return map;
    }
}
