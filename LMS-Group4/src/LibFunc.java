/**
   [Initial page draws the GUI and connects to the SQLite Database]
   
   [Notes]
   
   @author [Richard Arnold, Redi Delulo, Krista Burdick, Chris Hammond, Alyssa Knight and Matt Worman]
   @version $Revision: .7 $ $Date: 2020/29/04 12:24:36 $

**/

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class LibFunc extends JFrame {
	// Database Connection
	Connection conn = null;

	// Extra stuff
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibFunc frame = new LibFunc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LibFunc() {
		// Sqlite Connection
		conn = sqliteConnection.dbConnect();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Handles going back to login page
		JButton exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login user = new Login();
				user.frame.setVisible(true);
				dispose();
			}
		});
		exitBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		exitBtn.setBounds(509, 11, 117, 40);
		contentPane.add(exitBtn);

		// Project Label
		JLabel lblTitle = new JLabel("Library Management System - Group 1");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitle.setBounds(10, 11, 314, 40);
		contentPane.add(lblTitle);

		// Project Label
		JLabel lblType = new JLabel("Librarian Functionality");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblType.setBounds(10, 11, 314, 80);
		contentPane.add(lblType);

		// mainPanel creation
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(10, 81, 616, 301);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Main Panel

		// Main page User and Media items
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

		// Handles changing to the Remove Media Class
		JButton btnRemoveMedia = new JButton("Remove Media");
		btnRemoveMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibFuncRemoveMedia user = new LibFuncRemoveMedia();
				user.setVisible(true);
				dispose();
			}
		});
		btnRemoveMedia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemoveMedia.setBounds(321, 133, 285, 30);
		mainPanel.add(btnRemoveMedia);

		// Adds 500 randomized media items into the database to test database stability
		// and load limits
		JButton btnAdd500 = new JButton("Add 500 Media Items");
		btnAdd500.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = 500;
				while (count > 0) {
					try {
						String query = "insert into Media (ISBN,MediaName,MediaType,Author,PublicationDate,CheckedOut,CheckedBy) values (?,?,?,?,?,?,?)";
						PreparedStatement stmt = conn.prepareStatement(query);
						stmt.setString(1, "LimitTest");
						stmt.setString(2, "LimitTest");
						stmt.setString(3, "LimitTest");
						stmt.setString(4, "LimitTest");
						stmt.setString(5, "LimitTest");
						stmt.setString(6, "No");
						stmt.setString(7, "");

						stmt.execute();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					count--;
				}
			}
		});
		btnAdd500.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd500.setBounds(321, 174, 285, 30);
		mainPanel.add(btnAdd500);

		// Handles changing to the Search User Class
		JButton btnSearchUser = new JButton("Search User");
		btnSearchUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibFuncSearchUser user = new LibFuncSearchUser();
				user.setVisible(true);
				dispose();
			}
		});
		btnSearchUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearchUser.setBounds(10, 51, 285, 30);
		mainPanel.add(btnSearchUser);

		// Handles changing to the Add User Class
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibFuncAddUser user = new LibFuncAddUser();
				user.setVisible(true);
				dispose();
			}
		});

		// Handles changing to the Add Media Class
		JButton btnAddMed = new JButton("Add Media");
		btnAddMed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibFuncAddMedia user = new LibFuncAddMedia();
				user.setVisible(true);
				dispose();
			}
		});
		btnAddMed.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddMed.setBounds(321, 92, 285, 30);
		mainPanel.add(btnAddMed);
		btnAddUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddUser.setBounds(10, 133, 285, 30);
		mainPanel.add(btnAddUser);

		// Handles changing to the Modify User Class
		JButton btnModifyUser = new JButton("Modify User");
		btnModifyUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibFuncModifyUser user = new LibFuncModifyUser();
				user.setVisible(true);
				dispose();
			}
		});
		btnModifyUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnModifyUser.setBounds(10, 92, 285, 30);
		mainPanel.add(btnModifyUser);

		// Handles changing to the Remove User Class
		JButton btnRemoveUser = new JButton("Remove User");
		btnRemoveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibFuncRemoveUser user = new LibFuncRemoveUser();
				user.setVisible(true);
				dispose();
			}
		});
		btnRemoveUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemoveUser.setBounds(10, 174, 285, 30);
		mainPanel.add(btnRemoveUser);

		// Handles changing to the Search/Modify Media Class
		JButton btnModifyMedia = new JButton("Search/Modify Media");
		btnModifyMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibFuncSearchMedia user = new LibFuncSearchMedia();
				user.setVisible(true);
				dispose();
			}
		});
		btnModifyMedia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnModifyMedia.setBounds(321, 51, 285, 30);
		mainPanel.add(btnModifyMedia);
		
		JButton btnRegisterLibrarian = new JButton("Register Librarian");
		btnRegisterLibrarian.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				LibFuncRegLib user = new LibFuncRegLib();
				user.setVisible(true);
				dispose();
			}
		});
		btnRegisterLibrarian.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegisterLibrarian.setBounds(10, 260, 285, 30);
		mainPanel.add(btnRegisterLibrarian);

	}
}
