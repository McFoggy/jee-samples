package fr.brouillard.oss.jee.samples.conversation.control;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.Serializable;
import java.time.LocalDateTime;

@ConversationScoped
public class Timing implements Serializable {
    private LocalDateTime time;

    public Timing() {
    }

    @PostConstruct
    public void init() {
        this.time = LocalDateTime.now();
    }

    public JsonObject asJson() {
        return Json.createObjectBuilder().add("time", this.time.toString()).build();
    }
}
