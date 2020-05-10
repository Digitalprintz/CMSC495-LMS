/**
   [Creates connection to SQLite for database use.]
   
   [Notes]
   
   @author [Richard Arnold, Redi Delulo, Krista Burdick, Chris Hammond, Alyssa Knight and Matt Worman]
   @version $Revision: .8 $ $Date: 05/06/2020 17:13:53 $

**/

import java.sql.*;
import javax.swing.*;

public class sqliteConnection 
{
	
	Connection conn = null;
	
	
	public static Connection dbConnect()
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:LMSLogin.sqlite");
			return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}

}
