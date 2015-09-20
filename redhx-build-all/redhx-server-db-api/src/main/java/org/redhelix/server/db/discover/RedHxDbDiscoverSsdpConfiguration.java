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

/**
 * Discover Redfish servers using the Simple Service Discovery Protocol.
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public interface RedHxDbDiscoverSsdpConfiguration
        extends RedHxDbDiscoverConfiguration
{

    /**
     * The type of multicast IP used for SSDP.
     */
    public enum SimpleServiceDiscoveryProtocolTypeEnum
    {

        /**
         * 239.255.255.250 (IPv4 site-local address)
         */
        SITE_LOCAL_IP_V4,
        /**
         * [FF05::C] (IPv6 site-local)
         */
        SITE_LOCAL_IP_V6,
        /**
         * [FF02::C] (IPv6 link-local)
         */
        SITE_LOCAL_LINK_IP_6,
        /**
         * [FF08::C] (IPv6 organization-local)
         */
        ORGANIZATION_LOCAL_IP_6,
        /**
         * [FF0E::C] (IPv6 global)
         */
        GLOBAL_IP_V6;
    }

    SimpleServiceDiscoveryProtocolTypeEnum getSimpleServiceDiscoveryProtocolType();

}
