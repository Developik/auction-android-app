package comp3350.bms.tests.persistence;

// Purpose: a dummy database that contains a base set of data

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import comp3350.bms.application.Main;
import comp3350.bms.objects.Bid;
import comp3350.bms.objects.BidUser;
import comp3350.bms.objects.ChatMessages;
import comp3350.bms.objects.Paymentcard;
import comp3350.bms.objects.PaymentcardWallet;
import comp3350.bms.objects.Product;
import comp3350.bms.objects.ProductBid;
import comp3350.bms.objects.ProductUser;
import comp3350.bms.objects.User;
import comp3350.bms.objects.Wallet;
import comp3350.bms.objects.WalletUser;
import comp3350.bms.persistence.DataAccess;


public class DataAccessStub implements DataAccess {
    private String dbName;
    private String dbType = "stub";

    private ArrayList<Product> products;
    private ArrayList<User> users;
    private ArrayList<ChatMessages> chatMessages;
    private ArrayList<Wallet> wallets;
    private ArrayList<WalletUser> walletUsers;
    private ArrayList<Paymentcard> paymentcards;
    private ArrayList<PaymentcardWallet> paymentcardWallets;
    private ArrayList<Bid> bids = new ArrayList<>();
    private ArrayList<ProductUser> productUsers = new ArrayList<>();
    private ArrayList<ProductBid> productBids = new ArrayList<>();
    private ArrayList<BidUser> bidUsers = new ArrayList<>();


    public DataAccessStub(String dbName) {
        this.dbName = dbName;
    }

    public DataAccessStub() {
        this(Main.dbName);
    }

    public void open(String dbName) throws Exception {
        User user;
        Product product;
        ChatMessages newMessage;
        String picture;

        List<String> categories = Arrays.asList("Books", "Watches", "Garden");

        users = new ArrayList<>();
        try {
            user = new User("joedoe", "Joe", "Doe", "66 Chancellor Dr, Winnipeg, MB", 25, false);
            users.add(user);
            user = new User("easyUser", "easy", "User", "67 Chancellor Dr, Winnipeg, MB", 20, false);
            users.add(user);
        } catch (Exception e) {
            throw new NumberFormatException("Objects have not been created");
        }


        products = new ArrayList<>();
        picture = "mortarboard";
        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
        Product prod = new Product(1L, "test product", today, picture, 50.00, 75.00, today, tomorrow, false, "Watches");
        products.add(prod);

        Date date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        Date start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        Date end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        picture = "rolex_watch";
        try {
            product = new Product(2L, "Rolex Watch", date, picture, 10.0, 25.0, start, end, false, categories.get(1));
            products.add(product);
        } catch (Exception e) {
            throw new NumberFormatException("Objects have not been created");
        }

        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        picture = "garden_bucket";
        try {
            product = new Product(3L, "Garden Bucket", date, picture, 5.0, 5.0, start, end, false, categories.get(2));
            products.add(product);
        } catch (Exception e) {
            throw new NumberFormatException("Objects have not been created");
        }

        chatMessages = new ArrayList<>();

        newMessage = new ChatMessages("Welcome to the BMS game.", "Ryan");
        chatMessages.add(newMessage);
        newMessage = new ChatMessages("BMS (Bidding Market Simulation)", "Ryan");
        chatMessages.add(newMessage);
        newMessage = new ChatMessages("Random Messages pop up every time you post.", "Ryan");
        chatMessages.add(newMessage);
        newMessage = new ChatMessages("This is meant to simulate a sort of live chat function.", "Ryan");
        chatMessages.add(newMessage);
        newMessage = new ChatMessages("Users will be generated randomly in later iterations.", "Ryan");
        chatMessages.add(newMessage);

        wallets = new ArrayList<>();
        walletUsers = new ArrayList<>();
        try {
            Wallet wallet = new Wallet(999, 100.00);
            walletUsers.add(new WalletUser(999, "joedoe"));
            wallets.add(wallet);
        } catch (Exception e) {
            throw new NumberFormatException("Objects have not been created");
        }

        paymentcards = new ArrayList<>();
        paymentcardWallets = new ArrayList<>();
        try {
            Paymentcard paymentcard = new Paymentcard(1, "99999999");
            paymentcardWallets.add(new PaymentcardWallet(1, 999));
            paymentcards.add(paymentcard);
        } catch (Exception e) {
            throw new NumberFormatException("Objects have not been created");
        }

        System.out.println("Opened " + dbType + " database " + dbName);
    }

