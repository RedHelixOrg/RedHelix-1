/*
 * Copyright 2015 JBlade LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 *
 */
package org.redhelix.core.computer.system;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public final class RedHxComputerSystemColumnFormatter
        extends RedHxAbstractColumnFormatter
{

    /**
     * output in ALPH or SECTION format. No section headers are printed.
     *
     * @param isRowTitlePrinted
     * @param columnDelimiter
     * @param outputOrder
     * @param isPathPrinted
     */
    public RedHxComputerSystemColumnFormatter(boolean isRowTitlePrinted,
                                              String columnDelimiter,
                                              PrintOrder outputOrder,
                                              boolean isPathPrinted)
    {
        super(isRowTitlePrinted,
              columnDelimiter,
              outputOrder,
              isPathPrinted);
    }

    /**
     * output the rows in sections and optionaly print a section header.
     *
     * @param isRowTitlePrinted
     * @param columnDelimiter
     * @param isSectionHeaderPrinted
     * @param isPathPrinted
     */
    public RedHxComputerSystemColumnFormatter(boolean isRowTitlePrinted,
                                              String columnDelimiter,
                                              boolean isSectionHeaderPrinted,
                                              boolean isPathPrinted)
    {
        super(isRowTitlePrinted,
              columnDelimiter,
              isSectionHeaderPrinted,
              isPathPrinted);
    }

    public void print(RedHxComputerSystem computerSystem,
                      PrintStream streamOut)
    {
        switch (super.getOutputOrder())
        {
            case ALPHA:
                printAlphaOrder(computerSystem,
                                streamOut);

                break;
            case SECTION:
                printSectionOrder(computerSystem,
                                  streamOut);

                break;
            default:
                throw new IllegalArgumentException("Unknown format order " + getOutputOrder());
        }
    }

    private void printAlphaOrder(RedHxComputerSystem computerSystem,
                                 PrintStream streamOut)
    {
        List<String> promptList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();
    }

    private void printSectionOrder(RedHxComputerSystem computerSystem,
                                   PrintStream streamOut)
    {
        StringBuilder sb = new StringBuilder();
        List<String> promptList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();
        Map<Integer, String> rowNumberToSectionHeaderMap = new HashMap<>();

        /*
         * ID section
         */
        int lineCount = promptList.size();

        rowNumberToSectionHeaderMap.put(lineCount,
                                        "Computer Identification");

        /**
         * The URI Path always is present and not null.
         */
        promptList.add("URI Path");
        valueList.add(computerSystem.getComputerSystemPath().getValue());

        /*
         *
         */
        promptList.add("Computer ID");

        if (computerSystem.getComputerId() != null)
        {
            valueList.add(computerSystem.getComputerId().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Description");

        if (computerSystem.getDescription() != null)
        {
            valueList.add(computerSystem.getDescription().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Name");

        if (computerSystem.getName() == null)
        {
            valueList.add(computerSystem.getName().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Computer Type");

        if (computerSystem.getSystemType() != null)
        {
            valueList.add(computerSystem.getSystemType().toString());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Manufacturer");

        if (computerSystem.getManufacturerName() != null)
        {
            valueList.add(computerSystem.getManufacturerName().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Model Number");

        if (computerSystem.getModelNumber() != null)
        {
            valueList.add(computerSystem.getModelNumber().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Part Number");

        if (computerSystem.getPartNumber() != null)
        {
            valueList.add(computerSystem.getPartNumber().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Asset Tag");

        if (computerSystem.getAssetTag() != null)
        {
            valueList.add(computerSystem.getAssetTag().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Serial Number");

        if (computerSystem.getSerialNumber() != null)
        {
            valueList.add(computerSystem.getSerialNumber().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("SKU");

        if (computerSystem.getSku() != null)
        {
            valueList.add(computerSystem.getSku().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Led State");

        if (computerSystem.getIndicatorLedState() != null)
        {
            valueList.add(computerSystem.getIndicatorLedState().toString());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("BIOS Version");

        if (computerSystem.getBiosVersion() != null)
        {
            valueList.add(computerSystem.getBiosVersion().getValue());
        }
        else
        {
            valueList.add("");
        }

        /**
         * boot
         */
        lineCount = promptList.size();
        rowNumberToSectionHeaderMap.put(lineCount,
                                        "Computer Boot");
        promptList.add("Boot Source");

        if (computerSystem.getBootSource() != null)
        {
            valueList.add(computerSystem.getBootSource().toString());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Boot Source Override");

        if (computerSystem.getBootSourceOverride() != null)
        {
            valueList.add(computerSystem.getBootSourceOverride().toString());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Boot UEFI Target");

        if (computerSystem.getBootUefiTarget() != null)
        {
            valueList.add(computerSystem.getBootUefiTarget().toString());
        }
        else
        {
            valueList.add("");
        }

        /**
         * memory
         */
        lineCount = promptList.size();
        rowNumberToSectionHeaderMap.put(lineCount,
                                        "Computer Memory");
        promptList.add("Memory Status");

        if (computerSystem.getMemoryOperatingStatus() != null)
        {
            valueList.add(computerSystem.getMemoryOperatingStatus().toString());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Memory Size");

        if (computerSystem.getTotalSystemMemoryGigaBytes() >= 0)
        {
            valueList.add(computerSystem.getTotalSystemMemoryGigaBytes() + " Gig");
        }
        else
        {
            valueList.add("");
        }

        /**
         * Processor
         */
        lineCount = promptList.size();
        rowNumberToSectionHeaderMap.put(lineCount,
                                        "Computer Processor");
        promptList.add("Processor count");

        if (computerSystem.getProcessorCount() >= 0)
        {
            valueList.add(computerSystem.getProcessorCount() + " ");
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Model");

        if (computerSystem.getModelName() != null)
        {
            valueList.add(computerSystem.getModelName() + " ");
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Status");

        if (computerSystem.getProcessorOperatingStatus() != null)
        {
            valueList.add(computerSystem.getProcessorOperatingStatus() + " ");
        }
        else
        {
            valueList.add("");
        }

        /*
         * Paths
         */
        if (isPathPrinted())
        {
            lineCount = promptList.size();
            rowNumberToSectionHeaderMap.put(lineCount,
                                            "Computer Paths");
            promptList.add("Chassis Paths");

            if (computerSystem.getChassisUriPathList() != null)
            {
                valueList.add(RedHxUriPath.getPathListAsString(computerSystem.getChassisUriPathList()));
            }
            else
            {
                valueList.add("");
            }

            promptList.add("Cooled By Paths");

            if (computerSystem.getCooledByUriPathList() != null)
            {
                valueList.add(RedHxUriPath.getPathListAsString(computerSystem.getCooledByUriPathList()));
            }
            else
            {
                valueList.add("");
            }

            promptList.add("Log Services Path");

            if (computerSystem.getLogServicesUriPath() != null)
            {
                valueList.add(computerSystem.getLogServicesUriPath().getValue());
            }
            else
            {
                valueList.add("");
            }

            promptList.add("Powered By Paths");

            if (computerSystem.getPoweredByList() != null)
            {
                valueList.add(RedHxUriPath.getPathListAsString(computerSystem.getPoweredByList()));
            }
            else
            {
                valueList.add("");
            }

            promptList.add("System Manager Paths");

            if (computerSystem.getSystemManagerUriPathList() != null)
            {
                valueList.add(RedHxUriPath.getPathListAsString(computerSystem.getSystemManagerUriPathList()));
            }
            else
            {
                valueList.add("");
            }
        }

        printOutRowsWithSectionTitles(streamOut,
                                      promptList,
                                      valueList,
                                      rowNumberToSectionHeaderMap);
    }
}
