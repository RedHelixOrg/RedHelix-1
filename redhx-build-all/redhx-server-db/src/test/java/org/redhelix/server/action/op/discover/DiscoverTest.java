package org.redhelix.server.action.op.discover;

import java.util.HashMap;
import java.util.Map;
import org.apache.olingo.client.api.communication.response.ODataInvokeResponse;
import org.apache.olingo.client.api.domain.ClientInvokeResult;
import org.apache.olingo.client.api.domain.ClientValue;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.redhelix.redhx.server.db.RedHexlixAbstractServiceTest;

/**
 * Read a service OData Entity Data Model(EDM) and validate it contains only one schema with the namespace of {@link #RED_HELIX_NAME_SPACE}.
 *
 * @since RedHelix Version 0.2
 * @author Hank Bruning
 *
 */
public final class DiscoverTest
        extends RedHexlixAbstractServiceTest
{

    private static final String SERVICE_NAME = RedHxDiscoverSystemEdmProvider.ET_DISCOVER_SYSTEM_NAME;

    @Test
    public void postDiscoveryTest()
            throws Exception
    {

        final Map<String, ClientValue> parameters = new HashMap<>();
        final boolean returnMinimal = false;
        final ODataInvokeResponse<DiscoverSystemResult> response = callAction(SERVICE_NAME,
                                                                              DiscoverSystemResult.class,
                                                                              parameters,
                                                                              returnMinimal);

        System.out.println("HFB5: discover response=" + response);
        assertEquals(HttpStatusCode.OK.getStatusCode(),
                     response.getStatusCode());
    }

    private class DiscoverSystemResult
            implements ClientInvokeResult
    {
    }
}
