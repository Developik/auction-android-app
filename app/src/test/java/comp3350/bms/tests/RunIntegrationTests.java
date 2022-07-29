package comp3350.bms.tests;

// Purpose: a central suite that runs all Integration tests for this project

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.bms.tests.Integration.AccessPaymentcardsTest;
import comp3350.bms.tests.Integration.AccessProductTest;
import comp3350.bms.tests.Integration.AccessWalletTest;
import comp3350.bms.tests.Integration.PingChatTest;
import comp3350.bms.tests.Integration.ProductLogicTest;
import comp3350.bms.tests.Integration.AuctionManagerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AuctionManagerTest.class,
        AccessWalletTest.class,
        AccessPaymentcardsTest.class,
        PingChatTest.class,
        AccessProductTest.class,
        AccessWalletTest.class,
        ProductLogicTest.class
})

public class RunIntegrationTests {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(RunIntegrationTests.class);
        System.out.println("Tests run: " + result.getRunCount());
        System.out.println("Tests failed: " + result.getFailureCount());
        System.out.println("Tests ignored: " + result.getIgnoreCount());
    }
}