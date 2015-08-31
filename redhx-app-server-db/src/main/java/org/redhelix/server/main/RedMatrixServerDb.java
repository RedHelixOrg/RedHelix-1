/*
 * Copyright 2015 JBlade LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package org.redhelix.server.main;

import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.redhelix.core.util.RedHxServiceRootLocator;

/**
 *
 * @author Hank Bruning Date: $Date$ <br><br>Git SHA: $Id$
 * @since RedHelix Version 0.0.1
 *
 */
public class RedMatrixServerDb
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel",
                           "error");

        final ServiceRootReader serviceRootReader = new ServiceRootReader();

        try
        {
            RedHxServiceRootLocator serviceRootLocator = serviceRootReader.getServiceRootLocator(ServiceRootReader.TcpProtocol.HTTP, "localhost", 9080);

            System.out.println("HFB5: " + serviceRootLocator);
        }
        catch (URISyntaxException ex)
        {
            Logger.getLogger(RedMatrixServerDb.class.getName()).log(Level.SEVERE,
                                                                    null,
                                                                    ex);
        }
    }

}
