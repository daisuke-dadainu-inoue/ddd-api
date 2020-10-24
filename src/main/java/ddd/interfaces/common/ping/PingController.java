package ddd.interfaces.common.ping;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ping")
public class PingController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response exec() {
        return Response.status(200).entity("OK").build();
    }
}
