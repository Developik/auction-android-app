package comp3350.bms.persistence;

// Purpose: DataAccessObject interface for accessing the data directly from the database. Refer
// to the DataAccessStub for the stub implementation.

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import comp3350.bms.business.AuctionManager;
import comp3350.bms.objects.Bid;
import comp3350.bms.objects.ChatMessages;
import comp3350.bms.objects.Paymentcard;
import comp3350.bms.objects.Product;
import comp3350.bms.objects.User;
import comp3350.bms.objects.Wallet;

public class DataAccessObject implements DataAccess {
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5;

    private String dbName;
    private String dbType;

    private ArrayList<Product> products;
    private ArrayList<User> users;
    private ArrayList<ChatMessages> chatMessages;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DataAccessObject(String dbName) {
        this.dbName = dbName;
    }

    public void open(String dbPath) {
        String url;
        try {
            // Setup for HSQL
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();
            st2 = c1.createStatement();
            st3 = c1.createStatement();
        } catch (Exception e) {
            processSQLError(e);
        }
        System.out.println("Opened " + dbType + " database " + dbPath);
    }

    public void close() {
        try {    // commit all changes to the database
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        } catch (Exception e) {
            processSQLError(e);
        }
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    public String getChatMessagesSequential(List<ChatMessages> ChatMessagesResult) {
        ChatMessages chatMessage;
        String username, message;
        username = EOF;
        message = EOF;

        result = null;
        try {
            cmdString = "Select * from ChatMessages";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        } catch (Exception e) {
            processSQLError(e);
        }
        try {
            while (rs2.next()) {
                username = rs2.getString("username");
                message = rs2.getString("message");
                chatMessage = new ChatMessages(message, username);
                ChatMessagesResult.add(chatMessage);
            }
            rs2.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return result;
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> productResult = new ArrayList<Product>();

        Product product;
        Long itemID;
        String name = EOF;
        Date datePosted;
        String picture = EOF;
        double startingBid;
        double currentBid = 0;
        Date auctionStart;
        Date auctionEnd;
        boolean sold = false;
        String category = EOF;

        result = null;
        try {
            cmdString = "Select * from Product";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        } catch (Exception e) {
            processSQLError(e);
        }
        try {
            while (rs2.next()) {
                itemID = rs2.getLong("itemID");
                name = rs2.getString("name");
                datePosted = rs2.getDate("datePosted");
                //pictures = rs2.getString("pictures"); // implement later
                startingBid = rs2.getDouble("startingBid");
                currentBid = rs2.getDouble("currentBid");
                auctionStart = rs2.getDate("auctionStart");
                auctionEnd = rs2.getDate("auctionEnd");
                sold = rs2.getBoolean("sold");
                category = rs2.getString("category");
                product = new Product(itemID, name, datePosted, picture, startingBid, currentBid, auctionStart, auctionEnd, sold, category);
                productResult.add(product);
            }
            rs2.close();
        } catch (Exception e) {
            processSQLError(e);
        }

        return productResult;
    }

    public String getProductSequential(List<Product> productResult) {
        Product product;
        Long itemID;
        String name = EOF;
        Date datePosted;
        String picture = EOF;
        double startingBid;
        double currentBid = 0;
        Date auctionStart;
        Date auctionEnd;
        boolean sold = false;
        String category = EOF;

        result = null;
        try {
            cmdString = "Select * from Product";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        } catch (Exception e) {
            processSQLError(e);
        }
        try {
            while (rs2.next()) {
                itemID = rs2.getLong("itemID");
                name = rs2.getString("name");
                datePosted = rs2.getDate("datePosted");
                //pictures = rs2.getString("pictures"); // implement later
                startingBid = rs2.getDouble("startingBid");
                currentBid = rs2.getDouble("currentBid");
                auctionStart = rs2.getDate("auctionStart");
                auctionEnd = rs2.getDate("auctionEnd");
                sold = rs2.getBoolean("sold");
                category = rs2.getString("category");
                product = new Product(itemID, name, datePosted, picture, startingBid, currentBid, auctionStart, auctionEnd, sold, category);
                productResult.add(product);
            }
            rs2.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return result;
    }

    @Override
    public int getBidsNumber() {
        int count = 0;
        try {
            cmdString = "Select COUNT(bidID) as num from Bid";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        } catch (Exception e) {
            processSQLError(e);
        }
        try {
            while (rs2.next()) {
                count = rs2.getInt("num");
            }
            rs2.close();
        } catch (Exception e) {
            processSQLError(e);
        }

        return count;
    }


    @Override
    public Bid getHighestBid(Product product) {
        Bid bid = null;
        Date date;
        double value;
        int bidID;

        int count = 0;
        try {
            //cmdString = "Select Max(value) as maxVal from Bid";
            cmdString = "Select * from Bid " +
                    "JOIN ProductBid ON " +
                    "ProductBid.bidID=Bid.bidID " +
                    "JOIN Product ON " +
                    "Product.itemID=ProductBid.itemID " +
                    "where Product.itemID=" +
                    "'" + product.getItemID() + "' " +
                    "ORDER BY value DESC " +
                    "LIMIT 1";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        } catch (Exception e) {
            processSQLError(e);
        }

        try {
            while (rs2.next()) {
                bidID = rs2.getInt("bidID");
                value = rs2.getInt("value");
                date = rs2.getDate("date");
                bid = new Bid(bidID, value, date);
            }
            rs2.close();
        } catch (Exception e) {
            processSQLError(e);
        }

        return bid;
    }


    public User getOwnerOfBid(Bid bid) {
        User user = null;
        String username = EOF;
        String firstName = EOF;
        String lastName = EOF;
        String address = EOF;
        int age = 0;

        try {
            //cmdString = "Select * from Bid";

            // max at this product
            cmdString = "Select * from User " +
                    "JOIN BidUser ON " +
                    "BidUser.username=User.username " +
                    "JOIN Bid ON " +
                    "Bid.bidID=BidUser.bidID " +
                    "where Bid.bidID=" +
                    "'" + bid.getBidID() + "'";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        } catch (Exception e) {
            processSQLError(e);
        }

        try {
            while (rs2.next()) {
                username = rs2.getString("username");
                firstName = rs2.getString("firstName");
                lastName = rs2.getString("lastName");
                address = rs2.getString("address");
                age = rs2.getInt("age");
                user = new User(username, firstName, lastName, address, age, false);
            }
            rs2.close();
        } catch (Exception e) {
            processSQLError(e);
        }

        return user;
    }

    @Override
    public String getBidSequential(List<Bid> bids) {
        Bid bid = null;
        Date date;
        double value;
        int bidID;

        result = null;
        try {
            cmdString = "Select * from Bid";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        } catch (Exception e) {
            processSQLError(e);
        }

        try {
            while (rs2.next()) {
                bidID = rs2.getInt("bidID");
                value = rs2.getInt("value");
                date = rs2.getDate("date");
                bid = new Bid(bidID, value, date);
                bids.add(bid);
            }
            rs2.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return result;
    }

    @Override
    public String getAllBidsForProduct(ArrayList<Bid> bids, Product product) {
        Bid bid = null;
        Date date;
        double value;
        int bidID;

        result = null;
        try {
            cmdString = "Select * from Bid " +
                    "JOIN ProductBid ON " +
                    "ProductBid.bidID=Bid.bidID " +
                    "JOIN Product ON " +
                    "Product.itemID=ProductBid.itemID " +
                    "where Product.itemID=" +
                    "'" + product.getItemID() + "'";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        } catch (Exception e) {
            processSQLError(e);
        }

        try {
            while (rs2.next()) {
                bidID = rs2.getInt("bidID");
                value = rs2.getInt("value");
                date = rs2.getDate("date");
                bid = new Bid(bidID, value, date);
                bids.add(bid);
            }
            rs2.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return result;
    }

    public String insertProduct(Product currentProduct) {
        String values;

        result = null;
        try {
            Timestamp datePosted = currentProduct.getDatePostedTimestamp();
            Timestamp auctionStart = currentProduct.getAuctionStartTimestamp();
            Timestamp auctionEnd = currentProduct.getAuctionEndTimestamp();

            values = currentProduct.getItemID()
                    + ", '" + currentProduct.getName() + "'"
                    + ", '" + datePosted + "'"
                    + ", '" + currentProduct.getStartingBid() + "'"
                    + ", '" + currentProduct.getCurrentBid() + "'"
                    + ", '" + auctionStart + "'"
                    + ", '" + auctionEnd + "'"
                    + ", '" + currentProduct.isSold() + "'"
                    + ", '" + currentProduct.getCategory() + "'";
            cmdString = "Insert into Product " + " Values(" + values + ")";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e) {
            result = processSQLError(e);
        }
        return result;
    }

    public String insertBid(Product currentProduct, Bid newBid, User user) {
        String values;

        result = null;
        try {
            Timestamp date = new Timestamp(newBid.getDate().getTime());
            values = newBid.getBidID()
                    + ", '" + newBid.getValue() + "'"
                    + ", '" + date + "'";
            cmdString = "Insert into Bid " + " Values(" + values + ")";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e) {
            result = processSQLError(e);
        }

        try {
            values = currentProduct.getItemID()
                    + ", '" + newBid.getBidID() + "'";
            cmdString = "Insert into ProductBid " + " Values(" + values + ")";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e) {
            result = processSQLError(e);
        }

        try {
            values = newBid.getBidID()
                    + ", '" + user.getUsername()+ "'";
            cmdString = "Insert into BidUser " + " Values(" + values + ")";
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return result;
    }

    public String updateProduct(Product currentProduct) {
        String values;
        String where;

        result = null;
        try {
            // Should check for empty values and not update them
            values = "name='" + currentProduct.getName()
                    + "', datePosted='" + currentProduct.getDatePosted() + "'"
                    + "', startingBid='" + currentProduct.getStartingBid() + "'"
                    + ", currentBid='" + currentProduct.getCurrentBid() + "'"
                    + ", auctionStart='" + currentProduct.getAuctionStart() + "'"
                    + ", auctionEnd='" + currentProduct.getAuctionEnd() + "'"
                    + ", isSold='" + currentProduct.isSold() + "'"
                    + ", category='" + currentProduct.getCategory() + "'";
            where = "where itemID=" +
                    "'" + currentProduct.getItemID() + "'";
            cmdString = "Update Product " + " Set " + values + " " + where;
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e) {
            result = processSQLError(e);
        }
        return result;
    }

    public String updateWallet(Wallet currentWallet) {
        String values;
        String where;

        result = null;
        try {
            // Should check for empty values and not update them
            values = "balance='" + currentWallet.getBalance() + "'";
            where = "where walletID=" +
                    "'" + currentWallet.getWalletID() + "'";
            cmdString = "Update Wallet " + " Set " + values + " " + where;
            System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e) {
            result = processSQLError(e);
        }
        return result;
    }

    public String getUserSequential(List<User> userResult) {
        User user;
        String username = EOF;
        String firstName = EOF;
        String lastName = EOF;
        String address = EOF;
        int age = 0;
        // ArrayList<Bid> myBids = new ArrayList<>(); // complete
        // add remaining fields later

        result = null;
        try {
            cmdString = "Select * from User";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        } catch (Exception e) {
            processSQLError(e);
        }
        try {
            while (rs2.next()) {
                username = rs2.getString("username");
                firstName = rs2.getString("firstName");
                lastName = rs2.getString("lastName");
                address = rs2.getString("address");
                age = rs2.getInt("age");
                user = new User(username, firstName, lastName, address, age, false);
                userResult.add(user);
            }
            rs2.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return result;
    }

    public String getWalletSequential(List<Wallet> walletResult) {
        Wallet wallet;
        int walletID;
        int balance = 0;

        result = null;
        try {
            cmdString = "Select * from Wallet";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        } catch (Exception e) {
            processSQLError(e);
        }
        try {
            while (rs2.next()) {
                walletID = rs2.getInt("walletID");
                balance = rs2.getInt("balance");
                wallet = new Wallet(walletID, balance);
                walletResult.add(wallet);
            }
            rs2.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return result;
    }

    public String getPaymentcardsSequential(List<Paymentcard> paymentcards,
                                            Wallet wallet) {
        Paymentcard paymentcard;
        int cardID;
        String cardNumbers = EOF;

        result = null;
        try {
            cmdString = "Select * from Paymentcard " +
                    "JOIN PaymentcardWallet ON " +
                    "PaymentcardWallet.cardID=Paymentcard.cardID " +
                    "JOIN Wallet ON " +
                    "Wallet.walletID=PaymentcardWallet.walletID " +
                    "where Wallet.walletID=" +
                    "'" + wallet.getWalletID() + "'";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        } catch (Exception e) {
            processSQLError(e);
        }
        try {
            while (rs2.next()) {
                cardID = rs2.getInt("cardID");
                cardNumbers = rs2.getString("cardNumbers");
                paymentcard = new Paymentcard(cardID, cardNumbers);
                paymentcards.add(paymentcard);
            }
            rs2.close();
        } catch (Exception e) {
            result = processSQLError(e);
        }

        return result;
    }

    public Wallet getWalletFromUser(String username) {
        Wallet wallet = null;
        int walletID;
        int balance = 0;

        result = null;
        try {
            cmdString = "Select * from Wallet " +
                    "JOIN WalletUser ON " +
                    "WalletUser.walletID=Wallet.walletID " +
                    "JOIN User ON " +
                    "User.username=WalletUser.username " +
                    "where User.username=" +
                    "'" + username + "'";

            System.out.println(cmdString);
            //cmdString = "Select * from Wallet where username=" +
            //        "'" + username + "'";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        } catch (Exception e) {
            processSQLError(e);
        }
        try {
            while (rs2.next()) {
                walletID = rs2.getInt("walletID");
                balance = rs2.getInt("balance");
                wallet = new Wallet(walletID, balance);
            }
            rs2.close();
        } catch (Exception e) {
            processSQLError(e);
        }

        return wallet;
    }

    public String checkWarning(Statement st, int updateCount) {
        String result;

        result = null;
        try {
            SQLWarning warning = st.getWarnings();
            if (warning != null) {
                result = warning.getMessage();
            }
        } catch (Exception e) {
            result = processSQLError(e);
        }
        if (updateCount != 1) {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    public String processSQLError(Exception e) {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }
}
