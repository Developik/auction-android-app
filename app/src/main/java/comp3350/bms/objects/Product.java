package comp3350.bms.objects;

import android.os.Build;
import android.support.annotation.RequiresApi;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import comp3350.bms.business.ProductLogic;
public class Product {
	private Long itemID;
	private String name;
	private LocalDateTime datePosted;
	private String picture;
	private String description;
	private String category;
	private SealedBidAuction auction;

	// pre-generated Product
	@RequiresApi(api = Build.VERSION_CODES.O)
	public Product(String name, LocalDateTime start, LocalDateTime end, String picture, String description,
				   String category) throws Exception {
		// separate productLogic later
		this.itemID = ProductLogic.generateID();
		this.name = name;
		this.datePosted = LocalDateTime.now();
		this.category = category;
		this.picture = picture;
		this.description = description;
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

	public int getBidCount() {
		return auction.getBidCount();
	}

	public LocalDateTime getDatePosted() { return (datePosted); }

	@RequiresApi(api = Build.VERSION_CODES.O)
	public Bid getHighestBid() { return (auction.getHighestBid()); }

	@RequiresApi(api = Build.VERSION_CODES.O)
	public Bid getLowestBid() { return (auction.getLowestBid()); }

	@RequiresApi(api = Build.VERSION_CODES.O)
	public AuctionStatus getAuctionStatus() { return (auction.getAuctionStatus()); }

	public LocalDateTime getAuctionStart() { return (auction.getAuctionStart()); }

	public LocalDateTime getAuctionEnd() { return (auction.getAuctionEnd()); }

	public String getPicture() { return (picture); }

	@RequiresApi(api = Build.VERSION_CODES.O)
	public AuctionStatus isSold() { return (auction.getAuctionStatus()); }

	public void addBid(Bid bid) {
		if (bid.getBidAmount() > 0)
			this.auction.addBid(bid);
	}

	public boolean itemObjectValidation(){
		return itemID >= 1 && name != null && name.length() >= 1 && datePosted != null &&
				category != null && category.length() >= 1;
	}

}
