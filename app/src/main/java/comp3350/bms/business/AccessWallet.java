package comp3350.srsys.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.srsys.application.Main;
import comp3350.srsys.application.Services;
import comp3350.srsys.objects.Product;
import comp3350.srsys.objects.Wallet;
import comp3350.srsys.persistence.DataAccess;
//import comp3350.srsys.persistence.DataAccessStub;

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

    // public String getUserWallet(String username) {
    //    wallets.clear();
    //    return dataAccess.getWallet(username);
    // }

    public String updateWallet(Wallet currentWallet) {
        return dataAccess.updateWallet(currentWallet);
    }

}
