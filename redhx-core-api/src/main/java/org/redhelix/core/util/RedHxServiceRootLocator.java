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



package org.redhelix.core.util;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import org.redhelix.core.service.root.RedHxServiceRootIdEum;

/**
 * Convert from a Redfish root service ID to the URL of the service. The URL contains the Redfish protocol version number and path to the
 * Redfish service. This allows each server to present a Redfish implemention of different UDP ports with any URL path the service that a
 * vendor may choose.
 * <br><br>Git SHA: $Id$
 *
 * @since RedHelix Version HELIX_VERSION_TAG // Do not change this line. It will be replaced when checked in to the master branch
 * @author Hank Bruning
 *
 */
public class RedHxServiceRootLocator
{
    private final Map<RedHxServiceRootIdEum, URI> serviceToUriMap;

    public RedHxServiceRootLocator( Map<RedHxServiceRootIdEum, URI> serviceToUriMap )
    {
        this.serviceToUriMap = Collections.unmodifiableMap(serviceToUriMap);
    }

    private RedHxServiceRootLocator( )
    {
        this.serviceToUriMap = null;
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

        final RedHxServiceRootLocator other = (RedHxServiceRootLocator) obj;

        if (!Objects.equals(this.serviceToUriMap, other.serviceToUriMap))
        {
            return false;
        }

        return true;
    }

    public URI getUri( RedHxServiceRootIdEum serviceId )
    {
        final URI uri = serviceToUriMap.get(serviceId);

        return uri;
    }

    @Override
    public int hashCode( )
    {
        int hash = 7;

        hash = 89 * hash + Objects.hashCode(this.serviceToUriMap);

        return hash;
    }

    @Override
    public String toString( )
    {
        StringBuilder sb = new StringBuilder();

        sb.append("[ ");

        boolean isCommaInserted = false;

        for (RedHxServiceRootIdEum id : serviceToUriMap.keySet())
        {
            if (isCommaInserted)
            {
                sb.append(", ");
              
            }

            URI uri = serviceToUriMap.get(id);

            sb.append("[");
            sb.append(id);
            sb.append(", ");
            sb.append(uri);
            sb.append("]");
              isCommaInserted = true;
        }

        sb.append(" ]");

        return sb.toString();
    }
}
