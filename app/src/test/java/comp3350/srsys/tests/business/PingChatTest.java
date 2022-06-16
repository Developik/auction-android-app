package comp3350.srsys.tests.business;

import static org.junit.Assert.*;
import org.junit.Test;

import comp3350.srsys.application.Main;
import comp3350.srsys.business.PingChat;

public class PingChatTest {

	PingChat pingChat;
	String message;

	@Test
	public void testEmptyList()	{
		System.out.println("\nStarting testPingChat: empty list");

		Main.startUp();

		pingChat = new PingChat();
		message = pingChat.getRandom();

		assertEquals("", message);

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
		assertEquals("Range {5}, Invalid Index", message);
		message = pingChat.getRandomS(0);
		assertEquals("Ryan: Welcome to the BMS game.", message);
		message = pingChat.getRandomS(1);
		assertEquals("Ryan: BMS (Bidding Market Simulation)", message);
		message = pingChat.getRandomS(2);
		assertEquals("Ryan: Random Messages pop up every time you post.", message);
		message = pingChat.getRandomS(3);
		assertEquals("Ryan: This is meant to simulate a sort of live chat function.", message);
		message = pingChat.getRandomS(4);
		assertEquals("Ryan: Users will be generated randomly in later iterations.", message);
		message = pingChat.getRandomS(5);
		assertEquals("Range {5}, Invalid Index", message);

		Main.shutDown();

		System.out.println("Finished testPingChat: Initialized list");
	}
}
