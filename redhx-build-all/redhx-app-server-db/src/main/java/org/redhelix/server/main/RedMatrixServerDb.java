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
package org.redhelix.server.main;

import java.net.URISyntaxException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.redhelix.core.chassis.RedHxChassis;
import org.redhelix.core.chassis.RedHxChassisCollection;
import org.redhelix.core.chassis.RedHxChassisColumnFormatter;
import org.redhelix.core.chassis.RedHxChassisParseException;
import org.redhelix.core.service.root.RedHxTcpProtocolTypeEnum;
import org.redhelix.core.util.RedHxHttpResponseException;
import org.redhelix.core.util.RedHxParseException;
import org.redhelix.core.util.RedHxRedfishProtocolVersionEnum;
import org.redhelix.core.util.RedHxUriPath;
import org.redhelix.server.main.reader.chassis.RedHxChassisCollectionReader;
import org.redhelix.server.main.reader.chassis.RedHxChassisPathCollectionReader;

/**
 * A simple way to test reading the Redfish Chassis JSON mockups from the DMTF. This requires the DMTF DSP2043_0.99.0a is running on TCP
 * port 9080. Also each of the chassis index.html files has to be modified and switch the field names "Name" and "Description".
 *
 * @since RedHelix Version 0.1
 * @author Hank Bruning
 */
public class RedMatrixServerDb
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {

        /**
         * turn off logging
         */
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel",
                           "error");

        /*
         * create a communication context that will be used to talk with a single Redfish server. This does not
         * allocate and nextwork sockets.
         */
        RedHxServerConnectionContext ctx = new RedHxServerConnectionContext(RedHxRedfishProtocolVersionEnum.VERSION_1);

        try
        {
            /**
             * open a HTTP connection to the Redfish server provided by the DMTF mockup. The mockup server is started with the command "node
             * server.js"
             */
            ctx.openConnection(RedHxTcpProtocolTypeEnum.HTTP,
                               "localhost",
                               9080,
                               "mockup1");

            Set<RedHxUriPath> chassisPathSet;

            try
            {
                /*
                 * read all the chasiss information from the Redfish server. The next two lines are the heart of this program. Both are
                 * part of the RedHelix API and all that is necessary to convert from the Redfish JSON messages describing all the chassis
                 * on the server to Java classes. All RedHelix raaders classes have a single static method to read data. This allows them
                 * be used in a multi-threaded application with out locking or contention between Java threads. This allows RedHelix to
                 * scale to large numbers of server.
                 *
                 * First read the paths to each chassis in the Redfish server.
                 */
                chassisPathSet = RedHxChassisPathCollectionReader.readChassisCollection(ctx);

                /**
                 * Second, for each path read all the chassis data. That is it, your done.
                 */
                RedHxChassisCollection chassisCollection = RedHxChassisCollectionReader.chassisCollectionReader(ctx,
                                                                                                                chassisPathSet);

                /*
                 * All communication with the Redfish server is over now print out the results. These are output format parameters.
                 */
                final boolean isRowTitlePrinted = true;
                final String columnDelimiter = ":";
                final boolean isSectionHeaderPrinted = true;
                final boolean isPathPrinted = true;
                final RedHxChassisColumnFormatter formatter = new RedHxChassisColumnFormatter(isRowTitlePrinted,
                                                                                              columnDelimiter,
                                                                                              isSectionHeaderPrinted,
                                                                                              isPathPrinted);

                /**
                 * loop thru the chassis and print out the data in column format.
                 */
                for (RedHxChassis chassis : chassisCollection)
                {
                    formatter.print(chassis,
                                    System.out);
                }
            }
            catch (RedHxChassisParseException ex)
            {
                Logger.getLogger(RedMatrixServerDb.class.getName()).log(Level.SEVERE,
                                                                        null,
                                                                        ex);
            }
            catch (RedHxHttpResponseException ex)
            {
                Logger.getLogger(RedMatrixServerDb.class.getName()).log(Level.SEVERE,
                                                                        null,
                                                                        ex);
            }
            catch (RedHxParseException ex)
            {
                Logger.getLogger(RedMatrixServerDb.class.getName()).log(Level.SEVERE,
                                                                        null,
                                                                        ex);
            }
        }
        catch (URISyntaxException ex)
        {
            Logger.getLogger(RedMatrixServerDb.class.getName()).log(Level.SEVERE,
                                                                    null,
                                                                    ex);
        }
        catch (RedHxHttpResponseException ex)
        {
            Logger.getLogger(RedMatrixServerDb.class.getName()).log(Level.SEVERE,
                                                                    null,
                                                                    ex);
        }
    }
}
