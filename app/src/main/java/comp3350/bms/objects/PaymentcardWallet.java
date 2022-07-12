package comp3350.bms.objects;

// Purpose: a connection object between Paymentcard and Wallet objects

public class PaymentcardWallet {
    private int cardID;
    private int walletID;

    public PaymentcardWallet(int cardID, int walletID) {
        if (cardID < 0)
            throw new NullPointerException("cardID cannot be less than 0");
        if (walletID < 0)
            throw new NullPointerException("walletID cannot be less than 0");

        this.cardID = cardID;
        this.walletID = walletID;
    }

    public int getCardID() {
        return cardID;
    }

    public int getWalletID() {
        return walletID;
    }

}
