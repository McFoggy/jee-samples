package fr.brouillard.oss.jee.samples.conversation.boundary;

import fr.brouillard.oss.jee.samples.conversation.control.Conversations;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by awxgx on 17/01/2017.
 */
public class AbstractConversationServlet extends HttpServlet {
    @Inject
    protected Conversation conversation;

    @Inject
    protected Conversations conversations;

    protected void addExistingConversations(ServletOutputStream out) throws IOException {
        Collection<String> existingConversations = conversations.getExistingConversations();
        if(!existingConversations.isEmpty()) {
            out.println("<h3>View existing conversations</h3>");
            out.println("<ul>");
            for (String id: existingConversations) {
                out.println("<li><a href=\"" + AppURLs.withConversationId(AppURLs.VIEW, id) + "\">conversation[" + id + "]</a>.</li>");
            }
            out.println("</ul>");
        }
    }
}
