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

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.redhelix.core.action.RedHxActionGroup;
import org.redhelix.core.annotations.Immutable;
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
import org.redhelix.core.computer.system.id.RedHxComputerSKU;
import org.redhelix.core.computer.system.id.RedHxComputerSerialNumber;
import org.redhelix.core.computer.system.id.RedHxComputerSystemTypeEnum;
import org.redhelix.core.util.RedHxDnsHostName;
import org.redhelix.core.util.RedHxIndicatorLedStateEnum;
import org.redhelix.core.util.RedHxOperatingStatus;
import org.redhelix.core.util.RedHxUriPath;

@Immutable
final class ComputerSystemImpl implements RedHxComputerSystem {
  /*
   * fields appear in alpha order of the field name.
   */

  //
  private final RedHxActionGroup actionGroup;
  private final RedHxComputerAssetTag assetTag;
  private final RedHxComputerBiosVersion biosVersion;
  private final RedHxComputerBootSourceEnum bootSource;
  private final RedHxComputerSystemBootSourceOverrideEnabledEnum bootSourceOverride;
  private final RedHxComputerBootUefiTargetSourceOverride bootUefiTarget;
  private final List<RedHxUriPath> chassisUriPathList;
  private final RedHxComputerId computerId;
  private final RedHxComputerName computerName;
  private final RedHxOperatingStatus computerOperatingStatus;
  private final RedHxComputerSKU computerSku;
  private final RedHxUriPath computerSystemPath;
  private final List<RedHxUriPath> cooledByUriPathList;
  private final RedHxComputerDescription description;
  private final RedHxDnsHostName hostname;
  private final RedHxIndicatorLedStateEnum indicatorLed;
  private final RedHxUriPath logServicesUriPath;
  private final RedHxComputerManufacturerName manufacturerName;
  private final RedHxOperatingStatus memoryOperatingStatus;
  private final RedHxComputerProcessorModelName modelName;
  private final RedHxComputerModelNumber modelNumber;
  private final RedHxComputerPartNumber partNumber;
  private final List<RedHxUriPath> poweredByList;
  private final RedHxComputerPowerStateEnum powerState;
  private final int processorCount;
  private final RedHxOperatingStatus processorOperatingStatus;

  private final RedHxComputerSerialNumber serialNumber;
  private final List<RedHxUriPath> systemManagerUriPathList;
  private final RedHxComputerSystemTypeEnum systemType;
  private final long totalSystemMemoryGiB;
  private final UUID uuid;

