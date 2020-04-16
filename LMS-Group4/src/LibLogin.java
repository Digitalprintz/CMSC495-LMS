/**
   [Login Page for the Librarian.]
   
   [Notes]
   
   @author <A HREF="mailto:[christophera1997@yahoo.com]">[Christopher Hammond]</A>
   @version $Revision: .2 $ $Date: 2020/16/04 07:51:25 $

**/

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SuppressWarnings("serial")
public class LibLogin extends JFrame 
{

	private JPanel contentPane;
	private JTextField userText;
	private JPasswordField passText;

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
					LibLogin frame = new LibLogin();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	
	//Database Connection
	Connection conn = null;
	
	/**
	 * Create the frame.
	 */
	public LibLogin() 
	{
		//Sqlite Connection
		conn = sqliteConnection.dbConnect();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Handles going back to login page
		JButton exitBtn = new JButton("Back");
		exitBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Login user = new Login();
				user.frame.setVisible(true);
				dispose();
			}
		});
		exitBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		exitBtn.setBounds(509, 11, 117, 40);
		contentPane.add(exitBtn);
		
		//Project Label
		JLabel lblTitle = new JLabel("Library Management System - Group 1");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle.setBounds(10, 11, 314, 40);
		contentPane.add(lblTitle);
		
		//Project Label
		JLabel lblType = new JLabel("Librarian Login");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblType.setBounds(10, 11, 314, 80);
		contentPane.add(lblType);
		
		//login form stuff
		
		JLabel userLabel = new JLabel("Username:");
		userLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		userLabel.setBounds(48, 108, 109, 40);
		contentPane.add(userLabel);
		
		JLabel passLabel = new JLabel("Password:");
		passLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passLabel.setBounds(48, 168, 109, 40);
		contentPane.add(passLabel);
		
		userText = new JTextField();
		userText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userText.setBounds(150, 109, 477, 40);
		contentPane.add(userText);
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
					String query = "select * from LibLogin where Username=? and Password=? ";
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
						LibFunc user2 = new LibFunc();
						user2.setVisible(true);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Username or Password is incorrect, please try again.");
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
		contentPane.add(loginBtn);
		
		passText = new JPasswordField();
		passText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passText.setBounds(150, 169, 477, 40);
		contentPane.add(passText);
		
	}

}
