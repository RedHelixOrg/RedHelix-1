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
package org.redhelix.core.computer.system;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.redhelix.core.util.RedHxAbstractColumnFormatter;
import org.redhelix.core.util.RedHxUriPath;

/**
 * print the contents of a Computer System to a stream
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public final class RedHxComputerSystemColumnFormatter extends RedHxAbstractColumnFormatter {

  /**
   * output in ALPH format. No section headers are printed.
   *
   * @param isRowTitlePrinted
   * @param columnDelimiter
   * @param outputOrder
   * @param isPathPrinted
   */
  public RedHxComputerSystemColumnFormatter(boolean isRowTitlePrinted, String columnDelimiter,
      boolean isPathPrinted) {
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
  public RedHxComputerSystemColumnFormatter(boolean isRowTitlePrinted, String columnDelimiter,
      boolean isSectionHeaderPrinted, boolean isPathPrinted) {
    super(isRowTitlePrinted, columnDelimiter, isSectionHeaderPrinted, isPathPrinted);
  }

  public void print(RedHxComputerSystem computerSystem, PrintStream streamOut) {
    super.clearRows();
    switch (super.getOutputOrder()) {
      case ALPHA:
        printAlphaOrder(computerSystem, streamOut);

        break;
      case SECTION:
        printSectionOrder(computerSystem, streamOut);

        break;
      default:
        throw new IllegalArgumentException("Unknown format order " + getOutputOrder());
    }
  }

  private void createRows(RedHxComputerSystem computerSystem,
      Map<Integer, String> rowNumberToSectionHeaderMap) {
    String prompt;

    /*
     * ID section
     */
    rowNumberToSectionHeaderMap.put(super.getRowCount(), "Computer Identification");

    /**
     * The URI Path always is present and not null.
     */
    prompt = "URI Path";
    addRow(prompt, computerSystem.getComputerSystemPath().getValue());

    /*
     *
     */
    prompt = "Computer ID";

    if (computerSystem.getComputerId() != null) {
      addRow(prompt, computerSystem.getComputerId().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Description";

    if (computerSystem.getDescription() != null) {
      addRow(prompt, computerSystem.getDescription().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Name";

    if (computerSystem.getName() == null) {
      addRow(prompt, computerSystem.getName().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Computer Type";

    if (computerSystem.getSystemType() != null) {
      addRow(prompt, computerSystem.getSystemType().toString());
    } else {
      addRow(prompt);
    }

    prompt = "Manufacturer";

    if (computerSystem.getManufacturerName() != null) {
      addRow(prompt, computerSystem.getManufacturerName().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Model Number";

    if (computerSystem.getModelNumber() != null) {
      addRow(prompt, computerSystem.getModelNumber().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Part Number";

    if (computerSystem.getPartNumber() != null) {
      addRow(prompt, computerSystem.getPartNumber().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Asset Tag";

    if (computerSystem.getAssetTag() != null) {
      addRow(prompt, computerSystem.getAssetTag().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Serial Number";

    if (computerSystem.getSerialNumber() != null) {
      addRow(prompt, computerSystem.getSerialNumber().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "SKU";

    if (computerSystem.getSku() != null) {
      addRow(prompt, computerSystem.getSku().getValue());
    } else {
      addRow(prompt);
    }

    prompt = "Led State";

    if (computerSystem.getIndicatorLedState() != null) {
      addRow(prompt, computerSystem.getIndicatorLedState().toString());
    } else {
      addRow(prompt);
    }

    prompt = "BIOS Version";

    if (computerSystem.getBiosVersion() != null) {
      addRow(prompt, computerSystem.getBiosVersion().getValue());
    } else {
      addRow(prompt);
    }

    /**
     * boot
     */
    rowNumberToSectionHeaderMap.put(getRowCount(), "Computer Boot");
    prompt = "Boot Source";

    if (computerSystem.getBootSource() != null) {
      addRow(prompt, computerSystem.getBootSource().toString());
    } else {
      addRow(prompt);
    }

    prompt = "Boot Source Override";

    if (computerSystem.getBootSourceOverride() != null) {
      addRow(prompt, computerSystem.getBootSourceOverride().toString());
    } else {
      addRow(prompt);
    }

    prompt = "Boot UEFI Target";

    if (computerSystem.getBootUefiTarget() != null) {
      addRow(prompt, computerSystem.getBootUefiTarget().toString());
    } else {
      addRow(prompt);
    }

    /**
     * memory
     */
    rowNumberToSectionHeaderMap.put(super.getRowCount(), "Computer Memory");
    prompt = "Memory Status";

    if (computerSystem.getMemoryOperatingStatus() != null) {
      addRow(prompt, computerSystem.getMemoryOperatingStatus().toString());
    } else {
      addRow(prompt);
    }

    prompt = "Memory Size";

    if (computerSystem.getTotalSystemMemoryGigaBytes() >= 0) {
      addRow(prompt, computerSystem.getTotalSystemMemoryGigaBytes() + " Gig");
    } else {
      addRow(prompt);
    }

    /**
     * Processor
     */
    rowNumberToSectionHeaderMap.put(super.getRowCount(), "Computer Processor");
    prompt = "Processor count";

    if (computerSystem.getProcessorCount() >= 0) {
      addRow(prompt, computerSystem.getProcessorCount() + " ");
    } else {
      addRow(prompt);
    }

    prompt = "Model";

    if (computerSystem.getModelName() != null) {
      addRow(prompt, computerSystem.getModelName() + " ");
    } else {
      addRow(prompt);
    }

    prompt = "Status";

    if (computerSystem.getProcessorOperatingStatus() != null) {
      addRow(prompt, computerSystem.getProcessorOperatingStatus() + " ");
    } else {
      addRow(prompt);
    }

    /*
     * Paths
     */
    if (isPathPrinted()) {
      rowNumberToSectionHeaderMap.put(super.getRowCount(), "Computer Paths");
      prompt = "Chassis Paths";

      if (computerSystem.getChassisUriPathList() != null) {
        addRow(prompt, RedHxUriPath.getPathListAsString(computerSystem.getChassisUriPathList()));
      } else {
        addRow(prompt);
      }

      prompt = "Cooled By Paths";

      if (computerSystem.getCooledByUriPathList() != null) {
        addRow(prompt, RedHxUriPath.getPathListAsString(computerSystem.getCooledByUriPathList()));
      } else {
        addRow(prompt);
      }

      prompt = "Log Services Path";

      if (computerSystem.getLogServicesUriPath() != null) {
        addRow(prompt, computerSystem.getLogServicesUriPath().getValue());
      } else {
        addRow(prompt);
      }

      prompt = "Powered By Paths";

      if (computerSystem.getPoweredByList() != null) {
        addRow(prompt, RedHxUriPath.getPathListAsString(computerSystem.getPoweredByList()));
      } else {
        addRow(prompt);
      }

      prompt = "System Manager Paths";

      if (computerSystem.getSystemManagerUriPathList() != null) {
        addRow(prompt,
            RedHxUriPath.getPathListAsString(computerSystem.getSystemManagerUriPathList()));
      } else {
        addRow(prompt);
      }
    }
  }

  private void printAlphaOrder(RedHxComputerSystem computerSystem, PrintStream streamOut) {

    /**
     * create the map but it will be ignored when actually printed out.
     */
    Map<Integer, String> rowNumberToSectionHeaderMap = new HashMap<>();

    createRows(computerSystem, rowNumberToSectionHeaderMap);

    /**
     * calling this print method sorts the output in Alpha order.
     */
    printOutRowsAlphaOrder(streamOut);
  }

  private void printSectionOrder(RedHxComputerSystem computerSystem, PrintStream streamOut) {
    Map<Integer, String> rowNumberToSectionHeaderMap = new HashMap<>();

    createRows(computerSystem, rowNumberToSectionHeaderMap);
    printOutRowsWithSectionTitles(streamOut, rowNumberToSectionHeaderMap);
  }
}
