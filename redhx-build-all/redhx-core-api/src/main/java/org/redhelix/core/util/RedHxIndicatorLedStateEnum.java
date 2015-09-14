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
package org.redhelix.core.util;

/**
 *
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public enum RedHxIndicatorLedStateEnum {
  BLINKING("Blinking", "The Indicator LED is blinking."), LIT("Lit", "The Indicator LED is lit."), OFF(
      "Off", "The Indicator LED is off."), UNKNOWN("Unknown",
      "The state of the Indicator LED cannot be determined.");

  private final String desc;
  private final String jsonKeyword;

  private RedHxIndicatorLedStateEnum(String jsonKeyword, String desc) {
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
  public static RedHxIndicatorLedStateEnum getInstance(String jsonKeyword) {
    RedHxIndicatorLedStateEnum retVal = null;

    for (RedHxIndicatorLedStateEnum tmp : values()) {
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
