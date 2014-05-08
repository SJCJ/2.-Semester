package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import controller.UserHistory;

public class Persistance implements Serializable 
	{
	  private static String fileName = "historik.csv";
	   
	  //public static is a class method. Saves the "MemberControl" arraylist to a csv file
	  public static void save(UserHistory uh)
		{
		 	ObjectOutputStream out = null;
		 	FileOutputStream fileOut = null;	 	
		 	File yourFile = new File(fileName);
		 	try 
	    {
		 		yourFile.createNewFile(); //create file if it doesn't exist
	      fileOut = new FileOutputStream(fileName, false);
	 			out = new ObjectOutputStream(fileOut);
	 			out.writeObject(uh); //write to file
	 		} 
	    catch (IOException e) {
	 			e.printStackTrace();
	 		}
	 		finally 
	    {
	 			try 
	      {
	 				out.close();
	 				fileOut.close();
	 	    } 
	      catch (IOException e) 
	      {
	 				e.printStackTrace();
	 			}
	 		}
	 	}
	     
	  //Loads the csv file to the "UserHistory" arraylist
	  public static UserHistory load()
	  {
		  UserHistory uh = new UserHistory();
	 	  try
	 	  {
	 	    FileInputStream fileIn = new FileInputStream(fileName);
	 	    ObjectInputStream in = new ObjectInputStream(fileIn);
	 	    try 
	      {
	     		uh = (UserHistory)in.readObject(); //cast object read from file to MemberControl
	 	    }
	     	catch(Exception e)
	    	{ 
	        e.printStackTrace();
	     	} 
	 	    in.close();
	 	    fileIn.close();
	 	    }
	 	  catch(IOException i) 	    
	    {
		    i.printStackTrace();
		    //something went wrong :-(
	 	    return null;
	 	  }
	 		return uh;
	 	}
}