    public void close() {
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    public String getChatMessagesSequential(List<ChatMessages> ChatMessagesResult) {
        ChatMessagesResult.addAll(chatMessages);
        return null;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public ArrayList<Product> getAllProducts() {
        return this.products;
    }

    public String getProductSequential(List<Product> productResult) {
        productResult.addAll(products);
        return null;
    }

    public String insertProduct(Product currentProduct) {
        // don't bother checking for duplicates
        products.add(currentProduct);
        return null;
    }

    public String removeProduct(Product currentProduct) {
        products.remove(currentProduct);
        return null;
    }

    public String updateProduct(Product currentProduct) {
        int index;

        index = products.indexOf(currentProduct);
        if (index >= 0) {
            products.set(index, currentProduct);
        }
        return null;
    }

    public String getUserSequential(List<User> userResult) {
        userResult.addAll(users);
        return null;
    }

    public String updateWallet(Wallet currentWallet) {
        int currentId = currentWallet.getWalletID();
        // Find the wallet with the same ID and replace it in the index
        int index = -1;
        for (int i = 0; i < wallets.size(); i++) {
            if (wallets.get(i).getWalletID() == currentId) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            wallets.set(index, currentWallet);
        }
        return null;
    }

    public String getWalletSequential(List<Wallet> wallets) {
        wallets.addAll(this.wallets);
        return null;
    }

    public Wallet getWalletFromUser(String username) {
        for (WalletUser w : walletUsers) {
            if (w.getUsername().equals(username)) {
                int id = w.getWalletID();
                for (Wallet w2 : wallets) {
                    if (w2.getWalletID() == id) {
                        return w2;
                    }
                }
            }
        }
        return null;
    }

    public String getPaymentcardsSequential(List<Paymentcard> list, Wallet wallet) {
        // Find the wallet with the same ID and get its paymentcards
        int id = wallet.getWalletID();
        for (PaymentcardWallet w : paymentcardWallets) {
            if (w.getWalletID() == id) {
                int cardId = w.getCardID();
                for (Paymentcard c : paymentcards) {
                    if (c.getCardID() == cardId) {
                        list.add(c);
                    }
                }
            }
            else{
                return "No paymentcards found";
            }
        }

        return null;
    }

    @Override
    public String insertBid(Product product, Bid bid, User user) {
        String result = null;
        if (product == null || bid == null || user == null)
            result = "Null object Error";
        else if (bid.getValue() <= 0){
            result = "Bid value too low";
        }
        else {
            bids.add(bid);
            ProductBid productBid = new ProductBid(product.getItemID(),
                    bid.getBidID());
            BidUser bidUser = new BidUser(bid.getBidID(),
                    user.getUsername());

            productBids.add(productBid);
            bidUsers.add(bidUser);
        }

        return result;
    }

    @Override
    public int getBidsNumber() {
        return bids.size();
    }

    @Override
    public Bid getHighestBid(Product product) {
        int bidID = 0;
        double min = 0;
        Bid res = null;
        for (int j = 0; j < productBids.size(); j++) {
            if (product.getItemID() == productBids.get(j).getProductID() &&
                    bids.get(j).getValue() > min) {
                bidID = productBids.get(j).getBidID();
                min = bids.get(j).getValue();
            }
        }

        for (int i = 0; i < bids.size(); i++) {
            if (bidID == bids.get(i).getBidID()) {
                res = bids.get(i);
            }
        }

        return res;
    }

    @Override
    public User getOwnerOfBid(Bid bid) {
        String username = "";
        User res = null;
        for (int i = 0; i < bids.size(); i++) {
            for (int j = 0; j < bidUsers.size(); j++) {
                System.out.println("--- " + bids.get(i).getBidID() +
                        "---" + bidUsers.get(j).getBidID());
                if (bids.get(i).getBidID() == bidUsers.get(j).getBidID()) {
                    username = bidUsers.get(j).getUsername();
                }
            }
        }

        System.out.println("--- " + username);

        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername()))
                res = users.get(i);
        }
        return res;
    }

    @Override
    public String getBidSequential(List<Bid> bids) {
        bids.addAll(this.bids);
        return null;
    }

    @Override
    public String getAllBidsForProduct(ArrayList<Bid> bids, Product product) {
        ArrayList <Integer> bidTempIds = new ArrayList<Integer>();
        for (int j = 0; j < productBids.size(); j++) {
            if (product.getItemID() == productBids.get(j).getProductID())
                bidTempIds.add(productBids.get(j).getBidID());
        }

        for (int i = 0; i < this.bids.size(); i++) {
            for (int j = 0; j < bidTempIds.size(); j++) {
                if (bidTempIds.get(j) == this.bids.get(i).getBidID()) {
                    bids.add(this.bids.get(i));
                }
            }
        }

        return null;
    }
}
