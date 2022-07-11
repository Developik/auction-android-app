package comp3350.srsys.objects;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.time.LocalDateTime;

import comp3350.srsys.business.ProductLogic;
public class Product {
	private Long itemID;
	private String name;
	private LocalDateTime datePosted;
	private ArrayList<String> pictures;
	private String description;
	private String category;
	private SealedBidAuction auction;

	@RequiresApi(api = Build.VERSION_CODES.O)
	public Product(String name, LocalDateTime start, LocalDateTime end, ArrayList<String> pictures,
				   String category) throws Exception {
		this.itemID = ProductLogic.generateID();
		this.name = name;
		this.datePosted = LocalDateTime.now();
		this.pictures = pictures;
		this.category = category;
		this.auction = new SealedBidAuction(start, end);

		if (!itemObjectValidation()) {
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

	public LocalDateTime getDatePosted() {
		return (datePosted);
	}

	@RequiresApi(api = Build.VERSION_CODES.O)
	public Bid getHighestBid() { return (auction.getHighestBid()); }

	public LocalDateTime getAuctionStart() { return (auction.getAuctionStart()); }

	public LocalDateTime getAuctionEnd() { return (auction.getAuctionEnd()); }

	@RequiresApi(api = Build.VERSION_CODES.O)
	public AuctionStatus getAuctionStatus() { return (auction.getAuctionStatus()); }

	public ArrayList<String> getPictures() { return (pictures); }

	public void addBid(Bid bid) {
		this.auction.addBid(bid);
	}



	public boolean itemObjectValidation(){
		return itemID >= 1 && name != null && name.length() >= 1 && datePosted != null &&
				category != null && category.length() >= 1;
	}

}
