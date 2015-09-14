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
package org.redhelix.core.chassis;

import java.util.List;
import org.redhelix.core.action.RedHxActionGroup;
import org.redhelix.core.chassis.id.RedHxChassisAssetTag;
import org.redhelix.core.chassis.id.RedHxChassisDescription;
import org.redhelix.core.chassis.id.RedHxChassisId;
import org.redhelix.core.chassis.id.RedHxChassisManufacturerName;
import org.redhelix.core.chassis.id.RedHxChassisModelNumber;
import org.redhelix.core.chassis.id.RedHxChassisName;
import org.redhelix.core.chassis.id.RedHxChassisPartNumber;
import org.redhelix.core.chassis.id.RedHxChassisSKU;
import org.redhelix.core.chassis.id.RedHxChassisSerialNumber;
import org.redhelix.core.util.RedHxIndicatorLedStateEnum;
import org.redhelix.core.util.RedHxOperatingHealthEnum;
import org.redhelix.core.util.RedHxOperatingStateEnum;
import org.redhelix.core.util.RedHxUriPath;

/**
 *
 * A chassis that may contain other chassis or computer systems.
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 */
public interface RedHxChassis {

  /**
   * get all actions that can be preformed on the shelf.
   *
   * @return a null if no actions can be preformed otherwise the actions.
   */
  RedHxActionGroup getActionGroup();

  /**
   * get the asset tag assigned to the shelf.
   *
   * @return a null if no asset tag has been assigned otherwise the asset tag.
   */
  RedHxChassisAssetTag getAssetTag();

  RedHxChassisDescription getChassisDescription();

  RedHxChassisId getChassisId();

  RedHxChassisName getChassisName();

  RedHxChassisTypeEnum getChassisType();

  List<RedHxUriPath> getComputerSystemUriPathList();

  RedHxUriPath getContainedByUriPath();

  List<RedHxUriPath> getContainsList();

  List<RedHxUriPath> getCooledByUriPathList();

  RedHxIndicatorLedStateEnum getIndicatorLedState();

  RedHxUriPath getLogServicesUriPath();

  RedHxChassisManufacturerName getManufacturerName();

  RedHxChassisModelNumber getModelNumber();

  RedHxOperatingHealthEnum getOperatingHealth();

  RedHxOperatingStateEnum getOperatingState();

  RedHxChassisPartNumber getPartNumber();

  List<RedHxUriPath> getPoweredByList();

  RedHxUriPath getPowerUriPath();

  RedHxChassisSerialNumber getSerialNumber();

  RedHxChassisSKU getSku();

  List<RedHxUriPath> getSystemManagerUriPathList();

  RedHxUriPath getThermalUriPath();
}
