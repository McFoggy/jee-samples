package fr.brouillard.oss.jee.samples.echo;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("echo")
@Consumes(MediaType.WILDCARD)
public class EchoService {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String echo(@QueryParam("message") String message) {
        return message;
    }
}
