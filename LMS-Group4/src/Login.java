/**
   [Main login page. Login for the user, link to create an account, link to librarian login.]
   
   [Notes]
   
   @author [Richard Arnold, Redi Delulo, Krista Burdick, Chris Hammond, Alyssa Knight and Matt Worman]
   @version $Revision: .5 $ $Date: 2020/23/04 04:23:26 $

**/

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection conn = null;
	private JTextField userText;
	private JPasswordField passText;
	
	/**
	 * Create the application.
	 */
	public Login() 
	{
		//Sqlite Connection
		conn = sqliteConnection.dbConnect();
		
		//Frame
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Labels
		JLabel lblNewLabel = new JLabel("Library Management System - Group 1");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 314, 40);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel userLabel = new JLabel("Library Card:");
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		userLabel.setBounds(48, 108, 109, 40);
		frame.getContentPane().add(userLabel);
		
		JLabel passLabel = new JLabel("Password:");
		passLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passLabel.setBounds(48, 168, 109, 40);
		frame.getContentPane().add(passLabel);
		
		userText = new JTextField();
		userText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userText.setBounds(150, 109, 477, 40);
		frame.getContentPane().add(userText);
		userText.setColumns(10);
		
		JButton loginBtn = new JButton("Login");
		
		//Handles the actions after clicking the login button. 
		//Should verify whether user exists, print an invalid username/password if user doesn't exist, and then go to the user page.
		loginBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					String query = "select * from Login where LibraryCard=? and Password=? ";
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.setString(1, userText.getText());
					stmt.setString(2, passText.getText());
					
					ResultSet rs = stmt.executeQuery();
					int count=0;
					while(rs.next())
					{
						count++;
						
					}
					if(count == 1)
					{
						UserPage user = new UserPage();
						user.passCard(userText.getText());
						frame.setVisible(false);
						user.setVisible(true);
						Logging.Log("1", "SUCCESSFUL_LOGIN", "Login successful with username: " + userText.getText());
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Username or Password is incorrect, please try again.");
						Logging.Log("2", "FAILED_LOGIN", "Login failed with username: " + userText.getText());
					}
					
					rs.close();
					stmt.close();

				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
					
				}
				
			}
		});
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		loginBtn.setForeground(new Color(0, 0, 0));
		loginBtn.setBackground(Color.GREEN);
		loginBtn.setBounds(461, 220, 166, 40);
		frame.getContentPane().add(loginBtn);
		
		passText = new JPasswordField();
		passText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passText.setBounds(150, 169, 477, 40);
		frame.getContentPane().add(passText);
		
		//Goes to the Register window
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Register user = new Register();
				frame.setVisible(false);
				user.setVisible(true);
			}
		});
		registerBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		registerBtn.setBounds(10, 331, 191, 50);
		frame.getContentPane().add(registerBtn);
		
		//Handles switching to the librarian login page
		JButton libLoginBtn = new JButton("Librarian Login");
		libLoginBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				LibLogin user3 = new LibLogin();
				frame.setVisible(false);
				user3.setVisible(true);
			}
		});
		libLoginBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		libLoginBtn.setBounds(436, 331, 191, 50);
		frame.getContentPane().add(libLoginBtn);
	}
}
