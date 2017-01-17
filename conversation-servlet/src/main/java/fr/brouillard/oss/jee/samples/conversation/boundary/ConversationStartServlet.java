package fr.brouillard.oss.jee.samples.conversation.boundary;

import fr.brouillard.oss.jee.samples.conversation.control.Conversations;
import fr.brouillard.oss.jee.samples.conversation.control.Timing;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Locale;

@WebServlet("/start")
public class ConversationStartServlet extends AbstractConversationServlet {

    @Inject
    Timing timingInfo;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType(MediaType.TEXT_HTML);
        resp.setLocale(Locale.ENGLISH);

        conversation.begin();

        conversations.registerConversation(conversation.getId());

        @SuppressWarnings("resource")
        final ServletOutputStream out = resp.getOutputStream();
        out.println("<html><body>");
        out.println("<h3>Context</h3>");
        out.println("<p>Started conversation: " + conversation.getId() + ".</p>");
        out.println("<p>registered time:<code>" + timingInfo.asJson() + "</code></p>");
        final String viewConversationServletURL = AppURLs.withConversationId(AppURLs.VIEW, conversation.getId());
        final String startConversationServletURL = AppURLs.START;
        final String stopConversationServletURL = AppURLs.withConversationId(AppURLs.STOP, conversation.getId());;

        out.println("<h3>Actions</h3>");
        out.println("<ul>");
        out.println("<li>view conversation <a href=\"" + viewConversationServletURL + "\">conversation[" + conversation.getId() + "]</a>.</li>");
        out.println("<li>stop conversation <a href=\"" + stopConversationServletURL + "\">conversation[" + conversation.getId() + "]</a>.</li>");
        out.println("<li>start <a href=\"" + startConversationServletURL + "\">new conversation</a>.</li>");
        out.println("</ul>");

        addExistingConversations(out);
        out.println("</body></html>");    }

}
