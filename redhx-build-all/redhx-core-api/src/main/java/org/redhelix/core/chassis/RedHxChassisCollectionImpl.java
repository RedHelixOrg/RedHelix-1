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



package org.redhelix.core.chassis;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 *
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public final class RedHxChassisCollectionImpl
        implements RedHxChassisCollection
{
    private final List<RedHxChassis> list;

    public RedHxChassisCollectionImpl( final List<RedHxChassis> list )
    {
        this.list = Collections.unmodifiableList(list);
    }

    private RedHxChassisCollectionImpl( )
    {
        list = null;
    }

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

        final RedHxChassisCollectionImpl other = (RedHxChassisCollectionImpl) obj;

        if (!Objects.equals(this.list, other.list))
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode( )
    {
        int hash = 7;

        hash = 97 * hash + Objects.hashCode(this.list);

        return hash;
    }

    @Override
    public Iterator<RedHxChassis> iterator( )
    {
        ChassisIterator<RedHxChassis> iter = new ChassisIterator<>(list);

        return iter;
    }

    @Override
    public int size( )
    {
        return list.size();
    }

    @Override
    public String toString( )
    {
        return list.toString();
    }
}
