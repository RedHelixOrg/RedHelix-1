/*
 * Copyright 2015 JBlade LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 *
 */
package org.redhelix.server.db;

/**
 *
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
abstract class AbstractPersistantId
{

    private final transient String exteralId;
    private final int seqId;

    protected AbstractPersistantId(int seqId,
                                   String prefix)
    {
        this.seqId = seqId;
        this.exteralId = prefix + seqId;
    }

    private AbstractPersistantId()
    {
        this.seqId = 0;
        this.exteralId = null;
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

        final AbstractPersistantId other = (AbstractPersistantId) obj;

        if (this.seqId != other.seqId)
        {
            return false;
        }

        return true;
    }

    protected int getSeqId()
    {
        return seqId;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;

        hash = 23 * hash + this.seqId;

        return hash;
    }

    @Override
    public String toString()
    {
        return exteralId;
    }
}
