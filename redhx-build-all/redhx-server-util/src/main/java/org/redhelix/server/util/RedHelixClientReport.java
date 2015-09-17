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
package org.redhelix.server.util;

import java.net.URISyntaxException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.redhelix.core.chassis.RedHxChassisCollection;
import org.redhelix.core.chassis.RedHxChassisParseException;
import org.redhelix.core.computer.system.RedHxComputerSystemCollection;
import org.redhelix.core.output.stream.RedHxPrintCollectionsHelper;
import org.redhelix.core.service.root.RedHxTcpProtocolTypeEnum;
import org.redhelix.core.util.RedHxColumnOutputFormatter;
import org.redhelix.core.util.RedHxHttpResponseException;
import org.redhelix.core.util.RedHxParseException;
import org.redhelix.core.util.RedHxRedfishProtocolVersionEnum;
import org.redhelix.core.util.RedHxUriPath;
import org.redhelix.server.lib.reader.chassis.RedHxChassisCollectionReader;
import org.redhelix.server.lib.reader.chassis.RedHxChassisPathCollectionReader;
import org.redhelix.server.lib.reader.computer.system.RedHxComputerSystemCollectionReader;
import org.redhelix.server.lib.reader.util.RedHxServerConnectionContext;

/**
 * A simple way to test reading the Redfish Chassis JSON mockups from the DMTF. This read all
 * Redfish chassis objects from a HTTP connection and the Redfish computer systems with the chassis
 * and prints them out on the console. To run this requires a server implementingthe Redfish
 * protocol or the Redfish mockup DMTF DSP2043_0.99.0a. In either case the the TCP port of the
 * Redfish server must defined by system properties(below). If you are using DSP2043_0.99.0a each of
 * the Redfish mockup chassis files named index.html files has to be modified and switch the field
 * names "Name" and "Description".
 * <p>
 * This requires these system properties be set.
 * <ul>
 * <li>param_protocol</li>
 * <li>param_hostname</li>
 * <li>param_port</li>
 * <li>param_prefix</li>
 * </ul>
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 */
public class RedHelixClientReport {

  private static final String PROP_NAME_PROTOCOL = "param_protocol";
  private static final String PROP_NAME_HOST_NAME = "param_hostname";
  private static final String PROP_NAME_REMOTE_TCP_PORT = "param_port";
  private static final String PROP_NAME_HTTP_USER_NAME = "param_username";
  private static final String PROP_NAME_HTTP_PASSWORD = "param_password";

  /**
   * @param args
   */
  public static void main(String[] args) {

    /**
     * turn off logging
     */
    System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "error");

    /**
     * When building using command <i>maven package</i> the linux command line<br>
     * java -Dparam_protocol="http" -Dparam_hostname="localhost" -Dparam_port="9080" -jar
     * ./redhx-app-server-db/target/redhx-app-server-db-0.1-SNAPSHOT.jar can be run from the dir
     * RedHelix-1/redhx-build-all to connect to a local Redfish mockup server
     */
    final String protocol = System.getProperty(PROP_NAME_PROTOCOL);

    logMissingProperty(protocol, PROP_NAME_PROTOCOL);

    final String hostname = System.getProperty(PROP_NAME_HOST_NAME);

    logMissingProperty(hostname, PROP_NAME_HOST_NAME);

    final String port = System.getProperty(PROP_NAME_REMOTE_TCP_PORT);

    logMissingProperty(port, PROP_NAME_REMOTE_TCP_PORT);

    /**
     * get the optional HTTP basic security params.
     */
    final String username = System.getProperty(PROP_NAME_HTTP_USER_NAME);
    final String password = System.getProperty(PROP_NAME_HTTP_PASSWORD);

