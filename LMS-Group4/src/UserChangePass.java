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
import javax.swing.JTextField;
import java.awt.Color;

@SuppressWarnings("serial")
public class UserChangePass extends JFrame {

	private JPanel contentPane;
	private String libraryCard;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserChangePass frame = new UserChangePass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// handles passing the password to the class
	public void passCard(String card) {
		libraryCard = card;
		JLabel LibCard = new JLabel(libraryCard);
		LibCard.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LibCard.setBounds(123, 11, 138, 40);
		contentPane.add(LibCard);

	}

	Connection conn = null;
	private JTextField passField;
	private JTextField textField;
	private JTextField textField_1;
	private int count = 0;

	/**
	 * Create the frame.
	 */
	public UserChangePass() {
		conn = sqliteConnection.dbConnect();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton exitBtn = new JButton("Logout");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login user = new Login();
				user.frame.setVisible(true);
				Logging.Log("4", "SUCCESSFUL_LOGOUT", "Logged out successfully with username: " + libraryCard);
				dispose();
			}
		});
		exitBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		exitBtn.setBounds(509, 11, 117, 40);
		contentPane.add(exitBtn);

		// Handles returning to the LibFunc page
		JButton addMediaBackBtn = new JButton("Back");
		addMediaBackBtn.setBounds(382, 11, 117, 40);
		addMediaBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserPage user = new UserPage();
				user.passCard(libraryCard);
				user.setVisible(true);
				dispose();

			}
		});
		addMediaBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(addMediaBackBtn);

		// labels and textfields and stuff
		JLabel LibCardLabel = new JLabel("Library Card #");
		LibCardLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LibCardLabel.setBounds(10, 11, 103, 40);
		contentPane.add(LibCardLabel);

		JPanel passPanel = new JPanel();
		passPanel.setBounds(20, 60, 606, 322);
		contentPane.add(passPanel);
		passPanel.setLayout(null);
		passPanel.setVisible(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(20, 60, 606, 322);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(10, 110, 109, 40);
		mainPanel.add(lblPassword);

		JLabel textLabel = new JLabel("Please verify your password:");
		textLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textLabel.setBounds(10, 62, 397, 40);
		mainPanel.add(textLabel);

		JLabel lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword_1.setBounds(10, 62, 109, 40);
		passPanel.add(lblPassword_1);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(112, 62, 477, 40);
		passPanel.add(textField);

		// Handles the functionality for changing a password.
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().contentEquals(textField_1.getText())
						&& Validator.checkPassword(textField.getText())) {
					try {
						// updates the password
						String query2 = "Update Login set Password=? where LibraryCard=?";
						PreparedStatement stmt2 = conn.prepareStatement(query2);
						stmt2.setString(1, Authenticate.encrypt(textField.getText(), libraryCard));
						stmt2.setString(2, libraryCard);
						stmt2.execute();
						Logging.Log("5", "PASSWORD_CHANGED",
								libraryCard + " changed their password.");
						stmt2.close();
						conn.close();

						// states password was updated, then redirects to UserPage.
						JOptionPane.showMessageDialog(null, "Password has been changed. Redirecting.");
						UserPage user = new UserPage();
						user.passCard(libraryCard);
						user.setVisible(true);
						dispose();
					} catch (Exception f) {
						f.printStackTrace();

					}
				} else {
					if(!textField.getText().contentEquals(textField_1.getText())) {
						JOptionPane.showMessageDialog(null, "Password does not match. Please try again.");
					}
				}
			}
		});
		btnChangePassword.setBackground(Color.GREEN);
		btnChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnChangePassword.setBounds(405, 164, 184, 40);
		passPanel.add(btnChangePassword);

		JLabel lblEnterThePassword = new JLabel("Enter the password you would like to change it to:");
		lblEnterThePassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEnterThePassword.setBounds(10, 11, 535, 40);
		passPanel.add(lblEnterThePassword);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(112, 113, 477, 40);
		passPanel.add(textField_1);

		passField = new JTextField();
		passField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passField.setColumns(10);
		passField.setBounds(112, 111, 477, 40);
		mainPanel.add(passField);

		// verifies passwords match. Also takes a global count to 5. If the count = 5,
		// then it will automatically redirect to the login page
		// for security, in the event that someone is left logged in. It also logs the
		// forceful logout in the logging database.
		JButton btnNewButton = new JButton("Verify");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (count >= 5) {
					JOptionPane.showMessageDialog(null, "Password Verify Limit Reached. Redirecting to Login.");
					Login user = new Login();
					user.frame.setVisible(true);
					Logging.Log("8", "FORCEFUL LOGOUT", "Security Logout for User: " + libraryCard);
					dispose();
				} else {
					try {
						String query = "select * from Login where LibraryCard=?";
						PreparedStatement stmt = conn.prepareStatement(query);
						stmt.setString(1, libraryCard);
						ResultSet rs = stmt.executeQuery();

						while (rs.next()) {
							if (Authenticate.encrypt(passField.getText(), libraryCard).equals(rs.getString(5))) {
								mainPanel.setVisible(false);
								passPanel.setVisible(true);
							} else {
								JOptionPane.showMessageDialog(null,
										"Password does not match account currently logged in. Please try again.");
								count++;

							}
						}
						rs.close();
						stmt.close();

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);

					}
				}
			}
		});
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(468, 162, 121, 40);
		mainPanel.add(btnNewButton);

	}
}
