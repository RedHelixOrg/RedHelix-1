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



package org.redhelix.core.computer.system.power.limit;

/**
 * The time required for the limiting process to reduce power consumption to below the limit.", Git SHA: $Id$
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public class RedHxPowerCorrectionTimeMs
{
    private final int mills;

    /**
     * @param mills
     */
    public RedHxPowerCorrectionTimeMs( int mills )
    {
        super();
        this.mills = mills;

        if (mills < 0)
        {
            throw new IllegalArgumentException("Invalid argument mills. It can not be less than zero. It was " + mills + ".");
        }
    }

    private RedHxPowerCorrectionTimeMs( )
    {
        super();
        this.mills = 0;
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

        final RedHxPowerCorrectionTimeMs other = (RedHxPowerCorrectionTimeMs) obj;

        if (mills != other.mills)
        {
            return false;
        }

        return true;
    }

    /**
     * get the number of milliseconds.
     *
     * @return a value greater or equal to zero.
     */
    public int getMilliseconds( )
    {
        return mills;
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

        result = prime * result + mills;

        return result;
    }

    @Override
    public String toString( )
    {
        final StringBuilder sb = new StringBuilder();

        sb.append("[powerCorrectionTime");
        sb.append(mills);
        sb.append(" ms]");

        return sb.toString();
    }
}
