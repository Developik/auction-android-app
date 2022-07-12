package comp3350.bms.tests.persistence;

// Purpose: a dummy database that contains a base set of data

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import comp3350.bms.application.Main;
import comp3350.bms.objects.ChatMessages;
import comp3350.bms.objects.Paymentcard;
import comp3350.bms.objects.Product;
import comp3350.bms.objects.User;
import comp3350.bms.objects.Wallet;
import comp3350.bms.persistence.DataAccess;


public class DataAccessStub implements DataAccess {
    private String dbName;
    private String dbType = "stub";

    private ArrayList<Product> products;
    private ArrayList<User> users;
    private ArrayList<ChatMessages> chatMessages;

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

        // OUR OBJECTS:
        //
        // Add corrections later

        List<String> categories = Arrays.asList("Books", "Watches", "Garden");

        users = new ArrayList<>();
        try {
            user = new User("joedoe", "Joe", "Doe", "66 Chancellor Dr, Winnipeg, MB", 25, false);
            users.add(user);
            user = new User("easyUser", "easy", "User", "67 Chancellor Dr, Winnipeg, MB", 20, false);
            users.add(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        products = new ArrayList<>();
        picture = "../../../res/drawable/mortarboard.png";
        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
        Product prod = new Product("test product", today, picture, 50.00, 75.00, today, tomorrow, false, "watches");
        products.add(prod);

        Date date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        Date start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        Date end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        picture = "2.png";
        try {
            product = new Product("Rolex Watch", date, picture, 10.0, 25.0, start, end, false, categories.get(1));
            products.add(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
        picture = "3.png";
        try {
            product = new Product("Garden Bucket", date, picture, 5.0, 5.0, start, end, false, categories.get(2));
            products.add(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

        // SAMPLE PROJECT OBJECTS :
        //
        //

        System.out.println("Opened " + dbType + " database " + dbName);
    }

    public void close() {
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    public String getChatMessagesSequential(List<ChatMessages> ChatMessagesResult) {
        ChatMessagesResult.addAll(chatMessages);
        return null;
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
        return null;
    }

    @Override
    public String getWalletSequential(List<Wallet> wallets) {
        return null;
    }

    public Wallet getWalletFromUser(String username) {
        return null;
    }

    public String getPaymentcardsSequential(List<Paymentcard> paymentcards,
                                            Wallet wallet) {
        return null;
    }
}
