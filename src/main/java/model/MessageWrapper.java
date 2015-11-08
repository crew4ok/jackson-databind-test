package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import jackson.PayloadTypeIdResolver;

public class MessageWrapper<T> {
    private final MessageType messageType;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "messageType")
    @JsonTypeIdResolver(PayloadTypeIdResolver.class)
    private final T payload;

    @JsonCreator
    public MessageWrapper(@JsonProperty("messageType") MessageType messageType,
                          @JsonProperty("payload") T payload) {
        this.messageType = messageType;
        this.payload = payload;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public T getPayload() {
        return payload;
    }
}
