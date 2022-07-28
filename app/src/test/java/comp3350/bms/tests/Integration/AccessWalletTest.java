package comp3350.bms.tests.Integration;

// Purpose: Tests the AccessWalletTest business object and its functions

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import comp3350.bms.application.Main;
import comp3350.bms.application.Services;
import comp3350.bms.business.AccessUsers;
import comp3350.bms.business.AccessWallet;
import comp3350.bms.objects.User;
import comp3350.bms.objects.Wallet;
import comp3350.bms.persistence.DataAccess;
import comp3350.bms.tests.persistence.DataAccessStub;

public class AccessWalletTest {

    DataAccess testAccess;
    AccessWallet accessWallet;
    Wallet wallet;
    String result;

    public void setup() {
        testAccess = new DataAccessStub();
        try {
            Services.createDataAccess(testAccess);
            accessWallet = new AccessWallet();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    public void setupReal() {
        Main.startUp();

        accessWallet = new AccessWallet();
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

    //tests to make sure that accessWallet gets a wallet with an empty and null username
    @Test
    public void testGetWalletFromUser() {
        System.out.println("\nStarting testAccessWallet: empty list");

        setupReal();

        AccessUsers accessUsers = new AccessUsers();
        ArrayList<User> users = new ArrayList<>();
        accessUsers.getUsers(users);
        wallet = accessWallet.getWalletFromUser(users.get(0).getUsername());
        result = accessWallet.updateWallet(wallet);
        Assert.assertNull(result);//ensures no errors were thrown
        Assert.assertTrue(wallet.getBalance() >= 0);

        shutdown();

        System.out.println("Finished testAccessWallet: empty list");
    }
}