package fr.brouillard.oss.jee.samples.conversation.boundary;

public class AppURLs {
    public static final String VIEW = "view";
    public static final String START = "start";
    public static final String STOP = "stop";

    public static String withConversationId(String baseURL, String id) {
        return baseURL + "?cid=" + id;
    }
}
