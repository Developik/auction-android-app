package comp3350.bms.objects;

// Purpose: Wallet object that handles the user's cards, their balance, and their previous
// transactions.

public class Wallet {
    private int walletID;
    private double balance;

    public Wallet(int walletID, double balance) {
        if (walletID < 0) {
            throw new IllegalArgumentException("Wallet ID cannot be negative.");
        } else {
            this.walletID = walletID;
        }
        this.balance = balance;
    }

    public boolean topUp(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else if (amount == 0) {
            throw new IllegalArgumentException("Amount cannot be zero.");
        } else {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }

    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        } else if (amount == 0) {
            throw new IllegalArgumentException("Amount cannot be zero.");
        } else if (balance < amount) {
            throw new IllegalArgumentException("Insufficient funds.");
        } else {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public int getWalletID() {
        return walletID;
    }

}

