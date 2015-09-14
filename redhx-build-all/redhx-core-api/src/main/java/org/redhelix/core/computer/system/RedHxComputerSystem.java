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

import java.util.List;
import java.util.UUID;
import org.redhelix.core.action.RedHxActionGroup;
import org.redhelix.core.computer.system.boot.RedHxComputerBootProperties;
import org.redhelix.core.computer.system.id.RedHxComputerAssetTag;
import org.redhelix.core.computer.system.id.RedHxComputerBiosVersion;
import org.redhelix.core.computer.system.id.RedHxComputerDescription;
import org.redhelix.core.computer.system.id.RedHxComputerId;
import org.redhelix.core.computer.system.id.RedHxComputerManufacturerName;
import org.redhelix.core.computer.system.id.RedHxComputerModelNumber;
import org.redhelix.core.computer.system.id.RedHxComputerName;
import org.redhelix.core.computer.system.id.RedHxComputerPartNumber;
import org.redhelix.core.computer.system.id.RedHxComputerPowerStateEnum;
import org.redhelix.core.computer.system.id.RedHxComputerSKU;
import org.redhelix.core.computer.system.id.RedHxComputerSerialNumber;
import org.redhelix.core.computer.system.id.RedHxComputerSystemTypeEnum;
import org.redhelix.core.util.RedHxDnsHostName;
import org.redhelix.core.util.RedHxIndicatorLedStateEnum;
import org.redhelix.core.util.RedHxOperatingStatus;
import org.redhelix.core.util.RedHxUriPath;

/**
 *
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public interface RedHxComputerSystem
    extends RedHxComputerProcessorSummary, RedHxComputerMemorySummary, RedHxComputerBootProperties {

  RedHxUriPath getComputerSystemPath();

  RedHxActionGroup getActionGroup();

  RedHxComputerAssetTag getAssetTag();

  RedHxComputerBiosVersion getBiosVersion();

  List<RedHxUriPath> getChassisUriPathList();

  RedHxOperatingStatus getComputerOperatingStatus();

  List<RedHxUriPath> getCooledByUriPathList();

  RedHxComputerDescription getDescription();

  /**
   * @return the hostname
   */
  RedHxDnsHostName getHostname();

  RedHxComputerId getComputerId();

  /**
   * @return the indicatorLed
   */
  RedHxIndicatorLedStateEnum getIndicatorLedState();

  RedHxUriPath getLogServicesUriPath();

  RedHxComputerManufacturerName getManufacturerName();

  RedHxComputerModelNumber getModelNumber();

  RedHxComputerName getName();

  RedHxComputerPartNumber getPartNumber();

  List<RedHxUriPath> getPoweredByList();

  RedHxComputerPowerStateEnum getPowerState();

  RedHxComputerSerialNumber getSerialNumber();

  RedHxComputerSKU getSku();

  List<RedHxUriPath> getSystemManagerUriPathList();

  /**
   * @return the systemType
   */
  RedHxComputerSystemTypeEnum getSystemType();

  /**
   * @return the uuid
   */
  UUID getUuid();
}
