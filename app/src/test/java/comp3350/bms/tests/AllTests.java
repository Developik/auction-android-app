package comp3350.bms.tests;

// Purpose: a central suite that runs all tests for this project

import org.junit.runner.*;
import org.junit.runners.*;

import comp3350.bms.tests.business.AuctionManagerTest;
import comp3350.bms.tests.objects.BidTest;
import comp3350.bms.tests.business.PingChatTest;
import comp3350.bms.tests.business.ProductLogicTest;
import comp3350.bms.tests.objects.ChatMessagesTest;
import comp3350.bms.tests.objects.ProductTest;
import comp3350.bms.tests.objects.UserTest;
import comp3350.bms.tests.objects.WalletTest;
import comp3350.bms.tests.persistence.DataAccessStubTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuctionManagerTest.class,
        BidTest.class,
        PingChatTest.class,
        ProductLogicTest.class,
        ChatMessagesTest.class,
        ProductTest.class,
        UserTest.class,
        WalletTest.class,
        DataAccessStubTest.class
})

public class AllTests {
    public static void main(String[] args) {
        Result result = org.junit.runner.JUnitCore.runClasses(AllTests.class);
        System.out.println("Tests run: " + result.getRunCount());
        System.out.println("Tests failed: " + result.getFailureCount());
        System.out.println("Tests ignored: " + result.getIgnoreCount());
    }

}