  ComputerSystemImpl(RedHxActionGroup actionGroup, RedHxComputerAssetTag assetTag,
      RedHxComputerBiosVersion biosVersion, RedHxComputerBootSourceEnum bootSource,
      RedHxComputerSystemBootSourceOverrideEnabledEnum bootSourceOverride,
      RedHxComputerBootUefiTargetSourceOverride bootUefiTarget, RedHxComputerId computerId,
      RedHxComputerName computerName, RedHxOperatingStatus computerOperatingStatus,
      RedHxComputerSKU computerSku, RedHxUriPath computerSystemPath,
      RedHxComputerDescription description, RedHxDnsHostName hostname,
      RedHxIndicatorLedStateEnum indicatorLed, RedHxComputerManufacturerName manufacturerName,
      RedHxOperatingStatus memoryOperatingStatus, RedHxComputerProcessorModelName modelName,
      RedHxComputerModelNumber modelNumber, RedHxComputerPartNumber partNumber,
      RedHxComputerPowerStateEnum powerState, int processorCount,
      RedHxOperatingStatus processorOperatingStatus, RedHxComputerSerialNumber serialNumber,
      RedHxComputerSystemTypeEnum systemType, int totalSystemMemoryGiB, UUID uuid,
      List<RedHxUriPath> chassisUriPathList, List<RedHxUriPath> poweredByList,
      List<RedHxUriPath> cooledByUriPathList, List<RedHxUriPath> systemManagerUriPathList,
      RedHxUriPath logServicesUriPath) {
    this.actionGroup = actionGroup;
    this.assetTag = assetTag;
    this.biosVersion = biosVersion;
    this.bootSource = bootSource;
    this.bootSourceOverride = bootSourceOverride;
    this.bootUefiTarget = bootUefiTarget;
    this.chassisUriPathList = Collections.unmodifiableList(chassisUriPathList);
    this.computerId = computerId;
    this.computerName = computerName;
    this.computerOperatingStatus = computerOperatingStatus;
    this.computerSku = computerSku;
    this.computerSystemPath = computerSystemPath;
    this.cooledByUriPathList = Collections.unmodifiableList(cooledByUriPathList);
    this.description = description;
    this.hostname = hostname;
    this.indicatorLed = indicatorLed;
    this.logServicesUriPath = logServicesUriPath;
    this.manufacturerName = manufacturerName;
    this.memoryOperatingStatus = memoryOperatingStatus;
    this.modelName = modelName;
    this.modelNumber = modelNumber;
    this.partNumber = partNumber;
    this.powerState = powerState;
    this.poweredByList = Collections.unmodifiableList(poweredByList);
    this.processorCount = processorCount;
    this.processorOperatingStatus = processorOperatingStatus;

    this.serialNumber = serialNumber;
    this.systemManagerUriPathList = Collections.unmodifiableList(systemManagerUriPathList);
    this.systemType = systemType;
    this.totalSystemMemoryGiB = totalSystemMemoryGiB;
    this.uuid = uuid;
    /**
     *
     */
    if (computerSystemPath == null) {
      throw new IllegalArgumentException("The argument computerSystemPath may not be null.");
    }
  }

  private ComputerSystemImpl() {
    this.actionGroup = null;
    this.assetTag = null;
    this.biosVersion = null;
    this.bootSource = null;
    this.bootSourceOverride = null;
    this.bootUefiTarget = null;
    this.chassisUriPathList = null;
    this.computerId = null;
    this.computerName = null;
    this.computerOperatingStatus = null;
    this.computerSku = null;
    this.computerSystemPath = null;
    this.cooledByUriPathList = null;
    this.description = null;
    this.hostname = null;
    this.indicatorLed = null;
    this.logServicesUriPath = null;
    this.manufacturerName = null;
    this.memoryOperatingStatus = null;
    this.modelName = null;
    this.modelNumber = null;
    this.partNumber = null;
    this.powerState = null;
    this.poweredByList = null;
    this.processorCount = -1;
    this.processorOperatingStatus = null;

    this.serialNumber = null;
    this.systemManagerUriPathList = null;
    this.systemType = null;
    this.totalSystemMemoryGiB = -1;
    this.uuid = null;
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
    final ComputerSystemImpl other = (ComputerSystemImpl) obj;
    if (this.processorCount != other.processorCount) {
      return false;
    }
    if (this.totalSystemMemoryGiB != other.totalSystemMemoryGiB) {
      return false;
    }
    if (!Objects.equals(this.actionGroup, other.actionGroup)) {
      return false;
    }
    if (!Objects.equals(this.assetTag, other.assetTag)) {
      return false;
    }
    if (!Objects.equals(this.biosVersion, other.biosVersion)) {
      return false;
    }
    if (this.bootSource != other.bootSource) {
      return false;
    }
    if (this.bootSourceOverride != other.bootSourceOverride) {
      return false;
    }
    if (!Objects.equals(this.bootUefiTarget, other.bootUefiTarget)) {
      return false;
    }
    if (!Objects.equals(this.chassisUriPathList, other.chassisUriPathList)) {
      return false;
    }
    if (!Objects.equals(this.computerId, other.computerId)) {
      return false;
    }
    if (!Objects.equals(this.computerName, other.computerName)) {
      return false;
    }
    if (!Objects.equals(this.computerOperatingStatus, other.computerOperatingStatus)) {
      return false;
    }
    if (!Objects.equals(this.computerSku, other.computerSku)) {
      return false;
    }
    if (!Objects.equals(this.computerSystemPath, other.computerSystemPath)) {
      return false;
    }
    if (!Objects.equals(this.cooledByUriPathList, other.cooledByUriPathList)) {
      return false;
    }
    if (!Objects.equals(this.description, other.description)) {
      return false;
    }
    if (!Objects.equals(this.hostname, other.hostname)) {
      return false;
    }
    if (this.indicatorLed != other.indicatorLed) {
      return false;
    }
    if (!Objects.equals(this.logServicesUriPath, other.logServicesUriPath)) {
      return false;
    }
    if (!Objects.equals(this.manufacturerName, other.manufacturerName)) {
      return false;
    }
    if (!Objects.equals(this.memoryOperatingStatus, other.memoryOperatingStatus)) {
      return false;
    }
    if (!Objects.equals(this.modelName, other.modelName)) {
      return false;
    }
    if (!Objects.equals(this.modelNumber, other.modelNumber)) {
      return false;
    }
    if (!Objects.equals(this.partNumber, other.partNumber)) {
      return false;
    }
    if (!Objects.equals(this.poweredByList, other.poweredByList)) {
      return false;
    }
    if (this.powerState != other.powerState) {
      return false;
    }
    if (!Objects.equals(this.processorOperatingStatus, other.processorOperatingStatus)) {
      return false;
    }

    if (!Objects.equals(this.serialNumber, other.serialNumber)) {
      return false;
    }
    if (!Objects.equals(this.systemManagerUriPathList, other.systemManagerUriPathList)) {
      return false;
    }
    if (this.systemType != other.systemType) {
      return false;
    }
    if (!Objects.equals(this.uuid, other.uuid)) {
      return false;
    }
    return true;
  }

