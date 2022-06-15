package comp3350.srsys.tests.objects;

import static org.junit.Assert.*;
import org.junit.Test;

import comp3350.srsys.objects.ChatMessages;

public class ChatMessagesTest {

	@Test
	public void testMessageSet()
	{
		ChatMessages chatMessages;

		System.out.println("\nStarting testMessageSet");

		chatMessages = new ChatMessages("12345", "TestUser");
		assertNotNull(chatMessages);
		assertEquals("TestUser: 12345", chatMessages.getMessage());
		chatMessages = new ChatMessages();
		assertNotNull(chatMessages);
		assertEquals("Temp: ", chatMessages.getMessage());
		chatMessages = new ChatMessages(null, null);
		assertNotNull(chatMessages);
		assertEquals("Temp: null", chatMessages.getMessage());

		System.out.println("Finished testMessageSet");
	}
}
