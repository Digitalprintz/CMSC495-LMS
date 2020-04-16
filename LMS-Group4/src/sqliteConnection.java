/**
   [Creates connection to SQLite for database use.]
   
   [Notes]
   
   @author <A HREF="mailto:[christophera1997@yahoo.com]">[Christopher Hammond]</A>
   @version $Revision: .2 $ $Date: 2020/16/04 07:51:25 $

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
