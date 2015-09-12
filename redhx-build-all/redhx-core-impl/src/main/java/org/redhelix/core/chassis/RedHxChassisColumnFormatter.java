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
package org.redhelix.core.chassis;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public final class RedHxChassisColumnFormatter
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
    public RedHxChassisColumnFormatter(final boolean isRowTitlePrinted,
                                       final String columnDelimiter,
                                       final PrintOrder outputOrder,
                                       final boolean isPathPrinted)
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
    public RedHxChassisColumnFormatter(final boolean isRowTitlePrinted,
                                       final String columnDelimiter,
                                       final boolean isSectionHeaderPrinted,
                                       final boolean isPathPrinted)
    {
        super(isRowTitlePrinted,
              columnDelimiter,
              isSectionHeaderPrinted,
              isPathPrinted);
    }

    public void print(RedHxChassis chassis,
                      PrintStream streamOut)
    {
        switch (super.getOutputOrder())
        {
            case ALPHA:
                printAlphaOrder(chassis,
                                streamOut);

                break;
            case SECTION:
                printSectionOrder(chassis,
                                  streamOut);

                break;
            default:
                throw new IllegalArgumentException("Unknown format order " + getOutputOrder());
        }
    }

    private void printAlphaOrder(RedHxChassis chassis,
                                 PrintStream streamOut)
    {
        List<String> promptList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();

        promptList.add("Actions");

        if (chassis.getActionGroup() != null)
        {
            valueList.add(chassis.getActionGroup().getActionSet().toString());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Asset Tag");

        if (chassis.getAssetTag() != null)
        {
            valueList.add(chassis.getAssetTag().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Chassis Description");

        if (chassis.getChassisDescription() != null)
        {
            valueList.add(chassis.getChassisDescription().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Chassis ID");

        if (chassis.getChassisId() != null)
        {
            valueList.add(chassis.getChassisId().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Chassis Name");

        if (chassis.getChassisName() != null)
        {
            valueList.add(chassis.getChassisName().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Chassis Type");

        if (chassis.getChassisType() != null)
        {
            valueList.add(chassis.getChassisType().toString());
        }
        else
        {
            valueList.add("");
        }

        if (isPathPrinted())
        {
            promptList.add("Computer Systems Paths");

            if (chassis.getComputerSystemUriPathList() != null)
            {
                valueList.add(RedHxUriPath.getPathListAsString(chassis.getComputerSystemUriPathList()));
            }
            else
            {
                valueList.add("");
            }

            promptList.add("Contained By Paths");

            if (chassis.getContainedByUriPath() != null)
            {
                valueList.add(chassis.getContainedByUriPath().getValue());
            }
            else
            {
                valueList.add("");
            }

            promptList.add("Contains Paths");

            if (chassis.getContainsList() != null)
            {
                valueList.add(RedHxUriPath.getPathListAsString(chassis.getContainsList()));
            }
            else
            {
                valueList.add("");
            }

            promptList.add("Cooled By Paths");

            if (chassis.getCooledByUriPathList() != null)
            {
                valueList.add(RedHxUriPath.getPathListAsString(chassis.getCooledByUriPathList()));
            }
            else
            {
                valueList.add("");
            }
        }

        promptList.add("Led State");

        if (chassis.getIndicatorLedState() != null)
        {
            valueList.add(chassis.getIndicatorLedState().toString());
        }
        else
        {
            valueList.add("");
        }

        if (isPathPrinted())
        {
            promptList.add("Log Services Path");

            if (chassis.getLogServicesUriPath() != null)
            {
                valueList.add(chassis.getLogServicesUriPath().getValue());
            }
            else
            {
                valueList.add("");
            }
        }

        promptList.add("Manufacturer Name");

        if (chassis.getManufacturerName() != null)
        {
            valueList.add(chassis.getManufacturerName().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Model Number");

        if (chassis.getModelNumber() != null)
        {
            valueList.add(chassis.getModelNumber().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Operating Health");

        if (chassis.getOperatingHealth() != null)
        {
            valueList.add(chassis.getOperatingHealth().toString());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Operating State");

        if (chassis.getOperatingState() != null)
        {
            valueList.add(chassis.getOperatingState().toString());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("PartNumber");

        if (chassis.getPartNumber() != null)
        {
            valueList.add(chassis.getPartNumber().getValue());
        }
        else
        {
            valueList.add("");
        }

        if (isPathPrinted())
        {
            promptList.add("Powered By");

            if (chassis.getPoweredByList() != null)
            {
                valueList.add(RedHxUriPath.getPathListAsString(chassis.getPoweredByList()));
            }
            else
            {
                valueList.add("");
            }

            promptList.add("Power Path");

            if (chassis.getPowerUriPath() != null)
            {
                valueList.add(chassis.getPowerUriPath().getValue());
            }
            else
            {
                valueList.add("");
            }
        }

        promptList.add("Serial Number");

        if (chassis.getSerialNumber() != null)
        {
            valueList.add(chassis.getSerialNumber().getValue());
        }

        valueList.add("");
        promptList.add("SKU");

        if (chassis.getSku() != null)
        {
            valueList.add(chassis.getSku().getValue());
        }

        valueList.add("");

        if (isPathPrinted())
        {
            promptList.add("System Manager Paths");

            if (chassis.getSystemManagerUriPathList() != null)
            {
                valueList.add(RedHxUriPath.getPathListAsString(chassis.getSystemManagerUriPathList()));
            }

            valueList.add("");
            promptList.add("Thermal Path");

            if (chassis.getThermalUriPath() != null)
            {
                valueList.add(chassis.getThermalUriPath().getValue());
            }

            valueList.add("");
        }

        printOutRows(streamOut,
                     promptList,
                     valueList);
    }

    private void printSectionOrder(RedHxChassis chassis,
                                   PrintStream streamOut)
    {
        StringBuilder sb = new StringBuilder();
        List<String> promptList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();
        Map<Integer, String> rowNumberToSectionHeaderMap = new HashMap<>();

        /*
         * ID section
         */
        int lineCount = 0;

        rowNumberToSectionHeaderMap.put(lineCount,
                                        "Chassis Identification");
        promptList.add("Chassis ID");

        if (chassis.getChassisId() != null)
        {
            valueList.add(chassis.getChassisId().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Description");

        if (chassis.getChassisDescription() != null)
        {
            valueList.add(chassis.getChassisDescription().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Name");

        if (chassis.getChassisName() != null)
        {
            valueList.add(chassis.getChassisName().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Chassis Type");

        if (chassis.getChassisType() != null)
        {
            valueList.add(chassis.getChassisType().toString());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Manufacturer");

        if (chassis.getManufacturerName() != null)
        {
            valueList.add(chassis.getManufacturerName().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Model Number");

        if (chassis.getModelNumber() != null)
        {
            valueList.add(chassis.getModelNumber().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Part Number");

        if (chassis.getPartNumber() != null)
        {
            valueList.add(chassis.getPartNumber().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Asset Tag");

        if (chassis.getAssetTag() != null)
        {
            valueList.add(chassis.getAssetTag().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Serial Number");

        if (chassis.getSerialNumber() != null)
        {
            valueList.add(chassis.getSerialNumber().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("SKU");

        if (chassis.getSku() != null)
        {
            valueList.add(chassis.getSku().getValue());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Led State");

        if (chassis.getIndicatorLedState() != null)
        {
            valueList.add(chassis.getIndicatorLedState().toString());
        }
        else
        {
            valueList.add("");
        }

        /*
         * health
         */
        lineCount = promptList.size();
        rowNumberToSectionHeaderMap.put(lineCount,
                                        "Chassis Health");
        promptList.add("Operating Health");

        if (chassis.getOperatingHealth() != null)
        {
            valueList.add(chassis.getOperatingHealth().toString());
        }
        else
        {
            valueList.add("");
        }

        promptList.add("Operating State");

        if (chassis.getOperatingState() != null)
        {
            valueList.add(chassis.getOperatingState().toString());
        }
        else
        {
            valueList.add("");
        }

        /*
         * Actions
         */
        lineCount = promptList.size();
        rowNumberToSectionHeaderMap.put(lineCount,
                                        "Chassis Actions");
        promptList.add("Actions");

        if (chassis.getActionGroup() != null)
        {
            valueList.add(chassis.getActionGroup().getActionSet().toString());
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
                                            "Chassis Paths");
            promptList.add("Computer Systems Paths");

            if (chassis.getComputerSystemUriPathList() != null)
            {
                valueList.add(RedHxUriPath.getPathListAsString(chassis.getComputerSystemUriPathList()));
            }
            else
            {
                valueList.add("");
            }

            promptList.add("Contained By Path");

            if (chassis.getContainedByUriPath() != null)
            {
                valueList.add(chassis.getContainedByUriPath().getValue());
            }
            else
            {
                valueList.add("");
            }

            promptList.add("Contains Paths");

            if (chassis.getContainsList() != null)
            {
                valueList.add(RedHxUriPath.getPathListAsString(chassis.getContainsList()));
            }
            else
            {
                valueList.add("");
            }

            promptList.add("Cooled By Paths");

            if (chassis.getCooledByUriPathList() != null)
            {
                valueList.add(RedHxUriPath.getPathListAsString(chassis.getCooledByUriPathList()));
            }
            else
            {
                valueList.add("");
            }

            promptList.add("Log Services Path");

            if (chassis.getLogServicesUriPath() != null)
            {
                valueList.add(chassis.getLogServicesUriPath().getValue());
            }
            else
            {
                valueList.add("");
            }

            promptList.add("Powered By Paths");

            if (chassis.getPoweredByList() != null)
            {
                valueList.add(RedHxUriPath.getPathListAsString(chassis.getPoweredByList()));
            }
            else
            {
                valueList.add("");
            }

            promptList.add("Power Path");

            if (chassis.getPowerUriPath() != null)
            {
                valueList.add(chassis.getPowerUriPath().getValue());
            }
            else
            {
                valueList.add("");
            }

            promptList.add("System Manager Paths");

            if (chassis.getSystemManagerUriPathList() != null)
            {
                valueList.add(RedHxUriPath.getPathListAsString(chassis.getSystemManagerUriPathList()));
            }
            else
            {
                valueList.add("");
            }

            promptList.add("Thermal Path");

            if (chassis.getThermalUriPath() != null)
            {
                valueList.add(chassis.getThermalUriPath().getValue());
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
