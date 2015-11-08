package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatMessage {
    private final String body;

    @JsonCreator
    public ChatMessage(@JsonProperty("body") String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
