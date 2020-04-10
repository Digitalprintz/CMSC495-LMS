/*
 * UMGC CMSC 495 Library Management System
 * Group 4
 */

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
