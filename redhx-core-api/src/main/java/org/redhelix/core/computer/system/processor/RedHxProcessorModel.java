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



package org.redhelix.core.computer.system.processor;

/**
 *
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public class RedHxProcessorModel
{
    /**
     * The maximum number of characters allowed in a Processor Model Name.
     */
    public static final byte MAX_CHAR_COUNT_REDH_DEFINED = 30;    // arbrartray.
    private final String     model;

    public RedHxProcessorModel( String model )
    {
        this.model = model;
    }

    private RedHxProcessorModel( )
    {
        this.model = null;
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

        final RedHxProcessorModel other = (RedHxProcessorModel) obj;

        if (model == null)
        {
            if (other.model != null)
            {
                return false;
            }
        }
        else if (!model.equals(other.model))
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

        result = prime * result + ((model == null)
                                   ? 0
                                   : model.hashCode());

        return result;
    }

    @Override
    public String toString( )
    {
        return model;
    }
}
