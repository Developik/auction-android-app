package comp3350.bms.objects;

// Purpose: an object that contains a cardID and the number of the card

public class Paymentcard {

    private int cardID;
    private String cardNumbers;

    public Paymentcard(int cardID, String cardNumbers) {
        if (cardNumbers == null)
            throw new NullPointerException("cardNumbers cannot be null");
        if (cardID < 0)
            throw new NullPointerException("cardID cannot be less than 0");

        this.cardID = cardID;
        this.cardNumbers = cardNumbers;
    }

    public int getCardID() {
        return cardID;
    }

    public String getCardNumbers() {
        return cardNumbers;
    }

    public Long getLongCardNumbers() {
        return Long.parseLong(cardNumbers);
    }
}
