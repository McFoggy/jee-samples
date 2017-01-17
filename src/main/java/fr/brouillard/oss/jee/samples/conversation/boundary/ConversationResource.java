package fr.brouillard.oss.jee.samples.conversation.boundary;

import fr.brouillard.oss.jee.samples.conversation.control.Timing;

import javax.annotation.Resource;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("jaxrs")
@Produces(MediaType.APPLICATION_JSON)
public class ConversationResource {
    @Inject Conversation conversation;

    @Context UriInfo uriInfo;

    @Inject
    Timing timingInfo;

    @Path("start")
    @GET
    public Response startConversation() {
        String action = null;
        if (conversation.isTransient()) {
            conversation.begin();
            action = String.format("started new conversation: %s", conversation.getId());
        }

        JsonObject jo = Json.createObjectBuilder()
                .add("action", action)
                .add("data", timingInfo.asJson())
                .add("links", Json.createObjectBuilder()
                        .add("start", uriInfo.getBaseUriBuilder().path(ConversationResource.class, "startConversation").build().toASCIIString())
                        .add("view", uriInfo.getBaseUriBuilder().path(ConversationResource.class, "startConversation").queryParam("cid", conversation.getId()).build().toASCIIString())
                        .add("stop", uriInfo.getBaseUriBuilder().path(ConversationResource.class, "stopConversation").build().toASCIIString())
                        .build()
                )
                .build();

        return Response.ok().entity(jo).build();
    }

    @Path("view")
    @GET
    public Response viewConversation() {
        if (conversation.isTransient()) {
            return Response.noContent().build();
        }

        JsonObject jo = Json.createObjectBuilder()
                .add("action", String.format("accessing data of conversation:", conversation.getId()))
                .add("data", timingInfo.asJson())
                .add("links", Json.createObjectBuilder()
                        .add("start", uriInfo.getBaseUriBuilder().path(ConversationResource.class, "startConversation").build().toASCIIString())
                        .add("view", uriInfo.getBaseUriBuilder().path(ConversationResource.class, "viewConversation").build().toASCIIString())
                        .add("stop", uriInfo.getBaseUriBuilder().path(ConversationResource.class, "stopConversation").build().toASCIIString())
                        .build()
                )
                .build();

        return Response.ok().entity(jo).build();
    }

    @Path("stop")
    @GET
    public Response stopConversation() {
        String action = "no conversation found, no action performed";
        if (!conversation.isTransient()) {
            action = String.format("conversation %s closed", conversation.getId());
            conversation.end();
        }

        JsonObject jo = Json.createObjectBuilder()
            .add("action", action)
            .add("links", Json.createObjectBuilder()
                    .add("start", uriInfo.getBaseUriBuilder().path(ConversationResource.class, "startConversation").build().toASCIIString())
                    .build()
            )
            .build();

        return Response.ok().entity(jo).build();
    }
}
