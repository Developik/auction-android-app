package comp3350.bms.objects;

// Purpose: Wallet object that handles the user's cards, their balance, and their previous
// transactions.

import java.util.ArrayList;

public class Wallet {
    private User user;
    private double balance;
    // private ArrayList<Transaction> transactions;
    private ArrayList<String> paymentCards;

    public Wallet(User user) {
        this.user = user;
        this.balance = Math.round((Math.random() * 100) * 100) / 100.0;
        this.paymentCards = new ArrayList<>();
        // Give user two payment cards
        this.paymentCards.add(generateCard());
        this.paymentCards.add(generateCard());
    }

    private String generateCard() {
        String[] predefinedNumbers = {"4005550000000019",
                "4503300000000008",
                "4311780000241417",
                "6011000990099818",
                "5018000000000009",
                "5018000000000012",
                "5018000000000015",
                "5018000000000018",
                "5018000000000021",};
        int randomNumber = (int) (Math.random() * predefinedNumbers.length);
        return predefinedNumbers[randomNumber];
    }

    public boolean topUp(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public String getCard() {
        return paymentCards.get(0);
    }

    public void addCard(String card) {
        paymentCards.add(card);
    }

    public void removeCard(String card) {
        paymentCards.remove(card);
    }

    public ArrayList<String> getCards() {
        return paymentCards;
    }


}

