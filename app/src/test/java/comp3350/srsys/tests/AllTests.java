package comp3350.srsys.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.srsys.tests.business.PingChatTest;
import comp3350.srsys.tests.objects.ChatMessagesTest;
import comp3350.srsys.tests.objects.ProductTest;

public class AllTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();
        return suite;
    }

    private static void testObjects()
    {
        suite.addTestSuite(ProductTest.class);
        suite.addTestSuite(ChatMessagesTest.class);
    }

    private static void testBusiness()
    {
        suite.addTestSuite(PingChatTest.class);
    }
}