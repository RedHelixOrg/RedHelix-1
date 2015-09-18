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
package org.redhelix.fit.rproxy;

import java.io.IOException;
import java.io.Writer;

import org.apache.http.HttpEntityEnclosingRequest;
import org.esigate.Renderer;

public class LinkRewriteRenderer implements Renderer {

  private static final char SLASH = '/';

  private String baseUrl;

  private String replacementUrl;

  public LinkRewriteRenderer(final String baseUrl, final String visibleBaseUrl) {
    if (visibleBaseUrl == null || visibleBaseUrl.isEmpty()) {
      throw new IllegalArgumentException("Need to specify baseUrl and visibleBaseUrl");
    }

    this.baseUrl = removeLeadingSlash(baseUrl);
    replacementUrl = removeLeadingSlash(visibleBaseUrl);
  }

  private String removeLeadingSlash(final String src) {
    final int lastCharPosition = src.length() - 1;
    return src.charAt(lastCharPosition) == SLASH ? src.substring(0, lastCharPosition) : src;
  }

  @Override
  public void render(final HttpEntityEnclosingRequest httpRequest, final String src,
      final Writer out) throws IOException {

    out.write(src.replaceAll(baseUrl, replacementUrl));
  }
}
