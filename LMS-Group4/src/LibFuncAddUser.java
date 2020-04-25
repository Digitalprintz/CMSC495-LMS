/**
   [Handles the add user functionality of LibFunc.java]
   
   [Notes]
   
   @author [Richard Arnold, Redi Delulo, Krista Burdick, Chris Hammond, Alyssa Knight and Matt Worman]
   @version $Revision: .6 $ $Date: 2020/25/04 14:38:25 $

**/

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class LibFuncAddUser extends JFrame 
{

	//Database Connection
	Connection conn = null;
	
	//other stuff
	private JPanel contentPane;

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
					LibFuncAddUser frame = new LibFuncAddUser();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LibFuncAddUser() 
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
		JButton exitBtn = new JButton("Exit");
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
		JLabel lblType = new JLabel("Librarian Functionality Add User");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblType.setBounds(10, 11, 314, 80);
		contentPane.add(lblType);
		
		//Add User Panel creation
		JPanel addUserPanel = new JPanel();
		addUserPanel.setBounds(10, 81, 616, 301);
		contentPane.add(addUserPanel);
		addUserPanel.setLayout(null);
		
		//Handles going back to the LibFunc page
		JButton addUserBackBtn = new JButton("Back");
		addUserBackBtn.setBounds(382, 11, 117, 40);
		addUserBackBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				LibFunc user = new LibFunc();
				user.setVisible(true);
				dispose();
			}
		});
		addUserBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(addUserBackBtn);
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//Add User Panel
		
		JLabel LibraryCard = new JLabel("Library Card #");
		LibraryCard.setHorizontalAlignment(SwingConstants.RIGHT);
		LibraryCard.setFont(new Font("Tahoma", Font.PLAIN, 18));
		LibraryCard.setBounds(10, 12, 131, 22);
		addUserPanel.add(LibraryCard);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(10, 45, 131, 22);
		addUserPanel.add(lblPassword);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFirstName.setBounds(10, 78, 131, 22);
		addUserPanel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLastName.setBounds(10, 111, 131, 22);
		addUserPanel.add(lblLastName);
		
		JLabel lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmailAddress.setBounds(10, 144, 131, 22);
		addUserPanel.add(lblEmailAddress);
		
		JLabel lblAddress2 = new JLabel("Address:");
		lblAddress2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAddress2.setBounds(10, 177, 131, 22);
		addUserPanel.add(lblAddress2);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPhoneNumber.setBounds(10, 210, 131, 22);
		addUserPanel.add(lblPhoneNumber);
		
		//Generates a random 15 digit library card for registration.
        String AlphaNumericString = "0123456789"; 
        StringBuilder sb = new StringBuilder(15); 
  
        for (int i = 0; i < 15; i++) { 
            int index = (int)(AlphaNumericString.length() * Math.random()); 
            sb.append(AlphaNumericString.charAt(index)); 
        } 
        String libCard = sb.toString();
		
		JTextField libCardText = new JTextField();
		libCardText.setText(libCard);
		libCardText.setEditable(false);
		libCardText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		libCardText.setBounds(151, 11, 455, 26);
		addUserPanel.add(libCardText);
		libCardText.setColumns(10);
		
		JTextField passText = new JTextField();
		passText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passText.setColumns(10);
		passText.setBounds(151, 44, 455, 26);
		addUserPanel.add(passText);
		
		JTextField firstNameText = new JTextField();
		firstNameText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstNameText.setColumns(10);
		firstNameText.setBounds(151, 77, 455, 26);
		addUserPanel.add(firstNameText);
		
		JTextField lastNameText = new JTextField();
		lastNameText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lastNameText.setColumns(10);
		lastNameText.setBounds(151, 110, 455, 26);
		addUserPanel.add(lastNameText);
		
		JTextField emailText = new JTextField();
		emailText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailText.setColumns(10);
		emailText.setBounds(151, 143, 455, 26);
		addUserPanel.add(emailText);
		
		JTextField addressText = new JTextField();
		addressText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addressText.setColumns(10);
		addressText.setBounds(151, 176, 455, 26);
		addUserPanel.add(addressText);
		
		JTextField phoneText = new JTextField();
		phoneText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		phoneText.setColumns(10);
		phoneText.setBounds(151, 209, 455, 26);
		addUserPanel.add(phoneText);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//checks to make sure all text fields are not null and are greater then 2 in length.
				if((firstNameText.getText() != null && firstNameText.getText().length() > 2) && 
						(lastNameText.getText() != null && lastNameText.getText().length() > 2) && 
						(passText.getText() != null && passText.getText().length() > 2) &&
						(emailText.getText() != null && emailText.getText().length() > 2) && 
						(addressText.getText() != null && addressText.getText().length() > 2) && 
						(phoneText.getText() != null && phoneText.getText().length() > 2))
				{
					try
					{
						//verifies that the library card doesn't exist already. Notifies user if it does and resets the library card number.
						String queryVerify = "select * from Login where LibraryCard=?";
						PreparedStatement stmtVerify = conn.prepareStatement(queryVerify);
						stmtVerify.setString(1, libCardText.getText());
						
						ResultSet rs = stmtVerify.executeQuery();
						int count = 0;
						while(rs.next())
						{
							count++;
						}
						
						if(count > 0)
						{
							JOptionPane.showMessageDialog(null, "Registration failed, Library Card already exists. Please try again.");
							//regenerates a random 15 digit library card for registration.
					        String AlphaNumericString = "0123456789"; 
					        StringBuilder sb = new StringBuilder(15); 
					  
					        for (int i = 0; i < 15; i++) { 
					            int index = (int)(AlphaNumericString.length() * Math.random()); 
					            sb.append(AlphaNumericString.charAt(index)); 
					        } 
					        String libCard = sb.toString();
					        
					        //resets the libcardtext
					        libCardText.setText(libCard);
						}
						else
						{
							try 
							{
								String query = "insert into Login (LibraryCard,FirstName,LastName,Password,EmailAddress,Address,PhoneNumber) values (?,?,?,?,?,?,?)";
								PreparedStatement stmt = conn.prepareStatement(query);
								stmt.setString(1, libCardText.getText());
								stmt.setString(2, firstNameText.getText());
								stmt.setString(3, lastNameText.getText());
								stmt.setString(4, passText.getText());
								stmt.setString(5, emailText.getText());
								stmt.setString(6, addressText.getText());
								stmt.setString(7, phoneText.getText());
								
								stmt.execute();
								JOptionPane.showMessageDialog(null, "Registration Success.");
								
								stmt.close();
								
								//Logs into the log database
								Logging.Log("3", "ACCOUNT_CREATED", "Account created with Library ID: " + libCardText.getText());
								
								//Clears all fields and resets library card # when finished.
								passText.setText("");
								firstNameText.setText("");
								lastNameText.setText("");
								emailText.setText("");
								addressText.setText("");
								phoneText.setText("");

						        String AlphaNumericString = "0123456789"; 
						        StringBuilder sb = new StringBuilder(15); 
						        for (int i = 0; i < 15; i++) { 
						            int index = (int)(AlphaNumericString.length() * Math.random()); 
						            sb.append(AlphaNumericString.charAt(index)); 
						        } 
						        String libCard = sb.toString();
						        libCardText.setText(libCard);
								
								
							}
							catch(Exception e1)
							{
								JOptionPane.showMessageDialog(null, e1);
								
							}
						}
						
					}
					catch(Exception e2)
					{
						JOptionPane.showMessageDialog(null, e2);
						
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Registration failed. All fields must be filled out.");
				}
			}
		});
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(460, 250, 146, 40);
		addUserPanel.add(btnNewButton);
				
				
	}

}
