package comp3350.srsys.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class User implements Parcelable {
	private String username;
	private String first_name;
	private String last_name;
	private String wallet; // expand more who will be working on this part (amount, list of transactions)
	private String address;
	private Integer age;
	private ArrayList<Bid> myBids;
	private Integer[] watchlist; // list of IDs or the person working on this part could create relation object
	private Integer[] soldItems;
	private Integer[] auctionsWon;
	private Integer[] itemsListed;
	private boolean isBot;
	private ArrayList<String> chatHistory;


	// pre-generated User
	// add new fields later
	public User(String username, String first_name, String last_name, String address,
                Integer age, boolean isBot){
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.age = age;
		this.myBids = new ArrayList<>();
		this.isBot = isBot;
		this.chatHistory = new ArrayList<>();
	}

	protected User(Parcel in) {
		username = in.readString();
		first_name = in.readString();
		last_name = in.readString();
		wallet = in.readString();
		address = in.readString();
		if (in.readByte() == 0) {
			age = null;
		} else {
			age = in.readInt();
		}
		isBot = in.readByte() != 0;
		chatHistory = in.createStringArrayList();
	}

	public static final Creator<User> CREATOR = new Creator<User>() {
		@Override
		public User createFromParcel(Parcel in) {
			return new User(in);
		}

		@Override
		public User[] newArray(int size) {
			return new User[size];
		}
	};

	public String getUsername(){
		return (username);
	}

	public String getFirstName(){
		return (first_name);
	}

	public String getLastName(){
		return (last_name);
	}

	public String getFullName(){
		return (first_name + " " + last_name);
	}

	public String getAddress(){
		return (address);
	}

	public Integer getAge(){
		return (age);
	}

	public boolean checkIsBot(){
		return (isBot);
	}

	public void setBid(Bid b){
		if(b != null){
			myBids.add(b);
		}
	}

	public Bid getLastBid(){
		return myBids.get(myBids.size()-1);
	}

	public void createMessage(String newMessage, int userPing){
		ChatMessages message;
		if(userPing == -1){
			message = new ChatMessages(newMessage, "Ryan");//sets Temp random chat user
		}
		else{
			message = new ChatMessages(newMessage, username);
		}
		chatHistory.add(0,message.getMessage());
	}

	public String getChatMessage(int index){
		String result = "";
		if(index < chatHistory.size() && index >= 0 && chatHistory.get(index) != null){
			result = (chatHistory.get(index));
		}
		return result;
	}

	public int getNumberOfChats(){
		int result = 0;
		if(chatHistory != null){
			result = chatHistory.size() - 2;
		}
		return result;
	}

	public void setChatHistory(ArrayList<String> savedChatHistory){
		chatHistory = savedChatHistory;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(username);
		parcel.writeString(first_name);
		parcel.writeString(last_name);
		parcel.writeString(wallet);
		parcel.writeString(address);
		if (age == null) {
			parcel.writeByte((byte) 0);
		} else {
			parcel.writeByte((byte) 1);
			parcel.writeInt(age);
		}
		parcel.writeByte((byte) (isBot ? 1 : 0));
		parcel.writeStringList(chatHistory);
	}
}
