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



package org.redhelix.core.computer.system.power.supply;

import org.redhelix.core.computer.system.power.RedHxAbstractPowerVoltageReading;

/**
 * A voltage reading for the line supplying the power supply.
 *
 * @I
 *    <br><br>Git SHA: $Id$
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public class RedHxPowerSupplyLineInputVoltage
        extends RedHxAbstractPowerVoltageReading
{
    /**
     * A voltage reading for the line supplying the power supply.
     *
     * @param voltageMajor
     *            the voltage as a whole number. The range is between
     *            {@link org.redhelix.core.computer.system.power.RedHxPowerVoltageReading#MIN_VOLTAGE_MAJOR_READING}
     *            and
     *            {@link org.redhelix.core.computer.system.power.RedHxPowerVoltageReading#MAX_VOLTAGE_MAJOR_READING}
     *            .
     *
     * @param voltageMinor
     *            the voltage in 1/10 of a volts. The range is between
     *            {@link org.redhelix.core.computer.system.power.RedHxPowerVoltageReading#MIN_VOLTAGE_MINOR_READING}
     *            and
     *            {@link org.redhelix.core.computer.system.power.RedHxPowerVoltageReading#MAX_VOLTAGE_MINOR_READING}
     */
    public RedHxPowerSupplyLineInputVoltage( short voltageMajor,
            byte                                   voltageMinor )
    {
        super(voltageMajor,
              voltageMinor);
    }
}
