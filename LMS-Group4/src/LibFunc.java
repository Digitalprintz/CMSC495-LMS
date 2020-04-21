/**
   [Initial page draws the GUI and connects to the SQLite Database]
   
   [Notes]
   
   @author [Richard Arnold, Redi Delulo, Krista Burdick, Chris Hammond, Alyssa Knight and Matt Worman]
   @version $Revision: .4 $ $Date: 2020/21/04 08:40:21 $

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
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class LibFunc extends JFrame 
{

	private JPanel contentPane;
	private JTextField searchText;
	private String libCard;

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
					LibFunc frame = new LibFunc();
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
	private JTable table;
	private JTextField txtFieldLibCard;
	private JTextField txtFieldFirst;
	private JTextField txtFieldLast;
	private JTextField txtFieldEmail;
	private JTextField txtFieldAddress;
	private JTextField txtFieldPhone;
	
	/**
	 * Create the frame.
	 */
	public LibFunc() 
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
		JLabel lblType = new JLabel("Librarian Functionality");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblType.setBounds(10, 11, 314, 80);
		contentPane.add(lblType);
		
		//Panel Creations~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		//Add User Panel creation
		JPanel addUserPanel = new JPanel();
		addUserPanel.setBounds(10, 81, 616, 301);
		contentPane.add(addUserPanel);
		addUserPanel.setLayout(null);
		addUserPanel.setVisible(false);
				
		//Modify User Panel creation
		JPanel modifyPanel = new JPanel();
		modifyPanel.setBounds(10, 81, 616, 301);
		contentPane.add(modifyPanel);
		modifyPanel.setLayout(null);
		modifyPanel.setVisible(false);
		
		
		//mainPanel creation
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(10, 81, 616, 301);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setVisible(true);
		
		//searchPanel creation
		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(10, 81, 616, 301);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);
		searchPanel.setVisible(false);

		
		//Back Buttons creation~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		JButton searchBackBtn = new JButton("Back");
		searchBackBtn.setBounds(382, 11, 117, 40);
		contentPane.add(searchBackBtn);
		searchBackBtn.setVisible(false);
		
		JButton modifyBackBtn = new JButton("Back");
		modifyBackBtn.setBounds(382, 11, 117, 40);
		contentPane.add(modifyBackBtn);
		modifyBackBtn.setVisible(false);
		
		JButton addUserBackBtn = new JButton("Back");
		addUserBackBtn.setBounds(382, 11, 117, 40);
		contentPane.add(addUserBackBtn);
		addUserBackBtn.setVisible(false);
		
		
		
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//Main Panel
		
		//Main page User and Media items
		JLabel lblMedia = new JLabel("Media");
		lblMedia.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMedia.setHorizontalAlignment(SwingConstants.CENTER);
		lblMedia.setBounds(321, 10, 285, 30);
		mainPanel.add(lblMedia);
		
		JLabel lblUsers = new JLabel("Users");
		lblUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsers.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUsers.setBounds(10, 10, 285, 30);
		mainPanel.add(lblUsers);
		
		//Handles changing to the Remove Media panel
		JButton btnRemoveMedia = new JButton("Remove Media");
		btnRemoveMedia.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			}
		});
		btnRemoveMedia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemoveMedia.setBounds(321, 92, 285, 30);
		mainPanel.add(btnRemoveMedia);
		
		//Adds 500 randomized media items into the database to test database stability and load limits
		JButton btnAdd500 = new JButton("Add 500 Media Items");
		btnAdd500.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			}
		});
		btnAdd500.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd500.setBounds(321, 133, 285, 30);
		mainPanel.add(btnAdd500);
		
		//Handles changing to the Search User panel
		JButton btnSearchUser = new JButton("Search User");
		btnSearchUser.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				mainPanel.setVisible(false);
				searchPanel.setVisible(true);
				searchBackBtn.setVisible(true);
			}
		});
		btnSearchUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearchUser.setBounds(10, 51, 285, 30);
		mainPanel.add(btnSearchUser);
		
		//Handles changing to the Add User panel
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				addUserPanel.setVisible(true);
				addUserBackBtn.setVisible(true);
				mainPanel.setVisible(false);
			}
		});
		
		//Handles changing to the Add Media panel
		JButton btnAddMed = new JButton("Add Media");
		btnAddMed.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			}
		});
		btnAddMed.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddMed.setBounds(321, 51, 285, 30);
		mainPanel.add(btnAddMed);
		btnAddUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddUser.setBounds(10, 133, 285, 30);
		mainPanel.add(btnAddUser);
		
		
		//Handles changing to the Modify User panel
		JButton btnModifyUser = new JButton("Modify User");
		btnModifyUser.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				mainPanel.setVisible(false);
				modifyPanel.setVisible(true);
				modifyBackBtn.setVisible(true);
			}
		});
		btnModifyUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnModifyUser.setBounds(10, 92, 285, 30);
		mainPanel.add(btnModifyUser);
		
		//Handles changing to the Remove User panel
		JButton btnRemoveUser = new JButton("Remove User");
		btnRemoveUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemoveUser.setBounds(10, 174, 285, 30);
		mainPanel.add(btnRemoveUser);
		
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//Search Panel
		
		//searchPanel Stuff:
		searchText = new JTextField();
		searchText.setBounds(10, 9, 457, 35);
		searchPanel.add(searchText);
		searchText.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 596, 235);
		searchPanel.add(scrollPane);
	
		//Performs the search based on what is inside the Search textfield 
		JButton SearchBtn = new JButton("Search");
		SearchBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					//Queries and finds number of rows
					String query = "select * from Login where LibraryCard like ? or FirstName like ? or Lastname like ? or EmailAddress like ? or Address like ? or PhoneNumber like ? ";
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.setString(1, "%" + searchText.getText() + "%");
					stmt.setString(2, "%" + searchText.getText() + "%");
					stmt.setString(3, "%" + searchText.getText() + "%");
					stmt.setString(4, "%" + searchText.getText() + "%");
					stmt.setString(5, "%" + searchText.getText() + "%");
					stmt.setString(6, "%" + searchText.getText() + "%");
					ResultSet rs = stmt.executeQuery();
					
					int rowCount = 0;
					while(rs.next())
					{
						rowCount++;
					}
					
					//Actual search query
					String query2 = "select * from Login where LibraryCard like ? or FirstName like ? or Lastname like ? or EmailAddress like ? or Address like ? or PhoneNumber like ? ";
					PreparedStatement stmt2 = conn.prepareStatement(query2);
					stmt2.setString(1, "%" + searchText.getText() + "%");
					stmt2.setString(2, "%" + searchText.getText() + "%");
					stmt2.setString(3, "%" + searchText.getText() + "%");
					stmt2.setString(4, "%" + searchText.getText() + "%");
					stmt2.setString(5, "%" + searchText.getText() + "%");
					stmt2.setString(6, "%" + searchText.getText() + "%");
					ResultSet rs2 = stmt2.executeQuery();
					
					
					//Adds table to scrollpane
					table = new JTable(rowCount,5);
					scrollPane.setViewportView(table);
					
					//Sets table headers and sizing automatically
					table.getColumnModel().getColumn(0).setHeaderValue("Library Card");
					table.getColumnModel().getColumn(1).setHeaderValue("First Name");
					table.getColumnModel().getColumn(2).setHeaderValue("Last Name");
					table.getColumnModel().getColumn(3).setHeaderValue("Email Address");
					table.getColumnModel().getColumn(4).setHeaderValue("Address");
					table.getTableHeader().resizeAndRepaint();
					

					
					//iterates through resultset and fills table
					int count = 0;
					while(rs2.next())
					{
						table.setValueAt(rs2.getString(2),count, 0);
						table.setValueAt(rs2.getString(3),count, 1);
						table.setValueAt(rs2.getString(4),count, 2);
						table.setValueAt(rs2.getString(6),count, 3);
						table.setValueAt(rs2.getString(7),count, 4);
						count++;
					}
					
					count = 0;
					rowCount = 0;
					stmt.close();
					stmt2.close();
					rs.close();
					rs2.close();
					
					
				}
				catch(Exception d)
				{
					d.printStackTrace();
					
				}
			}
			
		});
		SearchBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		SearchBtn.setBounds(480, 9, 126, 35);
		searchPanel.add(SearchBtn);
		
		//Handles going back to main panel
		searchBackBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				searchPanel.setVisible(false);
				searchBackBtn.setVisible(false);
				mainPanel.setVisible(true);
				
				
			}
		});
		searchBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//Modify Users Panel
		
		JLabel lblFindLibCard = new JLabel("Library Card:");
		lblFindLibCard.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFindLibCard.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFindLibCard.setBounds(10, 11, 106, 30);
		modifyPanel.add(lblFindLibCard);
		
		txtFieldLibCard = new JTextField();
		txtFieldLibCard.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFieldLibCard.setBounds(121, 11, 326, 30);
		modifyPanel.add(txtFieldLibCard);
		txtFieldLibCard.setColumns(10);
		
		JButton btnFindUser = new JButton("Auto Fill Info");
		btnFindUser.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					
					//Fills out the information in the text fields for the librarian to edit based on entered library card #
					libCard = txtFieldLibCard.getText();
					String query3 = "select * from Login where LibraryCard=?";
					PreparedStatement stmt3 = conn.prepareStatement(query3);
					stmt3.setString(1, libCard);
					ResultSet rs3 = stmt3.executeQuery();
	
					//Runs the query and fills out text fields while keeping track of whether the library card existed or not.
					//Sends message if library card doesn't exist to try again.
					int count = 0;
					while(rs3.next())
					{
						txtFieldFirst.setText(rs3.getString(3));
						txtFieldLast.setText(rs3.getString(4));
						txtFieldEmail.setText(rs3.getString(6));
						txtFieldAddress.setText(rs3.getString(7));
						txtFieldPhone.setText(rs3.getString(8));
						count++;
					}
					if(count == 1)
					{
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Library Card doesn't exist, please try again.");
					}
					
					stmt3.close();
					rs3.close();
				}
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnFindUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFindUser.setBounds(457, 11, 149, 30);
		modifyPanel.add(btnFindUser);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				if(txtFieldLibCard.getText().contentEquals(libCard))
				{
					try 
					{
						String query2 = "Update Login set FirstName=?,LastName=?,EmailAddress=?,Address=?,PhoneNumber=? where LibraryCard=?";
						
						PreparedStatement stmt2 = conn.prepareStatement(query2);
						stmt2.setString(1, txtFieldFirst.getText());
						stmt2.setString(2, txtFieldLast.getText());
						stmt2.setString(3, txtFieldEmail.getText());
						stmt2.setString(4, txtFieldAddress.getText());
						stmt2.setString(5, txtFieldPhone.getText());
						stmt2.setString(6, libCard);
						stmt2.execute();
	
						
						String query3 = "select * from Login where LibraryCard=? ";
						PreparedStatement stmt3 = conn.prepareStatement(query3);
						stmt3.setString(1, libCard);
						ResultSet rs3 = stmt3.executeQuery();
						
						
						while(rs3.next())
						{
							txtFieldFirst.setText(rs3.getString(3));
							txtFieldLast.setText(rs3.getString(4));
							txtFieldPhone.setText(rs3.getString(8));
							txtFieldAddress.setText(rs3.getString(7));
							txtFieldEmail.setText(rs3.getString(6));
						}
						
						stmt2.close();
						stmt3.close();
						rs3.close();
						
					} 
					catch (SQLException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Library Card has changed. Please re-enter and try again.");
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(457, 260, 149, 30);
		modifyPanel.add(btnUpdate);
		
		
		txtFieldFirst = new JTextField();
		txtFieldFirst.setHorizontalAlignment(SwingConstants.LEFT);
		txtFieldFirst.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFieldFirst.setColumns(10);
		txtFieldFirst.setBounds(121, 52, 485, 30);
		modifyPanel.add(txtFieldFirst);
		
		txtFieldLast = new JTextField();
		txtFieldLast.setHorizontalAlignment(SwingConstants.LEFT);
		txtFieldLast.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFieldLast.setColumns(10);
		txtFieldLast.setBounds(121, 93, 485, 30);
		modifyPanel.add(txtFieldLast);
		
		txtFieldEmail = new JTextField();
		txtFieldEmail.setHorizontalAlignment(SwingConstants.LEFT);
		txtFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFieldEmail.setColumns(10);
		txtFieldEmail.setBounds(121, 134, 485, 30);
		modifyPanel.add(txtFieldEmail);
		
		txtFieldAddress = new JTextField();
		txtFieldAddress.setHorizontalAlignment(SwingConstants.LEFT);
		txtFieldAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFieldAddress.setColumns(10);
		txtFieldAddress.setBounds(121, 175, 485, 30);
		modifyPanel.add(txtFieldAddress);
		
		txtFieldPhone = new JTextField();
		txtFieldPhone.setHorizontalAlignment(SwingConstants.LEFT);
		txtFieldPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtFieldPhone.setColumns(10);
		txtFieldPhone.setBounds(121, 216, 485, 30);
		modifyPanel.add(txtFieldPhone);
		
		JLabel lblFirst = new JLabel("First Name:");
		lblFirst.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirst.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirst.setBounds(10, 52, 106, 30);
		modifyPanel.add(lblFirst);
		
		JLabel lblLast = new JLabel("Last Name:");
		lblLast.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLast.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLast.setBounds(10, 93, 106, 30);
		modifyPanel.add(lblLast);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(10, 134, 106, 30);
		modifyPanel.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddress.setBounds(10, 175, 106, 30);
		modifyPanel.add(lblAddress);
		
		JLabel lblPhone = new JLabel("Phone #:");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhone.setBounds(10, 216, 106, 30);
		modifyPanel.add(lblPhone);
		modifyPanel.setVisible(false);
		
		//Handles going back to main panel
		modifyBackBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				modifyPanel.setVisible(false);
				modifyBackBtn.setVisible(false);
				txtFieldFirst.setText("");
				txtFieldLast.setText("");
				txtFieldEmail.setText("");
				txtFieldAddress.setText("");
				txtFieldPhone.setText("");
				txtFieldLibCard.setText("");
				mainPanel.setVisible(true);
				
				
			}
		});
		modifyBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
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
						
						Logging.Log("3", "ACCOUNT_CREATED", "Account created with Library ID: " + libCardText.getText());
						
						
						//Returns back to the main panel, resets all text fields, regenerates random library card # for next add user.
						addUserPanel.setVisible(false);
						addUserBackBtn.setVisible(false);
						mainPanel.setVisible(true);
						addressText.setText("");
						firstNameText.setText("");
						lastNameText.setText("");
						emailText.setText("");
						phoneText.setText("");
						passText.setText("");
						
						
						//Generates a random 15 digit library card for next add user and adds it to libCardText.
						//This is required because the panel is not disposed, but hidden so the variables stay the same.
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
				else
				{
					JOptionPane.showMessageDialog(null, "Registration failed. All fields must be filled out.");
				}
			}
		});
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(460, 250, 146, 40);
		addUserPanel.add(btnNewButton);
		
		//Handles going back to main panel
		addUserBackBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				addUserPanel.setVisible(false);
				addUserBackBtn.setVisible(false);
				mainPanel.setVisible(true);
				addressText.setText("");
				firstNameText.setText("");
				lastNameText.setText("");
				emailText.setText("");
				phoneText.setText("");
				passText.setText("");
				
				
				//Generates a random 15 digit library card for next add user and adds it to libCardText.
				//This is required because the panel is not disposed, but hidden so the variables stay the same.
		        String AlphaNumericString = "0123456789"; 
		        StringBuilder sb = new StringBuilder(15); 
		  
		        for (int i = 0; i < 15; i++) { 
		            int index = (int)(AlphaNumericString.length() * Math.random()); 
		            sb.append(AlphaNumericString.charAt(index)); 
		        } 
		        String libCard = sb.toString();
		        libCardText.setText(libCard);
				
				
			}
		});
		addUserBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
	}
}
