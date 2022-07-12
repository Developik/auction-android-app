/**
 * This code is not used in the first iteration. It is provided as
 * an example of usage of HSQLDB (for iteration 2).
 */

package comp3350.bms.persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import comp3350.bms.objects.ChatMessages;
import comp3350.bms.objects.Product;
import comp3350.bms.objects.User;

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

            /*** Alternate setups for different DB engines, just given as examples. Don't use them. ***/

            /*
             * // Setup for SQLite. Note that this is undocumented and is not guaranteed to work.
             * // See also: https://github.com/SQLDroid/SQLDroid
             * dbType = "SQLite";
             * Class.forName("SQLite.JDBCDriver").newInstance();
             * url = "jdbc:sqlite:" + dbPath;
             * c1 = DriverManager.getConnection(url);
             *
             * ... create statements
             */

            /*** The following two work on desktop builds: ***/

            /*
             * // Setup for Access
             * dbType = "Access";
             * Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();
             * url = "jdbc:odbc:SC";
             * c1 = DriverManager.getConnection(url,"userid","userpassword");
             *
             * ... create statements
             */

            /*
             * //Setup for MySQL
             * dbType = "MySQL";
             * Class.forName("com.mysql.jdbc.Driver");
             * url = "jdbc:mysql://localhost/database01";
             * c1 = DriverManager.getConnection(url, "root", "");
             *
             * ... create statements
             */
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
                name = rs2.getString("name");
                datePosted = rs2.getDate("datePosted");
                //pictures = rs2.getString("pictures"); // implement later
                startingBid = rs2.getDouble("startingBid");
                currentBid = rs2.getDouble("currentBid");
                auctionStart = rs2.getDate("auctionStart");
                auctionEnd = rs2.getDate("auctionEnd");
                sold = rs2.getBoolean("sold");
                category = rs2.getString("category");
                product = new Product(name, datePosted, picture, startingBid, currentBid, auctionStart, auctionEnd, sold, category);
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
                name = rs2.getString("name");
                datePosted = rs2.getDate("datePosted");
                //pictures = rs2.getString("pictures"); // implement later
                startingBid = rs2.getDouble("startingBid");
                currentBid = rs2.getDouble("currentBid");
                auctionStart = rs2.getDate("auctionStart");
                auctionEnd = rs2.getDate("auctionEnd");
                sold = rs2.getBoolean("sold");
                category = rs2.getString("category");
                product = new Product(name, datePosted, picture, startingBid, currentBid, auctionStart, auctionEnd, sold, category);
                productResult.add(product);
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
            where = "where itemID=" + currentProduct.getItemID();
            cmdString = "Update Product " + " Set " + values + " " + where;
            //System.out.println(cmdString);
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
