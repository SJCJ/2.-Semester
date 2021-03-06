package controller;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class UserHistory implements Serializable
{
	private final ArrayList<String> itemIds = new ArrayList<String>();
	
	public ArrayList<String> getIds()
	{
		return itemIds;
	}
	
	public String getId(String i)
	{
		for(String item : getIds())
		{
			if (item.equals(i))
			{
				return item;
			}
		}
		return null;
	}
	public void addItem(String id)
	{
		itemIds.add(id);
	}
	
	public void deleteItem(String id)
	{
		itemIds.remove(id);
	}
	
}
