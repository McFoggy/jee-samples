package fr.brouillard.oss.jee.samples.conversation.boundary;

import fr.brouillard.oss.jee.samples.conversation.control.ServletHelper;
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
import java.util.Locale;

@WebServlet("/stop")
public class ConversationStopServlet extends HttpServlet {
    @Inject
    Conversation conversation;

    @Inject
    Timing timingInfo;

    @Inject
    ServletHelper helper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType(MediaType.TEXT_HTML);
        resp.setLocale(Locale.ENGLISH);

        String action = "no conversation to stop";
        if (!conversation.isTransient()) {
            action = String.format("conversation[%s] has been stopped", conversation.getId());
            conversation.end();
        }

        @SuppressWarnings("resource")
        final ServletOutputStream out = resp.getOutputStream();
        out.println("<html><body>");
        out.println("<p>" + action + ".</p>");
        final String startConversationServletURL = helper.getServletURL(ConversationStartServlet.class);

        out.println("<ul>");
        out.println("<li>start <a href=\"" + startConversationServletURL + "\">new conversation</a>.</li>");
        out.println("</ul>");
        out.println("</body></html>");    }
}
