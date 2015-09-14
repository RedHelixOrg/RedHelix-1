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
package org.redhelix.core.computer.system.boot;

import static org.redhelix.core.computer.system.boot.RedHxComputerSystemBootSourceOverrideEnabledEnum.values;

/**
 *
 *
 *
 * @since RedHelix Version 0.1 It will be replaced when checked in to the master branch
 * @author Hank Bruning
 *
 */
public enum RedHxComputerSystemBootSourceOverrideEnabledEnum {
  CONTINUOUS("Continuous",
      "The system will boot to the target specified in the BootSourceOverrideTarget until this property is set to Disabled."), DISABLED(
          "Disabled", "The system will boot as normal"), ONCE("Once",
              "ON ITS NEXT BOOT CYCLE, THE SYSTEM WILL BOOT (ONE TIME) TO THE BOOT SOURCE OVERRIDE TARGET.");

  private final String desc;
  private final String jsonKeyword;

  private RedHxComputerSystemBootSourceOverrideEnabledEnum(String jsonKeyword, String desc) {
    this.jsonKeyword = jsonKeyword;
    this.desc = desc;
  }

  public String getDescription() {
    return desc;
  }

  /**
   * convert from the RedFish JSON keyword into the enumeration.
   *
   * @param jsonKeyword the keyword to lookup.
   * @return null if the argument is not a valid Redfish JSON keyword otherwise the enumeration.
   */
  public static RedHxComputerSystemBootSourceOverrideEnabledEnum getInstance(String jsonKeyword) {
    RedHxComputerSystemBootSourceOverrideEnabledEnum retVal = null;

    for (RedHxComputerSystemBootSourceOverrideEnabledEnum tmp : values()) {
      if (tmp.jsonKeyword.equals(jsonKeyword)) {
        retVal = tmp;

        break;
      }
    }

    return retVal;
  }

  /**
   * get the JSON keyword that identifies the enum.
   *
   * @return a keyword. This has zero spaces. A null is not returned.
   */
  public String getJsonKeyword() {
    return jsonKeyword;
  }
}
