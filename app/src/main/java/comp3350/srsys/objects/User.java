package comp3350.srsys.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class User implements Parcelable {
    private String username;
    private String firstName;
    private String lastName;
    private String wallet; // expand more who will be working on this part (amount, list of transactions)
    private String address;
    private Integer age;
    private ArrayList<Bid> myBids;
    private String[] messages; // expand more who will be working on this part // Add MU
    private Integer[] watchlist; // list of IDs or the person working on this part could create relation object
    private Integer[] soldItems;
    private Integer[] auctionsWon;
    private Integer[] itemsListed;
    private boolean isBot;
    private ArrayList<String> chatHistory;


    // pre-generated User
    // add new fields later
    public User(String username, String firstName, String lastName, String address,
                Integer age, boolean isBot) throws Exception {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
        this.myBids = new ArrayList<>();
        this.chatHistory = new ArrayList<>();

        if (!userObjectValidation()) {
            System.out.println(username + " " + firstName + " " + lastName + " " + address +
                    " " + age + " ");
            throw new Exception("User parameters are incorrect!");
        }
    }

    protected User(Parcel in) {
        username = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        wallet = in.readString();
        address = in.readString();
        if (in.readByte() == 0) {
            age = null;
        } else {
            age = in.readInt();
        }
        messages = in.createStringArray();
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

    public String getUsername() {
        return (username);
    }

    public String getFirstName() {
        return (firstName);
    }

    public String getLastName() {
        return (lastName);
    }

    public String getFullName() {
        return (firstName + " " + lastName);
    }

    public String getAddress() {
        return (address);
    }

    public Integer getAge() {
        return (age);
    }

    public void setBid(Bid b) {
        if (b != null) {
            myBids.add(b);
        }
    }

    public Bid getLastBid() {
        return myBids.get(myBids.size() - 1);
    }

    public boolean userObjectValidation() {
        boolean result = true;

        if (username == null || username.length() < 1 || firstName == null ||
                firstName.length() < 1 || lastName == null || lastName.length() < 1 ||
                address == null || address.length() < 1 || age == null || age < 0) {
            result = false;
        }

        return result;
    }

    public void createMessage(String newMessage, int userPing) {
        ChatMessages message;
        if (userPing == -1) {
            message = new ChatMessages(newMessage, "Ryan");//sets Temp random chat user
        } else {
            message = new ChatMessages(newMessage, username);
        }
        chatHistory.add(0, message.getMessage());
        if (chatHistory.size() > 5) {
            chatHistory.remove(5);
        }
    }

    public String getChatMessage(int index) {
        String result = "";
        if (index < chatHistory.size() && index >= 0 && chatHistory.get(index) != null) {
            result = (chatHistory.get(index));
        }
        return result;
    }

    public int getNumberOfChats() {
        int result = 0;
        if (chatHistory != null) {
            result = chatHistory.size() - 2;
        }
        return result;
    }

    public void setChatHistory(ArrayList<String> savedChatHistory) {
        chatHistory = savedChatHistory;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(wallet);
        parcel.writeString(address);
        if (age == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(age);
        }
        parcel.writeStringArray(messages);
        parcel.writeByte((byte) (isBot ? 1 : 0));
        parcel.writeStringList(chatHistory);
    }
}
