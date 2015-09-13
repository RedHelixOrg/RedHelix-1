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
package org.redhelix.server.lib.reader.computer.system;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.redhelix.core.chassis.RedHxChassis;
import org.redhelix.core.chassis.RedHxChassisCollection;
import org.redhelix.core.computer.system.RedHxComputerSystem;
import org.redhelix.core.computer.system.RedHxComputerSystemCollection;
import org.redhelix.core.computer.system.RedHxComputerSystemCollectionImpl;
import org.redhelix.core.util.RedHxHttpResponseException;
import org.redhelix.core.util.RedHxParseException;
import org.redhelix.core.util.RedHxUriPath;
import org.redhelix.server.lib.reader.util.RedHxServerConnectionContext;

/**
 * read a collection of links to unique computer systems on the Redfish server. This class does not have a public constructor.
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 */
public final class RedHxComputerSystemCollectionReader
{

    private RedHxComputerSystemCollectionReader()
    {
    }

    /**
     * read all computer systems in a collection of chassis.
     *
     * @param ctx
     * @param chassisCollection
     * @return
     * @throws RedHxHttpResponseException
     * @throws URISyntaxException
     * @throws RedHxParseException
     */
    public static RedHxComputerSystemCollection readPaths(RedHxServerConnectionContext ctx,
                                                          RedHxChassisCollection chassisCollection)
            throws RedHxHttpResponseException,
                   URISyntaxException,
                   RedHxParseException
    {
        final List<RedHxComputerSystem> list = new ArrayList<>();

        for (RedHxChassis chassis : chassisCollection)
        {
            for (RedHxUriPath uriPath : chassis.getComputerSystemUriPathList())
            {
                ComputerSystemReader reader = new ComputerSystemReader(ctx,
                                                                       uriPath);
                RedHxComputerSystem computer = reader.readComputerSystem();

                if (computer != null)
                {
                    list.add(computer);
                }
            }
        }

        RedHxComputerSystemCollection collection = new RedHxComputerSystemCollectionImpl(list);

        return collection;
    }
}
