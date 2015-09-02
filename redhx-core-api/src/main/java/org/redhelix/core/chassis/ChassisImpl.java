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



package org.redhelix.core.chassis;

import org.redhelix.core.chassis.id.RedHxChassisAssetTag;
import org.redhelix.core.chassis.id.RedHxChassisManufacturerName;
import org.redhelix.core.chassis.id.RedHxChassisModelNumber;
import org.redhelix.core.chassis.id.RedHxChassisPartNumber;
import org.redhelix.core.chassis.id.RedHxChassisSerialNumber;
import org.redhelix.core.chassis.id.RedHxChassisSKU;
import org.redhelix.core.util.RedHxIndicatorLedStateEnum;

/**
 *
 * <br><br>Git SHA: $Id$
 *
 * @since RedHelix Version 0.0.1
 * @author Hank Bruning
 */
class ChassisImpl
        implements RedHxChassis
{
    private final RedHxChassisAssetTag         assetTag;
    private final RedHxChassisTypeEnum         chassisType;
    private final RedHxIndicatorLedStateEnum   ledState;
    private final RedHxChassisManufacturerName manufacturerName;
    private final RedHxChassisModelNumber      modelName;
    private final RedHxChassisPartNumber       partNumber;
    private final RedHxChassisSerialNumber     serialNumber;
    private final RedHxChassisSKU              sku;

    ChassisImpl( RedHxChassisModelNumber      modelName,
                 RedHxChassisPartNumber       partNumber,
                 RedHxChassisSKU              sku,
                 RedHxChassisSerialNumber     serialNumber,
                 RedHxChassisTypeEnum         chassisType,
                 RedHxChassisManufacturerName manufacturerName,
                 RedHxIndicatorLedStateEnum   ledState,
                 RedHxChassisAssetTag         assetTag )
    {
        this.modelName        = modelName;
        this.partNumber       = partNumber;
        this.sku              = sku;
        this.serialNumber     = serialNumber;
        this.chassisType      = chassisType;
        this.manufacturerName = manufacturerName;
        this.ledState         = ledState;
        this.assetTag         = assetTag;
    }

    private ChassisImpl( )
    {
        this.modelName        = null;
        this.partNumber       = null;
        this.sku              = null;
        this.serialNumber     = null;
        this.chassisType      = null;
        this.manufacturerName = null;
        this.ledState         = null;
        this.assetTag         = null;
    }

    @Override
    public RedHxChassisAssetTag getAssetTag( )
    {
        return assetTag;
    }

    @Override
    public RedHxChassisTypeEnum getChassisType( )
    {
        return chassisType;
    }

    @Override
    public RedHxIndicatorLedStateEnum getLedState( )
    {
        return ledState;
    }

    @Override
    public RedHxChassisManufacturerName getManufacturerName( )
    {
        return manufacturerName;
    }

    @Override
    public RedHxChassisModelNumber getModelName( )
    {
        return modelName;
    }

    @Override
    public RedHxChassisPartNumber getPartNumber( )
    {
        return partNumber;
    }

    @Override
    public RedHxChassisSerialNumber getSerialNumber( )
    {
        return serialNumber;
    }

    @Override
    public RedHxChassisSKU getSku( )
    {
        return sku;
    }
}
