package comp3350.bms.tests.Integration;

// Purpose: Tests the AccessPaymentcards business object and its functions

import org.junit.*;

import org.junit.Test;

import java.util.ArrayList;

import comp3350.bms.application.Main;
import comp3350.bms.application.Services;
import comp3350.bms.business.AccessPaymentcards;
import comp3350.bms.business.AccessWallet;
import comp3350.bms.objects.Paymentcard;
import comp3350.bms.objects.User;
import comp3350.bms.objects.Wallet;
import comp3350.bms.objects.WalletUser;
import comp3350.bms.persistence.DataAccess;
import comp3350.bms.tests.persistence.DataAccessStub;

public class AccessPaymentcardsTest {
    AccessPaymentcards accessPaymentcards;
    AccessWallet accessWallet;
    Wallet wallet;
    WalletUser walletUser;
    User testUser;
    String message;
    DataAccess testAccess;

    public void setup() {
        testAccess = new DataAccessStub();
        try {
            Services.createDataAccess(testAccess);
            testUser = new User("testUser", "testFirstName", "testLastName", "testAddr", 1, false);
        } catch (Exception e) {
            Assert.fail();
        }
        accessPaymentcards = new AccessPaymentcards();
        accessWallet = new AccessWallet();
        wallet = accessWallet.getWalletFromUser(testUser.getUsername());
        walletUser = new WalletUser(999, testUser.getUsername());

    }

    public void setupReal() {
        Main.startUp();
        accessPaymentcards = new AccessPaymentcards();
    }

    public void shutdown() { Services.closeDataAccess(); }

    // tests to make sure that a new AccessPaymentcards object has no paymentcards in it (stub db)
    @Test
    public void testEmptyObjectStub() {
        System.out.println("\nStarting AccessPaymentcards Test: empty object (stub db)");

        // test with stub db
        setup();

        ArrayList<Paymentcard> paymentcards = new ArrayList<>();
        message = accessPaymentcards.getPaymentCards(paymentcards, wallet);

        Assert.assertEquals(0, paymentcards.size());

        shutdown();

        System.out.println("\nFinished test.");
    }

    // tests to make sure that a new AccessPaymentcards object has no paymentcards in it (real db)
    @Test
    public void testEmptyObjectReal() {
        System.out.println("\nStarting AccessPaymentcards Test: empty object (real db)");

        // test with real db
        setupReal();

        ArrayList<Paymentcard> paymentcards = new ArrayList<>();
        message = accessPaymentcards.getPaymentCards(paymentcards, wallet);

        Assert.assertEquals(0, paymentcards.size());

        shutdown();

        System.out.println("\nFinished test.");
    }

    // tests to make sure AccessPaymentcards works when populated (stub db)
    @Test
    public void testPopulatedObjectStub() {
        System.out.println("\nStarting AccessPaymentcards Test: populated object (real db)");

        setup();

        ArrayList<Paymentcard> paymentcards = new ArrayList<>();
        String result = accessPaymentcards.getPaymentCards(paymentcards, wallet);
        Assert.assertEquals(0, paymentcards.size());

        shutdown();

        System.out.println("\nFinished test.");
    }

    // tests to make sure AccessPaymentcards works when populated (real db)
    @Test
    public void testPopulatedObjectReal() {
        System.out.println("\nStarting AccessPaymentcards Test: populated object (real db)");

        setupReal();

        Wallet joesWallet = accessWallet.getWalletFromUser("joedoe");
        ArrayList<Paymentcard> paymentcards = new ArrayList<>();
        String result = accessPaymentcards.getPaymentCards(paymentcards, joesWallet); // result will be null unless error

        Assert.assertEquals(1, paymentcards.get(0).getCardID());
        Assert.assertEquals(2, paymentcards.get(1).getCardID());

        shutdown();

        System.out.println("\nFinished test.");
    }
}
