import java.sql.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

import javax.swing.JOptionPane;

public class Logging {
	public static void Log(String c, String t, String d) {
		Connection conn = null;
		conn = sqliteConnection.dbConnect();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		String dateFormatted = dtf.format(now);
		dateFormatted = dateFormatted.replace("/", "");
		dateFormatted = dateFormatted.replace(" ", "");
		dateFormatted = dateFormatted.replace(":", "");
		String id = dateFormatted;
		try 
		{
			String query = "insert into Log (EventID,EventCode,EventType,EventDescription) values (?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, id);
			stmt.setString(2, c);
			stmt.setString(3, t);
			stmt.setString(4, d);
			stmt.execute();			
			stmt.close();
		}
		catch(Exception e1)
		{
			JOptionPane.showMessageDialog(null, e1);
			
		}
		
	}
}
