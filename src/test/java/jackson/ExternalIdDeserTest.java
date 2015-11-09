package jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ChatMessage;
import model.MessageType;
import model.MessageWrapper;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ExternalIdDeserTest {

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        ChatMessage chatMessage = new ChatMessage("simple body");
        MessageWrapper<ChatMessage> messageWrapper = new MessageWrapper<>(MessageType.CHAT_MESSAGE, chatMessage);

        String json = objectMapper.writeValueAsString(messageWrapper);
        MessageWrapper actualMessageWrapper = objectMapper.readValue(json, new TypeReference<MessageWrapper<ChatMessage>>() { });

        assertNotNull(actualMessageWrapper.getMessageType());
    }
}
