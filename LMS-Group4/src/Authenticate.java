/**
   [Authentication function to hash passwords for storage.]
   
   [Notes]
   
   @author [Richard Arnold, Redi Delulo, Krista Burdick, Chris Hammond, Alyssa Knight and Matt Worman]
   @version $Revision: .6 $ $Date: 2020/25/04 14:38:25 $

**/

//Imports
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Authenticate 
{
	//Returns the hashed String
	public static String encrypt(String p, String uid) 
	{
		try 
		{
			//Uses SHA-256 for security
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			//Combines password and unique ID for added security.
			String fullString = p + uid;
			//Hashes the password using SHA-256
			byte[] hashedByte = digest.digest(fullString.getBytes(StandardCharsets.UTF_8));
			
			//Converts the hashed password to a string for storage in SQLite
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hashedByte.length; i++) 
			{
				String hex = Integer.toHexString(0xff & hashedByte[i]);
			    if(hex.length() == 1) hexString.append('0');
			        hexString.append(hex);
			}
			//Returns the hashed password in string format.
			return hexString.toString();		
			
		} 
		catch (NoSuchAlgorithmException exception) 
		{
			exception.printStackTrace();
	    }
		return "";
	}
}