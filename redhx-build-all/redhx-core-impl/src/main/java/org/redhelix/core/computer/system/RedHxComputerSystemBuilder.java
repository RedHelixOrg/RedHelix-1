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
package org.redhelix.core.computer.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.redhelix.core.action.RedHxActionGroup;
import org.redhelix.core.action.RedHxActionGroupImpl;
import org.redhelix.core.action.RedHxActionProperties;
import org.redhelix.core.computer.system.boot.RedHxComputerBootSourceEnum;
import org.redhelix.core.computer.system.boot.RedHxComputerBootUefiTargetSourceOverride;
import org.redhelix.core.computer.system.boot.RedHxComputerBootUefiTargetSourceOverrideImpl;
import org.redhelix.core.computer.system.boot.RedHxComputerSystemBootSourceOverrideEnabledEnum;
import org.redhelix.core.computer.system.id.RedHxComputerAssetTag;
import org.redhelix.core.computer.system.id.RedHxComputerAssetTagImpl;
import org.redhelix.core.computer.system.id.RedHxComputerBiosVersion;
import org.redhelix.core.computer.system.id.RedHxComputerBiosVersionImpl;
import org.redhelix.core.computer.system.id.RedHxComputerDescription;
import org.redhelix.core.computer.system.id.RedHxComputerDescriptionImpl;
import org.redhelix.core.computer.system.id.RedHxComputerId;
import org.redhelix.core.computer.system.id.RedHxComputerIdImpl;
import org.redhelix.core.computer.system.id.RedHxComputerManufacturerName;
import org.redhelix.core.computer.system.id.RedHxComputerManufacturerNameImpl;
import org.redhelix.core.computer.system.id.RedHxComputerModelNumber;
import org.redhelix.core.computer.system.id.RedHxComputerModelNumberImpl;
import org.redhelix.core.computer.system.id.RedHxComputerName;
import org.redhelix.core.computer.system.id.RedHxComputerNameImpl;
import org.redhelix.core.computer.system.id.RedHxComputerPartNumber;
import org.redhelix.core.computer.system.id.RedHxComputerPartNumberImpl;
import org.redhelix.core.computer.system.id.RedHxComputerPowerStateEnum;
import org.redhelix.core.computer.system.id.RedHxComputerProcessorModelName;
import org.redhelix.core.computer.system.id.RedHxComputerProcessorModelNameImpl;
import org.redhelix.core.computer.system.id.RedHxComputerSKU;
import org.redhelix.core.computer.system.id.RedHxComputerSKUImpl;
import org.redhelix.core.computer.system.id.RedHxComputerSerialNumber;
import org.redhelix.core.computer.system.id.RedHxComputerSerialNumberImpl;
import org.redhelix.core.computer.system.id.RedHxComputerSystemTypeEnum;
import org.redhelix.core.util.RedHxDnsHostName;
import org.redhelix.core.util.RedHxDnsHostNameImpl;
import org.redhelix.core.util.RedHxIndicatorLedStateEnum;
import org.redhelix.core.util.RedHxOperatingStatus;
import org.redhelix.core.util.RedHxUriPath;
import org.redhelix.core.util.RedHxUriPathImpl;

