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

import org.redhelix.core.computer.system.boot.RedHxComputerBootSourceEnum;
import org.redhelix.core.computer.system.boot.RedHxComputerBootUefiTargetSourceOverride;
import org.redhelix.core.computer.system.boot.RedHxComputerSystemBootSourceOverrideEnabledEnum;
import org.redhelix.core.computer.system.id.RedHxComputerAssetTag;
import org.redhelix.core.computer.system.id.RedHxComputerBiosVersion;
import org.redhelix.core.computer.system.id.RedHxComputerDescription;
import org.redhelix.core.computer.system.id.RedHxComputerId;
import org.redhelix.core.computer.system.id.RedHxComputerManufacturerName;
import org.redhelix.core.computer.system.id.RedHxComputerModelNumber;
import org.redhelix.core.computer.system.id.RedHxComputerName;
import org.redhelix.core.computer.system.id.RedHxComputerPartNumber;
import org.redhelix.core.computer.system.id.RedHxComputerPowerStateEnum;
import org.redhelix.core.computer.system.id.RedHxComputerProcessorModelName;
import org.redhelix.core.computer.system.id.RedHxComputerSerialNumber;
import org.redhelix.core.computer.system.id.RedHxComputerSKU;
import org.redhelix.core.computer.system.id.RedHxComputerSystemTypeEnum;
import org.redhelix.core.util.RedHxDnsHostNameImpl;
import org.redhelix.core.util.RedHxIndicatorLedStateEnum;
import org.redhelix.core.util.RedHxOperatingStatus;

import java.util.UUID;

/**
 *
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public final class RedHxComputerSystemBuilder
{
    private RedHxComputerAssetTag                            assetTag;
    private RedHxComputerBiosVersion                         biosVersion;
    private RedHxComputerBootSourceEnum                      bootSource;
    private RedHxComputerSystemBootSourceOverrideEnabledEnum bootSourceOverride;
    private RedHxComputerBootUefiTargetSourceOverride        bootUefiTarget;
    private RedHxComputerId                                  computerId;
    private RedHxComputerName                                computerName;
    private RedHxComputerSKU                                 computerSku;
    private RedHxComputerDescription                         description;
    private RedHxDnsHostNameImpl                             hostname;
    private RedHxIndicatorLedStateEnum                       indicatorLed;
    private RedHxComputerManufacturerName                    manufacturerName;
    private RedHxOperatingStatus                             memoryOperatingStatus;
    private RedHxComputerProcessorModelName                  modelName;
    private RedHxComputerModelNumber                         modelNumber;
    private RedHxComputerPartNumber                          partNumber;
    private RedHxComputerPowerStateEnum                      powerState;
    private int                                              processorCount;
    private RedHxOperatingStatus                             processorOperatingStatus;
    private RedHxComputerSerialNumber                        serialNumber;
    private RedHxComputerSystemTypeEnum                      systemType;
    private int                                              totalSystemMemoryGiB;
    private UUID                                             uuid;

    public RedHxComputerSystemBuilder( ) {}

    public RedHxComputerAssetTag getAssetTag( )
    {
        return assetTag;
    }

    public RedHxComputerBiosVersion getBiosVersion( )
    {
        return biosVersion;
    }

    public RedHxComputerBootSourceEnum getBootSource( )
    {
        return bootSource;
    }

    public RedHxComputerSystemBootSourceOverrideEnabledEnum getBootSourceOverride( )
    {
        return bootSourceOverride;
    }

    public RedHxComputerBootUefiTargetSourceOverride getBootUefiTarget( )
    {
        return bootUefiTarget;
    }

    public RedHxComputerId getComputerId( )
    {
        return computerId;
    }

    public RedHxComputerName getComputerName( )
    {
        return computerName;
    }

    public RedHxComputerSKU getComputerSku( )
    {
        return computerSku;
    }

    public RedHxComputerDescription getDescription( )
    {
        return description;
    }

    public RedHxDnsHostNameImpl getHostname( )
    {
        return hostname;
    }

    public RedHxIndicatorLedStateEnum getIndicatorLed( )
    {
        return indicatorLed;
    }

    public RedHxComputerSystem getInstance( )
    {
        RedHxComputerSystem computerSystem = new ComputerSystemImpl(assetTag,
                biosVersion,
                bootSource,
                bootSourceOverride,
                bootUefiTarget,
                computerId,
                computerName,
                computerSku,
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
                uuid);

        return computerSystem;
    }

    public RedHxComputerManufacturerName getManufacturerName( )
    {
        return manufacturerName;
    }

    public RedHxOperatingStatus getMemoryOperatingStatus( )
    {
        return memoryOperatingStatus;
    }

    public RedHxComputerProcessorModelName getModelName( )
    {
        return modelName;
    }

    public RedHxComputerModelNumber getModelNumber( )
    {
        return modelNumber;
    }

    public RedHxComputerPartNumber getPartNumber( )
    {
        return partNumber;
    }

    public RedHxComputerPowerStateEnum getPowerState( )
    {
        return powerState;
    }

    public int getProcessorCount( )
    {
        return processorCount;
    }

    public RedHxOperatingStatus getProcessorOperatingStatus( )
    {
        return processorOperatingStatus;
    }

    public RedHxComputerSerialNumber getSerialNumber( )
    {
        return serialNumber;
    }

    public RedHxComputerSystemTypeEnum getSystemType( )
    {
        return systemType;
    }

    public int getTotalSystemMemoryGiB( )
    {
        return totalSystemMemoryGiB;
    }

    public UUID getUuid( )
    {
        return uuid;
    }
}
