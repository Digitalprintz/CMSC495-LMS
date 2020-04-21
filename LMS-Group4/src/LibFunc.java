/**
   [Initial page draws the GUI and connects to the SQLite Database]
   
   [Notes]
   
   @author [Richard Arnold, Redi Delulo, Krista Burdick, Chris Hammond, Alyssa Knight and Matt Worman]
   @version $Revision: .4 $ $Date: 2020/21/04 08:40:21 $

**/


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class LibFunc extends JFrame 
{

	private JPanel contentPane;
	private JTextField searchText;

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
		
			//searchPanel creation
			JPanel searchPanel = new JPanel();
			searchPanel.setBounds(10, 81, 616, 301);
			contentPane.add(searchPanel);
			searchPanel.setLayout(null);
			searchPanel.setVisible(false);
			
			
			//mainPanel creation
			JPanel mainPanel = new JPanel();
			mainPanel.setBounds(10, 81, 616, 301);
			contentPane.add(mainPanel);
			mainPanel.setLayout(null);
			mainPanel.setVisible(true);
		
		
		//Back Buttons creation~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			JButton searchBackBtn = new JButton("Back");
			searchBackBtn.setBounds(382, 11, 117, 40);
			contentPane.add(searchBackBtn);
			searchBackBtn.setVisible(false);
		
		
		
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//Main Panel
		
		//Main page User and Media items
		JLabel lblNewLabel_1 = new JLabel("Media");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(321, 10, 285, 30);
		mainPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Users");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(10, 10, 285, 30);
		mainPanel.add(lblNewLabel_1_1);
		
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
		
		JButton btnAddMedia = new JButton("Add 500 Media Items");
		btnAddMedia.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			}
		});
		btnAddMedia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddMedia.setBounds(321, 133, 285, 30);
		mainPanel.add(btnAddMedia);
		
		JButton btnAddUser = new JButton("Search User");
		btnAddUser.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				mainPanel.setVisible(false);
				searchPanel.setVisible(true);
				searchBackBtn.setVisible(true);
			}
		});
		btnAddUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddUser.setBounds(10, 51, 285, 30);
		mainPanel.add(btnAddUser);
		
		JButton btnRemoveUser = new JButton("Add User");
		btnRemoveUser.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			}
		});
		
		JButton btnNewButton = new JButton("Add Media");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(321, 51, 285, 30);
		mainPanel.add(btnNewButton);
		btnRemoveUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemoveUser.setBounds(10, 133, 285, 30);
		mainPanel.add(btnRemoveUser);
		
		JButton btnRemoveUser_1 = new JButton("Remove User");
		btnRemoveUser_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			}
		});
		btnRemoveUser_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemoveUser_1.setBounds(10, 174, 285, 30);
		mainPanel.add(btnRemoveUser_1);
		
		JButton btnModifyUser = new JButton("Modify User");
		btnModifyUser.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			}
		});
		btnModifyUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnModifyUser.setBounds(10, 92, 285, 30);
		mainPanel.add(btnModifyUser);
		
		
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
		
		//Handles going back to functionality page page
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
		
	}
}
