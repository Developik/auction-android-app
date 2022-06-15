package comp3350.srsys.objects;

import java.util.ArrayList;
import java.util.Date;

import comp3350.srsys.business.ProductLogic;
public class Product
{
	private Long itemID;
	private String name;
	private Date datePosted;
	private ArrayList<String> pictures;
	private double startingBid;
	private double currentBid;
	private Date auctionStart;
	private Date auctionEnd;
	private boolean onGoingAuction;
	private String description;
	private boolean sold;
	private String category;

	// pre-generated Product
	public Product(String name, Date datePosted, ArrayList<String> pictures, double startingBid,
						double currentBid, Date auctionStart, Date auctionEnd, boolean sold,
						String category) throws Exception {
		this.itemID = ProductLogic.generateID();
		this.name = name;
		this.datePosted = datePosted;
		this.pictures = pictures;
		this.startingBid = startingBid;
		this.currentBid = currentBid;
		this.auctionStart = auctionStart;
		this.auctionEnd = auctionEnd;
		this.sold = sold;
		this.category = category;

		if (!itemObjectValidation()){
			throw new Exception("Product Item parameters are incorrect!");
		}
	}

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

	public double getCurrentBid() { return (currentBid); }

	public double getStartingBid() { return (startingBid); }

	public Date getAuctionStart() { return (auctionStart); }

	public Date getAuctionEnd() { return (auctionEnd); }

	public ArrayList<String> getPictures() { return (pictures); }

	public boolean isSold() { return (sold); }

	public void setNewBid(double newBid) { this.currentBid = newBid; }

	public boolean itemObjectValidation(){
		boolean result = true;

		if (itemID < 1 || name == null || name.length() < 1 || datePosted == null ||
				category == null || category.length() < 1 || startingBid < 0 || (currentBid < 0) ||
				auctionStart == null || auctionEnd == null){
			result = false;
		}

		return result;
	}

}
