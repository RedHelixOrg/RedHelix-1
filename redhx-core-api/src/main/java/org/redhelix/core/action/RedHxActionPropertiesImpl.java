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



package org.redhelix.core.action;

import org.redhelix.core.util.RedHxUriPath;

import java.util.Objects;

/**
 *
 * <br><br>
 * Git SHA: $Id$
 *
 * @since RedHelix Version HELIX_VERSION_TAG // Do not change this line.
 * @author Hank Bruning
 *
 */
public final class RedHxActionPropertiesImpl
        implements RedHxActionProperties
{
    private RedHxActionName actionName;
    private RedHxUriPath    actionsPath;

    public RedHxActionPropertiesImpl( RedHxActionName actionName,
                                      RedHxUriPath    actionsPath )
    {
        this.actionName  = actionName;
        this.actionsPath = actionsPath;
    }

    private RedHxActionPropertiesImpl( )
    {
        this.actionName  = null;
        this.actionsPath = null;
    }

    @Override
    public int compareTo( RedHxActionProperties other )
    {
        return this.actionName.compareTo(other.getActionName());
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

        final RedHxActionPropertiesImpl other = (RedHxActionPropertiesImpl) obj;

        if (!Objects.equals(this.actionName, other.actionName))
        {
            return false;
        }

        if (!Objects.equals(this.actionsPath, other.actionsPath))
        {
            return false;
        }

        return true;
    }

    public RedHxActionName getActionName( )
    {
        return actionName;
    }

    public RedHxUriPath getActionsPath( )
    {
        return actionsPath;
    }

    @Override
    public int hashCode( )
    {
        int hash = 7;

        hash = 29 * hash + Objects.hashCode(this.actionsPath);

        return hash;
    }

    @Override
    public String toString( )
    {
        StringBuilder sb = new StringBuilder();

        sb.append("[ ");
        sb.append("actionName=");
        sb.append(actionName.getActionName());
        sb.append(", actionsPath=");
        sb.append(actionsPath.getPath());
        sb.append(" ]");

        return sb.toString();
    }
}
