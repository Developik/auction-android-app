package comp3350.bms.persistence;

// Purpose: Interface for accessing the data.

import java.util.ArrayList;
import java.util.List;

import comp3350.bms.objects.ChatMessages;
import comp3350.bms.objects.Paymentcard;
import comp3350.bms.objects.Product;
import comp3350.bms.objects.User;
import comp3350.bms.objects.Wallet;

public interface DataAccess {
    void open(String string) throws Exception;

    void close();

    String getChatMessagesSequential(List<ChatMessages> ChatMessagesResult);

    ArrayList<Product> getAllProducts();

    String getProductSequential(List<Product> productResult);

    String insertProduct(Product currentProduct);

    String updateProduct(Product currentProduct);

    String getUserSequential(List<User> userResult);

    String updateWallet(Wallet currentWallet);

    String getWalletSequential(List<Wallet> wallets);

    Wallet getWalletFromUser(String username);

    String getPaymentcardsSequential(List<Paymentcard> paymentcards,
                                     Wallet wallet);
}
