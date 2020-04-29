/**
   [User forgot password page. Allows a user to change their password after verifying information.]
   
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
import java.sql.ResultSet;

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
public class UserForgotPass extends JFrame 
{

	// Database Connection
	Connection conn = null;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblPassword;
	private JLabel lblRepeat;
	private JLabel lblPleaseEnterThe;
	private JButton btnChangePassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserForgotPass frame = new UserForgotPass();
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
	public UserForgotPass() 
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
		JLabel lblType = new JLabel("Forgot Password");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblType.setBounds(10, 11, 338, 80);
		contentPane.add(lblType);

		// Forgot Password Panel creation
		JPanel forgotPanel = new JPanel();
		forgotPanel.setBounds(10, 81, 616, 300);
		contentPane.add(forgotPanel);
		forgotPanel.setLayout(null);
		
		JLabel lblLibraryCard = new JLabel("Library Card:");
		lblLibraryCard.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLibraryCard.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLibraryCard.setBounds(10, 60, 131, 22);
		forgotPanel.add(lblLibraryCard);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(151, 59, 455, 26);
		forgotPanel.add(textField);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(10, 93, 131, 22);
		forgotPanel.add(lblEmail);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(151, 92, 455, 26);
		forgotPanel.add(textField_1);
		
		JButton btnVerify = new JButton("Verify");
		btnVerify.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					String query = "select * from Login where LibraryCard=? and EmailAddress=? ";
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.setString(1, textField.getText());
					stmt.setString(2, textField_1.getText());

					ResultSet rs = stmt.executeQuery();
					int count = 0;
					while (rs.next()) 
					{
						lblPassword.setVisible(true);
						textField_2.setVisible(true);
						lblRepeat.setVisible(true);
						textField_3.setVisible(true);
						btnChangePassword.setVisible(true);
						lblPleaseEnterThe.setVisible(true);
						textField.setEditable(false);
						count++;
					}
					if(count>0)
					{
						JOptionPane.showMessageDialog(null, "Verification successful.");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Verification failed. Please Try Again.");
					}

					rs.close();
					stmt.close();

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);

				}
			}
		});
		btnVerify.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVerify.setBackground(Color.GREEN);
		btnVerify.setBounds(454, 129, 152, 40);
		forgotPanel.add(btnVerify);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(10, 180, 131, 22);
		forgotPanel.add(lblPassword);
		lblPassword.setVisible(false);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(151, 179, 455, 26);
		forgotPanel.add(textField_2);
		textField_2.setVisible(false);
		
		lblRepeat = new JLabel("Repeat:");
		lblRepeat.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRepeat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRepeat.setBounds(10, 213, 131, 22);
		forgotPanel.add(lblRepeat);
		lblRepeat.setVisible(false);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_3.setColumns(10);
		textField_3.setBounds(151, 212, 455, 26);
		forgotPanel.add(textField_3);
		textField_3.setVisible(false);
		
		btnChangePassword = new JButton("Update");
		btnChangePassword.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (textField_2.getText().contentEquals(textField_3.getText()) && Validator.checkPassword(textField_2.getText())) {
					try {
						// updates the password
						String query2 = "Update Login set Password=? where LibraryCard=?";
						PreparedStatement stmt2 = conn.prepareStatement(query2);
						stmt2.setString(1, Authenticate.encrypt(textField_2.getText(), textField.getText()));
						stmt2.setString(2, textField.getText());
						stmt2.execute();
						Logging.Log("5", "PASSWORD_CHANGED", textField.getText() + " changed their password.");
						stmt2.close();
						conn.close();

						// states password was updated, then redirects to UserPage.
						JOptionPane.showMessageDialog(null, "Password has been changed. Redirecting.");
						UserPage user = new UserPage();
						user.passCard(textField.getText());
						user.setVisible(true);
						dispose();
					} 
					catch (Exception f) {
						f.printStackTrace();
					}
				} 
				else {
					if (!textField_2.getText().contentEquals(textField_3.getText())) {
						JOptionPane.showMessageDialog(null, "Password does not match. Please try again.");
					}
				}
			}
		});
		btnChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnChangePassword.setBackground(Color.GREEN);
		btnChangePassword.setBounds(454, 249, 152, 40);
		forgotPanel.add(btnChangePassword);
		btnChangePassword.setVisible(false);
		
		lblPleaseEnterThe = new JLabel("Please enter your Library Card and Email to recover your account.");
		lblPleaseEnterThe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPleaseEnterThe.setBounds(10, 11, 596, 40);
		forgotPanel.add(lblPleaseEnterThe);
	}

}
