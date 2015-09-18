/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.redhelix.fit.rest;

import java.util.List;
import java.util.Map;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.redhelix.fit.utils.ConstantKey;
import org.redhelix.fit.utils.Constants;

public class XHTTPMethodInterceptor extends AbstractPhaseInterceptor<Message> {

  public XHTTPMethodInterceptor() {
    super(Phase.PRE_PROTOCOL);
  }

  @Override
  public void handleMessage(final Message message) throws Fault {
    @SuppressWarnings("unchecked")
    final Map<String, List<String>> headers =
        (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);

    if (headers.containsKey(Constants.get(ConstantKey.XHTTP_HEADER_NAME))) {
      message.put(Message.HTTP_REQUEST_METHOD,
          headers.get(Constants.get(ConstantKey.XHTTP_HEADER_NAME)).iterator().next());
    }
  }
}
