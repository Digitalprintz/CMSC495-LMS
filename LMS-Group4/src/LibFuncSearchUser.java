/**
   [Handles the search user functionality of LibFunc.java]
   
   [Notes]
   
   @author [Richard Arnold, Redi Delulo, Krista Burdick, Chris Hammond, Alyssa Knight and Matt Worman]
   @version $Revision: .5 $ $Date: 2020/23/04 04:23:26 $

**/

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class LibFuncSearchUser extends JFrame 
{

	//Database Connection
	Connection conn = null;
	
	//other stuff
	private JPanel contentPane;
	private JTextField searchText;
	private JTable table;

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
					LibFuncSearchUser frame = new LibFuncSearchUser();
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
	public LibFuncSearchUser() 
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
		JLabel lblType = new JLabel("Librarian Functionality Search User");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblType.setBounds(10, 11, 314, 80);
		contentPane.add(lblType);
		
		//searchPanel creation
		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(10, 81, 616, 301);
		contentPane.add(searchPanel);
		searchPanel.setLayout(null);
		
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
					//Verifies that the search box is not empty and has a length greater then 1 (Makes sure they enter atleast 2 characters)
					if(searchText.getText().length() > 1)
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
					else
					{
						JOptionPane.showMessageDialog(null, "Please enter a value to search for users.");
					}	
					
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
		
		//handles going back to the LibFunc main page.
		JButton searchBackBtn = new JButton("Back");
		searchBackBtn.setBounds(382, 11, 117, 40);
		contentPane.add(searchBackBtn);
		searchBackBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				LibFunc user = new LibFunc();
				user.setVisible(true);
				dispose();
			}
		});
		searchBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
	}

}
