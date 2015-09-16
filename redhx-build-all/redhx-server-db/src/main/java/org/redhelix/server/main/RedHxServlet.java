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
package org.redhelix.server.main;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataHttpHandler;
import org.apache.olingo.server.api.ServiceMetadata;
import org.apache.olingo.server.api.edmx.EdmxReference;
import org.redhelix.server.message.op.chassis.RedHxChassisCollectionProcessor;
import org.redhelix.server.message.op.discover.RedHxDiscoveryProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Hank Bruning
 */
public class RedHxServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static final Logger LOG = LoggerFactory.getLogger(RedHxServlet.class);

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "RedHelix OData v4 service";
  }

  @Override
  public void init() throws ServletException {
    super.init();
  }

  @Override
  protected void service(final HttpServletRequest req, final HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      /*
       * create odata handler and configure it with CsdlEdmProvider and Processor. todo: this needs
       * to be rewritten the list does not have to be recreated each time. The advantage of this is
       * that the multiple threads calling this are seperated.
       */
      OData odata = OData.newInstance();
      ServiceMetadata edm = odata.createServiceMetadata(new RedHxServiceEdmProvider(),
          new ArrayList<EdmxReference>());
      ODataHttpHandler handler = odata.createHandler(edm);

      handler.register(new RedHxChassisCollectionProcessor());
      handler.register(new RedHxDiscoveryProcessor());

      // let the handler do the work
      handler.process(req, resp);
    } catch (RuntimeException ex) {
      LOG.error("Server Error occurred in RedHxServlet", ex);

      throw new ServletException(ex);
    }
  }
}
