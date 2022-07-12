package comp3350.bms.objects;

// Purpose: a connection object between User and Wallet objects

public class WalletUser {
    private int walletID;
    private String username;

    public WalletUser(int walletID, String username) {
        if (walletID < 0) {
            throw new IllegalArgumentException("Invalid walletID");
        }
        if (username == null) {
            throw new IllegalArgumentException("Invalid username");
        }
        this.walletID = walletID;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public int getWalletID() {
        return walletID;
    }
}
