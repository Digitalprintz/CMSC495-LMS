/**
   [Performs validation on data entry]
   
   [Notes]
   
   @author [Richard Arnold, Redi Delulo, Krista Burdick, Chris Hammond, Alyssa Knight and Matt Worman]
   @version $Revision: .6 $ $Date: 2020/25/04 14:38:25 $

**/

//Imports
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	// Verifies that the input only contains letters
	public static boolean checkName(String fn) {
		if (!(!fn.isBlank()) && (fn != null) && (fn.matches("^[a-zA-Z]*$"))) {
			// Warns that data must be entered and only contain letters.
			JOptionPane.showMessageDialog(null, "Names can only contain letters and cannot be blank.");
			return false;
		} else {
			return true;
		}
	}

	// Verifies that the password meets the requirements.
	public static boolean checkPassword(String pw) {
		// Checks that the Password is valid
		if (pw.length() < 4 || pw.contains(" ")) {
			// Warns that the password must meet the complexity requirements.
			JOptionPane.showMessageDialog(null, "Password must be 4 characters or more and not include spaces");
			return false;
		} else {
			return true;
		}
	}

	// Verifies the email entered. No duplicate emails can be registered and they
	// must meet the formatting requirements.
	public static boolean checkEmail(String e) {
		// Checks that the Email is valid
		e = e.replace(" ", "");

		String validInput = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern p = Pattern.compile(validInput);

		// Starter variables
		boolean validCheck = false;
		long count = 0;
		String afterDot = "";

		// Verifies that the email entered is formatted properly
		if (e.contains("@")) {
			String domain = e.substring(e.indexOf("@"), e.length());
			if (domain.contains(".")) {
				afterDot = domain.substring(domain.indexOf("."), domain.length());
				count = domain.chars().filter(ch -> ch == '.').count();
				validCheck = true;

			}
		}

		// Creates a matcher profile to compare against the email
		Matcher m = p.matcher(e);

		// Connection to the database
		Connection conn = sqliteConnection.dbConnect();

		// If the input does not meet one of the conditions
		try {
			if (!validCheck || !m.matches() || count != 1 || afterDot.length() == 1) {
				JOptionPane.showMessageDialog(null, "Email format must be XXX@XXX.XXX");
				conn.close();
				return false;
			} else {

				String query = "SELECT * FROM Login WHERE EmailAddress like '" + e + "'";

				PreparedStatement doQuery = conn.prepareStatement(query);
				ResultSet result = doQuery.executeQuery();

				// If the email is found in the database
				if (result.next()) {
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

	// Verifies that the phone entry is blank or meets the requirements
	public static boolean checkPhone(String pn) {
		// Checks that the Password is valid
		pn = pn.replace("-", "");
		pn = pn.replace(" ", "");

		if (!pn.isBlank() && (!pn.matches("\\d+") || pn.length() != 10)) {
			// Warn about data not being entered properly
			JOptionPane.showMessageDialog(null, "Please enter a 10 digit phone number, or leave blank.");
			return false;
		} else {
			return true;
		}
	}

	// Performs the same checks as email validation but does not check for duplicate
	// email
	public static boolean checkEmailUpdate(String e, boolean c) {
		// Checks that the Email is valid
		e = e.replace(" ", "");

		String validInput = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern p = Pattern.compile(validInput);

		// Starter variables
		boolean validCheck = false;
		long count = 0;
		String afterDot = "";

		// Verifies that the email entered is formatted properly
		if (e.contains("@")) {
			String domain = e.substring(e.indexOf("@"), e.length());
			if (domain.contains(".")) {
				afterDot = domain.substring(domain.indexOf("."), domain.length());
				count = domain.chars().filter(ch -> ch == '.').count();
				validCheck = true;

			}
		}

		// Creates a matcher profile to compare against the email
		Matcher m = p.matcher(e);

		// Connection to the database
		Connection conn = sqliteConnection.dbConnect();

		// If the input does not meet one of the conditions
		try {
			if (!validCheck || !m.matches() || count != 1 || afterDot.length() == 1) {
				JOptionPane.showMessageDialog(null, "Email format must be XXX@XXX.XXX");
				conn.close();
				return false;
			} else {
				if (c) {
					String query = "SELECT * FROM Login WHERE EmailAddress like '" + e + "'";

					PreparedStatement doQuery = conn.prepareStatement(query);
					ResultSet result = doQuery.executeQuery();

					// If the email is found in the database
					if (result.next()) {
						JOptionPane.showMessageDialog(null, "A user has already registered with the email: " + e);
						conn.close();
						return false;
					} else {
						conn.close();
						return true;
					}
				} else {
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
}