/**
 * Build a Redfish Computer System. All field in a computer System are optional.
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public final class RedHxComputerSystemBuilder
{

    private final RedHxUriPath computerSystemPath;
    private RedHxActionGroup actionGroup;
    private RedHxComputerAssetTag assetTag;
    private RedHxComputerBiosVersion biosVersion;
    private RedHxComputerBootSourceEnum bootSource;
    private RedHxComputerSystemBootSourceOverrideEnabledEnum bootSourceOverride;
    private RedHxComputerBootUefiTargetSourceOverride bootUefiTarget;
    private List<RedHxUriPath> chassisUriPathList;
    private RedHxComputerId computerId;
    private RedHxComputerName computerName;
    private RedHxOperatingStatus computerOperatingStatus;
    private RedHxComputerSKU computerSku;
    private List<RedHxUriPath> cooledByUriPathList;
    private RedHxComputerDescription description;
    private RedHxDnsHostName hostname;
    private RedHxIndicatorLedStateEnum indicatorLed;
    private RedHxUriPath logServicesUriPath;
    private RedHxComputerManufacturerName manufacturerName;
    private RedHxOperatingStatus memoryOperatingStatus;
    private RedHxComputerProcessorModelName modelName;
    private RedHxComputerModelNumber modelNumber;
    private RedHxComputerPartNumber partNumber;
    private List<RedHxUriPath> poweredByList;
    private RedHxComputerPowerStateEnum powerState;
    private int processorCount;
    private RedHxOperatingStatus processorOperatingStatus;

    private RedHxComputerSerialNumber serialNumber;
    private List<RedHxUriPath> systemManagerUriPathList;
    private RedHxComputerSystemTypeEnum systemType;
    private int totalSystemMemoryGiB;
    private UUID uuid;

    public RedHxComputerSystemBuilder(RedHxUriPath computerSystemPath)
    {
        this.computerSystemPath = computerSystemPath;

        /**
         *
         */
        if (computerSystemPath == null)
        {
            throw new IllegalArgumentException("The argument computerSystemPath may not be null.");
        }
    }

    private RedHxComputerSystemBuilder()
    {
        this.computerSystemPath = null;
    }

    public RedHxComputerSystem getInstance()
    {
        RedHxComputerSystem computerSystem = new ComputerSystemImpl(actionGroup,
                                                                    assetTag,
                                                                    biosVersion,
                                                                    bootSource,
                                                                    bootSourceOverride,
                                                                    bootUefiTarget,
                                                                    computerId,
                                                                    computerName,
                                                                    computerOperatingStatus,
                                                                    computerSku,
                                                                    computerSystemPath,
                                                                    description,
                                                                    hostname,
                                                                    indicatorLed,
                                                                    manufacturerName,
                                                                    memoryOperatingStatus,
                                                                    modelName,
                                                                    modelNumber,
                                                                    partNumber,
                                                                    powerState,
                                                                    processorCount,
                                                                    processorOperatingStatus,
                                                                    serialNumber,
                                                                    systemType,
                                                                    totalSystemMemoryGiB,
                                                                    uuid,
                                                                    chassisUriPathList,
                                                                    poweredByList,
                                                                    cooledByUriPathList,
                                                                    systemManagerUriPathList,
                                                                    logServicesUriPath
        );

        return computerSystem;
    }

    public void setActions(Set<RedHxActionProperties> actionSet)
    {
        actionGroup = new RedHxActionGroupImpl(actionSet);
    }

    public void setAssetTag(final String value)
    {
        if (!value.isEmpty())
        {
            assetTag = new RedHxComputerAssetTagImpl(value);
        }
        else
        {
            assetTag = null;
        }
    }

    public void setBiosVersion(final String value)
    {
        if (!value.isEmpty())
        {
            biosVersion = new RedHxComputerBiosVersionImpl(value);
        }
        else
        {
            biosVersion = null;
        }
    }

    public void setBootSource(final String value)
    {
        if (!value.isEmpty())
        {
            bootSource = RedHxComputerBootSourceEnum.getInstance(value);
        }
        else
        {
            bootSource = null;
        }
    }

    public void setBootSourceOverride(final String value)
    {
        if (!value.isEmpty())
        {
            bootSourceOverride = RedHxComputerSystemBootSourceOverrideEnabledEnum.getInstance(value);
        }
        else
        {
            bootSourceOverride = null;
        }
    }

    public void setBootUefiTarget(final String value)
    {
        if (!value.isEmpty())
        {
            bootUefiTarget = new RedHxComputerBootUefiTargetSourceOverrideImpl(value);
        }
        else
        {
            bootUefiTarget = null;
        }
    }

    public void setChassisList(List<String> valueList)
    {
        List<RedHxUriPath> list = new ArrayList<>();

        for (String str : valueList)
        {
            list.add(new RedHxUriPathImpl(str));
        }

        Collections.sort(list);
        chassisUriPathList = Collections.unmodifiableList(list);
    }

    public void setComputerId(final String value)
    {
        if (!value.isEmpty())
        {
            computerId = new RedHxComputerIdImpl(value);
        }
        else
        {
            computerId = null;
        }
    }

    public void setComputerName(final String value)
    {
        if (!value.isEmpty())
        {
            computerName = new RedHxComputerNameImpl(value);
        }
        else
        {
            computerName = null;
        }
    }

    public void setComputerOperatingStatus(RedHxOperatingStatus computerOperatingStatus)
    {
        this.computerOperatingStatus = computerOperatingStatus;
    }

    public void setComputerSku(final String value)
    {
        if (!value.isEmpty())
        {
            computerSku = new RedHxComputerSKUImpl(value);
        }
        else
        {
            computerSku = null;
        }
    }

    public void setCooledByList(List<String> valueList)
    {
        List<RedHxUriPathImpl> list = new ArrayList<>();

        for (String str : valueList)
        {
            list.add(new RedHxUriPathImpl(str));
        }

        Collections.sort(list);
        cooledByUriPathList = Collections.unmodifiableList(list);
    }

    public void setDescription(final String value)
    {
        if (!value.isEmpty())
        {
            description = new RedHxComputerDescriptionImpl(value);
        }
        else
        {
            description = null;
        }
    }

    public void setEthernetInterfaces(final String value)
    {

        // throw new UnsupportedOperationException("Ethernet intfaces are not yet supported. " + value);
    }

    public void setHostname(final String value)
    {
        if (!value.isEmpty())
        {
            hostname = new RedHxDnsHostNameImpl(value);
        }
        else
        {
            hostname = null;
        }
    }

    public void setIndicatorLed(final String value)
    {
        if (!value.isEmpty())
        {
            indicatorLed = RedHxIndicatorLedStateEnum.getInstance(value);
        }
        else
        {
            indicatorLed = null;
        }
    }

    public void setLogServicesUriPath(final String value)
    {
        if (!value.isEmpty())
        {
            logServicesUriPath = new RedHxUriPathImpl(value);
        }
        else
        {
            logServicesUriPath = null;
        }
    }

    public void setManufacturerName(final String value)
    {
        if (!value.isEmpty())
        {
            manufacturerName = new RedHxComputerManufacturerNameImpl(value);
        }
        else
        {
            manufacturerName = null;
        }
    }

    public void setMemoryOperatingStatus(RedHxOperatingStatus memoryOperatingStatus)
    {
        this.memoryOperatingStatus = memoryOperatingStatus;
    }

    public void setModelName(final String value)
    {
        if (!value.isEmpty())
        {
            modelName = new RedHxComputerProcessorModelNameImpl(value);
        }
        else
        {
            modelName = null;
        }
    }

    public void setModelNumber(final String value)
    {
        if (!value.isEmpty())
        {
            modelNumber = new RedHxComputerModelNumberImpl(value);
        }
        else
        {
            modelNumber = null;
        }
    }

    public void setPartNumber(final String value)
    {
        if (!value.isEmpty())
        {
            partNumber = new RedHxComputerPartNumberImpl(value);
        }
        else
        {
            partNumber = null;
        }
    }

    public void setPathToLogServices(String value)
    {
        logServicesUriPath = new RedHxUriPathImpl(value);
    }

    public void setPoweredByList(List<String> valueList)
    {
        List<RedHxUriPathImpl> list = new ArrayList<>();

        for (String str : valueList)
        {
            list.add(new RedHxUriPathImpl(str));
        }

        Collections.sort(list);
        poweredByList = Collections.unmodifiableList(list);
    }

    public void setPowerState(final String value)
    {
        if (!value.isEmpty())
        {
            powerState = RedHxComputerPowerStateEnum.getInstance(value);
        }
        else
        {
            powerState = null;
        }
    }

    public void setProcessorCount(final String value)
    {
        if (!value.isEmpty())
        {
            processorCount = Integer.parseInt(value);
        }
        else
        {
            processorCount = 0;
        }
    }

    public void setProcessorOperatingStatus(RedHxOperatingStatus processorOperatingStatus)
    {
        this.processorOperatingStatus = processorOperatingStatus;
    }

    public void setSerialNumber(final String value)
    {
        if (!value.isEmpty())
        {
            serialNumber = new RedHxComputerSerialNumberImpl(value);
        }
        else
        {
            serialNumber = null;
        }
    }

    public void setSystemManagerList(List<String> valueList)
    {
        List<RedHxUriPathImpl> list = new ArrayList<>();

        for (String str : valueList)
        {
            list.add(new RedHxUriPathImpl(str));
        }

        Collections.sort(list);
        systemManagerUriPathList = Collections.unmodifiableList(list);
    }

    public void setSystemType(final String value)
    {
        if (!value.isEmpty())
        {
            systemType = RedHxComputerSystemTypeEnum.getInstance(value);
        }
        else
        {
            systemType = null;
        }
    }

    public void setTotalSystemMemoryGiB(final String value)
    {
        if (!value.isEmpty())
        {
            totalSystemMemoryGiB = Integer.parseInt(value);
        }
        else
        {
            totalSystemMemoryGiB = 0;
        }
    }

    public void setUuid(final String value)
    {
        if (!value.isEmpty())
        {
            uuid = UUID.fromString(value);
        }
        else
        {
            uuid = null;
        }
    }
}
