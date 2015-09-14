/*
 * Copyright 2015 JBlade LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the License); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an AS IS BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and limitations under
 * the License
 */
package org.redhelix.core.computer.system.boot;

/**
 *
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public enum RedHxComputerBootSourceEnum {
  BIOS_SETUP("BiosSetup", "Boot to the BIOS Setup Utility"), CD("Cd",
      "Boot from the CD/DVD disc"), DIAGS("Diags",
          "Boot the manufacturer's Diagnostics program"), FLOPPY("Floppy",
              "Boot from the floppy disk drive"), HDD("Hdd", "Boot from a hard drive"), NONE("None",
                  "Boot from the normal boot device"), PXE("Pxe",
                      "Boot from the Pre-Boot EXecution (PXE) environment"), UEFI_SHELL("UefiShell",
                          "Boot to the UEFI Shell"), UEFI_TARGET("UefiTarget",
                              "Boot to the UEFI Device specified in the UefiTargetBootSourceOverride property"), USB(
                                  "Usb",
                                  "Boot from a USB device as specified by the system BIOS"), UTILITIES(
                                      "Utilities", "Boot the manufacturer's Utilities program(s)");

  private final String desc;
  private final String jsonKeyword;

  private RedHxComputerBootSourceEnum(String jsonKeyword, String desc) {
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
  public static RedHxComputerBootSourceEnum getInstance(String jsonKeyword) {
    RedHxComputerBootSourceEnum retVal = null;

    for (RedHxComputerBootSourceEnum tmp : values()) {
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
