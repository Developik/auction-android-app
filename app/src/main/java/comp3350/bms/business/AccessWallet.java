package comp3350.bms.business;

import java.util.List;

import comp3350.bms.application.Main;
import comp3350.bms.application.Services;
import comp3350.bms.objects.Wallet;
import comp3350.bms.persistence.DataAccess;

public class AccessWallet {
    private DataAccess dataAccess;
    private List<Wallet> wallets;
    private Wallet wallet;
    private int currentItem;

    public AccessWallet() {
        dataAccess = Services.getDataAccess(Main.dbName);
        wallets = null;
        wallet = null;
        currentItem = 0;
    }

    public String getWallets(List<Wallet> wallets) {
        wallets.clear();
        return dataAccess.getWalletSequential(wallets);
    }

    public Wallet getWalletFromUser(String username) {
        return dataAccess.getWalletFromUser(username);
    }

    public String updateWallet(Wallet currentWallet) {
        return dataAccess.updateWallet(currentWallet);
    }

}
