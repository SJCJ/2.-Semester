package model;

public class Store
{
	private int id;
	private String name;
	private Location location;
	private String openHour;
	private String closeHour;
	
	public Store(String name, Location location, String openHour, String closeHour)
	{
		this.name = name;
		this.location = location;
		this.openHour = openHour;
		this.closeHour = closeHour;
	}
}
