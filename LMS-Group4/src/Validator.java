import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	public static boolean checkName(String fn) {
		//Checks that the First Name is valid
		if(!(!fn.isBlank()) && (fn != null) && (fn.matches("^[a-zA-Z]*$"))) {
			JOptionPane.showMessageDialog(null, "Names can only contain letters and cannot be blank.");
			return false;
		} else {
			return true;
		}
	}	
		
	public static boolean checkPassword(String pw) {
		//Checks that the Password is valid
		if(pw.length() < 4 || pw.contains(" ")){
			JOptionPane.showMessageDialog(null, "Password must be 4 characters or more and not include spaces");
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean checkEmail(String e){
		//Checks that the Email is valid
		e = e.replace(" ", "");
		
		String validInput = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern p = Pattern.compile(validInput);
		Matcher m = p.matcher(e);
		Connection conn = sqliteConnection.dbConnect();
		try {
			if(!m.matches()) {
				JOptionPane.showMessageDialog(null, "Email format must be XXX@XXX.XXX");
				conn.close();
				return false;
			} else {
			
				String query = "SELECT * FROM Login WHERE EmailAddress like '" + e + "'";
			
				PreparedStatement doQuery = conn.prepareStatement(query);
				ResultSet result = doQuery.executeQuery();
			
				if(result.next()) {
					JOptionPane.showMessageDialog(null, "A user has already registered with the email: " + e);
					conn.close();
					return false;
				} else {
					conn.close();
					return true;
				}
			}
					
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		return false;
	}

	
	public static boolean checkPhone(String pn) {
		//Checks that the Password is valid
		pn = pn.replace("-", "");
		
		if(!pn.isBlank() && (!pn.matches("\\d+") || pn.length() != 10)){
			JOptionPane.showMessageDialog(null, "Please enter a 10 digit phone number, or leave blank.");
			return false;
		} else {
			return true;
		}
	}
}