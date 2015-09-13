/*
 * Copyright 2015 JBlade LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
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
import org.redhelix.server.main.chassis.RedHxChassisCollectionProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Hank Bruning
 */
public class RedHxServlet
        extends HttpServlet
{

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(RedHxServlet.class);

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "RedHelix OData v4 service";
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doGet(HttpServletRequest request,
//                         HttpServletResponse response)
//            throws ServletException,
//                   IOException
//    {
//        processRequest(request,
//                       response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request,
//                          HttpServletResponse response)
//            throws ServletException,
//                   IOException
//    {
//        processRequest(request,
//                       response);
//    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request,
//                                  HttpServletResponse response)
//            throws ServletException,
//                   IOException
//    {
//        response.setContentType("text/html;charset=UTF-8");
//
//        try (PrintWriter out = response.getWriter())
//        {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet RedHxDemoServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet RedHxDemoServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//
//        System.out.println("HFB5: processing request.");
//    }
    @Override
    protected void service(final HttpServletRequest req,
                           final HttpServletResponse resp)
            throws ServletException,
                   IOException
    {

        try
        {
            // create odata handler and configure it with CsdlEdmProvider and Processor
            OData odata = OData.newInstance();
            ServiceMetadata edm = odata.createServiceMetadata(new RedHxServiceEdmProvider(),
                                                              new ArrayList<EdmxReference>());
            ODataHttpHandler handler = odata.createHandler(edm);

            handler.register(new RedHxChassisCollectionProcessor());

            // let the handler do the work
            handler.process(req,
                            resp);
        }
        catch (RuntimeException ex)
        {
            LOG.error("Server Error occurred in RedHxServlet",
                      ex);

            throw new ServletException(ex);
        }
    }
}
