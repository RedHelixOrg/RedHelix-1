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
package org.redhelix.core.output.stream;

import java.io.PrintStream;
import org.redhelix.core.chassis.RedHxChassis;
import org.redhelix.core.chassis.RedHxChassisCollection;
import org.redhelix.core.chassis.RedHxChassisColumnFormatter;
import org.redhelix.core.computer.system.RedHxComputerSystem;
import org.redhelix.core.computer.system.RedHxComputerSystemCollection;
import org.redhelix.core.computer.system.RedHxComputerSystemColumnFormatter;
import org.redhelix.core.util.RedHxColumnOutputFormatter;
import org.redhelix.core.util.RedHxUriPath;

/**
 * static methods to print RedHelix collections on a PrintStream.
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 *
 */
public final class RedHxPrintCollectionsHelper {

  private static final String CHASSIS_SEPERATOR = "***********  Chassis  ************";
  private static final String COMPUTER_SEPERATOR = ".......  Computer System  ........";

  private RedHxPrintCollectionsHelper() {}

  /**
   * print the chassis and the Computer Systems in the chassis to a stream. The data of the chassis
   * and computer system are divided into logical sections and printed.
   *
   * @param chassisCollection
   * @param computerSystemCollection
   */
  public static void printCollections(RedHxColumnOutputFormatter.PrintOrder outputOrder,
      RedHxChassisCollection chassisCollection,
      RedHxComputerSystemCollection computerSystemCollection) {

    /*
     * All communication with the Redfish server is over now print out the results. These are output
     * format parameters.
     */
    final boolean isRowTitlePrinted = true;
    final String columnDelimiter = ":";
    final boolean isSectionHeaderPrinted = true;
    final boolean isPathPrinted = true;
    final RedHxChassisColumnFormatter chassisFormatter;
    final RedHxComputerSystemColumnFormatter computerSystemFormatter;

    /**
     * choose between the row printing out in alpha order or grouped by sections.
     */
    switch (outputOrder) {
      case ALPHA:
        chassisFormatter =
            new RedHxChassisColumnFormatter(isRowTitlePrinted, columnDelimiter, isPathPrinted);
        computerSystemFormatter = new RedHxComputerSystemColumnFormatter(isRowTitlePrinted,
            columnDelimiter, isPathPrinted);

        break;
      case SECTION:
        chassisFormatter = new RedHxChassisColumnFormatter(isRowTitlePrinted, columnDelimiter,
            isSectionHeaderPrinted, isPathPrinted);
        computerSystemFormatter = new RedHxComputerSystemColumnFormatter(isRowTitlePrinted,
            columnDelimiter, isSectionHeaderPrinted, isPathPrinted);

        break;
      default:
        throw new IllegalArgumentException("Unknown print order. " + outputOrder);
    }

    final PrintStream outStream = System.out;

    /**
     * loop thru the chassis and print out the data in column format.
     */
    for (RedHxChassis chassis : chassisCollection) {
      System.out.println(CHASSIS_SEPERATOR);
      chassisFormatter.print(chassis, outStream);

      for (RedHxUriPath computerSystemPath : chassis.getComputerSystemUriPathList()) {
        final RedHxComputerSystem computerSystem =
            computerSystemCollection.getComputerSystem(computerSystemPath);

        if (computerSystem == null) {
          outStream.println(
              "Unable to find a computer system with path " + computerSystemPath.getValue());
        } else {
          System.out.println(COMPUTER_SEPERATOR);
          computerSystemFormatter.print(computerSystem, outStream);
        }
      }
    }
  }
}
