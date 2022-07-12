package comp3350.srsys.objects;

import java.util.ArrayList;

public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private Integer age;
    private ArrayList<Bid> myBids;
    private String[] messages; // expand more who will be working on this part // Add MU
    private Integer[] watchlist; // list of IDs or the person working on this part could create relation object
    private Integer[] soldItems;
    private Integer[] auctionsWon;
    private Integer[] itemsListed;
    private boolean isBot;


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

        if (!userObjectValidation()) {
            System.out.println(username + " " + firstName + " " + lastName + " " + address +
                    " " + age + " ");
            throw new Exception("User parameters are incorrect!");
        }
    }

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

}
