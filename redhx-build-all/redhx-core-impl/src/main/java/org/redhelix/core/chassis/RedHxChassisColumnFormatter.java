/*
 * Copyright 2015 JBlade LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License"; you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License
 *
 */
package org.redhelix.core.chassis;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.redhelix.core.util.RedHxAbstractColumnFormatter;
import org.redhelix.core.util.RedHxUriPath;

/**
 *
 * print the contents of a Chassis to a stream.
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public final class RedHxChassisColumnFormatter extends RedHxAbstractColumnFormatter {

  /**
   * output in ALPH or SECTION format. No section headers are printed.
   *
   * @param isRowTitlePrinted
   * @param columnDelimiter
   * @param outputOrder
   * @param isPathPrinted
   */
  public RedHxChassisColumnFormatter(final boolean isRowTitlePrinted, final String columnDelimiter,
      final boolean isPathPrinted) {
    super(isRowTitlePrinted, columnDelimiter, isPathPrinted);
  }

  /**
   * output the rows in sections and optionaly print a section header.
   *
   * @param isRowTitlePrinted
   * @param columnDelimiter
   * @param isSectionHeaderPrinted
   * @param isPathPrinted
   */
  public RedHxChassisColumnFormatter(final boolean isRowTitlePrinted, final String columnDelimiter,
      final boolean isSectionHeaderPrinted, final boolean isPathPrinted) {
    super(isRowTitlePrinted, columnDelimiter, isSectionHeaderPrinted, isPathPrinted);
  }

  public void print(RedHxChassis chassis, PrintStream streamOut) {
    super.clearRows();
    switch (super.getOutputOrder()) {
      case ALPHA:
        printAlphaOrder(chassis, streamOut);

        break;
      case SECTION:
        printSectionOrder(chassis, streamOut);

        break;
      default:
        throw new IllegalArgumentException("Unknown format order " + getOutputOrder());
    }
  }

  private void printAlphaOrder(RedHxChassis chassis, PrintStream streamOut) {
    String prompt;

    prompt = "Actions";

    if (chassis.getActionGroup() != null) {
      addRow(prompt, chassis.getActionGroup().getActionSet().toString());
    } else {
      addRow(prompt);
    }

    prompt = "Asset Tag";

    if (chassis.getAssetTag() != null) {
      addRow(prompt, chassis.getAssetTag().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Chassis Description";

    if (chassis.getChassisDescription() != null) {
      addRow(prompt, chassis.getChassisDescription().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Chassis ID";

    if (chassis.getChassisId() != null) {
      addRow(prompt, chassis.getChassisId().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Chassis Name";

    if (chassis.getChassisName() != null) {
      addRow(prompt, chassis.getChassisName().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Chassis Type";

    if (chassis.getChassisType() != null) {
      addRow(prompt, chassis.getChassisType().toString());
    } else {
      addRow(prompt);
    }

    if (isPathPrinted()) {
      prompt = "Computer Systems Paths";

      if (chassis.getComputerSystemUriPathList() != null) {
        addRow(prompt, RedHxUriPath.getPathListAsString(chassis.getComputerSystemUriPathList()));
      } else {
        addRow(prompt);
      }

      prompt = "Contained By Paths";

      if (chassis.getContainedByUriPath() != null) {
        addRow(prompt, chassis.getContainedByUriPath().getValue());
      } else {
        addRow(prompt);
      }

      prompt = "Contains Paths";

      if (chassis.getContainsList() != null) {
        addRow(prompt, RedHxUriPath.getPathListAsString(chassis.getContainsList()));
      } else {
        addRow(prompt);
      }

      prompt = "Cooled By Paths";

      if (chassis.getCooledByUriPathList() != null) {
        addRow(prompt, RedHxUriPath.getPathListAsString(chassis.getCooledByUriPathList()));
      } else {
        addRow(prompt);
      }
    }

    prompt = "Led State";

    if (chassis.getIndicatorLedState() != null) {
      addRow(prompt, chassis.getIndicatorLedState().toString());
    } else {
      addRow(prompt);
    }

    if (isPathPrinted()) {
      prompt = "Log Services Path";

      if (chassis.getLogServicesUriPath() != null) {
        addRow(prompt, chassis.getLogServicesUriPath().getValue());
      } else {
        addRow(prompt);
      }
    }

    prompt = "Manufacturer Name";

    if (chassis.getManufacturerName() != null) {
      addRow(prompt, chassis.getManufacturerName().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Model Number";

    if (chassis.getModelNumber() != null) {
      addRow(prompt, chassis.getModelNumber().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Operating Health";

    if (chassis.getOperatingHealth() != null) {
      addRow(prompt, chassis.getOperatingHealth().toString());
    } else {
      addRow(prompt);
    }

    prompt = "Operating State";

    if (chassis.getOperatingState() != null) {
      addRow(prompt, chassis.getOperatingState().toString());
    } else {
      addRow(prompt);
    }

    prompt = "PartNumber";

    if (chassis.getPartNumber() != null) {
      addRow(prompt, chassis.getPartNumber().getValue());
    } else {
      addRow(prompt);
    }

    if (isPathPrinted()) {
      prompt = "Powered By";

      if (chassis.getPoweredByList() != null) {
        addRow(prompt, RedHxUriPath.getPathListAsString(chassis.getPoweredByList()));
      } else {
        addRow(prompt);
      }

      prompt = "Power Path";

      if (chassis.getPowerUriPath() != null) {
        addRow(prompt, chassis.getPowerUriPath().getValue());
      } else {
        addRow(prompt);
      }
    }

    prompt = "Serial Number";

    if (chassis.getSerialNumber() != null) {
      addRow(prompt, chassis.getSerialNumber().getValue());
    }

    addRow(prompt);
    prompt = "SKU";

    if (chassis.getSku() != null) {
      addRow(prompt, chassis.getSku().getValue());
    }

    addRow(prompt);

    if (isPathPrinted()) {
      prompt = "System Manager Paths";

      if (chassis.getSystemManagerUriPathList() != null) {
        addRow(prompt, RedHxUriPath.getPathListAsString(chassis.getSystemManagerUriPathList()));
      }

      addRow(prompt);
      prompt = "Thermal Path";

      if (chassis.getThermalUriPath() != null) {
        addRow(prompt, chassis.getThermalUriPath().getValue());
      }

      addRow(prompt);
    }

    printOutRowsAlphaOrder(streamOut);
  }

  private void printSectionOrder(RedHxChassis chassis, PrintStream streamOut) {

    Map<Integer, String> rowNumberToSectionHeaderMap = new HashMap<>();
    String prompt;

    /*
     * ID section
     */
    rowNumberToSectionHeaderMap.put(super.getRowCount(), "Chassis Identification");
    prompt = "Chassis ID";

    if (chassis.getChassisId() != null) {
      addRow(prompt, chassis.getChassisId().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Description";

    if (chassis.getChassisDescription() != null) {
      addRow(prompt, chassis.getChassisDescription().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Name";

    if (chassis.getChassisName() != null) {
      addRow(prompt, chassis.getChassisName().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Chassis Type";

    if (chassis.getChassisType() != null) {
      addRow(prompt, chassis.getChassisType().toString());
    } else {
      addRow(prompt);
    }

    prompt = "Manufacturer";

    if (chassis.getManufacturerName() != null) {
      addRow(prompt, chassis.getManufacturerName().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Model Number";

    if (chassis.getModelNumber() != null) {
      addRow(prompt, chassis.getModelNumber().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Part Number";

    if (chassis.getPartNumber() != null) {
      addRow(prompt, chassis.getPartNumber().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Asset Tag";

    if (chassis.getAssetTag() != null) {
      addRow(prompt, chassis.getAssetTag().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Serial Number";

    if (chassis.getSerialNumber() != null) {
      addRow(prompt, chassis.getSerialNumber().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "SKU";

    if (chassis.getSku() != null) {
      addRow(prompt, chassis.getSku().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Led State";

    if (chassis.getIndicatorLedState() != null) {
      addRow(prompt, chassis.getIndicatorLedState().toString());
    } else {
      addRow(prompt);
    }

    /*
     * health
     */
    rowNumberToSectionHeaderMap.put(super.getRowCount(), "Chassis Health");
    prompt = "Operating Health";

    if (chassis.getOperatingHealth() != null) {
      addRow(prompt, chassis.getOperatingHealth().toString());
    } else {
      addRow(prompt);
    }

    prompt = "Operating State";

    if (chassis.getOperatingState() != null) {
      addRow(prompt, chassis.getOperatingState().toString());
    } else {
      addRow(prompt);
    }

    /*
     * Actions
     */
    rowNumberToSectionHeaderMap.put(super.getRowCount(), "Chassis Actions");
    prompt = "Actions";

    if (chassis.getActionGroup() != null) {
      addRow(prompt, chassis.getActionGroup().getActionSet().toString());
    } else {
      addRow(prompt);
    }

    /*
     * Paths
     */
    if (isPathPrinted()) {
      rowNumberToSectionHeaderMap.put(super.getRowCount(), "Chassis Paths");
      prompt = "Computer Systems Paths";

      if (chassis.getComputerSystemUriPathList() != null) {
        addRow(prompt, RedHxUriPath.getPathListAsString(chassis.getComputerSystemUriPathList()));
      } else {
        addRow(prompt);
      }

      prompt = "Contained By Path";

      if (chassis.getContainedByUriPath() != null) {
        addRow(prompt, chassis.getContainedByUriPath().getValue());
      } else {
        addRow(prompt);
      }

      prompt = "Contains Paths";

      if (chassis.getContainsList() != null) {
        addRow(prompt, RedHxUriPath.getPathListAsString(chassis.getContainsList()));
      } else {
        addRow(prompt);
      }

      prompt = "Cooled By Paths";

      if (chassis.getCooledByUriPathList() != null) {
        addRow(prompt, RedHxUriPath.getPathListAsString(chassis.getCooledByUriPathList()));
      } else {
        addRow(prompt);
      }

      prompt = "Log Services Path";

      if (chassis.getLogServicesUriPath() != null) {
        addRow(prompt, chassis.getLogServicesUriPath().getValue());
      } else {
        addRow(prompt);
      }

      prompt = "Powered By Paths";

      if (chassis.getPoweredByList() != null) {
        addRow(prompt, RedHxUriPath.getPathListAsString(chassis.getPoweredByList()));
      } else {
        addRow(prompt);
      }

      prompt = "Power Path";

      if (chassis.getPowerUriPath() != null) {
        addRow(prompt, chassis.getPowerUriPath().getValue());
      } else {
        addRow(prompt);
      }

      prompt = "System Manager Paths";

      if (chassis.getSystemManagerUriPathList() != null) {
        addRow(prompt, RedHxUriPath.getPathListAsString(chassis.getSystemManagerUriPathList()));
      } else {
        addRow(prompt);
      }

      prompt = "Thermal Path";

      if (chassis.getThermalUriPath() != null) {
        addRow(prompt, chassis.getThermalUriPath().getValue());
      } else {
        addRow(prompt);
      }
    }

    printOutRowsWithSectionTitles(streamOut, rowNumberToSectionHeaderMap);
  }
}
