package comp3350.bms.business;

// Purpose: AccessWallet handles the business logic for the wallets, which accesses them from the database.

import comp3350.bms.application.Main;
import comp3350.bms.application.Services;
import comp3350.bms.objects.Wallet;
import comp3350.bms.persistence.DataAccess;

public class AccessWallet {
    private DataAccess dataAccess;

    public AccessWallet() {
        dataAccess = Services.getDataAccess(Main.dbName);
    }

    public Wallet getWalletFromUser(String username) {
        return dataAccess.getWalletFromUser(username);
    }

    public String updateWallet(Wallet currentWallet) {
        return dataAccess.updateWallet(currentWallet);
    }

}
