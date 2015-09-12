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

import java.util.List;
import org.redhelix.core.util.RedHxUriPath;

/**
 * A single action with optional parameters.
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public interface RedHxActionProperties
        extends Comparable<RedHxActionProperties>
{

    /**
     * get the name of the action.
     *
     * @return the action name. A null is not returned.
     */
    RedHxActionName getActionName();

    /**
     * get the path to invoke the action.
     *
     * @return the URI path used to invoke the action. A null is not returned.
     */
    RedHxUriPath getActionPath();

    /**
     * get a list of parameters for the action. An action may have only one parameter
     *
     * @return an immutable list of action values. The list is sorted in alpha order. The list may be empty.
     */

    List<String> getActionValues();
}
