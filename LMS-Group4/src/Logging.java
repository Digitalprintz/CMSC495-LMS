
/**
   [Logging functions to track all actions.]
   
   [Notes]
   
   @author [Richard Arnold, Redi Delulo, Krista Burdick, Chris Hammond, Alyssa Knight and Matt Worman]
   @version $Revision: .6 $ $Date: 2020/25/04 14:38:25 $

**/

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

public class Logging {
	public static void Log(String c, String t, String d) {
		Connection conn = null;

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateFormatted = dtf.format(now);
		dateFormatted = dateFormatted.replace("/", "");
		dateFormatted = dateFormatted.replace(" ", "");
		dateFormatted = dateFormatted.replace(":", "");
		String id = dateFormatted;
		try {
			conn = sqliteConnection.dbConnect();
			String query = "insert into Log (EventID,EventCode,EventType,EventDescription) values (?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, id);
			stmt.setString(2, c);
			stmt.setString(3, t);
			stmt.setString(4, d);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);

		}
		try {
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
