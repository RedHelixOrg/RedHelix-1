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
                                        final RedHxChassisColumnFormatter.PrintOrder outputOrder,
                                        final boolean isPathPrinted)
    {
        this.isRowPromptPrinted = isRowTitlePrinted;
        this.columnDelimiter = columnDelimiter;
        this.outputOrder = outputOrder;
        this.isSectionHeaderPrinted = false;
        this.isPathPrinted = isPathPrinted;
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
    }

    private RedHxAbstractColumnFormatter()
    {
        this.isRowPromptPrinted = false;
        this.columnDelimiter = null;
        this.outputOrder = null;
        this.isSectionHeaderPrinted = false;
    }

    protected String getColumnDelimiter()
    {
        return columnDelimiter;
    }

    protected PrintOrder getOutputOrder()
    {
        return outputOrder;
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
    protected void printOutRows(PrintStream streamOut,
                                List<String> promptList,
                                List<String> valueList)
    {

        /**
         * All the output data is in two lists, now place them on the ouputStream
         */
        valiateListElementCount(promptList,
                                valueList);

        int padTillColumn = COLUMN_CHAR_COUNT_PAD + getMaxCharCount(promptList);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < promptList.size(); ++i)
        {
            sb.setLength(0);

            if (isRowPromptPrinted())
            {
                sb.append(promptList.get(i));
            }

            sb.append(getColumnDelimiter());

            while (sb.length() < padTillColumn)
            {
                sb.append(" ");
            }

            sb.append(valueList.get(i));
            streamOut.println(sb.toString());
        }
    }

    protected void printOutRowsWithSectionTitles(PrintStream streamOut,
                                                 List<String> promptList,
                                                 List<String> valueList,
                                                 Map<Integer, String> rowNumberToSectionHeaderMap)
    {

        /**
         * All the output data is in two lists, now place them on the ouputStream
         */
        valiateListElementCount(promptList,
                                valueList);

        int padTillColumn = COLUMN_CHAR_COUNT_PAD + getMaxCharCount(promptList);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < promptList.size(); ++i)
        {
            sb.setLength(0);

            if (isSectionHeaderPrinted() && rowNumberToSectionHeaderMap.keySet().contains(i))
            {
                String sectionHeader = rowNumberToSectionHeaderMap.get(i);

                streamOut.println("-------------------------");
                streamOut.println(sectionHeader);
            }

            if (isRowPromptPrinted())
            {
                sb.append(promptList.get(i));
            }

            sb.append(getColumnDelimiter());

            while (sb.length() < padTillColumn)
            {
                sb.append(" ");
            }

            sb.append(valueList.get(i));
            streamOut.println(sb.toString());
        }
    }

    private static int getMaxCharCount(List<String> promptList)
    {
        int max = 0;

        for (String str : promptList)
        {
            max = Math.max(max, str.length());
        }

        return max;
    }

    private static void valiateListElementCount(List<String> promptList,
                                                List<String> valueList)
    {
        if (promptList.size() != valueList.size())
        {
            throw new IllegalStateException("The outout lists are not identical in size. The prompt list contains " + promptList.size()
                    + " elements and the value list " + valueList.size() + " elements.");
        }
    }

    protected class PromptValuePair
            implements Comparable<PromptValuePair>
    {

        private final String prompt;
        private final String value;

        public PromptValuePair(String prompt,
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
