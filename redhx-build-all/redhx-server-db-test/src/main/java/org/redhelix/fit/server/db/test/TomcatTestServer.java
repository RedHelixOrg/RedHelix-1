
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package org.redhelix.fit.server.db.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.startup.Tomcat;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Server for integration tests.
 */
public class TomcatTestServer {

  private static final Logger LOG = LoggerFactory.getLogger(TomcatTestServer.class);
  private static TestServerBuilder builder;
  private static final String TEST_PROPERTIES_FILE_NAME = "redhelix-db-server-test.properties";
  private final Tomcat tomcat;

  private TomcatTestServer(final Tomcat tomcat) {
    this.tomcat = tomcat;
  }

  public static int extractPortParam(final String portParameter) {
    String[] portParam = portParameter.split("=");

    if (portParam.length == 2) {
      try {
        return Integer.parseInt(portParam[1]);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException(
            "Port parameter (" + portParameter + ") could not be parsed.");
      }
    }

    throw new IllegalArgumentException(
        "Port parameter (" + portParameter + ") could not be parsed.");
  }

  public static TestServerBuilder init(final int port) {
    if (builder == null) {
      builder = new TestServerBuilder(port);
    }

    return builder;
  }

  public void invalidateAllSessions() {
    SessionHolder.invalidateAllSession();
  }

  public static void main(final String[] params) {
    System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "debug");

