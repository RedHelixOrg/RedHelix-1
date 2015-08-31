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



package org.redhelix.core.sensor;

/**
 *
 * <br><br>Git SHA: $Id$
 *
 * @since RedHelix Version HELIX_VERSION_TAG // Do not change this line.
 * @author Hank Bruning
 *
 */
public abstract class RedHxAbstractSensor
        implements RedHxGenericSensor
{
    private final RedHxSensorName   sensorName;
    private final RedHxSensorNumber sensorNumber;

    /**
     * A sensor.
     *
     * @param sensorName
     *            the sensor name. This shall not be null.
     * @param sensorNumber
     *            the sensor number. This shall not be null.
     */
    protected RedHxAbstractSensor( RedHxSensorName   sensorName,
                                   RedHxSensorNumber sensorNumber )
    {
        super();
        this.sensorName   = sensorName;
        this.sensorNumber = sensorNumber;
    }

    private RedHxAbstractSensor( )
    {
        super();
        this.sensorName   = null;
        this.sensorNumber = null;
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

        final RedHxAbstractSensor other = (RedHxAbstractSensor) obj;

        if (sensorName == null)
        {
            if (other.sensorName != null)
            {
                return false;
            }
        }
        else if (!sensorName.equals(other.sensorName))
        {
            return false;
        }

        if (sensorNumber == null)
        {
            if (other.sensorNumber != null)
            {
                return false;
            }
        }
        else if (!sensorNumber.equals(other.sensorNumber))
        {
            return false;
        }

        return true;
    }

    @Override
    public RedHxSensorName getSensorName( )
    {
        return sensorName;
    }

    @Override
    public RedHxSensorNumber getSensorNumber( )
    {
        return sensorNumber;
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

        result = prime * result + ((sensorName == null)
                                   ? 0
                                   : sensorName.hashCode());
        result = prime * result + ((sensorNumber == null)
                                   ? 0
                                   : sensorNumber.hashCode());

        return result;
    }

    @Override
    public String toString( )
    {
        final StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(", sensorNumber=" + sensorNumber);
        sb.append(", sensorName=" + sensorName);
        sb.append("]");

        return sb.toString();
    }
}
