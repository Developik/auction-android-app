package comp3350.srsys.objects;

public class WalletUser {
    private int walletID;
    private String username;

    public WalletUser(int walletID, String username) {
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
