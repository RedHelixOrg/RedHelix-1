package org.redhelix.redhx.server.db;

import java.io.IOException;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.EdmMetadataRequest;
import org.apache.olingo.client.api.communication.request.retrieve.ODataServiceDocumentRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.edm.Edm;
import org.apache.olingo.commons.api.edm.EdmSchema;
import org.apache.olingo.commons.api.format.ContentType;
import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

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
/**
 * Read a service OData Entity Data Model(EDM) and validate it contains only one schema with the
 * namespace of {@link #RED_HELIX_NAME_SPACE}.
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public final class EdmTest {

  private static final String RED_HELIX_NAME_SPACE = "RedHelix.OData.moon";

  @BeforeClass
  public static void beforeClass() throws Exception {
    TJWSEmbeddedJaxrsServer embeddedServer = RedHelixTestServer.startServer();
  }

  @Test
  public void getMetaData() throws Exception {
    final ODataClient client = ODataClientFactory.getClient();
    final String url =
        "http://localhost:" + RedHelixTestServer.TCP_PORT_NUMBER + RedHelixTestServer.HTTP_URL;
    final ODataServiceDocumentRequest req =
        client.getRetrieveRequestFactory().getServiceDocumentRequest(url);

    req.setFormat(ContentType.APPLICATION_XML);

    final Edm redHelixEdm = readEdm(client, url);

    Assert.assertEquals(1, redHelixEdm.getSchemas().size());

    for (EdmSchema schema : redHelixEdm.getSchemas()) {
      Assert.assertEquals(RED_HELIX_NAME_SPACE, schema.getNamespace());
    }
  }

  private Edm readEdm(ODataClient client, String serviceUrl) throws IOException {
    EdmMetadataRequest request = client.getRetrieveRequestFactory().getMetadataRequest(serviceUrl);
    ODataRetrieveResponse<Edm> response = request.execute();

    return response.getBody();
  }
}
