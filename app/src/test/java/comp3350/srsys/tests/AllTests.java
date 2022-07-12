package comp3350.srsys.tests;

import junit.framework.Test;
import junit.framework.TestSuite;


import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.srsys.tests.business.AuctionManagerTest;
import comp3350.srsys.tests.objects.BidTest;
import comp3350.srsys.tests.business.PingChatTest;
import comp3350.srsys.tests.business.ProductLogicTest;
import comp3350.srsys.tests.objects.ChatMessagesTest;
import comp3350.srsys.tests.objects.ProductTest;
import comp3350.srsys.tests.objects.UserTest;

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