import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Authenticate {

	public static String encrypt(String p, String uid) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			String fullString = p + uid;
			byte[] hashedByte = digest.digest(fullString.getBytes(StandardCharsets.UTF_8));
			
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hashedByte.length; i++) {
				String hex = Integer.toHexString(0xff & hashedByte[i]);
			    if(hex.length() == 1) hexString.append('0');
			        hexString.append(hex);
			}
			return hexString.toString();		
			
		} catch (NoSuchAlgorithmException exception) {
	            // TODO Auto-generated catch block
	            exception.printStackTrace();
	    }
		return "";
	}
}