package org.redhelix.redhx.server.db.test2;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 * @since 12.03.15 08:33
 */
public class InMemoryRestTest
{

    public static MyResource sut = new MyResource();
    public static InMemoryRestServer server;

    @AfterClass
    public static void afterClass()
            throws Exception
    {
        server.close();
    }

    @BeforeClass
    public static void beforeClass()
            throws Exception
    {
        server = InMemoryRestServer.create(sut);
    }

    @Test
    public void postSimpleBody()
            throws Exception
    {
        Builder builder = server.newRequest("/myresource").request();

        builder.accept(MediaType.APPLICATION_JSON);

        Response response = builder.buildPost(Entity.text("42")).invoke();

        Assert.assertEquals(Response.Status.OK.getStatusCode(),
                            response.getStatus());
        System.out.println("HFB5: start read entity. respons=" + response.getMediaType() + ", " + response.getEntityTag());

        MyModel myModel = response.readEntity(MyModel.class);

        Assert.assertEquals(42,
                            myModel.getResult());
    }

    @Path("myresource")
    public static class MyResource
    {
//      @POST
//      @Consumes(MediaType.TEXT_PLAIN)
//      @Produces(MediaType.APPLICATION_JSON)

        @POST
        //  @Path("/myresource/{result}")
        @Produces(MediaType.APPLICATION_JSON)
        public MyModel createMyModel(int number)
        {
            return new MyModel(number);
        }
    }
}
