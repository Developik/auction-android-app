package comp3350.srsys.objects;

import java.text.DecimalFormat;
import java.util.Date;

public class Bid {

    private double value;
    private Date date;
    private User user;


    public Bid(double value, User user){
        if (value < 0)
            value = 0;

        DecimalFormat df = new DecimalFormat("#.##");
        value = Double.parseDouble(df.format(value));

        this.value = value;
        this.date = new Date();
        this.user = user;

    }

    public double getValue(){
        return value;
    }
    public Date getDate(){
        return date;
    }
    public User getUser(){
        return user;
    }

}
