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
package org.redhelix.core.service.root;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Convert from a Redfish root service ID to the URL of the service. The URL contains the Redfish
 * protocol version number and path to the Redfish service. This allows each server to present a
 * Redfish implemention of different UDP ports with any URL path the service that a vendor may
 * choose.
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public interface RedHxServiceRootLocator {

  RedHxServiceRootId getServiceRoot();

  URI getUri(String urlString) throws URISyntaxException;

  URI getUri(RedHxServiceRootIdEum serviceId);
}
