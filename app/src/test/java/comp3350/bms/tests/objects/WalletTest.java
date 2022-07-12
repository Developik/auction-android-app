package comp3350.bms.tests.objects;

import org.junit.Test;

import comp3350.bms.objects.Wallet;

public class WalletTest {
    @Test
    public void testWalletArithmetic() {
        Wallet wallet = new Wallet(9999, 100.0);
        wallet.topUp(100.0);
        assert(wallet.getBalance() == 200.0);
        wallet.withdraw(50.0);
        assert(wallet.getBalance() == 150.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWalletIDNegative() {
        Wallet wallet = new Wallet(-1, 100.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWalletBalanceNegativeTopUp() {
        Wallet wallet = new Wallet(9999, 100.0);
        wallet.topUp(-1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWalletBalanceZeroTopUp() {
        Wallet wallet = new Wallet(9999, 100.0);
        wallet.topUp(0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWalletBalanceNegativeWithdraw() {
        Wallet wallet = new Wallet(9999, 100.0);
        wallet.withdraw(-1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWalletBalanceZeroWithdraw() {
        Wallet wallet = new Wallet(9999, 100.0);
        wallet.withdraw(0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawMoreThanBalance() {
        Wallet wallet = new Wallet(9999, 100.0);
        wallet.withdraw(101.0);
    }

}
