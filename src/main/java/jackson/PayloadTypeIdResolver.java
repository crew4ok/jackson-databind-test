package jackson;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import model.ChatMessage;
import model.MessageType;

import java.util.HashMap;
import java.util.Map;

public class PayloadTypeIdResolver extends TypeIdResolverBase {
    private Map<MessageType, Class<?>> typeFromIdMapping;
    private Map<Class<?>, MessageType> idFromTypeMapping;

    @Override
    public void init(JavaType bt) {
        typeFromIdMapping = new HashMap<>();
        typeFromIdMapping.put(MessageType.CHAT_MESSAGE, ChatMessage.class);

        idFromTypeMapping = new HashMap<>();
        idFromTypeMapping.put(ChatMessage.class, MessageType.CHAT_MESSAGE);
    }

    @Override
    public String idFromValue(Object value) {
        Class<?> clazz = value.getClass();

        return idFromTypeMapping.get(clazz).toString();
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return idFromValue(value);
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String id) {
        MessageType messageType = MessageType.valueOf(id);
        Class<?> payloadClass = typeFromIdMapping.get(messageType);

        return context.constructType(payloadClass);
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.NAME;
    }
}