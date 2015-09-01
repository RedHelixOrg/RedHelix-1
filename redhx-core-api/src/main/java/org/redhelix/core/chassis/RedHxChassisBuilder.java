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

import org.redhelix.core.chassis.id.RedHxChassisAssetTag;
import org.redhelix.core.chassis.id.RedHxChassisManufacturerName;
import org.redhelix.core.chassis.id.RedHxChassisModelName;
import org.redhelix.core.chassis.id.RedHxChassisPartNumber;
import org.redhelix.core.chassis.id.RedHxChassisSerialNumber;
import org.redhelix.core.chassis.id.RedHxChassisSKU;
import org.redhelix.core.util.RedHxIndicatorLedStateEnum;

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
    private final RedHxChassisManufacturerName manufacturerName;
    private final RedHxChassisSerialNumber     serialNumber;
    private RedHxChassisAssetTag               assetTag;
    private RedHxChassisTypeEnum               chassisType;
    private RedHxIndicatorLedStateEnum         ledState;
    private RedHxChassisModelName              modelName;
    private RedHxChassisPartNumber             partNumber;
    private RedHxChassisSKU                    sku;

    public RedHxChassisBuilder( RedHxChassisManufacturerName manufacturerName,
                                RedHxChassisSerialNumber     serialNumber )
    {
        this.serialNumber     = serialNumber;
        this.manufacturerName = manufacturerName;
    }

    public RedHxChassis getInstance( )
    {
        return new ChassisImpl(modelName,
                               partNumber,
                               sku,
                               serialNumber,
                               chassisType,
                               manufacturerName,
                               ledState,
                               assetTag);
    }

    public RedHxChassisBuilder setAssetTag( RedHxChassisAssetTag assetTag )
    {
        this.assetTag = assetTag;

        return this;
    }

    public RedHxChassisBuilder setChassisType( RedHxChassisTypeEnum chassisType )
    {
        this.chassisType = chassisType;

        return this;
    }

    public RedHxChassisBuilder setLedState( RedHxIndicatorLedStateEnum ledState )
    {
        this.ledState = ledState;

        return this;
    }

    public RedHxChassisBuilder setModelName( RedHxChassisModelName modelName )
    {
        this.modelName = modelName;

        return this;
    }

    public RedHxChassisBuilder setPartNumber( RedHxChassisPartNumber partNumber )
    {
        this.partNumber = partNumber;

        return this;
    }

    public RedHxChassisBuilder setSku( RedHxChassisSKU sku )
    {
        this.sku = sku;

        return this;
    }
}
