package comp3350.srsys.objects;

import java.util.Date;
import java.util.List;

import comp3350.srsys.business.ItemLogic;
public class Item
{
	private Long itemID;
	private String name;
	private Date datePosted;
	private String[] pictures;
	private double starting_bid;
	private double current_bid;
	private Date auction_start;
	private Date auction_end;
	private boolean ongoing_auction;
	private String description;
	// private seller
	private boolean sold;
	private String category;


	// pre-generated Item
	public Item(String name, Date datePosted, List<String> pictures, double starting_bid,
				double current_bid, Date auction_start, Date auction_end, boolean sold,
				String category)
	{
		this.itemID = ItemLogic.generateID();
		this.name = name;
		this.datePosted = datePosted;
		this.pictures = null;
		this.starting_bid = starting_bid;
		this.current_bid = current_bid;
		this.auction_start = auction_start;
		this.auction_end = auction_end;
		this.sold = sold;
		this.category = category;
	}

	// we need to generate one later
	/*
	public Item(String newID, String newStudentName, String newStudentAddress)
	{
		 = newID;
		studentName = newStudentName;
		studentAddress = newStudentAddress;
	}
	*/

	public Long getItemID() {
		return (itemID);
	}

	public String getName() {
		return (name);
	}

	public String getCategory() {
		return (category);
	}

	public Date getDatePosted() {
		return (datePosted);
	}

	public double getCurrentBid() { return (current_bid); }

	public boolean isSold() { return (sold); }

	public double setNewBid(double newBid)
	{
		return (this.current_bid = newBid);
	}

}
