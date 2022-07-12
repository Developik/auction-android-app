package comp3350.bms.tests.objects;

import org.junit.Test;

import comp3350.bms.objects.PaymentcardWallet;

public class PaymentcardWalletTest {
    @Test
    public void testPaymentcardWallet() {
        PaymentcardWallet paymentcardWallet = new PaymentcardWallet(1, 2);
        assert(paymentcardWallet.getCardID() == 1);
        assert(paymentcardWallet.getWalletID() == 2);
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
