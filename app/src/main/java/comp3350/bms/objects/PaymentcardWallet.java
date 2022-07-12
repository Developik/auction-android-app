package comp3350.bms.objects;

// Purpose: a connection object between Paymentcard and Wallet objects

public class PaymentcardWallet {
    private int cardID;
    private int walletID;

    public PaymentcardWallet(int cardID, int walletID) {
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
