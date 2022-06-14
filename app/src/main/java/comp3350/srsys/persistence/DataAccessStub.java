package comp3350.srsys.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import comp3350.srsys.application.Main;
import comp3350.srsys.objects.Student;
import comp3350.srsys.objects.Course;
import comp3350.srsys.objects.SC;
import comp3350.srsys.objects.Item;
import comp3350.srsys.objects.User;


public class DataAccessStub {
	private String dbName;
	private String dbType = "stub";

	private ArrayList<Student> students;
	private ArrayList<Course> courses;
	private ArrayList<SC> scs;
	private ArrayList<Item> items;
	private ArrayList<User> users;

	public DataAccessStub(String dbName) {
		this.dbName = dbName;
	}

	public DataAccessStub() {
		this(Main.dbName);
	}

	public void open(String dbName) {
		Student student;
		Course course;
		SC mySC;
		User user;
		Item item;

		// OUR OBJECTS:
		//
		// Add corrections later

		List<String> categories = Arrays.asList("Books", "Watches", "Garden");

		users = new ArrayList<User>();
		user = new User("joedoe", "Joe", "Doe", "66 Chancellor Dr, Winnipeg, MB", 25);
		users.add(user);
		user = new User("bot_user_1", "bot1", "user1", "67 Chancellor Dr, Winnipeg, MB", 20);
		users.add(user);

		items = new ArrayList<Item>();

		Date date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		Date start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		Date end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		List pictures = new ArrayList<>(Arrays.asList("1.png", "2.png"));
		item = new Item("Rolex Watch", date, pictures, 10.0, 25.0, start, end, false, categories.get(1));

		items.add(item);

		date = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		start = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		end = new GregorianCalendar(2012, Calendar.FEBRUARY, 11).getTime();
		pictures = new ArrayList<>(Arrays.asList("./3.png"));
		item = new Item("Rolex Watch", date, pictures, 5.0, 5.0, start, end, false, categories.get(2));

		items.add(item);
		// SAMPLE PROJECT OBJECTS :
		//
		//

		System.out.println("Opened " + dbType + " database " + dbName);
	}

	public void close() {
		System.out.println("Closed " + dbType + " database " + dbName);
	}

	public String getItemSequential(List<Item> itemResult) {
		itemResult.addAll(items);
		return null;
	}

	public ArrayList<Item> getItemRandom(Item currentItem) {
		ArrayList<Item> newItems;
		int index;

		newItems = new ArrayList<Item>();
		index = items.indexOf(currentItem);
		if (index >= 0) {
			newItems.add(items.get(index));
		}
		return newItems;
	}

	public String insertItem(Item currentItem) {
		// don't bother checking for duplicates
		items.add(currentItem);
		return null;
	}

	public String updateItem(Item currentItem) {
		int index;

		index = items.indexOf(currentItem);
		if (index >= 0) {
			items.set(index, currentItem);
		}
		return null;
	}
}
