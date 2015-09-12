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
package org.redhelix.core.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.redhelix.core.util.RedHxUriPath;

/**
 * A single action. An action is a URI Path with an optional parameter.
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public final class RedHxActionPropertiesImpl
        implements RedHxActionProperties
{

    private static final List<String> EMPTY_STR_LIST = Collections.unmodifiableList(new ArrayList<String>());
    private final RedHxActionName actionName;
    private final RedHxUriPath actionsPath;
    private final List<String> actionValues;

    public RedHxActionPropertiesImpl(final RedHxActionName actionName,
                                     final RedHxUriPath actionsPath,
                                     final List<String> actionValues)
    {
        this.actionName = actionName;
        this.actionsPath = actionsPath;

        final List<String> tmpList = new ArrayList<>();

        tmpList.addAll(actionValues);
        Collections.sort(tmpList);
        this.actionValues = Collections.unmodifiableList(tmpList);
    }

    public RedHxActionPropertiesImpl(final RedHxActionName actionName,
                                     final RedHxUriPath actionsPath)
    {
        this.actionName = actionName;
        this.actionsPath = actionsPath;
        this.actionValues = EMPTY_STR_LIST;
    }

    private RedHxActionPropertiesImpl()
    {
        this.actionName = null;
        this.actionsPath = null;
        this.actionValues = null;
    }

    @Override
    public int compareTo(RedHxActionProperties other)
    {
        return this.actionName.compareTo(other.getActionName());
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

        final RedHxActionPropertiesImpl other = (RedHxActionPropertiesImpl) obj;

        if (!Objects.equals(this.actionName, other.actionName))
        {
            return false;
        }

        if (!Objects.equals(this.actionsPath, other.actionsPath))
        {
            return false;
        }

        if (!Objects.equals(this.actionValues, other.actionValues))
        {
            return false;
        }

        return true;
    }

    @Override
    public RedHxActionName getActionName()
    {
        return actionName;
    }

    @Override
    public RedHxUriPath getActionPath()
    {
        return actionsPath;
    }

    @Override
    public List<String> getActionValues()
    {
        return actionValues;
    }

    @Override
    public int hashCode()
    {
        int hash = actionName.hashCode();

        hash = 29 * hash + Objects.hashCode(this.actionsPath);

        return hash;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("[ ");
        sb.append("actionName=");
        sb.append(actionName.getValue());
        sb.append(", actionsPath=");
        sb.append(actionsPath.getValue());
        sb.append(", actionValues=");
        sb.append(actionValues);
        sb.append(" ]");

        return sb.toString();
    }
}
