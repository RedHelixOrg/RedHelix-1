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
package org.redhelix.core.root;

import org.redhelix.core.service.root.RedHxServiceRootId;
import org.redhelix.core.service.root.RedHxTcpProtocolTypeEnum;
import org.redhelix.core.util.RedHxRedfishProtocolVersionEnum;

/**
 *
 *
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public final class RedHxServiceRootIdImpl implements RedHxServiceRootId {

  private final String serviceRootStr;

  public RedHxServiceRootIdImpl(final RedHxTcpProtocolTypeEnum httpProtocol, final String hostName,
      final int tcpPortNumber, final String servicePrefix,
      final RedHxRedfishProtocolVersionEnum redfishProtocolVersion) {
    StringBuilder sb = new StringBuilder();

    if (httpProtocol == RedHxTcpProtocolTypeEnum.HTTPS) {
      sb.append("https://");
    } else {
      sb.append("http://"); // Redfish spec mandates the Service Root is always reachable by the
      // http
    }
    // protocol.
    sb.append(hostName);
    if (tcpPortNumber != 0) {
      sb.append(":");
      sb.append(tcpPortNumber);
    }
    sb.append("/");

    if (servicePrefix != null) {
      sb.append(servicePrefix);
    }

    sb.append("redfish/");

    if (redfishProtocolVersion == RedHxRedfishProtocolVersionEnum.VERSION_1) {
      sb.append("v1/");
    } else {
      throw new IllegalArgumentException("Invalid argument protocolVersion. The value "
          + redfishProtocolVersion + " is recoganized.");
    }

    serviceRootStr = sb.toString();
  }

  private RedHxServiceRootIdImpl() {
    serviceRootStr = null;
  }

  public String getServiceRootString() {
    return serviceRootStr;
  }
}
