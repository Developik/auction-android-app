package comp3350.srsys.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.srsys.application.Main;
import comp3350.srsys.application.Services;
import comp3350.srsys.objects.Item;
import comp3350.srsys.persistence.DataAccessStub;

public class AccessItems
{
	private DataAccessStub dataAccess;
	private List<Item> items;
	private Item item;
	private int currentItem;

	public AccessItems()
	{
		dataAccess = (DataAccessStub) Services.getDataAccess(Main.dbName);
		items = null;
		item = null;
		currentItem = 0;
	}

    public String getItems(List<Item> items)
    {
        items.clear();
        return dataAccess.getItemSequential(items);
    }

    public Item getSequential()
    {
        String result = null;
        if (items == null)
        {
            items = new ArrayList<Item>();
            result = dataAccess.getItemSequential(items);
            currentItem = 0;
        }
        if (currentItem < items.size())
        {
            item = (Item) items.get(currentItem);
            currentItem++;
        }
        else
        {
            items = null;
            item = null;
            currentItem = 0;
        }
        return item;
    }

    /*
	public Item getRandom(String itemID)
	{
		item = null;
		if (itemID.trim().equals(""))
		{
			//System.out.println("*** Invalid item number");
		}
		else
		{
			items = dataAccess.getItemRandom(new Item(itemID));
			if (items.size()==1)
			{
				item = (Item) items.get(0);
			}
		}
		return item;
	}
	*/

	public String insertItem(Item currentItem)
	{
		return dataAccess.insertItem(currentItem);
	}

	public String updateItem(Item currentItem)
	{
		return dataAccess.updateItem(currentItem);
	}

}
