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
import comp3350.bms.objects.Wallet;
import comp3350.bms.persistence.DataAccess;
import comp3350.bms.tests.persistence.DataAccessStub;

public class AccessPaymentcardsTest {
    AccessPaymentcards accessPaymentcards;
    DataAccess testAccess;
    AccessWallet accessWallet;

    public void setup() {
        testAccess = new DataAccessStub();
        try {
            Services.createDataAccess(testAccess);
            accessPaymentcards = new AccessPaymentcards();
            accessWallet = new AccessWallet();
        } catch (Exception e) {
            Services.closeDataAccess();
            Assert.fail();
        }

    }

    public void setupReal() {
        Main.startUp();
        try {
            testAccess = Services.createDataAccess(Main.dbName);
            accessPaymentcards = new AccessPaymentcards();
            accessWallet = new AccessWallet();
        } catch (Exception e) {
            Services.closeDataAccess();
            Assert.fail();
        }
    }

    public void shutdown() {
        Services.closeDataAccess();
    }

    // Test to search for a payment card with a valid username
    @Test
    public void testGetPaymentCardsValidUserStub() {

        // test with stub db
        setup();

        Wallet wallet = accessWallet.getWalletFromUser("joedoe");
        ArrayList<Paymentcard> paymentCards = new ArrayList<>();

        accessPaymentcards.getPaymentCards(paymentCards, wallet);
        Assert.assertEquals(1, paymentCards.size());

        shutdown();

    }

    // Test to search for a payment card with a valid username
    @Test
    public void testGetPaymentCardsInvalidUserStub() {

        // test with stub db
        setup();

        Wallet wallet = accessWallet.getWalletFromUser("homer");
        ArrayList<Paymentcard> paymentCards = new ArrayList<>();

        accessPaymentcards.getPaymentCards(paymentCards, wallet);
        Assert.assertEquals(0, paymentCards.size());
        shutdown();

    }

    // Test to search for a payment card with a valid username
    @Test
    public void testGetPaymentCardsValidUserReal() {

        setupReal();

        Wallet wallet = accessWallet.getWalletFromUser("joedoe");
        ArrayList<Paymentcard> paymentCards = new ArrayList<>();

        accessPaymentcards.getPaymentCards(paymentCards, wallet);
        Assert.assertEquals(2, paymentCards.size());

        shutdown();

    }

    // Test to search for a payment card with a valid username
    @Test
    public void testGetPaymentCardsInvalidUserReal() {

        setupReal();

        Wallet wallet = accessWallet.getWalletFromUser("homer");
        ArrayList<Paymentcard> paymentCards = new ArrayList<>();

        accessPaymentcards.getPaymentCards(paymentCards, wallet);
        Assert.assertEquals(0, paymentCards.size());
        shutdown();

    }
}