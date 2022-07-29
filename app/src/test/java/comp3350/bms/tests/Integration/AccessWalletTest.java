package comp3350.bms.tests.Integration;

// Purpose: Tests the AccessWalletTest business object and its functions

import org.junit.Assert;
import org.junit.Test;

import comp3350.bms.application.Main;
import comp3350.bms.application.Services;
import comp3350.bms.business.AccessWallet;
import comp3350.bms.objects.Wallet;
import comp3350.bms.persistence.DataAccess;
import comp3350.bms.tests.persistence.DataAccessStub;

public class AccessWalletTest {

    DataAccess testAccess;
    AccessWallet accessWallet;
    Wallet wallet;

    public void setup() {
        testAccess = new DataAccessStub();
        try {
            Services.createDataAccess(testAccess);
            accessWallet = new AccessWallet();
        } catch (Exception e) {
            Services.closeDataAccess();
            Assert.fail();
        }
    }

    public void setupReal() {
        Main.startUp();
        try {
            testAccess = Services.getDataAccess(Main.dbName);
            accessWallet = new AccessWallet();
        } catch (Exception e) {
            Services.closeDataAccess();
            Assert.fail();
        }
    }

    public void shutdown() {
        Services.closeDataAccess();
    }

    //tests to make sure that accessWallet fails to get a wallet with an empty and null and non-existing usernames
    @Test
    public void testGetWalletInvalidUserStub() {

        setup();

        wallet = accessWallet.getWalletFromUser("");
        Assert.assertNull(wallet);
        wallet = accessWallet.getWalletFromUser(null);
        Assert.assertNull(wallet);
        wallet = accessWallet.getWalletFromUser("DoesNotExist");
        Assert.assertNull(wallet);

        shutdown();

    }

    @Test
    public void testGetWalletValidUserStub() {
        setup();

        wallet = accessWallet.getWalletFromUser("joedoe");
        Assert.assertEquals(100, wallet.getBalance(), 0.001);

        shutdown();

    }

    //tests to make sure that accessWallet fails to get a wallet with an empty and null and non-existing usernames
    @Test
    public void testGetWalletInvalidUserReal() {

        setupReal();

        wallet = accessWallet.getWalletFromUser("");
        Assert.assertNull(wallet);
        wallet = accessWallet.getWalletFromUser(null);
        Assert.assertNull(wallet);
        wallet = accessWallet.getWalletFromUser("DoesNotExist");
        Assert.assertNull(wallet);

        shutdown();

    }

    @Test
    public void testGetWalletValidUserReal() {
        setupReal();

        wallet = accessWallet.getWalletFromUser("joedoe");
        Assert.assertEquals(45, wallet.getBalance(), 0.001);

        shutdown();

    }

    @Test
    public void testUpdatingWalletStub() {
        setup();

        Wallet joesWallet = accessWallet.getWalletFromUser("joedoe");
        double oldBalance = joesWallet.getBalance();
        joesWallet.topUp(50);
        accessWallet.updateWallet(joesWallet);

        Wallet joesWallet2 = accessWallet.getWalletFromUser("joedoe");
        Assert.assertEquals(oldBalance + 50, joesWallet2.getBalance(), 0.001);

        // Reset the top up
        joesWallet.withdraw(50);
        accessWallet.updateWallet(joesWallet);
        Assert.assertEquals(oldBalance, joesWallet.getBalance(), 0.001);

        shutdown();
    }

    @Test
    public void testUpdatingWalletReal() {
        setupReal();

        Wallet joesWallet = accessWallet.getWalletFromUser("joedoe");
        double oldBalance = joesWallet.getBalance();
        joesWallet.topUp(50);
        accessWallet.updateWallet(joesWallet);

        Wallet joesWallet2 = accessWallet.getWalletFromUser("joedoe");
        Assert.assertEquals(oldBalance + 50, joesWallet2.getBalance(), 0.001);

        // Reset the top up
        joesWallet.withdraw(50);
        accessWallet.updateWallet(joesWallet);
        Assert.assertEquals(oldBalance, joesWallet.getBalance(), 0.001);

        shutdown();
    }
}