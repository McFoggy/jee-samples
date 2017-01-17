package fr.brouillard.oss.jee.samples.conversation.control;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.*;

@SessionScoped
public class Conversations implements Serializable {
    private Set<String> conversationIDs;

    public Conversations() {
    }

    @PostConstruct
    void init() {
        conversationIDs = new LinkedHashSet<>();
    }

    public Collection<String> getExistingConversations() {
        return new ArrayList<>(conversationIDs);
    }

    public void registerConversation(String conversationID) {
        conversationIDs.add(conversationID);
    }

    public void removeConversation(String conversationID) {
        conversationIDs.remove(conversationID);
    }
}
