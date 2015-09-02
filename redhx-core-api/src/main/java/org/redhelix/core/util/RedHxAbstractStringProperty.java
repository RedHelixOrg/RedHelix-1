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



package org.redhelix.core.util;

/**
 *
 * <br><br>Git SHA: $Id$
 *
 * @since RedHelix Version HELIX_VERSION_TAG // Do not change this line.
 * @author Hank Bruning
 *
 */
public abstract class RedHxAbstractStringProperty
        implements Comparable<RedHxAbstractStringProperty>
{
    /**
     * The maximum number of characters allowed in a
     */
    private final int    maxCharCount;
    private final String propName;

    protected RedHxAbstractStringProperty( int maxCharCount,
            String                             propName )
    {
        if (propName == null)
        {
            throw new NullPointerException("The argument propName can not be null.");
        }

        if (propName.length() > maxCharCount)
        {
            throw new IllegalArgumentException("The argument propName contains too many characters. The maximum is " + maxCharCount
                                               + " and it contains " + propName.length() + ". Invalid string is \"" + propName + "\".");
        }

        this.maxCharCount = maxCharCount;
        this.propName     = propName;
    }

    private RedHxAbstractStringProperty( )
    {
        this.maxCharCount = 0;
        this.propName     = null;
    }

    @Override
    public int compareTo( RedHxAbstractStringProperty other )
    {
        return propName.compareTo(other.propName);
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

        final RedHxAbstractStringProperty other = (RedHxAbstractStringProperty) obj;

        if (maxCharCount != other.maxCharCount)
        {
            return false;
        }

        if (propName == null)
        {
            if (other.propName != null)
            {
                return false;
            }
        }
        else if (!propName.equals(other.propName))
        {
            return false;
        }

        return true;
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

        result = prime * result + maxCharCount;
        result = prime * result + ((propName == null)
                                   ? 0
                                   : propName.hashCode());

        return result;
    }

    @Override
    public String toString( )
    {
        final StringBuilder sb = new StringBuilder();

        sb.append("[maxCharCount" + maxCharCount);
        sb.append(", value=" + propName);
        sb.append("]");

        return sb.toString();
    }

    protected String getValue( )
    {
        return propName;
    }
}
