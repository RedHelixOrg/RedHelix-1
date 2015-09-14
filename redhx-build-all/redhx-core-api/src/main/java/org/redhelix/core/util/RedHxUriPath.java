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
package org.redhelix.core.util;

import java.util.List;

/**
 * The path to a Redfish resource. This interface is added to a URI and the optional query options.
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public interface RedHxUriPath extends RedHxStringProperty {

  /**
   * The maximum number of characters allowed in a manufacturer name.
   */
  public final static short MAX_CHAR_COUNT_RED_HELIX_DEFINED = 250; // arbitrary

  public static String getPathListAsString(List<RedHxUriPath> list) {
    StringBuilder sb = new StringBuilder();

    for (RedHxUriPath path : list) {
      if (sb.length() > 0) {
        sb.append(", ");
      }

      sb.append(path.getValue());
    }

    return sb.toString();
  }
}