  @Override
  public RedHxActionGroup getActionGroup() {
    return actionGroup;
  }

  @Override
  public RedHxComputerAssetTag getAssetTag() {
    return assetTag;
  }

  @Override
  public RedHxComputerBiosVersion getBiosVersion() {
    return biosVersion;
  }

  @Override
  public RedHxComputerBootSourceEnum getBootSource() {
    return bootSource;
  }

  @Override
  public RedHxComputerSystemBootSourceOverrideEnabledEnum getBootSourceOverride() {
    return bootSourceOverride;
  }

  @Override
  public RedHxComputerBootUefiTargetSourceOverride getBootUefiTarget() {
    return bootUefiTarget;
  }

  @Override
  public List<RedHxUriPath> getChassisUriPathList() {
    return chassisUriPathList;
  }

  public RedHxOperatingStatus getComputerOperatingStatus() {
    return computerOperatingStatus;
  }

  @Override
  public RedHxUriPath getComputerSystemPath() {
    return computerSystemPath;
  }

  @Override
  public List<RedHxUriPath> getCooledByUriPathList() {
    return cooledByUriPathList;
  }

  @Override
  public RedHxComputerDescription getDescription() {
    return description;
  }

  @Override
  public RedHxDnsHostName getHostname() {
    return hostname;
  }

  @Override
  public RedHxComputerId getComputerId() {
    return computerId;
  }

  @Override
  public RedHxIndicatorLedStateEnum getIndicatorLedState() {
    return indicatorLed;
  }

  @Override
  public RedHxUriPath getLogServicesUriPath() {
    return logServicesUriPath;
  }

  @Override
  public RedHxComputerManufacturerName getManufacturerName() {
    return manufacturerName;
  }

  @Override
  public RedHxOperatingStatus getMemoryOperatingStatus() {
    return memoryOperatingStatus;
  }

  @Override
  public RedHxComputerProcessorModelName getModelName() {
    return modelName;
  }

  @Override
  public RedHxComputerModelNumber getModelNumber() {
    return modelNumber;
  }

  @Override
  public RedHxComputerName getName() {
    return computerName;
  }

  @Override
  public RedHxComputerPartNumber getPartNumber() {
    return partNumber;
  }

  @Override
  public List<RedHxUriPath> getPoweredByList() {
    return poweredByList;
  }

