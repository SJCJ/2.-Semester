package model;

public class Customer
{
	private int id;
	private String fName;
	private String lName;
	private String eMail;
	private String address;
	
	public Customer(String fName, String lName, String eMail, String address)
	{
		this.fName = fName;
		this.lName = lName;
		this.eMail = eMail;
		this.address = address;
		//comment
	}
}