    try {
      LOG.trace("Start tomcat embedded server from main()");

      TestServerBuilder server = TomcatTestServer.init(9180);

      server.addStaticContent("/stub/StaticService/V40/OpenType.svc/$metadata",
          "V40/openTypeMetadata.xml");
      server.addStaticContent("/stub/StaticService/V40/Demo.svc/$metadata", "V40/demoMetadata.xml");
      server.addStaticContent("/stub/StaticService/V40/Static.svc/$metadata", "V40/metadata.xml");

      boolean keepRunning = false;

      for (String param : params) {
        if (param.equalsIgnoreCase("keeprunning")) {
          keepRunning = true;
        } else if (param.equalsIgnoreCase("addwebapp")) {
          server.addWebApp();
        } else if (param.startsWith("port")) {
          server.atPort(extractPortParam(param));
        }
      }

      if (keepRunning) {
        LOG.info("...and keep server running.");
        server.startAndWait();
      } else {
        LOG.info("...and run as long as the thread is running.");
        server.start();
      }
    } catch (IOException e) {
      throw new RuntimeException("Failed to start Tomcat server from main method.", e);
    } catch (LifecycleException e) {
      throw new RuntimeException("Failed to start Tomcat server from main method.", e);
    }
  }

  public void stop() throws LifecycleException {
    if ((tomcat.getServer() != null)
        && (tomcat.getServer().getState() != LifecycleState.DESTROYED)) {
      if (tomcat.getServer().getState() != LifecycleState.STOPPED) {
        tomcat.stop();
      }

      tomcat.destroy();
    }
  }

  public static class SessionHolder implements HttpSessionListener {

    private static final Map<ServletContext, Set<HttpSession>> ALL_SESSIONS =
        Collections.synchronizedMap(new HashMap<ServletContext, Set<HttpSession>>());

    public static void invalidateAllSession() {
      synchronized (ALL_SESSIONS) {
        LOG.info("Invalidated sessions...");

        for (Map.Entry<ServletContext, Set<HttpSession>> e : ALL_SESSIONS.entrySet()) {
          for (HttpSession s : e.getValue()) {
            s.invalidate();
          }
        }

        ALL_SESSIONS.clear();
        LOG.info("...Invalidated all sessions.");
      }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
      LOG.info("Created session: {}", se);

      ServletContext c = se.getSession().getServletContext();
      Set<HttpSession> set = ALL_SESSIONS.get(c);

      if (set == null) {
        set = new HashSet<HttpSession>();
        ALL_SESSIONS.put(c, set);
      }

      set.add(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
      LOG.info("Destroy session: {}", se);
    }
  }

  public static class StaticContent extends HttpServlet {

    private static final long serialVersionUID = 6850459331131987539L;
    private final String resource;
    private final String uri;

    public StaticContent(final String uri, final String resource) {
      this.uri = uri;
      this.resource = resource;
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
        throws ServletException, IOException {
      String result;
      File resourcePath = new File(resource);

      if (resourcePath.exists() && resourcePath.isFile()) {
        FileInputStream fin = new FileInputStream(resourcePath);

        result = IOUtils.toString(fin, "UTF-8");
        LOG.info("Mapped uri '{}' to resource '{}'.", uri, resource);
        LOG.trace("Resource content {\n\n{}\n\n}", result);
      } else {
        LOG.debug("Unable to load resource for path {} as stream.", uri);
        result = "<html><head/><body>No resource for path found</body>";
      }

      resp.getOutputStream().write(result.getBytes());
    }
  }

  public static class TestServerBuilder {

    private static final String TOMCAT_BASE_DIR = "TOMCAT_BASE_DIR";
    private static final String PROJECT_WEB_APP_DIR = "PROJECT_WEB_APP_DIR";
    private static final String PROJECT_RESOURCES_DIR = "PROJECT_RESOURCES_DIR";
    private final File baseDir;
    private final Tomcat tomcat;
    private Context baseContext = null;
    private Properties properties;
    private TomcatTestServer server;

    private TestServerBuilder(final int fixedPort) {
      initializeProperties();

      // baseDir = new File(System.getProperty("java.io.tmpdir"), "tomcat-test");
      baseDir = getFileForDirProperty(TOMCAT_BASE_DIR);

      if (!baseDir.exists() && !baseDir.mkdirs()) {
        throw new RuntimeException(
            "Unable to create temporary test directory at {" + baseDir.getAbsolutePath() + "}");
      }

      //
      tomcat = new Tomcat();
      tomcat.setBaseDir(baseDir.getParentFile().getAbsolutePath());
      tomcat.setPort(fixedPort);
      tomcat.getHost().setAppBase(baseDir.getAbsolutePath());
      tomcat.getHost().setDeployOnStartup(true);
      tomcat.getConnector().setSecure(false);
      tomcat.setSilent(true);
      tomcat.addUser("odatajclient", "odatajclient");
      tomcat.addRole("odatajclient", "odatajclient");
    }

    public TestServerBuilder addServlet(final Class<? extends HttpServlet> factoryClass,
        final String path)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
      if (server != null) {
        return this;
      }

      String servletClassname = factoryClass.getName();
      HttpServlet httpServlet = (HttpServlet) Class.forName(servletClassname).newInstance();
      Context cxt = getContext();
      String randomServletId = UUID.randomUUID().toString();

      Tomcat.addServlet(cxt, randomServletId, httpServlet);
      cxt.addServletMapping(path, randomServletId);

      //
      LOG.info("Added servlet {} at context {} (mapping id={}).", servletClassname, path);
      LOG.info("Added (mapping id={}).", randomServletId);

      return this;
    }

    public TestServerBuilder addServlet(final HttpServlet httpServlet, final String path)
        throws IOException {
      String name = UUID.randomUUID().toString();

      return addServlet(httpServlet, name, path);
    }

    public TestServerBuilder addServlet(final HttpServlet httpServlet, final String name,
        final String path) throws IOException {
      if (server != null) {
        return this;
      }

      Context cxt = getContext();

      Tomcat.addServlet(cxt, name, httpServlet);
      cxt.addServletMapping(path, name);

      //
      LOG.info("Added servlet {} at context {}.", name, path);

      return this;
    }

    public TestServerBuilder addStaticContent(final String uri, final String resourceName)
        throws IOException {
      File targetResourcesDir = getFileForDirProperty(PROJECT_RESOURCES_DIR);
      String resource = new File(targetResourcesDir, resourceName).getAbsolutePath();

      LOG.info("Added static content from '{}' at uri '{}'.", resource, uri);

      StaticContent staticContent = new StaticContent(uri, resource);

      return addServlet(staticContent, String.valueOf(uri.hashCode()), uri);
    }

    public TestServerBuilder addWebApp() throws IOException {
      return addWebApp(true);
    }

    public TestServerBuilder addWebApp(final boolean copy) throws IOException {
      if (server != null) {
        return this;
      }

      File webAppProjectDir = getFileForDirProperty(PROJECT_WEB_APP_DIR);
      final File webAppDir;

      if (copy) {
        webAppDir = new File(baseDir, webAppProjectDir.getName());
        FileUtils.deleteDirectory(webAppDir);

        if (!webAppDir.mkdirs()) {
          throw new RuntimeException(
              "Unable to create temporary directory at {" + webAppDir.getAbsolutePath() + "}");
        }

        FileUtils.copyDirectory(webAppProjectDir, webAppDir);
      } else {
        webAppDir = webAppProjectDir;
      }

      String contextPath = "/stub";
      Context context =
          tomcat.addWebapp(tomcat.getHost(), contextPath, webAppDir.getAbsolutePath());

      context.setLoader(new WebappLoader(Thread.currentThread().getContextClassLoader()));
      LOG.info("Webapp {} at context {}.", webAppDir.getName(), contextPath);

      return this;
    }

    public void atPort(final int port) {
      tomcat.setPort(port);
    }

    public void enableLogging(final Level level) {
      tomcat.setSilent(false);

      try {
        Handler fileHandler =
            new FileHandler(tomcat.getHost().getAppBase() + "/catalina.out", true);

        fileHandler.setFormatter(new SimpleFormatter());
        fileHandler.setLevel(level);
        java.util.logging.Logger.getLogger("").addHandler(fileHandler);
      } catch (IOException e) {
        throw new RuntimeException("Unable to configure embedded tomcat logging.");
      }
    }

    public TomcatTestServer start() throws LifecycleException {
      if (server != null) {
        return server;
      }

      baseContext.addApplicationListener(SessionHolder.class.getName());
      tomcat.start();
      LOG.info("Started server at endpoint " + tomcat.getServer().getAddress() + ":"
          + tomcat.getConnector().getPort() + " (with base dir: " + baseDir.getAbsolutePath());
      server = new TomcatTestServer(tomcat);

      return server;
    }

    public void startAndWait() throws LifecycleException {
      start();
      tomcat.getServer().await();
    }

    private Context getContext() {
      if (baseContext == null) {
        baseContext = tomcat.addContext("/", baseDir.getAbsolutePath());
      }

      return baseContext;
    }

    private File getFileForDirProperty(final String propertyName) {
      File targetFile = new File(properties.getProperty(propertyName));

      if (targetFile.exists() && targetFile.isDirectory()) {
        return targetFile;
      } else if (targetFile.mkdirs()) {
        return targetFile;
      }

      URL targetURL =
          Thread.currentThread().getContextClassLoader().getResource(targetFile.getPath());

      if (targetURL == null) {
        throw new RuntimeException(
            "Project target was not found at '" + properties.getProperty(propertyName) + "'.");
      }

      return new File(targetURL.getFile());
    }

    private void initializeProperties() {
      InputStream propertiesFile = Thread.currentThread().getContextClassLoader()
          .getResourceAsStream(TEST_PROPERTIES_FILE_NAME);

      try {
        properties = new Properties();
        properties.load(propertiesFile);
      } catch (IOException e) {
        LOG.error("Unable to load properties for embedded tomcat test server. File "
            + TEST_PROPERTIES_FILE_NAME);

        throw new RuntimeException(
            "Unable to load properties for embedded tomcat test server. File "
                + TEST_PROPERTIES_FILE_NAME);
      }
    }
  }
}
