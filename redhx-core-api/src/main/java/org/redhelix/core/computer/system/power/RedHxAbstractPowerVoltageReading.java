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
 * A voltage in in a computer. This may be a value used to set power limit or it
 * can be a reading from a sensor. This is not a sensor because it does not have
 * a sensor name or number.
 * <br><br>Git SHA: $Id$
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public abstract class RedHxAbstractPowerVoltageReading
        implements RedHxPowerVoltageReading
{
    private final short voltageMajor;
    private final byte  voltageMinor;

    /**
     * A voltage reading.
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
     *            .
     */
    protected RedHxAbstractPowerVoltageReading( final short voltageMajor,
            final byte                                      voltageMinor )
    {
        super();

        if (voltageMajor < MIN_VOLTAGE_MAJOR_READING)
        {
            throw new IllegalArgumentException("Invalid argument voltageMajor. It can not be less than " + MIN_VOLTAGE_MAJOR_READING
                                               + ". It was " + voltageMajor + ".");
        }

        if (voltageMajor > MAX_VOLTAGE_MAJOR_READING)
        {
            throw new IllegalArgumentException("Invalid argument voltageMajor. It can not be more than " + MAX_VOLTAGE_MAJOR_READING
                                               + ". It was " + voltageMajor + ".");
        }

        if (voltageMinor < 0)
        {
            throw new IllegalArgumentException("Invalid argument voltageMinor. It can not be less than zero. It was " + voltageMinor + ".");
        }

        if (voltageMinor > MAX_VOLTAGE_MINOR_READING)
        {
            throw new IllegalArgumentException("Invalid argument voltageMinor. It can not be more than " + MAX_VOLTAGE_MINOR_READING
                                               + ". It was " + voltageMinor + ".");
        }

        if ((voltageMajor == MAX_VOLTAGE_MAJOR_READING) && (voltageMinor > 0))
        {
            throw new IllegalArgumentException("Invalid argumentsr. The voltage can not be more than " + MAX_VOLTAGE_MAJOR_READING
                                               + ".0 volts. It was " + voltageMajor + "." + voltageMinor + " volts.");
        }

        if ((voltageMajor == MIN_VOLTAGE_MAJOR_READING) && (voltageMinor > 0))
        {
            throw new IllegalArgumentException("Invalid arguments. The voltage can not be less than " + MIN_VOLTAGE_MAJOR_READING
                                               + ".0 volts. It was " + voltageMajor + "." + voltageMinor + " volts.");
        }

        this.voltageMajor = voltageMajor;
        this.voltageMinor = voltageMinor;
    }

    private RedHxAbstractPowerVoltageReading( )
    {
        super();
        this.voltageMajor = 0;
        this.voltageMinor = 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj )
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

        final RedHxAbstractPowerVoltageReading other = (RedHxAbstractPowerVoltageReading) obj;

        if (voltageMajor != other.voltageMajor)
        {
            return false;
        }

        if (voltageMinor != other.voltageMinor)
        {
            return false;
        }

        return true;
    }

    @Override
    public short getVoltageMajor( )
    {
        return voltageMajor;
    }

    @Override
    public byte getVoltageMinor( )
    {
        return voltageMinor;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode( )
    {
        final int prime  = 31;
        int       result = 1;

        result = prime * result + voltageMajor;
        result = prime * result + voltageMinor;

        return result;
    }

    @Override
    public String toString( )
    {
        final StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(", volts=");
        sb.append(voltageMajor);
        sb.append(".");
        sb.append(voltageMinor);
        sb.append("]");

        return sb.toString();
    }
}
