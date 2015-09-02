/*
 * Copyright 2015 JBlade LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */



package org.redhelix.core.computer.system;

import org.redhelix.core.computer.system.id.RedHxBootSourceOverrideEnabledEnum;
import org.redhelix.core.computer.system.id.RedHxComputerBootSourceEnum;
import org.redhelix.core.computer.system.id.RedHxComputerBootUefiTargetSourceOverride;
import org.redhelix.core.computer.system.id.RedHxComputerSystemTypeEnum;
import org.redhelix.core.util.RedHxDnsHostName;
import org.redhelix.core.util.RedHxIndicatorLedStateEnum;

import java.util.UUID;

/**
 *
 * <br><br>Git SHA: $Id$
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
class ComputerSystemImpl
        implements RedHxComputerSystem
{    /*
      * sorted in alpha order by class name.
      */
private final RedHxComputerBootSourceEnum                   bootSource;
    private final RedHxDnsHostName                          hostname;
    private final RedHxIndicatorLedStateEnum                indicatorLed;
    private final RedHxBootSourceOverrideEnabledEnum        overrideEnabled;
    private final RedHxComputerSystemTypeEnum               systemType;
    private final RedHxComputerBootUefiTargetSourceOverride uefiTargetSourcePath;
    private final UUID                                      uuid;

    /**
     * @param bootSource
     * @param hostname
     * @param indicatorLed
     * @param overrideEnabled
     * @param systemType
     * @param uefiTargetSourcePath
     * @param uuid
     */
    public ComputerSystemImpl( RedHxComputerBootSourceEnum               bootSource,
                               RedHxDnsHostName                          hostname,
                               RedHxIndicatorLedStateEnum                indicatorLed,
                               RedHxBootSourceOverrideEnabledEnum        overrideEnabled,
                               RedHxComputerSystemTypeEnum               systemType,
                               RedHxComputerBootUefiTargetSourceOverride uefiTargetSourcePath,
                               UUID                                      uuid )
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
    public RedHxDnsHostName getHostname( )
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
    public RedHxBootSourceOverrideEnabledEnum getOverrideEnabled( )
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
    public RedHxComputerBootUefiTargetSourceOverride getUefiTargetSourcePath( )
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
}
