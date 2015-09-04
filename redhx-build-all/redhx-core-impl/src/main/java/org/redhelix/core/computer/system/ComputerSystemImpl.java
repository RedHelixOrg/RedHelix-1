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

import org.redhelix.core.computer.system.boot.RedHxComputerSystemBootSourceOverrideEnabledEnum;
import org.redhelix.core.computer.system.boot.RedHxComputerBootSourceEnum;
import org.redhelix.core.computer.system.boot.RedHxComputerBootUefiTargetSourceOverrideImpl;
import org.redhelix.core.computer.system.id.RedHxComputerSystemTypeEnum;
import org.redhelix.core.util.RedHxDnsHostNameImpl;
import org.redhelix.core.util.RedHxIndicatorLedStateEnum;

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
{    /*
      *                                                         sorted in alpha order by class name.
      */
private final RedHxComputerBootSourceEnum                       bootSource;
    private final RedHxDnsHostNameImpl                          hostname;
    private final RedHxIndicatorLedStateEnum                    indicatorLed;
    private final RedHxComputerSystemBootSourceOverrideEnabledEnum            overrideEnabled;
    private final RedHxComputerSystemTypeEnum                   systemType;
    private final RedHxComputerBootUefiTargetSourceOverrideImpl uefiTargetSourcePath;
    private final UUID                                          uuid;

    /**
     * @param bootSource
     * @param hostname
     * @param indicatorLed
     * @param overrideEnabled
     * @param systemType
     * @param uefiTargetSourcePath
     * @param uuid
     */
    ComputerSystemImpl( RedHxComputerBootSourceEnum                   bootSource,
                        RedHxDnsHostNameImpl                          hostname,
                        RedHxIndicatorLedStateEnum                    indicatorLed,
                        RedHxComputerSystemBootSourceOverrideEnabledEnum            overrideEnabled,
                        RedHxComputerSystemTypeEnum                   systemType,
                        RedHxComputerBootUefiTargetSourceOverrideImpl uefiTargetSourcePath,
                        UUID                                          uuid )
    {
        super();
        this.bootSource           = bootSource;
        this.hostname             = hostname;
        this.indicatorLed         = indicatorLed;
        this.overrideEnabled      = overrideEnabled;
        this.systemType           = systemType;
        this.uefiTargetSourcePath = uefiTargetSourcePath;
        this.uuid                 = uuid;
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

        if (this.bootSource != other.bootSource)
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

        if (this.overrideEnabled != other.overrideEnabled)
        {
            return false;
        }

        if (this.systemType != other.systemType)
        {
            return false;
        }

        if (!Objects.equals(this.uefiTargetSourcePath, other.uefiTargetSourcePath))
        {
            return false;
        }

        if (!Objects.equals(this.uuid, other.uuid))
        {
            return false;
        }

        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.redhelix.core.computer.system.TT#getBootSource()
     */
    @Override
    public RedHxComputerBootSourceEnum getBootSource( )
    {
        return bootSource;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.redhelix.core.computer.system.TT#getHostname()
     */
    @Override
    public RedHxDnsHostNameImpl getHostname( )
    {
        return hostname;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.redhelix.core.computer.system.TT#getIndicatorLed()
     */
    @Override
    public RedHxIndicatorLedStateEnum getIndicatorLed( )
    {
        return indicatorLed;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.redhelix.core.computer.system.TT#getOverrideEnabled()
     */
    @Override
    public RedHxComputerSystemBootSourceOverrideEnabledEnum getOverrideEnabled( )
    {
        return overrideEnabled;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.redhelix.core.computer.system.TT#getSystemType()
     */
    @Override
    public RedHxComputerSystemTypeEnum getSystemType( )
    {
        return systemType;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.redhelix.core.computer.system.TT#getUefiTargetSourcePath()
     */
    @Override
    public RedHxComputerBootUefiTargetSourceOverrideImpl getUefiTargetSourcePath( )
    {
        return uefiTargetSourcePath;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.redhelix.core.computer.system.TT#getUuid()
     */
    @Override
    public UUID getUuid( )
    {
        return uuid;
    }

    @Override
    public int hashCode( )
    {
        int hash = 7;

        hash = 79 * hash + Objects.hashCode(this.hostname);
        hash = 79 * hash + Objects.hashCode(this.systemType);
        hash = 79 * hash + Objects.hashCode(this.uuid);

        return hash;
    }
}
