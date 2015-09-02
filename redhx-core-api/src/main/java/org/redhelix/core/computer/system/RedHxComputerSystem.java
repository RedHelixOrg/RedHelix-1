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
public interface RedHxComputerSystem
{
    /**
     * @return the bootSource
     */
    RedHxComputerBootSourceEnum getBootSource( );

    /**
     * @return the hostname
     */
    RedHxDnsHostName getHostname( );

    /**
     * @return the indicatorLed
     */
    RedHxIndicatorLedStateEnum getIndicatorLed( );

    /**
     * @return the overrideEnabled
     */
    RedHxBootSourceOverrideEnabledEnum getOverrideEnabled( );

    /**
     * @return the systemType
     */
    RedHxComputerSystemTypeEnum getSystemType( );

    /**
     * @return the uefiTargetSourcePath
     */
    RedHxComputerBootUefiTargetSourceOverride getUefiTargetSourcePath( );

    /**
     * @return the uuid
     */
    UUID getUuid( );
}