  @Override
  public RedHxComputerPowerStateEnum getPowerState() {
    return powerState;
  }

  @Override
  public int getProcessorCount() {
    return processorCount;
  }

  @Override
  public RedHxOperatingStatus getProcessorOperatingStatus() {
    return processorOperatingStatus;
  }

  @Override
  public RedHxComputerSerialNumber getSerialNumber() {
    return serialNumber;
  }

  @Override
  public RedHxComputerSKU getSku() {
    return computerSku;
  }

  @Override
  public List<RedHxUriPath> getSystemManagerUriPathList() {
    return systemManagerUriPathList;
  }

  @Override
  public RedHxComputerSystemTypeEnum getSystemType() {
    return systemType;
  }

  @Override
  public long getTotalSystemMemoryGigaBytes() {
    return totalSystemMemoryGiB;
  }

  @Override
  public UUID getUuid() {
    return uuid;
  }

  @Override
  public int hashCode() {
    int hash = 7;

    hash = 97 * hash + Objects.hashCode(this.assetTag);
    hash = 97 * hash + Objects.hashCode(this.computerSku);
    hash = 97 * hash + Objects.hashCode(this.modelNumber);
    hash = 97 * hash + Objects.hashCode(this.serialNumber);
    hash = 97 * hash + Objects.hashCode(this.uuid);

    return hash;
  }

  @Override
  public String toString() {
    StringBuilder toStringBuilder;
    toStringBuilder = new StringBuilder("ComputerSystemImpl{");
    toStringBuilder.append("actionGroup=").append(this.actionGroup);
    toStringBuilder.append(",assetTag=").append(this.assetTag);
    toStringBuilder.append(",biosVersion=").append(this.biosVersion);
    toStringBuilder.append(",bootSource=").append(this.bootSource);
    toStringBuilder.append(",bootSourceOverride=").append(this.bootSourceOverride);
    toStringBuilder.append(",bootUefiTarget=").append(this.bootUefiTarget);
    toStringBuilder.append(",chassisUriPathList=").append(this.chassisUriPathList);
    toStringBuilder.append(",computerId=").append(this.computerId);
    toStringBuilder.append(",computerName=").append(this.computerName);
    toStringBuilder.append(",computerOperatingStatus=").append(this.computerOperatingStatus);
    toStringBuilder.append(",computerSku=").append(this.computerSku);
    toStringBuilder.append(",computerSystemPath=").append(this.computerSystemPath);
    toStringBuilder.append(",cooledByUriPathList=").append(this.cooledByUriPathList);
    toStringBuilder.append(",description=").append(this.description);
    toStringBuilder.append(",hostname=").append(this.hostname);
    toStringBuilder.append(",indicatorLed=").append(this.indicatorLed);
    toStringBuilder.append(",logServicesUriPath=").append(this.logServicesUriPath);
    toStringBuilder.append(",manufacturerName=").append(this.manufacturerName);
    toStringBuilder.append(",memoryOperatingStatus=").append(this.memoryOperatingStatus);
    toStringBuilder.append(",modelName=").append(this.modelName);
    toStringBuilder.append(",modelNumber=").append(this.modelNumber);
    toStringBuilder.append(",partNumber=").append(this.partNumber);
    toStringBuilder.append(",poweredByList=").append(this.poweredByList);
    toStringBuilder.append(",powerState=").append(this.powerState);
    toStringBuilder.append(",processorCount=").append(this.processorCount);
    toStringBuilder.append(",processorOperatingStatus=").append(this.processorOperatingStatus);

    toStringBuilder.append(",serialNumber=").append(this.serialNumber);
    toStringBuilder.append(",systemManagerUriPathList=").append(this.systemManagerUriPathList);
    toStringBuilder.append(",systemType=").append(this.systemType);
    toStringBuilder.append(",totalSystemMemoryGiB=").append(this.totalSystemMemoryGiB);
    toStringBuilder.append(",uuid=").append(this.uuid);
    toStringBuilder.append('}');
    return toStringBuilder.toString();
  }

}
