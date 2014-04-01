package model;

public class Item
{
	private int id;
	private String name;
	private double price;
	private String boughtDate;
	//todo: warrenty & offerId
	
	public Item(String name, double price)
	{
		this.name = name;
		this.price = price;
	}
}
