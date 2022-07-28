package comp3350.bms;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

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
