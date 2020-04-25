/**
   [Handles the modify user functionality of LibFunc.java]
   
   [Notes]
   
   @author [Richard Arnold, Redi Delulo, Krista Burdick, Chris Hammond, Alyssa Knight and Matt Worman]
   @version $Revision: .6 $ $Date: 2020/25/04 14:38:25 $

**/

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class LibFuncModifyUser extends JFrame 
{
	//Database Connection
	Connection conn = null;
	
	//other stuff
	private JPanel contentPane;
	private JTextField txtFieldLibCard;
	private JTextField txtFieldFirst;
	private JTextField txtFieldLast;
	private JTextField txtFieldEmail;
	private JTextField txtFieldAddress;
	private JTextField txtFieldPhone;
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
					LibFuncModifyUser frame = new LibFuncModifyUser();
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
	public LibFuncModifyUser() 
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
		JLabel lblType = new JLabel("Librarian Functionality Modify User");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblType.setBounds(10, 11, 314, 80);
		contentPane.add(lblType);
		
		//Modify User Panel creation
		JPanel modifyPanel = new JPanel();
		modifyPanel.setBounds(10, 81, 616, 301);
		contentPane.add(modifyPanel);
		modifyPanel.setLayout(null);
		
		JButton modifyBackBtn = new JButton("Back");
		modifyBackBtn.setBounds(382, 11, 117, 40);
		modifyBackBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{			
				LibFunc user = new LibFunc();
				user.setVisible(true);
				dispose();
			}
		});
		modifyBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(modifyBackBtn);
		
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
		
		
		//labels and text fields
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
				
		
	}

}
