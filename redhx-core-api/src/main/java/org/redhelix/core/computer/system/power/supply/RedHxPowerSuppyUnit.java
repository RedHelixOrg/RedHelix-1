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

package org.redhelix.core.computer.system.power.supply;

/**
 * 
 * Git SHA: $Id$
 * 
 * @since RedHelix Version HELIX_VERSION_TAG // Do not change this line.
 * @author Hank Bruning
 *
 */
public interface RedHxPowerSuppyUnit
{

    /**
     * @return the capacityWatts
     */
    RedHxPowerSupplyCapacityWatts getPowerSupplyCapacityWatts();

    /**
     * @return the firmwareVersion
     */
    RedHxPowerSupplyFirmwareVersion getPowerSupplyFirmwareVersion();

    /**
     * @return the lastOutputWatts
     */
    RedHxPowerSupplyLastOutputWatts getPowerSupplyLastOutputWatts();

    /**
     * @return the lineInputVoltage
     */
    RedHxPowerSupplyLineInputVoltage getPowerSupplyLineInputVoltage();

    /**
     * @return the lineInputVoltageType
     */
    RedHxPowerSupplyLineInputVoltageTypeEnum getPowerSupplyLineInputVoltageType();

    /**
     * @return the modelNumber
     */
    RedHxPowerSupplyModelNumber getPowerSupplyModelNumber();

    /**
     * @return the powerSupplyName
     */
    RedHxPowerSupplyName getPowerSupplyName();

    /**
     * @return the partNumber
     */
    RedHxPowerSupplyPartNumber getPowerSupplyPartNumber();

    /**
     * @return the serialNumber
     */
    RedHxPowerSupplySerialNumber getPowerSupplySerialNumber();

    /**
     * @return the sparePartNumber
     */
    RedHxPowerSupplySparePartNumber getPowerSupplySparePartNumber();

    /**
     * @return the powerSupplyType
     */
    RedHxPowerSupplyTypeEnum getPowerSupplyType();

}
