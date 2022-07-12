package comp3350.bms.tests.objects;

import org.junit.Test;

import comp3350.bms.objects.WalletUser;

public class WalletUserTest {

    @Test
    public void testWalletUser() {
        WalletUser walletUser = new WalletUser(1, "test");
        assert(walletUser.getWalletID() == 1);
        assert(walletUser.getUsername().equals("test"));
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
