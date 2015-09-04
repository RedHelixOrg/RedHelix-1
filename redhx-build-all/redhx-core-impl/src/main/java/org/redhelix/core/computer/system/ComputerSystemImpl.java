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
import org.redhelix.core.util.RedHxDnsHostName;
import org.redhelix.core.util.RedHxIndicatorLedStateEnum;
import org.redhelix.core.util.RedHxOperatingStatus;

import java.util.Objects;
import java.util.UUID;

/**
 *
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
final class ComputerSystemImpl
        implements RedHxComputerSystem
{
    private final RedHxComputerAssetTag                            assetTag;
    private final RedHxComputerBiosVersion                         biosVersion;
    private final RedHxComputerBootSourceEnum                      bootSource;
    private final RedHxComputerSystemBootSourceOverrideEnabledEnum bootSourceOverride;
    private final RedHxComputerBootUefiTargetSourceOverride        bootUefiTarget;
    private final RedHxComputerId                                  computerId;
    private final RedHxComputerName                                computerName;
    private final RedHxComputerSKU                                 computerSku;
    private final RedHxComputerDescription                         description;
    private final RedHxDnsHostName                                 hostname;
    private final RedHxIndicatorLedStateEnum                       indicatorLed;
    private final RedHxComputerManufacturerName                    manufacturerName;
    private final RedHxOperatingStatus                             memoryOperatingStatus;
    private final RedHxComputerProcessorModelName                  modelName;
    private final RedHxComputerModelNumber                         modelNumber;
    private final RedHxComputerPartNumber                          partNumber;
    private final RedHxComputerPowerStateEnum                      powerState;
    private final int                                              processorCount;
    private final RedHxOperatingStatus                             processorOperatingStatus;
    private final RedHxComputerSerialNumber                        serialNumber;
    private final RedHxComputerSystemTypeEnum                      systemType;
    private final int                                              totalSystemMemoryGiB;
    private final UUID                                             uuid;

    ComputerSystemImpl( RedHxComputerAssetTag                            assetTag,
                        RedHxComputerBiosVersion                         biosVersion,
                        RedHxComputerBootSourceEnum                      bootSource,
                        RedHxComputerSystemBootSourceOverrideEnabledEnum bootSourceOverride,
                        RedHxComputerBootUefiTargetSourceOverride        bootUefiTarget,
                        RedHxComputerId                                  computerId,
                        RedHxComputerName                                computerName,
                        RedHxComputerSKU                                 computerSku,
                        RedHxComputerDescription                         description,
                        RedHxDnsHostName                                 hostname,
                        RedHxIndicatorLedStateEnum                       indicatorLed,
                        RedHxComputerManufacturerName                    manufacturerName,
                        RedHxOperatingStatus                             memoryOperatingStatus,
                        RedHxComputerProcessorModelName                  modelName,
                        RedHxComputerModelNumber                         modelNumber,
                        RedHxComputerPartNumber                          partNumber,
                        RedHxComputerPowerStateEnum                      powerState,
                        int                                              processorCount,
                        RedHxOperatingStatus                             processorOperatingStatus,
                        RedHxComputerSerialNumber                        serialNumber,
                        RedHxComputerSystemTypeEnum                      systemType,
                        int                                              totalSystemMemoryGiB,
                        UUID                                             uuid )
    {
        this.assetTag                 = assetTag;
        this.biosVersion              = biosVersion;
        this.bootSource               = bootSource;
        this.bootSourceOverride       = bootSourceOverride;
        this.bootUefiTarget           = bootUefiTarget;
        this.computerId               = computerId;
        this.computerName             = computerName;
        this.computerSku              = computerSku;
        this.description              = description;
        this.hostname                 = hostname;
        this.indicatorLed             = indicatorLed;
        this.manufacturerName         = manufacturerName;
        this.memoryOperatingStatus    = memoryOperatingStatus;
        this.modelName                = modelName;
        this.modelNumber              = modelNumber;
        this.partNumber               = partNumber;
        this.powerState               = powerState;
        this.processorCount           = processorCount;
        this.processorOperatingStatus = processorOperatingStatus;
        this.serialNumber             = serialNumber;
        this.systemType               = systemType;
        this.totalSystemMemoryGiB     = totalSystemMemoryGiB;
        this.uuid                     = uuid;
    }

    private ComputerSystemImpl( )
    {
        this.assetTag                 = null;
        this.biosVersion              = null;
        this.bootSource               = null;
        this.bootSourceOverride       = null;
        this.bootUefiTarget           = null;
        this.computerId               = null;
        this.computerName             = null;
        this.computerSku              = null;
        this.description              = null;
        this.hostname                 = null;
        this.indicatorLed             = null;
        this.manufacturerName         = null;
        this.memoryOperatingStatus    = null;
        this.modelName                = null;
        this.modelNumber              = null;
        this.partNumber               = null;
        this.powerState               = null;
        this.processorCount           = -1;
        this.processorOperatingStatus = null;
        this.serialNumber             = null;
        this.systemType               = null;
        this.totalSystemMemoryGiB     = -1;
        this.uuid                     = null;
    }

    @Override
    public boolean equals( Object obj )
    {
        if (this == obj)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (getClass() != obj.getClass())
        {
            return false;
        }

        final ComputerSystemImpl other = (ComputerSystemImpl) obj;

        if (this.processorCount != other.processorCount)
        {
            return false;
        }

        if (this.totalSystemMemoryGiB != other.totalSystemMemoryGiB)
        {
            return false;
        }

        if (!Objects.equals(this.assetTag, other.assetTag))
        {
            return false;
        }

        if (!Objects.equals(this.biosVersion, other.biosVersion))
        {
            return false;
        }

        if (this.bootSource != other.bootSource)
        {
            return false;
        }

        if (this.bootSourceOverride != other.bootSourceOverride)
        {
            return false;
        }

        if (!Objects.equals(this.bootUefiTarget, other.bootUefiTarget))
        {
            return false;
        }

        if (!Objects.equals(this.computerId, other.computerId))
        {
            return false;
        }

        if (!Objects.equals(this.computerName, other.computerName))
        {
            return false;
        }

        if (!Objects.equals(this.computerSku, other.computerSku))
        {
            return false;
        }

        if (!Objects.equals(this.description, other.description))
        {
            return false;
        }

        if (!Objects.equals(this.hostname, other.hostname))
        {
            return false;
        }

        if (this.indicatorLed != other.indicatorLed)
        {
            return false;
        }

        if (!Objects.equals(this.manufacturerName, other.manufacturerName))
        {
            return false;
        }

        if (!Objects.equals(this.memoryOperatingStatus, other.memoryOperatingStatus))
        {
            return false;
        }

        if (!Objects.equals(this.modelName, other.modelName))
        {
            return false;
        }

        if (!Objects.equals(this.modelNumber, other.modelNumber))
        {
            return false;
        }

        if (!Objects.equals(this.partNumber, other.partNumber))
        {
            return false;
        }

        if (this.powerState != other.powerState)
        {
            return false;
        }

        if (!Objects.equals(this.processorOperatingStatus, other.processorOperatingStatus))
        {
            return false;
        }

        if (!Objects.equals(this.serialNumber, other.serialNumber))
        {
            return false;
        }

        if (this.systemType != other.systemType)
        {
            return false;
        }

        if (!Objects.equals(this.uuid, other.uuid))
        {
            return false;
        }

        return true;
    }

    @Override
    public RedHxComputerAssetTag getAssetTag( )
    {
        return assetTag;
    }

    @Override
    public RedHxComputerBiosVersion getBiosVersion( )
    {
        return biosVersion;
    }

    @Override
    public RedHxComputerBootSourceEnum getBootSource( )
    {
        return bootSource;
    }

    @Override
    public RedHxComputerSystemBootSourceOverrideEnabledEnum getBootSourceOverride( )
    {
        return bootSourceOverride;
    }

    @Override
    public RedHxComputerBootUefiTargetSourceOverride getBootUefiTarget( )
    {
        return bootUefiTarget;
    }

    @Override
    public RedHxComputerDescription getDescription( )
    {
        return description;
    }

    @Override
    public RedHxDnsHostName getHostname( )
    {
        return hostname;
    }

    @Override
    public RedHxComputerId getId( )
    {
        return computerId;
    }

    @Override
    public RedHxIndicatorLedStateEnum getIndicatorLed( )
    {
        return indicatorLed;
    }

    @Override
    public RedHxComputerManufacturerName getManufacturerName( )
    {
        return manufacturerName;
    }

    @Override
    public RedHxOperatingStatus getMemoryOperatingStatus( )
    {
        return memoryOperatingStatus;
    }

    @Override
    public RedHxComputerProcessorModelName getModelName( )
    {
        return modelName;
    }

    @Override
    public RedHxComputerModelNumber getModelNumber( )
    {
        return modelNumber;
    }

    @Override
    public RedHxComputerName getName( )
    {
        return computerName;
    }

    @Override
    public RedHxComputerPartNumber getPartNumber( )
    {
        return partNumber;
    }

    @Override
    public RedHxComputerPowerStateEnum getPowerState( )
    {
        return powerState;
    }

    @Override
    public int getProcessorCount( )
    {
        return processorCount;
    }

    @Override
    public RedHxOperatingStatus getProcessorOperatingStatus( )
    {
        return processorOperatingStatus;
    }

    @Override
    public RedHxComputerSerialNumber getSerialNumber( )
    {
        return serialNumber;
    }

    @Override
    public RedHxComputerSKU getSku( )
    {
        return computerSku;
    }

    @Override
    public RedHxComputerSystemTypeEnum getSystemType( )
    {
        return systemType;
    }

    @Override
    public int getTotalSystemMemoryGiB( )
    {
        return totalSystemMemoryGiB;
    }

    @Override
    public UUID getUuid( )
    {
        return uuid;
    }

    @Override
    public int hashCode( )
    {
        int hash = 7;

        hash = 97 * hash + Objects.hashCode(this.assetTag);
        hash = 97 * hash + Objects.hashCode(this.computerSku);
        hash = 97 * hash + Objects.hashCode(this.modelNumber);
        hash = 97 * hash + Objects.hashCode(this.serialNumber);
        hash = 97 * hash + Objects.hashCode(this.uuid);

        return hash;
    }

    @Override
    public String toString( )
    {
        return "ComputerSystemImpl{" + "assetTag=" + assetTag + ", biosVersion=" + biosVersion + ", bootSource=" + bootSource
               + ", bootSourceOverride=" + bootSourceOverride + ", bootUefiTarget=" + bootUefiTarget + ", computerId=" + computerId
               + ", computerName=" + computerName + ", computerSku=" + computerSku + ", description=" + description + ", hostname="
               + hostname + ", indicatorLed=" + indicatorLed + ", manufacturerName=" + manufacturerName + ", memoryOperatingStatus="
               + memoryOperatingStatus + ", modelName=" + modelName + ", modelNumber=" + modelNumber + ", partNumber=" + partNumber
               + ", powerState=" + powerState + ", processorCount=" + processorCount + ", processorOperatingStatus="
               + processorOperatingStatus + ", serialNumber=" + serialNumber + ", systemType=" + systemType + ", totalSystemMemoryGiB="
               + totalSystemMemoryGiB + ", uuid=" + uuid + '}';
    }
}
