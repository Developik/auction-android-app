package comp3350.bms.tests.objects;

import org.junit.*;

import comp3350.bms.objects.ChatMessages;

public class ChatMessagesTest {

	@Test
	public void testMessageSet() {
		ChatMessages chatMessages;

		System.out.println("\nStarting testMessageSet");

		chatMessages = new ChatMessages("12345", "TestUser");
		Assert.assertEquals("TestUser: 12345", chatMessages.getMessage());
		chatMessages = new ChatMessages();
		Assert.assertEquals("Temp: ", chatMessages.getMessage());
		chatMessages = new ChatMessages(null, null);
		Assert.assertEquals("Temp: null", chatMessages.getMessage());

		System.out.println("Finished testMessageSet");
	}
}
