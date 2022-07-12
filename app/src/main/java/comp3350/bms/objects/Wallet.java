package comp3350.bms.objects;

// Purpose: Wallet object that handles the user's cards, their balance, and their previous
// transactions.

public class Wallet {
    private int walletID;
    private double balance;

    public Wallet(int walletID, double balance) {
        this.walletID = walletID;
        this.balance = balance;
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

    public int getWalletID() {
        return walletID;
    }

}

