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

import org.redhelix.core.util.RedHxUriPath;

/**
 *
 * a collection of computer systems on a single device HTTP connection that implements Redfish.
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 */
public interface RedHxComputerSystemCollection
        extends Iterable<RedHxComputerSystem>
{

    /**
     * test if the the collection contains any computer systems.
     *
     * @return true if there are one or more computer systems.
     */
    boolean isEmpty();

    /**
     * get the number of computer systems in the collection.
     *
     * @return a value zero or greater.
     */
    int size();

    /**
     * get a single computer system.
     *
     * @param computerSystemPath the path on the server implementing Redfish to a computer system.
     * @return null if the path is not found in this class otherwise a computer system.
     */
    RedHxComputerSystem getComputerSystem(RedHxUriPath computerSystemPath);
}
