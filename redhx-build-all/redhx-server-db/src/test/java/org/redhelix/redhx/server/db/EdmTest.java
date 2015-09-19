package org.redhelix.redhx.server.db;

import java.io.IOException;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.EdmMetadataRequest;
import org.apache.olingo.client.api.communication.request.retrieve.ODataServiceDocumentRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.commons.api.edm.Edm;
import org.apache.olingo.commons.api.edm.EdmSchema;
import org.apache.olingo.commons.api.format.ContentType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Read a service OData Entity Data Model(EDM) and validate it contains only one schema with the namespace of {@link #RED_HELIX_NAME_SPACE}.
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public final class EdmTest
        extends RedHexlixAbstractServiceTest
{

    private static final String RED_HELIX_NAME_SPACE = "RedHelix.OData.moon";

    @Test
    public void getEdmTest()
            throws Exception
    {
        final ODataClient client = getODataClient();
        final String serviceUrl = getServiceUrl();
        final ODataServiceDocumentRequest reqeust = client.getRetrieveRequestFactory().getServiceDocumentRequest(serviceUrl);

        reqeust.setFormat(ContentType.APPLICATION_XML);

        final Edm redHelixEdm = readEdm(client,
                                        serviceUrl);

        Assert.assertEquals(1,
                            redHelixEdm.getSchemas().size());

        for (EdmSchema schema : redHelixEdm.getSchemas())
        {
            Assert.assertEquals(RED_HELIX_NAME_SPACE,
                                schema.getNamespace());
        }
    }

    private Edm readEdm(ODataClient client,
                        String serviceUrl)
            throws IOException
    {
        EdmMetadataRequest request = client.getRetrieveRequestFactory().getMetadataRequest(serviceUrl);
        ODataRetrieveResponse<Edm> response = request.execute();

        return response.getBody();
    }
}
