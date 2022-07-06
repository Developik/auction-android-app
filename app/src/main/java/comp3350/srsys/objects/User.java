package comp3350.srsys.objects;

import java.util.ArrayList;

public class User
{
	private String username;
	private String first_name;
	private String last_name;
	private String wallet; // expand more who will be working on this part (amount, list of transactions)
	private String address;
	private Integer age;
	private ArrayList<Bid> myBids;
	private String[] messages; // expand more who will be working on this part
	private Integer[] watchlist; // list of IDs or the person working on this part could create relation object
	private Integer[] soldItems;
	private Integer[] auctionsWon;
	private Integer[] itemsListed;
	private boolean isBot;


	// pre-generated User
	// add new fields later
	public User(String username, String first_name, String last_name, String address,
                Integer age, boolean isBot) {
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.age = age;
		this.myBids = new ArrayList<>();
		this.isBot = isBot;
	}

	public String getUsername()
	{
		return (username);
	}

	public String getFirstName()
	{
		return (first_name);
	}

	public String getLastName()
	{
		return (last_name);
	}

	public String getFullName()
	{
		return (first_name + " " + last_name);
	}

	public String getAddress()
	{
		return (address);
	}

	public Integer getAge() { return (age); }

	public boolean checkIsBot() { return (isBot); }

	public void setBid(Bid b){
		if(b != null){
			myBids.add(b);
		}
	}

	public Bid getLastBid(){ return myBids.get(myBids.size()-1);}

}
