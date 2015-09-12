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
package org.redhelix.core.util;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.redhelix.core.chassis.RedHxChassisColumnFormatter;

/**
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public abstract class RedHxAbstractColumnFormatter
        implements RedHxColumnOutputFormatter
{

    protected static final int COLUMN_CHAR_COUNT_PAD = 2;
    private final String columnDelimiter;
    private final boolean isRowPromptPrinted;
    private final RedHxChassisColumnFormatter.PrintOrder outputOrder;
    private boolean isPathPrinted;
    private boolean isSectionHeaderPrinted;
    private final List<PromptValuePair> rowList;

    /**
     * output in ALPH or SECTION format. No section headers are printed.
     *
     * @param isRowTitlePrinted
     * @param columnDelimiter
     * @param outputOrder
     * @param isPathPrinted
     */
    public RedHxAbstractColumnFormatter(final boolean isRowTitlePrinted,
                                        final String columnDelimiter,
                                        final boolean isPathPrinted)
    {
        this.isRowPromptPrinted = isRowTitlePrinted;
        this.columnDelimiter = columnDelimiter;
        this.outputOrder = RedHxChassisColumnFormatter.PrintOrder.ALPHA;
        this.isSectionHeaderPrinted = false;
        this.isPathPrinted = isPathPrinted;
        this.rowList = new ArrayList<>();
    }

    /**
     * output the rows in sections and optionaly print a section header.
     *
     * @param isRowTitlePrinted
     * @param columnDelimiter
     * @param isSectionHeaderPrinted
     */
    public RedHxAbstractColumnFormatter(final boolean isRowTitlePrinted,
                                        String columnDelimiter,
                                        boolean isSectionHeaderPrinted,
                                        boolean isPathPrinted)
    {
        this.isRowPromptPrinted = isRowTitlePrinted;
        this.columnDelimiter = columnDelimiter;
        this.outputOrder = RedHxChassisColumnFormatter.PrintOrder.SECTION;
        this.isSectionHeaderPrinted = isSectionHeaderPrinted;
        this.isPathPrinted = isPathPrinted;
        this.rowList = new ArrayList<>();
    }

    private RedHxAbstractColumnFormatter()
    {
        this.isRowPromptPrinted = false;
        this.columnDelimiter = null;
        this.outputOrder = null;
        this.isSectionHeaderPrinted = false;
        this.rowList = null;
    }

    /**
     * add a row with a single prompt and single data value.
     *
     * @param prompt
     * @param value
     */
    protected void addRow(String prompt,
                          String value)
    {
        PromptValuePair row = new PromptValuePair(prompt,
                                                  value);

        rowList.add(row);
    }

    /**
     * add a row that has only a prompt and no data to display.
     *
     * @param prompt
     */
    protected void addRow(String prompt)
    {
        PromptValuePair row = new PromptValuePair(prompt,
                                                  "");

        rowList.add(row);
    }

    protected String getColumnDelimiter()
    {
        return columnDelimiter;
    }

    protected PrintOrder getOutputOrder()
    {
        return outputOrder;
    }

    protected int getRowCount()
    {
        return rowList.size();
    }

    protected boolean isPathPrinted()
    {
        return isPathPrinted;
    }

    protected boolean isRowPromptPrinted()
    {
        return isRowPromptPrinted;
    }

    protected boolean isSectionHeaderPrinted()
    {
        return isSectionHeaderPrinted;
    }

    /**
     * print out the prompts and data. One row has a single prompt and singe data value. No section title are printed
     *
     * @param streamOut
     * @param promptList
     * @param valueList
     */
    protected void printOutRowsAlphaOrder(PrintStream streamOut)
    {
        int padTillColumn = COLUMN_CHAR_COUNT_PAD + getMaxCharCount();
        StringBuilder sb = new StringBuilder();

        /**
         * sort the row list by prompt
         */
        Collections.sort(rowList);

        /*
         *
         */
        for (int i = 0; i < rowList.size(); ++i)
        {
            final PromptValuePair row = rowList.get(i);

            sb.setLength(0);

            if (isRowPromptPrinted())
            {
                sb.append(row.getPrompt());
            }

            sb.append(getColumnDelimiter());

            while (sb.length() < padTillColumn)
            {
                sb.append(" ");
            }

            sb.append(row.getValue());
            streamOut.println(sb.toString());
        }
    }
    private static final String SECTION_SEPERATOR = "-------------------------";

    protected void printOutRowsWithSectionTitles(PrintStream streamOut,
                                                 Map<Integer, String> rowNumberToSectionHeaderMap)
    {
        int padTillColumn = COLUMN_CHAR_COUNT_PAD + getMaxCharCount();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < rowList.size(); ++i)
        {
            final PromptValuePair row = rowList.get(i);

            sb.setLength(0);

            if (isSectionHeaderPrinted() && rowNumberToSectionHeaderMap.keySet().contains(i))
            {
                String sectionHeader = rowNumberToSectionHeaderMap.get(i);

                streamOut.println(SECTION_SEPERATOR);
                streamOut.println(sectionHeader);
            }

            if (isRowPromptPrinted())
            {
                sb.append(row.getPrompt());
            }

            sb.append(getColumnDelimiter());

            while (sb.length() < padTillColumn)
            {
                sb.append(" ");
            }

            sb.append(row.getValue());
            streamOut.println(sb.toString());
        }
    }

    private int getMaxCharCount()
    {
        int max = 0;

        for (PromptValuePair row : rowList)
        {
            max = Math.max(max, row.getPrompt().length());
        }

        return max;
    }

    private class PromptValuePair
            implements Comparable<PromptValuePair>
    {

        private final String prompt;
        private final String value;

        PromptValuePair(String prompt,
                        String value)
        {
            this.prompt = prompt;
            this.value = value;
        }

        @Override
        public int compareTo(PromptValuePair other)
        {
            return prompt.compareTo(other.prompt);
        }

        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
            {
                return true;
            }

            if (obj == null)
            {
                return false;
            }

            if (getClass() != obj.getClass())
            {
                return false;
            }

            final PromptValuePair other = (PromptValuePair) obj;

            if (!Objects.equals(this.prompt, other.prompt))
            {
                return false;
            }

            if (!Objects.equals(this.value, other.value))
            {
                return false;
            }

            return true;
        }

        public String getPrompt()
        {
            return prompt;
        }

        public String getValue()
        {
            return value;
        }

        @Override
        public int hashCode()
        {
            int hash = 3;

            hash = 59 * hash + Objects.hashCode(this.prompt);
            hash = 59 * hash + Objects.hashCode(this.value);

            return hash;
        }
    }
}
