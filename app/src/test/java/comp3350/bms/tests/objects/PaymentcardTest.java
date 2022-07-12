package comp3350.bms.tests.objects;

import org.junit.Test;

import comp3350.bms.objects.Paymentcard;

public class PaymentcardTest {

    @Test
    public void testPaymentcard() {
        Paymentcard paymentcard = new Paymentcard(1, "123456789");
        assert(paymentcard.getCardID() == 1);
        assert(paymentcard.getCardNumbers().equals("123456789"));
    }

    @Test(expected = NullPointerException.class)
    public void testPaymentcardNegativeCardID() {
        Paymentcard paymentcard = new Paymentcard(-1, "123456789");
        assert(paymentcard.getCardNumbers().equals("123456789"));
    }

    @Test(expected = NullPointerException.class)
    public void testPaymentcardNullCardNum() {
        Paymentcard paymentcard = new Paymentcard(1, null);
        assert(paymentcard.getCardID() == 1);
    }

    @Test
    public void testTypeLong() {
        Paymentcard paymentcard = new Paymentcard(1, "123456789");
        assert(paymentcard.getLongCardNumbers() == 123456789L);
    }
}
