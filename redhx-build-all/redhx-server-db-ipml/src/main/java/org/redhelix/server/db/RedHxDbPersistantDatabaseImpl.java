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

import org.redhelix.server.db.RedHxDbPersistantDatabase;
import org.redhelix.server.db.RedHxDbComputerId;
import java.net.InetAddress;
import org.redhelix.core.computer.system.RedHxComputerSystem;

/**
 *
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public final class RedHxDbPersistantDatabaseImpl implements RedHxDbPersistantDatabase {

  @Override
  public RedHxComputerSystem addComputerSystem(RedHxDbComputerId id,
      RedHxComputerSystem computerSystem) {
    throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
                                                                   // methods, choose Tools |
                                                                   // Templates.
  }

  @Override
  public RedHxComputerSystem getComputerSystem(RedHxDbComputerId id) {
    throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
                                                                   // methods, choose Tools |
                                                                   // Templates.
  }

  @Override
  public RedHxDbComputerId getComputerSystemId(InetAddress ipAddress) {
    throw new UnsupportedOperationException("Not supported yet."); // To change body of generated
                                                                   // methods, choose Tools |
                                                                   // Templates.
  }

}
