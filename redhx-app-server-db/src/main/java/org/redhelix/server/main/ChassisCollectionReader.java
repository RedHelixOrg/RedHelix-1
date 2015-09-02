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



package org.redhelix.server.main;

import org.redhelix.core.chassis.RedHxChassis;
import org.redhelix.core.chassis.RedHxChassisCollection;
import org.redhelix.core.chassis.RedHxChassisCollectionImpl;
import org.redhelix.core.util.RedHxHttpResponseException;
import org.redhelix.core.util.RedHxParseException;
import org.redhelix.core.util.RedHxUriPath;

import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * <br><br>
 * Git SHA: $Id$
 *
 * @since RedHelix Version HELIX_VERSION_TAG // Do not change this line.
 * @author Hank Bruning
 *
 */
final class ChassisCollectionReader
{
    private ChassisCollectionReader( ) {}

    static RedHxChassisCollection chassisCollectionReader( RedHxServerConnectionContext ctx,
            Set<RedHxUriPath>                                                           chassisPathSet )
            throws RedHxHttpResponseException,
                   URISyntaxException,
                   RedHxParseException
    {
        final List<RedHxChassis> list = new ArrayList<>();

        for (RedHxUriPath link : chassisPathSet)
        {
            ChassisReader reader  = new ChassisReader(ctx,
                    link);
            RedHxChassis  chassis = reader.readChassis();

            if (chassis != null)
            {
                list.add(chassis);
            }
        }

        RedHxChassisCollection chassisList = new RedHxChassisCollectionImpl(list);

        return chassisList;
    }
}
