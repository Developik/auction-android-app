package comp3350.bms.tests.business;

import org.junit.*;

import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import comp3350.bms.application.Main;
import comp3350.bms.application.Services;
import comp3350.bms.business.PingChat;
import comp3350.bms.persistence.DataAccess;
import comp3350.bms.tests.persistence.DataAccessStub;

public class PingChatTest {

    PingChat pingChat;
    String message;
    DataAccess testAccess;

    public void setup() {
        testAccess = new DataAccessStub();
        try {
            Services.createDataAccess(testAccess);
        } catch (Exception e) {
            Assert.assertEquals(0,1);
        }
        pingChat = new PingChat();
    }

    public void setupReal() {
        Main.startUp();
        pingChat = new PingChat();
    }

    public void shutdown() {
        Services.closeDataAccess();
    }

    //tests to make sure that a new pingChat object has no messages at first
    @Test
    public void testEmptyList() {
        System.out.println("\nStarting testPingChat: empty list");

        setup();

        //.getRandom sends -1 to .getRandomS and returns a valid message, but is empty
        message = pingChat.getRandom();
        Assert.assertEquals("", message);

        //-1 should return a valid empty message
        try {
            message = pingChat.getRandomS(-1);
            System.out.println("Should have thrown an out of bounds exception");
            Assert.assertEquals(0,1);
        } catch (IndexOutOfBoundsException e) {
            //passed invalid input
        }

        //all values not equal to -1 return invalid
        message = pingChat.getRandomS(-2);
        Assert.assertEquals("Range {0}, Invalid Index", message);
        message = pingChat.getRandomS(0);
		Assert.assertEquals("Range {0}, Invalid Index", message);

        shutdown();

        System.out.println("Finished testPingChat: empty list");
    }

    //tests to make sure that a new pingChat object has no messages at first using real DB
    @Test
    public void testEmptyListWithRealDB() {
        System.out.println("\nStarting testPingChat: empty list with real DB");

        setupReal();

        //.getRandom sends -1 to .getRandomS and returns a valid message, but is empty
        message = pingChat.getRandom();
		Assert.assertEquals("", message);

        //-1 should return a valid empty message
        try {
            message = pingChat.getRandomS(-1);
            System.out.println("Should have thrown an out of bounds exception");
            Assert.assertEquals(0,1);
        } catch (IndexOutOfBoundsException e) {
            //passed invalid input
        }

        //all values not equal to -1 return invalid
        message = pingChat.getRandomS(-2);
		Assert.assertEquals("Range {0}, Invalid Index", message);
        message = pingChat.getRandomS(0);
		Assert.assertEquals("Range {0}, Invalid Index", message);

        shutdown();

        System.out.println("Finished testPingChat: empty list with real DB");
    }

    //Tests to make sure that PingChat is initializing the DB chats correctly
    @Test
    public void testInitializedList() {
        System.out.println("\nStarting testPingChat: Initialized list");

        setup();

        pingChat.getMessages();

        //Tests the list of messages from Stub DB on first time acquiring the list
        message = pingChat.getRandomS(-2);//Invalid index
		Assert.assertEquals("Range {5}, Invalid Index", message);

        //valid index --V
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
        //valid index --^

        message = pingChat.getRandomS(5);//tests out of bounds edge index
		Assert. assertEquals("Range {5}, Invalid Index", message);


        //Tests that the list is unchanged after a second acquisition of the list
        message = pingChat.getRandomS(-2);//Invalid index
		Assert.assertEquals("Range {5}, Invalid Index", message);

        //valid index --V
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
        //valid index --^

        message = pingChat.getRandomS(5);//tests out of bounds edge index
		Assert.assertEquals("Range {5}, Invalid Index", message);

        shutdown();

        System.out.println("Finished testPingChat: Initialized list");
    }

    //Tests to make sure that PingChat is initializing the DB chats correctly
    @Test
    public void testInitializedListWithRealDB() {
        System.out.println("\nStarting testPingChat: Initialized list with real DB");

        setupReal();

        pingChat.getMessages();

        //Tests the list of messages from Stub DB on first time acquiring the list
        message = pingChat.getRandomS(-2);//Invalid index
		Assert.assertEquals("Range {5}, Invalid Index", message);

        //valid index --V
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
        //valid index --^

        message = pingChat.getRandomS(5);//tests out of bounds edge index
		Assert.assertEquals("Range {5}, Invalid Index", message);


        //Tests that the list is unchanged after a second acquisition of the list
        message = pingChat.getRandomS(-2);//Invalid index
		Assert.assertEquals("Range {5}, Invalid Index", message);

        //valid index --V
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
        //valid index --^

        message = pingChat.getRandomS(5);//tests out of bounds edge index
		Assert.assertEquals("Range {5}, Invalid Index", message);

        shutdown();

        System.out.println("Finished testPingChat: Initialized list with real DB");
    }
}
