package fr.brouillard.oss.jee.samples.conversation.control;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@ConversationScoped
public class Timing implements Serializable {
    private ZonedDateTime time;

    public Timing() {
    }

    @PostConstruct
    public void init() {
        this.time = ZonedDateTime.now();
    }

    public JsonObject asJson() {
        ZonedDateTime utcDateTime = time.withZoneSameInstant(ZoneOffset.UTC);

        return Json
                .createObjectBuilder()
                .add("time-local", this.time.toLocalDateTime().toString())
                .add("time-zoned", this.time.toString())
                .add("time-utc", this.time.withZoneSameInstant(ZoneOffset.UTC).toString())
                .build();
    }
}
