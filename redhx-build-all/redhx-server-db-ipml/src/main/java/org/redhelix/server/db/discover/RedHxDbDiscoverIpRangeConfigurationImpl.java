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
package org.redhelix.server.db.discover;

import java.net.InetAddress;
import javax.persistence.Entity;

/**
 *
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
@Entity
public class RedHxDbDiscoverIpRangeConfigurationImpl
        extends AbstractDiscoverConfiguration
        implements RedHxDbDiscoverIpRangeConfiguration
{

    private InetAddress beginingInetAddress;
    private InetAddress endingInetAddress;

    public RedHxDbDiscoverIpRangeConfigurationImpl(InetAddress beginingInetAddress,
                                                   InetAddress endingInetAddress)
    {
        this.beginingInetAddress = beginingInetAddress;
        this.endingInetAddress = endingInetAddress;
    }

    public RedHxDbDiscoverIpRangeConfigurationImpl()
    {
    }

    @Override
    public InetAddress getBeginingInetAddress()
    {
        return beginingInetAddress;
    }

    @Override
    public InetAddress getEndingInetAddress()
    {
        return endingInetAddress;
    }

    public void setBeginingInetAddress(InetAddress beginingInetAddress)
    {
        this.beginingInetAddress = beginingInetAddress;
    }

    public void setEndingInetAddress(InetAddress endingInetAddress)
    {
        this.endingInetAddress = endingInetAddress;
    }
}
