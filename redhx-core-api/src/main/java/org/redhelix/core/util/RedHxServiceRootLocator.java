/*
 * Copyright 2015 JBlade LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package org.redhelix.server.main;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.apache.olingo.client.api.Configuration;
import org.apache.olingo.client.api.uri.URIBuilder;
import org.apache.olingo.client.core.ConfigurationImpl;
import org.apache.olingo.client.core.uri.URIBuilderImpl;
import org.apache.olingo.commons.api.format.ODataFormat;
import org.redhelix.core.service.root.RedHxServiceRootIdEum;
import org.redhelix.core.util.RedHxRedfishProtocolVersionEnum;

/**
 * 
 * Git SHA: $Id$
 * 
 * @since RedHelix Version HELIX_VERSION_TAG // Do not change this line. It will
 *        be replaced when checked in to the master branch
 * @author Hank Bruning
 *
 */
public class RedHxServiceRootLocator
{
    private static final String SERVICE_ROOT = "http://localhost:8080/odata-server-sample/cars.svc/";
    private final Map<RedHxServiceRootIdEum, URI> serviceToUriMap;

    private RedHxServiceRootLocator()
    {
        this.serviceToUriMap = null;
    }

    public RedHxServiceRootLocator(RedHxRedfishProtocolVersionEnum protocolVersion, Map<RedHxServiceRootIdEum, String> serviceToStringiMap)
    {
        this.serviceToUriMap = new HashMap<RedHxServiceRootIdEum, URI>();
        final Configuration conf = new ConfigurationImpl();
        conf.setDefaultMediaFormat(ODataFormat.APPLICATION_JSON);
        final URIBuilder builder = new URIBuilderImpl(conf, SERVICE_ROOT);
        for (final RedHxServiceRootIdEum id : serviceToStringiMap.keySet())
        {
            final String loc = serviceToStringiMap.get(id);
            if (protocolVersion == RedHxRedfishProtocolVersionEnum.VERSION_1)
            {
                builder.appendNavigationSegment("v1");
            }
            else
            {
                throw new IllegalArgumentException("Unknown Redfish protocol version " + protocolVersion);
            }
            builder.appendNavigationSegment(loc);

            final URI uri = builder.build();
            serviceToUriMap.put(id, uri);
        }

    }

    public URI getUri(RedHxServiceRootIdEum serviceId)
    {
        final URI uri = serviceToUriMap.get(serviceId);

        return uri;
    }

}
