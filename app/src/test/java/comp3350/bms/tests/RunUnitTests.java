package comp3350.bms.tests;

// Purpose: a central suite that runs all Unit tests for this project

import org.junit.runner.*;
import org.junit.runners.*;

import comp3350.bms.tests.objects.BidTest;
import comp3350.bms.tests.objects.ChatMessagesTest;
import comp3350.bms.tests.objects.PaymentcardTest;
import comp3350.bms.tests.objects.PaymentcardWalletTest;
import comp3350.bms.tests.objects.ProductTest;
import comp3350.bms.tests.business.ProductLogicTest;
import comp3350.bms.tests.objects.UserTest;
import comp3350.bms.tests.objects.WalletTest;
import comp3350.bms.tests.objects.WalletUserTest;
import comp3350.bms.tests.persistence.DataAccessStubTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BidTest.class,
        ChatMessagesTest.class,
        ProductTest.class,
        ProductLogicTest.class,
        UserTest.class,
        WalletTest.class,
        DataAccessStubTest.class,
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