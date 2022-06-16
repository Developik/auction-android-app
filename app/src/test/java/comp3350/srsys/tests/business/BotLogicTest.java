package comp3350.srsys.tests.business;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.srsys.application.Main;
import comp3350.srsys.business.BotLogic;
import comp3350.srsys.business.PingChat;
import comp3350.srsys.objects.Bid;
import comp3350.srsys.objects.User;

public class BotLogicTest extends TestCase {

	User user;
	String username;
	String firstName;
	String lastName;
	String address;
	int age;
	boolean isBot;

	ArrayList<User> users = new ArrayList<>();

	@Before
	public void setUp() {
		Main.startUp();
		username = "joedoe";
		firstName = "Joe";
		lastName = "Doe";
		address = "66 Chancellor Dr, Winnipeg, MB";
		age = 25;
		isBot = false;
		user = new User(username, firstName, lastName, address, age, isBot);
		users.add(user);
	}

	@After
	public void tearDown() {
		Main.shutDown();
	}

	@Test
	public void testBidsValues()	{
		final BotLogic newBot = new BotLogic();
		newBot.assignBidToRandomProduct();

		double value = 95.12;

		Bid newBid = new Bid(value, user);
		user.setBid(newBid);
		assertEquals(user.getLastBid().getValue(), value, 0.0001);

		value = 243.24;

		newBid = new Bid(value, user);
		user.setBid(newBid);
		assertEquals(user.getLastBid().getValue(), value, 0.0001);

		value = -124243.23;

		newBid = new Bid(value, user);
		user.setBid(newBid);
		assertEquals(user.getLastBid().getValue(), 0, 0.0001);

		value = 43.232442;

		newBid = new Bid(value, user);
		user.setBid(newBid);
		assertTrue(Math.abs(user.getLastBid().getValue()-value) >= 0.0001);
	}
}
