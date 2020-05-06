/**
   [Registration page generates unique Library ID. Allows user to create an account.]
   
   [Notes]
   
   @author [Richard Arnold, Redi Delulo, Krista Burdick, Chris Hammond, Alyssa Knight and Matt Worman]
   @version $Revision: .8 $ $Date: 05/06/2020 17:13:53 $

**/

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;

@SuppressWarnings("serial")
public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField libCardText;
	private JTextField passText;
	private JTextField firstNameText;
	private JTextField lastNameText;
	private JTextField emailText;
	private JTextField addressText;
	private JTextField phoneText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn = null;

	/**
	 * Create the frame.
	 */
	public Register() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Handles going back to Login Page.
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
		JLabel lblNewLabel = new JLabel("Library Management System - Group 1");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 314, 40);
		contentPane.add(lblNewLabel);

		JLabel LibraryCard = new JLabel("Library Card #");
		LibraryCard.setHorizontalAlignment(SwingConstants.RIGHT);
		LibraryCard.setFont(new Font("Tahoma", Font.PLAIN, 18));
		LibraryCard.setBounds(10, 66, 131, 22);
		contentPane.add(LibraryCard);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(10, 99, 131, 22);
		contentPane.add(lblPassword);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFirstName.setBounds(10, 132, 131, 22);
		contentPane.add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLastName.setBounds(10, 165, 131, 22);
		contentPane.add(lblLastName);

		JLabel lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmailAddress.setBounds(10, 198, 131, 22);
		contentPane.add(lblEmailAddress);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAddress.setBounds(10, 231, 131, 22);
		contentPane.add(lblAddress);

		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPhoneNumber.setBounds(10, 264, 131, 22);
		contentPane.add(lblPhoneNumber);

		// Generates a random 15 digit library card.
		String AlphaNumericString = "0123456789";
		StringBuilder sb = new StringBuilder(15);

		for (int i = 0; i < 15; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		String libCard = sb.toString();

		libCardText = new JTextField();
		libCardText.setEditable(false);
		libCardText.setText(libCard);
		libCardText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		libCardText.setBounds(151, 65, 475, 26);
		contentPane.add(libCardText);
		libCardText.setColumns(10);

		passText = new JTextField();
		passText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passText.setColumns(10);
		passText.setBounds(151, 98, 475, 26);
		contentPane.add(passText);

		firstNameText = new JTextField();
		firstNameText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		firstNameText.setColumns(10);
		firstNameText.setBounds(151, 131, 475, 26);
		contentPane.add(firstNameText);

		lastNameText = new JTextField();
		lastNameText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lastNameText.setColumns(10);
		lastNameText.setBounds(151, 164, 475, 26);
		contentPane.add(lastNameText);

		emailText = new JTextField();
		emailText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailText.setColumns(10);
		emailText.setBounds(151, 197, 475, 26);
		contentPane.add(emailText);

		addressText = new JTextField();
		addressText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addressText.setColumns(10);
		addressText.setBounds(151, 230, 475, 26);
		contentPane.add(addressText);

		phoneText = new JTextField();
		phoneText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		phoneText.setColumns(10);
		phoneText.setBounds(151, 263, 475, 26);
		contentPane.add(phoneText);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//checks to make sure all text fields are not null and are greater then 2 in
				if (Validator.checkName(firstNameText.getText()) && Validator.checkName(lastNameText.getText())
						&& Validator.checkPassword(passText.getText()) && Validator.checkEmail(emailText.getText())
						&& Validator.checkPhone(phoneText.getText())) {
					try {
						// Sqlite Connection
						conn = sqliteConnection.dbConnect();
						String query = "insert into Login (LibraryCard,FirstName,LastName,Password,EmailAddress,Address,PhoneNumber) values (?,?,?,?,?,?,?)";
						PreparedStatement stmt = conn.prepareStatement(query);
						stmt.setString(1, libCardText.getText());
						stmt.setString(2, firstNameText.getText());
						stmt.setString(3, lastNameText.getText());
						stmt.setString(4, Authenticate.encrypt(passText.getText(), libCardText.getText()));
						stmt.setString(5, emailText.getText());
						stmt.setString(6, addressText.getText());
						stmt.setString(7, phoneText.getText());

						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Registration Success.");

						stmt.close();

						Logging.Log("3", "ACCOUNT_CREATED",
								"Account created with Library ID: " + libCardText.getText());

						Login user = new Login();
						user.frame.setVisible(true);
						conn.close();
						dispose();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);

					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Registration failed. All fields must be filled out correctly.");
				}
			}
		});
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(480, 300, 146, 40);
		contentPane.add(btnNewButton);
	}
}
