/**
   [Authentication function to hash passwords for storage.]
   
   [Notes]
   
   @author [Richard Arnold, Redi Delulo, Krista Burdick, Chris Hammond, Alyssa Knight and Matt Worman]
   @version $Revision: .7 $ $Date: 2020/29/04 12:24:36 $

**/

//Imports
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Authenticate 
{
	//Reverses the library card for use in salting.
	public static String reverseString(String rev)
	{
		String reversed;
		
		// getBytes() method to convert string  
        // into bytes[]. 
        byte [] strAsByteArray = rev.getBytes(); 
  
        byte [] result =  
                   new byte [strAsByteArray.length]; 
  
        // Store result in reverse order into the 
        // result byte[] 
        for (int i = 0; i<strAsByteArray.length; i++) 
            result[i] =  
             strAsByteArray[strAsByteArray.length-i-1]; 
        
        //sets the reversed value and returns it.
		reversed = new String(result);
		return reversed;
	}
	
	// Returns the hashed String
	public static String encrypt(String p, String uid)
	{
		try {
			String y = reverseString(p);
			// Uses SHA-256 for security
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			// Combines password and unique ID for added security.
			String fullString = p + uid + y;
			// Hashes the password using SHA-256
			byte[] hashedByte = digest.digest(fullString.getBytes(StandardCharsets.UTF_8));

			// Converts the hashed password to a string for storage in SQLite
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hashedByte.length; i++) 
			{
				String hex = Integer.toHexString(0xff & hashedByte[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			// Returns the hashed password in string format.
			return hexString.toString();

		} 
		catch (NoSuchAlgorithmException exception) 
		{
			exception.printStackTrace();
		}
		return "";
	}
	
	
}