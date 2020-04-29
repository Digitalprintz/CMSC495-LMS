import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;

@SuppressWarnings("serial")
public class LibFuncRegLib extends JFrame 
{

	// Database Connection
	Connection conn = null;
	
	private JPanel contentPane;
	private JTextField userText;
	private JTextField passText;
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
					LibFuncRegLib frame = new LibFuncRegLib();
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
	public LibFuncRegLib() 
	{
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
		JLabel lblType = new JLabel("Librarian Functionality Register Librarian");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblType.setBounds(10, 11, 338, 80);
		contentPane.add(lblType);

		// Register Librarian Panel creation
		JPanel regLibPanel = new JPanel();
		regLibPanel.setBounds(10, 81, 616, 300);
		contentPane.add(regLibPanel);
		regLibPanel.setLayout(null);
		
		JLabel lblPassword = new JLabel("Username:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(10, 12, 131, 22);
		regLibPanel.add(lblPassword);
		
		userText = new JTextField();
		userText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userText.setColumns(10);
		userText.setBounds(151, 11, 455, 26);
		regLibPanel.add(userText);
		
		JLabel lblFirstName = new JLabel("Password:");
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFirstName.setBounds(10, 45, 131, 22);
		regLibPanel.add(lblFirstName);
		
		passText = new JTextField();
		passText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passText.setColumns(10);
		passText.setBounds(151, 44, 455, 26);
		regLibPanel.add(passText);
		
		// Generates a random 15 digit ID for librarians.
		String AlphaNumericString = "0123456789";
		StringBuilder sb = new StringBuilder(15);

		for (int i = 0; i < 15; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		libCard = sb.toString();
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					
					// Sqlite Connection
					conn = sqliteConnection.dbConnect();
					String query = "insert into LibLogin (Username, Password, LibID) values (?,?,?)";
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.setString(1, userText.getText());
					stmt.setString(2, Authenticate.encrypt(passText.getText(), libCard));
					stmt.setString(3, libCard);

					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Registration Success.");

					stmt.close();
					
					

					Logging.Log("9", "ACCOUNT_CREATED", "Librarian Account created: " + userText.getText());

					LibFunc user = new LibFunc();
					user.setVisible(true);
					conn.close();
					dispose();
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);

				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(454, 81, 152, 40);
		regLibPanel.add(btnNewButton);

		// Handles returning to the LibFunc page
		JButton addMediaBackBtn = new JButton("Back");
		addMediaBackBtn.setBounds(382, 11, 117, 40);
		addMediaBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibFunc user = new LibFunc();
				user.setVisible(true);
				dispose();

			}
		});
		addMediaBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(addMediaBackBtn);
	}
}
