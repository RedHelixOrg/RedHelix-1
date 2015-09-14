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
package org.redhelix.core.computer.system.power.supply;

/**
 *
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public final class RedHxPowerSuppyUnitImpl implements RedHxPowerSuppyUnit {

  private final RedHxPowerSupplyCapacityWatts capacityWatts;
  private final RedHxPowerSupplyFirmwareVersion firmwareVersion;
  private final RedHxPowerSupplyLastOutputWatts lastOutputWatts;
  private final RedHxPowerSupplyLineInputVoltage lineInputVoltage;
  private final RedHxPowerSupplyLineInputVoltageTypeEnum lineInputVoltageType;
  private final RedHxPowerSupplyModelNumber modelNumber;
  private final RedHxPowerSupplyPartNumber partNumber;
  private final RedHxPowerSupplyName powerSupplyName;
  private final RedHxPowerSupplyTypeEnum powerSupplyType;
  private final RedHxPowerSupplySerialNumber serialNumber;
  private final RedHxPowerSupplySparePartNumber sparePartNumber;

  /**
   * @param capacityWatts
   * @param firmwareVersion
   * @param lastOutputWatts
   * @param lineInputVoltage
   * @param lineInputVoltageType
   * @param modelNumber
   * @param powerSupplyName
   * @param partNumber
   * @param serialNumber
   * @param sparePartNumber
   * @param powerSupplyType
   */
  public RedHxPowerSuppyUnitImpl(RedHxPowerSupplyCapacityWatts capacityWatts,
      RedHxPowerSupplyFirmwareVersion firmwareVersion,
      RedHxPowerSupplyLastOutputWatts lastOutputWatts,
      RedHxPowerSupplyLineInputVoltage lineInputVoltage,
      RedHxPowerSupplyLineInputVoltageTypeEnum lineInputVoltageType,
      RedHxPowerSupplyModelNumber modelNumber, RedHxPowerSupplyName powerSupplyName,
      RedHxPowerSupplyPartNumber partNumber, RedHxPowerSupplySerialNumber serialNumber,
      RedHxPowerSupplySparePartNumber sparePartNumber, RedHxPowerSupplyTypeEnum powerSupplyType) {
    super();
    this.capacityWatts = capacityWatts;
    this.firmwareVersion = firmwareVersion;
    this.lastOutputWatts = lastOutputWatts;
    this.lineInputVoltage = lineInputVoltage;
    this.lineInputVoltageType = lineInputVoltageType;
    this.modelNumber = modelNumber;
    this.powerSupplyName = powerSupplyName;
    this.partNumber = partNumber;
    this.serialNumber = serialNumber;
    this.sparePartNumber = sparePartNumber;
    this.powerSupplyType = powerSupplyType;
  }

  private RedHxPowerSuppyUnitImpl() {
    super();
    this.capacityWatts = null;
    this.firmwareVersion = null;
    this.lastOutputWatts = null;
    this.lineInputVoltage = null;
    this.lineInputVoltageType = null;
    this.modelNumber = null;
    this.powerSupplyName = null;
    this.partNumber = null;
    this.serialNumber = null;
    this.sparePartNumber = null;
    this.powerSupplyType = null;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null) {
      return false;
    }

    if (getClass() != obj.getClass()) {
      return false;
    }

    final RedHxPowerSuppyUnitImpl other = (RedHxPowerSuppyUnitImpl) obj;

    if (firmwareVersion == null) {
      if (other.firmwareVersion != null) {
        return false;
      }
    } else if (!firmwareVersion.equals(other.firmwareVersion)) {
      return false;
    }

    if (lineInputVoltage == null) {
      if (other.lineInputVoltage != null) {
        return false;
      }
    } else if (!lineInputVoltage.equals(other.lineInputVoltage)) {
      return false;
    }

    if (lineInputVoltageType != other.lineInputVoltageType) {
      return false;
    }

    if (modelNumber == null) {
      if (other.modelNumber != null) {
        return false;
      }
    } else if (!modelNumber.equals(other.modelNumber)) {
      return false;
    }

    if (partNumber == null) {
      if (other.partNumber != null) {
        return false;
      }
    } else if (!partNumber.equals(other.partNumber)) {
      return false;
    }

    if (powerSupplyName == null) {
      if (other.powerSupplyName != null) {
        return false;
      }
    } else if (!powerSupplyName.equals(other.powerSupplyName)) {
      return false;
    }

    if (powerSupplyType != other.powerSupplyType) {
      return false;
    }

    if (serialNumber == null) {
      if (other.serialNumber != null) {
        return false;
      }
    } else if (!serialNumber.equals(other.serialNumber)) {
      return false;
    }

    if (sparePartNumber == null) {
      if (other.sparePartNumber != null) {
        return false;
      }
    } else if (!sparePartNumber.equals(other.sparePartNumber)) {
      return false;
    }

    return true;
  }

  @Override
  public RedHxPowerSupplyCapacityWatts getPowerSupplyCapacityWatts() {
    return capacityWatts;
  }

  @Override
  public RedHxPowerSupplyFirmwareVersion getPowerSupplyFirmwareVersion() {
    return firmwareVersion;
  }

  @Override
  public RedHxPowerSupplyLastOutputWatts getPowerSupplyLastOutputWatts() {
    return lastOutputWatts;
  }

  @Override
  public RedHxPowerSupplyLineInputVoltage getPowerSupplyLineInputVoltage() {
    return lineInputVoltage;
  }

  @Override
  public RedHxPowerSupplyLineInputVoltageTypeEnum getPowerSupplyLineInputVoltageType() {
    return lineInputVoltageType;
  }

  @Override
  public RedHxPowerSupplyModelNumber getPowerSupplyModelNumber() {
    return modelNumber;
  }

  @Override
  public RedHxPowerSupplyName getPowerSupplyName() {
    return powerSupplyName;
  }

  @Override
  public RedHxPowerSupplyPartNumber getPowerSupplyPartNumber() {
    return partNumber;
  }

  @Override
  public RedHxPowerSupplySerialNumber getPowerSupplySerialNumber() {
    return serialNumber;
  }

  @Override
  public RedHxPowerSupplySparePartNumber getPowerSupplySparePartNumber() {
    return sparePartNumber;
  }

  @Override
  public RedHxPowerSupplyTypeEnum getPowerSupplyType() {
    return powerSupplyType;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;

    result = prime * result + ((firmwareVersion == null) ? 0 : firmwareVersion.hashCode());
    result = prime * result + ((lineInputVoltage == null) ? 0 : lineInputVoltage.hashCode());
    result =
        prime * result + ((lineInputVoltageType == null) ? 0 : lineInputVoltageType.hashCode());
    result = prime * result + ((modelNumber == null) ? 0 : modelNumber.hashCode());
    result = prime * result + ((partNumber == null) ? 0 : partNumber.hashCode());
    result = prime * result + ((powerSupplyName == null) ? 0 : powerSupplyName.hashCode());
    result = prime * result + ((powerSupplyType == null) ? 0 : powerSupplyType.hashCode());
    result = prime * result + ((serialNumber == null) ? 0 : serialNumber.hashCode());
    result = prime * result + ((sparePartNumber == null) ? 0 : sparePartNumber.hashCode());

    return result;
  }

  @Override
  public String toString() {
    return "RedHxPowerSuppyUnit [firmwareVersion=" + firmwareVersion + ", lineInputVoltage="
        + lineInputVoltage + ", lineInputVoltageType=" + lineInputVoltageType + ", modelNumber="
        + modelNumber + ", partNumber=" + partNumber + ", powerSupplyName=" + powerSupplyName
        + ", powerSupplyType=" + powerSupplyType + ", serialNumber=" + serialNumber
        + ", sparePartNumber=" + sparePartNumber + "]";
  }
}
