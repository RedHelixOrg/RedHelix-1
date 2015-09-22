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

import org.redhelix.server.db.RedHxDbChassisId;

/**
 *
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
final class ChassisId extends AbstractPersistantId implements RedHxDbChassisId {

  private static final String ID_PREFIX = "Cha";

  ChassisId(int seqId) {
    super(seqId, ID_PREFIX);
  }

  @Override
  public int compareTo(RedHxDbChassisId other) {
    int retVal = this.getSeqId() - super.getSeqId();

    if (retVal != 0) {
      retVal = this.getSeqId() < super.getSeqId() ? -1 : 1;
    }

    return retVal;
  }
}
