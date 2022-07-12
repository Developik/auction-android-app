package comp3350.bms.tests.business;

import org.junit.*;

import comp3350.bms.application.Main;
import comp3350.bms.business.PingChat;

public class PingChatTest {

	PingChat pingChat;
	String message;

	@Test
	public void testEmptyList()	{
		System.out.println("\nStarting testPingChat: empty list");

		// refactor
		Main.startUp();

		pingChat = new PingChat();
		message = pingChat.getRandom();

		Assert.assertEquals("", message);

		Main.shutDown();

		System.out.println("Finished testPingChat: empty list");
	}

	@Test
	public void testInitializedList(){
		System.out.println("\nStarting testPingChat: Initialized list");

		Main.startUp();

		pingChat = new PingChat();
		pingChat.getMessages();

		message = pingChat.getRandomS(-2);
		Assert.assertEquals("Range {5}, Invalid Index", message);
		message = pingChat.getRandomS(0);
		Assert.assertEquals("Ryan: Welcome to the BMS game.", message);
		message = pingChat.getRandomS(1);
		Assert.assertEquals("Ryan: BMS (Bidding Market Simulation)", message);
		message = pingChat.getRandomS(2);
		Assert.assertEquals("Ryan: Random Messages pop up every time you post.", message);
		message = pingChat.getRandomS(3);
		Assert.assertEquals("Ryan: This is meant to simulate a sort of live chat function.", message);
		message = pingChat.getRandomS(4);
		Assert.assertEquals("Ryan: Users will be generated randomly in later iterations.", message);
		message = pingChat.getRandomS(5);
		Assert.assertEquals("Range {5}, Invalid Index", message);

		Main.shutDown();

		System.out.println("Finished testPingChat: Initialized list");
	}
}
