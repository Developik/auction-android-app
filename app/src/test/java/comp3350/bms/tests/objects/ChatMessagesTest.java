package comp3350.bms.tests.objects;

// Purpose: Tests the ChatMessages object and its functions

import org.junit.Assert;
import org.junit.Test;

import comp3350.bms.objects.ChatMessages;

public class ChatMessagesTest {

    @Test
    public void testMessageSet() {
        ChatMessages chatMessages;


        chatMessages = new ChatMessages();//tests ChatMessages without input
        Assert.assertEquals("Temp: ", chatMessages.getMessage());
        chatMessages = new ChatMessages(null, null);//tests ChatMessages with both null values
        Assert.assertEquals("Temp: null", chatMessages.getMessage());
        chatMessages = new ChatMessages("Hello", null);//tests ChatMessages with a null value
        Assert.assertEquals("Temp: Hello", chatMessages.getMessage());
        chatMessages = new ChatMessages(null, "TestUser");//tests ChatMessages with a null value
        Assert.assertEquals("TestUser: null", chatMessages.getMessage());
        chatMessages = new ChatMessages("12345", "TestUser");//tests proper input
        Assert.assertEquals("TestUser: 12345", chatMessages.getMessage());

    }
}
