package comp3350.bms.business;

// Purpose: AccessPaymentcards handles the business logic for the Paymentcards, which accesses them from the database.

import java.util.List;

import comp3350.bms.application.Main;
import comp3350.bms.application.Services;
import comp3350.bms.objects.Paymentcard;
import comp3350.bms.objects.Wallet;
import comp3350.bms.persistence.DataAccess;

public class AccessPaymentcards {
    private DataAccess dataAccess;
    private List<Paymentcard> paymentcards;
    private Paymentcard paymentcard;
    private int currentItem;

    public AccessPaymentcards() {
        dataAccess = Services.getDataAccess(Main.dbName);
        paymentcards = null;
        paymentcard = null;
        currentItem = 0;
    }

    public String getPaymentCards(List<Paymentcard> paymentcards,
                                  Wallet wallet) {
        if (paymentcards == null || wallet == null) {
            return "No payment cards found";
        }
        return dataAccess.getPaymentcardsSequential(paymentcards, wallet);
    }


}
