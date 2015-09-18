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
package org.redhelix.fit.utils;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.redhelix.fit.UnsupportedMediaTypeException;

public enum Accept {

  TEXT(ContentType.TEXT_PLAIN.getMimeType(), ".txt"), XML(ContentType.APPLICATION_XML.getMimeType(),
      ".xml"), ATOM(ContentType.APPLICATION_ATOM_XML.getMimeType(), ".xml"), JSON(
          ContentType.APPLICATION_JSON.getMimeType() + ";odata.metadata=minimal",
          ".full.json"), JSON_NOMETA(
              ContentType.APPLICATION_JSON.getMimeType() + ";odata.metadata=none",
              ".full.json"), JSON_FULLMETA(
                  ContentType.APPLICATION_JSON.getMimeType() + ";odata.metadata=full",
                  ".full.json");

  private final String contentTypeV4;

  private final String fileExtension;

  private static Pattern allTypesPattern = Pattern.compile("(.*,)?\\*/\\*([,;].*)?");

  Accept(final String contentTypeV4, final String fileExtension) {
    this.contentTypeV4 = contentTypeV4;
    this.fileExtension = fileExtension;
  }

  @Override
  public String toString() {
    return contentTypeV4;
  }

  public String getExtension() {
    return fileExtension;
  }

  public static Accept parse(final String contentType) {
    return parse(contentType, JSON_NOMETA);
  }

  public static Accept parse(final String contentType, final Accept def) {
    if (StringUtils.isBlank(contentType) || allTypesPattern.matcher(contentType).matches()) {
      return def;
    } else if (contentType.startsWith(JSON_NOMETA.toString())) {
      return JSON_NOMETA;
    } else if (contentType.startsWith(JSON_FULLMETA.toString())) {
      return JSON_FULLMETA;
    } else if (contentType.startsWith(JSON.toString())
        || contentType.startsWith(ContentType.APPLICATION_JSON.getMimeType())) {

      return JSON;
    } else if (contentType.startsWith(XML.toString())) {
      return XML;
    } else if (contentType.startsWith(ATOM.toString())) {
      return ATOM;
    } else if (contentType.startsWith(TEXT.toString())) {
      return TEXT;
    } else {
      throw new UnsupportedMediaTypeException(contentType);
    }
  }
}
