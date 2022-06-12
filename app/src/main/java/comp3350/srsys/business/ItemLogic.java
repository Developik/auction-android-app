package comp3350.srsys.business;

import java.util.Date;
import java.util.List;

import comp3350.srsys.objects.SC;

public class ItemLogic
{
    public static Long generateID()
    {
        return new Date().getTime();
    }
}
