package comp3350.srsys.persistence;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import java.time.LocalDateTime;

import comp3350.srsys.application.Main;
import comp3350.srsys.objects.Student;
import comp3350.srsys.objects.Course;
import comp3350.srsys.objects.SC;
import comp3350.srsys.objects.Product;
import comp3350.srsys.objects.User;
import comp3350.srsys.objects.ChatMessages;


public class DataAccessStub {
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

	@RequiresApi(api = Build.VERSION_CODES.O)
	public void open(String dbName) throws Exception {
		Student student;
		Course course;
		SC mySC;
		User user;
		Product product;
		ChatMessages newMessage;

		// OUR OBJECTS:
		//
		// Add corrections later

		List<String> categories = Arrays.asList("Books", "Watches", "Garden");

		users = new ArrayList<>();
		user = new User("joedoe", "Joe", "Doe", "66 Chancellor Dr, Winnipeg, MB", 25);
		users.add(user);
		user = new User("bot_user_1", "bot1", "user1", "67 Chancellor Dr, Winnipeg, MB", 20);
		users.add(user);

		products = new ArrayList<>();
		ArrayList<String> pics = new ArrayList<>();
		pics.add("../../../res/drawable/mortarboard.png");
		Long addOneDay = 1L;
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime tomorrow = today.plusDays(addOneDay);
		Product prod = new Product("test product", today, tomorrow, pics, "watches");
		products.add(prod);

		ArrayList<String> pictures = new ArrayList<>(Arrays.asList("1.png", "2.png"));
		try{
			product = new Product("Rolex Watch", today, tomorrow, pictures,  categories.get(1));
			products.add(product);
		}
		catch (Exception ignored){}

		pictures = new ArrayList<>(Arrays.asList("3.png"));
		try{
			product = new Product("Garden Bucket", today, tomorrow, pictures, categories.get(2));
			products.add(product);
		}

		catch (Exception ignored){}

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

	public String getProductSequential(List<Product> productResult) {
		productResult.addAll(products);
		return null;
	}

	public ArrayList<Product> getAllProducts() {
		return this.products;
	}

	public ArrayList<Product> getProductRandom(Product currentProduct) {
		ArrayList<Product> newProducts;
		int index;

		newProducts = new ArrayList<>();
		index = products.indexOf(currentProduct);
		if (index >= 0) {
			newProducts.add(products.get(index));
		}
		return newProducts;
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
}
