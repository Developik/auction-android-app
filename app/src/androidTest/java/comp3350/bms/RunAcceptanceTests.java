package comp3350.bms;

// Purpose: a central suite that runs all Acceptance tests for this project

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.bms.tests.acceptance.TestBidding;
import comp3350.bms.tests.acceptance.TestChatting;
import comp3350.bms.tests.acceptance.TestLoggingIn;
import comp3350.bms.tests.acceptance.TestProductPage;
import comp3350.bms.tests.acceptance.TestSearching;
import comp3350.bms.tests.acceptance.TestToppingUp;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestLoggingIn.class,
        TestToppingUp.class,
        TestProductPage.class,
        TestSearching.class,
        TestBidding.class,
        TestChatting.class,
})
public class RunAcceptanceTests {
    public RunAcceptanceTests() {
        System.out.println("Running acceptance tests");

    }
}
