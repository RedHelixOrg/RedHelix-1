/*
 * Copyright 2015 JBlade LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License
 */



package org.redhelix.core.computer.system.power;

/**
 *
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public abstract class RedHxAbstractPowerWatts
        implements RedHxPowerWatts
{
    private final short wattsMajor;
    private final byte  wattsMinor;

    /**
     * @param wattsMajor < 0
     * @param wattsMinor
     */
    protected RedHxAbstractPowerWatts( final short wattsMajor,
                                       final byte  wattsMinor )
    {
        super();

        if (wattsMajor < 0)
        {
            throw new IllegalArgumentException("Invalid argument wattsMajor. It can not be less than zero. It was " + wattsMajor + ".");
        }

        if (wattsMinor < 0)
        {
            throw new IllegalArgumentException("Invalid argument wattsMinor. It can not be less than zero. It was " + wattsMinor + ".");
        }

        this.wattsMajor = wattsMajor;
        this.wattsMinor = wattsMinor;
    }

    private RedHxAbstractPowerWatts( )
    {
        super();
        this.wattsMajor = 0;
        this.wattsMinor = 0;
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

        final RedHxAbstractPowerWatts other = (RedHxAbstractPowerWatts) obj;

        if (wattsMajor != other.wattsMajor)
        {
            return false;
        }

        if (wattsMinor != other.wattsMinor)
        {
            return false;
        }

        return true;
    }

    /**
     * @return the wattsMajor
     */
    public short getWattsMajor( )
    {
        return wattsMajor;
    }

    /**
     * @return the wattsMinor
     */
    public byte getWattsMinor( )
    {
        return wattsMinor;
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

        result = prime * result + wattsMajor;
        result = prime * result + wattsMinor;

        return result;
    }

    @Override
    public String toString( )
    {
        final StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(", watts=");
        sb.append(wattsMajor);
        sb.append(".");
        sb.append(wattsMinor);
        sb.append("]");

        return sb.toString();
    }
}
