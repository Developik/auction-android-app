package comp3350.srsys.tests.objects;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import comp3350.srsys.objects.ChatMessages;

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
