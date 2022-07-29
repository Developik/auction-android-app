package comp3350.bms.tests;

// Purpose: a central suite that runs all Unit tests for this project

import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.bms.tests.objects.BidTest;
import comp3350.bms.tests.objects.ChatMessagesTest;
import comp3350.bms.tests.objects.PaymentcardTest;
import comp3350.bms.tests.objects.PaymentcardWalletTest;
import comp3350.bms.tests.objects.ProductLogicUnitTest;
import comp3350.bms.tests.objects.ProductTest;
import comp3350.bms.tests.objects.UserTest;
import comp3350.bms.tests.objects.WalletTest;
import comp3350.bms.tests.objects.WalletUserTest;
import comp3350.bms.tests.persistence.DataAccessStubTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BidTest.class,
        ChatMessagesTest.class,
        ProductTest.class,
        ProductLogicUnitTest.class,
        UserTest.class,
        WalletTest.class,
        DataAccessStubTest.class, // This tests the stub database and is located in the persistence package
        PaymentcardTest.class,
        PaymentcardWalletTest.class,
        WalletUserTest.class
})

public class RunUnitTests {
    public static void main(String[] args) {
        Result result = org.junit.runner.JUnitCore.runClasses(RunUnitTests.class);
        System.out.println("Tests run: " + result.getRunCount());
        System.out.println("Tests failed: " + result.getFailureCount());
        System.out.println("Tests ignored: " + result.getIgnoreCount());
    }

}