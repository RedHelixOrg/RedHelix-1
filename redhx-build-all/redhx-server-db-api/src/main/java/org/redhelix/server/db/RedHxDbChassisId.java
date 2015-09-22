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
 *
 */
package org.redhelix.server.db;

/**
 * an id that uniquely identifies a chassis in RedHelix.For a single Computer System the string may
 * change each time the JVM is started.
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public interface RedHxDbChassisId extends Comparable<RedHxDbChassisId> {

  @Override

  /**
   * get the chassisId as a string. For a single Computer System the string may change each time the
   * JVM is started.
   *
   * @return a string with the prefix "Cha".
   */
  String toString();
}
