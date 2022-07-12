package comp3350.bms.tests.objects;

// Purpose: Tests the PaymentcardWallet object and its functions

import org.junit.Assert;
import org.junit.Test;

import comp3350.bms.objects.PaymentcardWallet;

public class PaymentcardWalletTest {
    @Test
    public void testPaymentcardWallet() {
        PaymentcardWallet paymentcardWallet = new PaymentcardWallet(1, 2);
        Assert.assertEquals(1, paymentcardWallet.getCardID());
        Assert.assertEquals(2, paymentcardWallet.getWalletID());
    }

    @Test(expected = NullPointerException.class)
    public void testPaymentcardWalletNegativeCardNum() {
        PaymentcardWallet paymentcardWallet = new PaymentcardWallet(1, -1);
    }

    @Test(expected = NullPointerException.class)
    public void testPaymentcardWalletNegativeCardID() {
        PaymentcardWallet paymentcardWallet = new PaymentcardWallet(-1, 1);
    }
}
