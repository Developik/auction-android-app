package comp3350.bms.tests.objects;

// Purpose: Tests WalletUser object and its functions

import org.junit.Assert;
import org.junit.Test;

import comp3350.bms.objects.WalletUser;

public class WalletUserTest {

    @Test
    public void testWalletUser() {
        WalletUser walletUser = new WalletUser(1, "test");
        Assert.assertEquals(1, walletUser.getWalletID());
        Assert.assertEquals("test", walletUser.getUsername());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWalletUserInvalidWalletID() {
        WalletUser walletUser = new WalletUser(-1, "test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWalletUserInvalidUsername() {
        WalletUser walletUser = new WalletUser(1, null);
    }
}
