package comp3350.bms.tests.Integration;

// Purpose: Tests the AccessWalletTest business object and its functions

import org.junit.Assert;
import org.junit.Test;

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

    public void shutdown() {
        Services.closeDataAccess();
    }

    //tests to make sure that accessWallet fails to get a wallet with an empty and null and non-existing usernames
    @Test
    public void testGetWalletFromUserEmptyList() {
        System.out.println("\nStarting testAccessWallet: empty list");

        setup();

        wallet = accessWallet.getWalletFromUser("");
        Assert.assertNull(wallet);
        wallet = accessWallet.getWalletFromUser(null);
        Assert.assertNull(wallet);
        wallet = accessWallet.getWalletFromUser("DoesNotExist");
        Assert.assertNull(wallet);

        shutdown();

        System.out.println("Finished testAccessWallet: empty list");
    }
}