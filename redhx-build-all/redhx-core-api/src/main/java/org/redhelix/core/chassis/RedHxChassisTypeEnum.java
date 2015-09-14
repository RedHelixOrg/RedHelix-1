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
package org.redhelix.core.chassis;

/**
 *
 *
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 */
public enum RedHxChassisTypeEnum {
  BLADE(
      "An enclosed or semi-enclosed, typically vertically-oriented, system chassis which must be plugged into a multi-system chassis to function normally"), CARD(
      "A loose device or circuit board intended to be installed in a system or other enclosure"), CARTRIDGE(
      "A small self-contained system intended to be plugged into a multi-system chassis"), COMPONENT(
      "A small chassis, card, or device which contains devices for a particular subsystem or function"), DRAWER(
      "An enclosed or semi-enclosed, typically horizontally-oriented, system chassis which may be slid into a multi-system chassis."), ENCLOSURE(
      "A generic term for a chassis that does not fit any other description"), EXPANSION(
      "A chassis which expands the capabilities or capacity of another chassis"), MODULE(
      "A small, typically removable, chassis or card which contains devices for a particular subsystem or function"), OTHER(
      "A chassis that does not fit any of these definitions"), POD(
      "A collection of equipment racks in a large, likely transportable, container"), RACK(
      "An equipment rack, typically a 19-inch wide freestanding unit"), RACKMOUNT(
      "A single system chassis designed specifically for mounting in an equipment rack"), ROW(
      "A collection of equipment racks"), SHELF(
      "An enclosed or semi-enclosed, typically horizontally-oriented, system chassis which must be plugged into a multi-system chassis to function normally"), SIDECAR(
      "A chassis that mates mechanically with another chassis to expand its capabilities or capacity"), SLED(
      "An enclosed or semi-enclosed, system chassis which must be plugged into a multi-system chassis to function normally similar to a blade type chassis."), STANDALONE(
      "A single, free-standing system, commonly called a tower or desktop chassis"), ZONE(
      "A logical division or portion of a physical chassis that contains multiple devices or systems that cannot be physically separated");

  private RedHxChassisTypeEnum(String desc) {}
}
