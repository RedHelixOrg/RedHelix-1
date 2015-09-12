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

import org.redhelix.core.util.RedHxOperatingStatus;

/**
 * A summary of the memory quantity and status in a computer.
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public interface RedHxComputerMemorySummary
{
    /**
     * get the Computer Memory Summary.
     *
     * @return a null if the system memory was not defined otherwise a the summary.
     */
    RedHxOperatingStatus getMemoryOperatingStatus( );

    /**
     * get the total amount of system memory.
     *
     * @return a value zero or greater.
     */
    long getTotalSystemMemoryGigaBytes( );
}
