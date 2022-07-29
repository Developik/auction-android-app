package comp3350.bms.tests.Integration;

// Purpose: Tests the PingChat business object and its functions

import org.junit.*;

import org.junit.Test;

import comp3350.bms.application.Main;
import comp3350.bms.application.Services;
import comp3350.bms.business.PingChat;
import comp3350.bms.persistence.DataAccess;
import comp3350.bms.tests.persistence.DataAccessStub;

public class PingChatTest {

    PingChat pingChat;
    String message;
    DataAccess stubAccess;

    public void setup() {
        stubAccess = new DataAccessStub();
        try {
            Services.createDataAccess(stubAccess);
        } catch (Exception e) {
            Assert.fail();
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
        setup();

        //.getRandom sends -1 to .getRandomS and returns a valid message, but is empty
        Assert.assertEquals("", pingChat.getRandom());

        //-1 should return a valid empty message
        try {
            message = pingChat.getRandomS(-1);
            System.out.println("Should have thrown an out of bounds exception");
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
            //passed invalid input
        }

        //all values not equal to -1 return invalid
        Assert.assertEquals("Range {0}, Invalid Index", pingChat.getRandomS(-2));
        Assert.assertEquals("Range {0}, Invalid Index", pingChat.getRandomS(0));

        shutdown();

        System.out.println("Finished testPingChat: empty list");
    }

    @Test
    public void testEmptyListWithRealDB() {

        setupReal();

        //.getRandom sends -1 to .getRandomS and returns a valid message, but is empty
        Assert.assertEquals("", pingChat.getRandom());

        //-1 should return a valid empty message
        try {
            message = pingChat.getRandomS(-1);
            Assert.fail("Should have thrown an out of bounds exception");
        } catch (IndexOutOfBoundsException e) {
            //passed invalid input
        }

        //all values not equal to -1 return invalid
        message = pingChat.getRandomS(-2);
        Assert.assertEquals("Range {0}, Invalid Index", pingChat.getRandomS(-2));
        Assert.assertEquals("Range {0}, Invalid Index", pingChat.getRandomS(0));

        shutdown();

        System.out.println("Finished testPingChat: empty list with real DB");
    }

    //Tests to make sure that PingChat is initializing the DB chats correctly
    @Test
    public void testInitializedList() {

        setup();

        pingChat.getMessages();

        //Tests the list of messages from Stub DB on first time acquiring the list
        Assert.assertEquals("Range {5}, Invalid Index", pingChat.getRandomS(-2));

        //valid index --V
        Assert.assertEquals("Ryan: Welcome to the BMS game.", pingChat.getRandomS(0));
        Assert.assertEquals("Ryan: BMS (Bidding Market Simulation)", pingChat.getRandomS(1));
        Assert.assertEquals("Ryan: Random Messages pop up every time you post.", pingChat.getRandomS(2));
        Assert.assertEquals("Ryan: This is meant to simulate a sort of live chat function.", pingChat.getRandomS(3));
        Assert.assertEquals("Ryan: Users will be generated randomly in later iterations.", pingChat.getRandomS(4));
        //valid index --^

        //Tests out of bounds edge index
        Assert.assertEquals("Range {5}, Invalid Index", pingChat.getRandomS(5));


        //Tests that the list is unchanged after a second acquisition of the list
        Assert.assertEquals("Range {5}, Invalid Index", pingChat.getRandomS(-2));

        //valid index --V
        Assert.assertEquals("Ryan: Welcome to the BMS game.", pingChat.getRandomS(0));
        Assert.assertEquals("Ryan: BMS (Bidding Market Simulation)", pingChat.getRandomS(1));
        Assert.assertEquals("Ryan: Random Messages pop up every time you post.", pingChat.getRandomS(2));
        Assert.assertEquals("Ryan: This is meant to simulate a sort of live chat function.", pingChat.getRandomS(3));
        Assert.assertEquals("Ryan: Users will be generated randomly in later iterations.", pingChat.getRandomS(4));
        //valid index --^

        //Tests out of bounds edge index
        Assert.assertEquals("Range {5}, Invalid Index", pingChat.getRandomS(5));

        shutdown();

        System.out.println("Finished testPingChat: Initialized list");
    }

    //Tests to make sure that PingChat is initializing the DB chats correctly
    @Test
    public void testInitializedListWithRealDB() {

        setupReal();

        pingChat.getMessages();


        //Tests the list of messages from Stub DB on first time acquiring the list
        Assert.assertEquals("Range {5}, Invalid Index", pingChat.getRandomS(-2));

        //valid index --V
        Assert.assertEquals("Ryan: Welcome to the BMS game.", pingChat.getRandomS(0));
        Assert.assertEquals("Ryan: BMS (Bidding Market Simulation)", pingChat.getRandomS(1));
        Assert.assertEquals("Ryan: Random Messages pop up every time you post.", pingChat.getRandomS(2));
        Assert.assertEquals("Ryan: This is meant to simulate a sort of live chat function.", pingChat.getRandomS(3));
        Assert.assertEquals("Ryan: Users will be generated randomly in later iterations.", pingChat.getRandomS(4));
        //valid index --^

        //Tests out of bounds edge index
        Assert.assertEquals("Range {5}, Invalid Index", pingChat.getRandomS(5));


        //Tests that the list is unchanged after a second acquisition of the list
        Assert.assertEquals("Range {5}, Invalid Index", pingChat.getRandomS(-2));

        //valid index --V
        Assert.assertEquals("Ryan: Welcome to the BMS game.", pingChat.getRandomS(0));
        Assert.assertEquals("Ryan: BMS (Bidding Market Simulation)", pingChat.getRandomS(1));
        Assert.assertEquals("Ryan: Random Messages pop up every time you post.", pingChat.getRandomS(2));
        Assert.assertEquals("Ryan: This is meant to simulate a sort of live chat function.", pingChat.getRandomS(3));
        Assert.assertEquals("Ryan: Users will be generated randomly in later iterations.", pingChat.getRandomS(4));
        //valid index --^

        //Tests out of bounds edge index
        Assert.assertEquals("Range {5}, Invalid Index", pingChat.getRandomS(5));
        shutdown();

        System.out.println("Finished testPingChat: Initialized list with real DB");
    }
}
