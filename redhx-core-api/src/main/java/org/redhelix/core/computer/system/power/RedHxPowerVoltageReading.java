/*
 * Copyright 2015 JBlade LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package org.redhelix.core.computer.system.power;

/**
 * 
 * Git SHA: $Id$
 * 
 * @since RedHelix Version HELIX_VERSION_TAG // Do not change this line.
 * @author Hank Bruning
 *
 */
public interface RedHxPowerVoltageReading
{
    /**
     * The maximum voltage reading.
     */
    static final short MAX_VOLTAGE_MAJOR_READING = 600;
    /**
     * The maximum fractional voltage. Fractional voltages are in 1/10 of a
     * volt.
     */
    static final short MAX_VOLTAGE_MINOR_READING = 10;

    /**
     * The minimum voltage reading.
     */
    static final short MIN_VOLTAGE_MAJOR_READING = -100;
    /**
     * The minimum voltage reading. Fractional voltages are in 1/10 of a volt.
     */
    static final short MIN_VOLTAGE_MINOR_READING = 0;

    /**
     * get the voltage as a whole number.
     * 
     * @return the voltageMajor. A value between
     *         {@link MIN_VOLTAGE_MAJOR_READING} and
     *         {@link MAX_VOLTAGE_MAJOR_READING}.
     */
    short getVoltageMajor();

    /**
     * get the voltate in 1/10 of a volts.
     * 
     * @return the voltageMinor. A value between
     *         {@link MIN_VOLTAGE_MINOR_READING} and
     *         {@link MAX_VOLTAGE_MINOR_READING}.
     */
    byte getVoltageMinor();

}
