package comp3350.srsys.objects;

import java.util.Date;

public class Bid {

    private int value;
    private Date date;
    private User user;


    public Bid(int value, User user){
        this.value = value;
        this.date = new Date();
        this.user = user;
    }

    public int getValue(){
        return value;
    }
    public Date getDate(){
        return date;
    }
    public User getUser(){
        return user;
    }

}
