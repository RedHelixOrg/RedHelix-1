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

/**
 *
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public abstract class RedHxAbstractStringProperty implements RedHxStringProperty,
    Comparable<RedHxStringProperty> {

  /**
   * The maximum number of characters allowed in a
   */
  private final short maxCharCount;
  private final String propName;

  protected RedHxAbstractStringProperty(int maxCharCount, String propName) {
    if (propName == null) {
      throw new NullPointerException("The argument propName can not be null.");
    }

    if (propName.length() > maxCharCount) {
      throw new IllegalArgumentException(
          "The argument propName contains too many characters. The maximum is " + maxCharCount
              + " and it contains " + propName.length() + ". Invalid string is \"" + propName
              + "\".");
    }

    if (maxCharCount > Short.MAX_VALUE) {
      throw new IllegalArgumentException("The maximum chararcter count can not be greater than "
          + Short.MAX_VALUE + ". It has to fit in short type.");
    }

    this.maxCharCount = (short) maxCharCount;
    this.propName = propName;
  }

  private RedHxAbstractStringProperty() {
    this.maxCharCount = 0;
    this.propName = null;
  }

  @Override
  public int compareTo(RedHxStringProperty other) {
    return propName.compareTo(other.getValue());
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null) {
      return false;
    }

    if (getClass() != obj.getClass()) {
      return false;
    }

    final RedHxStringProperty other = (RedHxStringProperty) obj;

    if (maxCharCount != other.getMaxCharCount()) {
      return false;
    }

    if (propName == null) {
      if (other.getValue() != null) {
        return false;
      }
    } else if (!propName.equals(other.getValue())) {
      return false;
    }

    return true;
  }

  @Override
  public short getMaxCharCount() {
    return maxCharCount;
  }

  public String getValue() {
    return propName;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;

    result = prime * result + maxCharCount;
    result = prime * result + ((propName == null) ? 0 : propName.hashCode());

    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();

    sb.append("{");
    sb.append("value=" + propName);
    sb.append("}");

    return sb.toString();
  }
}
