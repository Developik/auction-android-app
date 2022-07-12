package comp3350.bms.tests;

import junit.framework.Test;
import junit.framework.TestSuite;


import comp3350.bms.tests.business.AuctionManagerTest;
import comp3350.bms.tests.objects.BidTest;
import comp3350.bms.tests.business.PingChatTest;
import comp3350.bms.tests.business.ProductLogicTest;
import comp3350.bms.tests.objects.ChatMessagesTest;
import comp3350.bms.tests.objects.ProductTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    AuctionManagerTest.class,
    BidTest.class,
    PingChatTest.class,
    ProductLogicTest.class,
    ChatMessagesTest.class,
    ProductTest.class,
    UserTest.class
})
public class AllTests {
    public static void main(String[] args) {
        Result result = org.junit.runner.JUnitCore.runClasses(AllTests.class);
        System.out.println("Tests run: " + result.getRunCount());
        System.out.println("Tests failed: " + result.getFailureCount());
        System.out.println("Tests ignored: " + result.getIgnoreCount());
    }

}