    if ((protocol != null) && (hostname != null) && (port != null)) {
      final int portNum = Integer.parseInt(port);

      openServerConnection(protocol, hostname, portNum, username, password);
    } else {
      printUsage();
    }
  }

  private static void logMissingProperty(String propertyValue, String propertyName) {
    if (propertyValue == null) {
      System.out.println("Error:The system property \"" + propertyName + "\" is not present.");
    }
  }

  /**
   * open a connection to a Redfish enabled server and read the chassis and Computer System JSON
   * messages.
   *
   * @param protocol
   * @param hostname
   * @param portNum
   */
  private static void openServerConnection(String protocol, String hostname, int portNum,
      String username, String password) {
    final RedHxTcpProtocolTypeEnum httpProtocol =
        (protocol.equals("https")) ? RedHxTcpProtocolTypeEnum.HTTPS : RedHxTcpProtocolTypeEnum.HTTP;

    /*
     * create a communication context that will be used to talk with a single Redfish server. This
     * does not allocate and nextwork sockets.
     */
    final RedHxServerConnectionContext ctx;

    if (username == null) {
      ctx = new RedHxServerConnectionContext(RedHxRedfishProtocolVersionEnum.VERSION_1,
          httpProtocol, hostname, portNum);
    } else {
      ctx = new RedHxServerConnectionContext(RedHxRedfishProtocolVersionEnum.VERSION_1,
          httpProtocol, hostname, portNum, username, password);
    }

    try {
      /**
       * open a HTTP connection to the Redfish server provided by the DMTF mockup. The mockup server
       * is started with the command "node server.js"
       *
       */
      ctx.openConnection();

      Set<RedHxUriPath> chassisPathSet;

      try {
        /*
         * read all the chasiss information from the Redfish server. The following three steps are
         * the heart of this program. The form the RedHelix API and all that is necessary to convert
         * from the Redfish JSON messages describing all the chassis and computer systems on a
         * Redfish enabled server to Java classes. All RedHelix raaders classes have a single static
         * method to read data. This allows them be used in a multi-threaded application with out
         * locking or contention between Java threads. This allows RedHelix to scale to large
         * numbers of server.
         *
         * First Step. Read the paths to each chassis in the Redfish server. There may be multiple
         * chasisis Redfish is aware of.
         */
        chassisPathSet = RedHxChassisPathCollectionReader.readChassisCollection(ctx);

        /**
         * Second Step. For each path describing a chassis read all the JSON messages.
         */
        RedHxChassisCollection chassisCollection =
            RedHxChassisCollectionReader.readPaths(ctx, chassisPathSet);

        if (!chassisCollection.isEmpty()) {
          /*
           * Thrid Step. Read all Redfish computer systems found within the chassis colllection.
           */
          RedHxComputerSystemCollection computerSystemCollection =
              RedHxComputerSystemCollectionReader.readPaths(ctx, chassisCollection);

          /**
           * Now all chassis and Computer System data has been read from the Redfish server so print
           * it out on stdio. todo print on in section order. A bug is in printing in alpha order
           * and all blades have the same prompt
           */
          RedHxPrintCollectionsHelper.printCollections(
              RedHxColumnOutputFormatter.PrintOrder.SECTION, chassisCollection,
              computerSystemCollection);
        }
      } catch (RedHxChassisParseException ex) {
        Logger.getLogger(RedHelixClientReport.class.getName()).log(Level.SEVERE, null, ex);
      } catch (RedHxHttpResponseException ex) {
        Logger.getLogger(RedHelixClientReport.class.getName()).log(Level.SEVERE, null, ex);
      } catch (RedHxParseException ex) {
        Logger.getLogger(RedHelixClientReport.class.getName()).log(Level.SEVERE, null, ex);
      }
    } catch (URISyntaxException ex) {
      Logger.getLogger(RedHelixClientReport.class.getName()).log(Level.SEVERE, null, ex);
    } catch (RedHxHttpResponseException ex) {
      Logger.getLogger(RedHelixClientReport.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private static void printUsage() {
    System.out.println("Usage: ");
    System.out.println("Required Parameters:  ");
    System.out.println(PROP_NAME_PROTOCOL + "=https | http");
    System.out.println(PROP_NAME_HOST_NAME + "=redfishServerName");
    System.out.println(PROP_NAME_REMOTE_TCP_PORT + "=redfishServerPortNumber");
    System.out.println("Optional Parameters:  ");
    System.out.println(PROP_NAME_HTTP_USER_NAME + "=httpUserName");
    System.out.println(PROP_NAME_HTTP_PASSWORD + "=httpPassword");
    System.out.println("Example command:");
    System.out.println(
        "java -Dparam_protocol=\"http\" -Dparam_hostname=\"localhost\" -Dparam_port=\"9080\" -jar ./redhx-server-util/target/redhx-server-util-0.1-SNAPSHOT.jar");
  }
}
