package comp3350.bms.tests;

import junit.framework.Test;
import junit.framework.TestSuite;


import comp3350.bms.tests.business.AuctionManagerTest;
import comp3350.bms.tests.objects.BidTest;
import comp3350.bms.tests.business.PingChatTest;
import comp3350.bms.tests.business.ProductLogicTest;
import comp3350.bms.tests.objects.ChatMessagesTest;
import comp3350.bms.tests.objects.ProductTest;

public class AllTests {
    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();
        return suite;
    }

    private static void testObjects() {
        suite.addTestSuite(ProductTest.class);
        suite.addTestSuite(ChatMessagesTest.class);
        suite.addTestSuite(BidTest.class);
    }

    private static void testBusiness() {
        suite.addTestSuite(ProductLogicTest.class);
        suite.addTestSuite(PingChatTest.class);
        suite.addTestSuite(AuctionManagerTest.class);
    }
}