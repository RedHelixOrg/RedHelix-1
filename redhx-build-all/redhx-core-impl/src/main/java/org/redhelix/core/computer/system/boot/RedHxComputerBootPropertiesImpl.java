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



package org.redhelix.core.computer.system.boot;

import java.util.Objects;

/**
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public final class RedHxComputerBootPropertiesImpl
        implements RedHxComputerBootProperties
{
    private final RedHxComputerSystemBootSourceOverrideEnabledEnum bootOverride;
    private final RedHxComputerBootSourceEnum                      bootSource;
    private final RedHxComputerBootUefiTargetSourceOverride        bootUefiTarget;

    public RedHxComputerBootPropertiesImpl( RedHxComputerBootSourceEnum bootSource,
            RedHxComputerBootUefiTargetSourceOverride                   bootUefiTarget,
            final RedHxComputerSystemBootSourceOverrideEnabledEnum      bootOverride )
    {
        this.bootSource     = bootSource;
        this.bootUefiTarget = bootUefiTarget;
        this.bootOverride   = bootOverride;
    }

    private RedHxComputerBootPropertiesImpl( )
    {
        this.bootSource     = null;
        this.bootUefiTarget = null;
        this.bootOverride   = null;
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

        final RedHxComputerBootPropertiesImpl other = (RedHxComputerBootPropertiesImpl) obj;

        if (this.bootSource != other.bootSource)
        {
            return false;
        }

        if (!Objects.equals(this.bootUefiTarget, other.bootUefiTarget))
        {
            return false;
        }

        if (this.bootOverride != other.bootOverride)
        {
            return false;
        }

        return true;
    }

    @Override
    public RedHxComputerBootSourceEnum getBootSource( )
    {
        return bootSource;
    }

    @Override
    public RedHxComputerSystemBootSourceOverrideEnabledEnum getBootSourceOverride( )
    {
        return bootOverride;
    }

    @Override
    public RedHxComputerBootUefiTargetSourceOverride getBootUefiTarget( )
    {
        return bootUefiTarget;
    }

    @Override
    public int hashCode( )
    {
        int hash = 7;

        hash = 97 * hash + Objects.hashCode(this.bootSource);
        hash = 97 * hash + Objects.hashCode(this.bootUefiTarget);
        hash = 97 * hash + Objects.hashCode(this.bootOverride);

        return hash;
    }

    @Override
    public String toString( )
    {
        StringBuilder sb = new StringBuilder();

        sb.append("[ ");
        sb.append("bootSource=");
        sb.append(bootSource);
        sb.append(", bootUefiTarget=");
        sb.append(bootUefiTarget);
        sb.append(", bootOverride=");
        sb.append(bootOverride);
        sb.append(" ]");

        return sb.toString();
    }
}
