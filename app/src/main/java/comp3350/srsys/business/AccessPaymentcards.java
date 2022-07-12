package comp3350.srsys.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.srsys.application.Main;
import comp3350.srsys.application.Services;
import comp3350.srsys.objects.Paymentcard;
import comp3350.srsys.objects.Product;
import comp3350.srsys.objects.Wallet;
import comp3350.srsys.persistence.DataAccess;

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
        return dataAccess.getPaymentcardsSequential(paymentcards, wallet);
    }


}
