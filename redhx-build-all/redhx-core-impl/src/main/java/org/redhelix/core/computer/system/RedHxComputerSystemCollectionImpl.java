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
package org.redhelix.core.computer.system;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.redhelix.core.util.RedHxUriPath;

/**
 *
 *
 * A collection of Redfish chassis.
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public final class RedHxComputerSystemCollectionImpl
        implements RedHxComputerSystemCollection
{

    private final List<RedHxComputerSystem> list;

    public RedHxComputerSystemCollectionImpl(final List<RedHxComputerSystem> list)
    {
        this.list = Collections.unmodifiableList(list);
    }

    private RedHxComputerSystemCollectionImpl()
    {
        list = null;
    }

    @Override
    public boolean equals(Object obj)
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

        final RedHxComputerSystemCollectionImpl other = (RedHxComputerSystemCollectionImpl) obj;

        if (!Objects.equals(this.list, other.list))
        {
            return false;
        }

        return true;
    }

    @Override
    public RedHxComputerSystem getComputerSystem(RedHxUriPath computerSystemPath)
    {
        RedHxComputerSystem retVal = null;
        for (RedHxComputerSystem cs : list)
        {
            if (cs.getComputerSystemPath().equals(computerSystemPath))
            {
                retVal = cs;
                break;
            }

        }
        return retVal;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;

        hash = 97 * hash + Objects.hashCode(this.list);

        return hash;
    }

    @Override
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    @Override
    public Iterator<RedHxComputerSystem> iterator()
    {
        ComputerSystemIterator<RedHxComputerSystem> iter = new ComputerSystemIterator<>(list);

        return iter;
    }

    @Override
    public int size()
    {
        return list.size();
    }

    @Override
    public String toString()
    {
        return list.toString();
    }
}
