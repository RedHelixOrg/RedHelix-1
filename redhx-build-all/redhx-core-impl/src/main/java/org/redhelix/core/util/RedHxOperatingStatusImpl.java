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
package org.redhelix.core.util;

import java.util.Objects;

/**
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public final class RedHxOperatingStatusImpl
        implements RedHxOperatingStatus
{

    private final RedHxOperatingHealthEnum operatingHealth;
    private final RedHxOperatingHealthRollupEnum operatingHealthRollUp;
    private final RedHxOperatingStateEnum operatingState;

    public RedHxOperatingStatusImpl(RedHxOperatingHealthEnum operatingHealth,
                                    RedHxOperatingHealthRollupEnum operatingHealthRollUp,
                                    RedHxOperatingStateEnum operatingState)
    {
        this.operatingHealth = operatingHealth;
        this.operatingHealthRollUp = operatingHealthRollUp;
        this.operatingState = operatingState;
    }

    private RedHxOperatingStatusImpl()
    {
        this.operatingHealth = null;
        this.operatingHealthRollUp = null;
        this.operatingState = null;
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

        final RedHxOperatingStatusImpl other = (RedHxOperatingStatusImpl) obj;

        if (this.operatingHealth != other.operatingHealth)
        {
            return false;
        }

        if (this.operatingHealthRollUp != other.operatingHealthRollUp)
        {
            return false;
        }

        if (this.operatingState != other.operatingState)
        {
            return false;
        }

        return true;
    }

    @Override
    public RedHxOperatingHealthEnum getOperatingHealth()
    {
        return operatingHealth;
    }

    @Override
    public RedHxOperatingHealthRollupEnum getOperatingHealthRollUp()
    {
        return operatingHealthRollUp;
    }

    @Override
    public RedHxOperatingStateEnum getOperatingState()
    {
        return operatingState;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;

        hash = 53 * hash + Objects.hashCode(this.operatingHealth);
        hash = 53 * hash + Objects.hashCode(this.operatingHealthRollUp);
        hash = 53 * hash + Objects.hashCode(this.operatingState);

        return hash;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("[ ");
        sb.append("operatingHealth=");
        sb.append(operatingHealth);
        sb.append(", operatingHealthRollUp=");
        sb.append(operatingHealthRollUp);
        sb.append(", operatingState=");
        sb.append(operatingState);
        sb.append(" ]");

        return sb.toString();
    }
}
