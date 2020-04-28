/**
   [User search page. Used to find books in the library's database. Also allows user to access and modify their personal information.]
   
   [Notes]
   
   @author [Richard Arnold, Redi Delulo, Krista Burdick, Chris Hammond, Alyssa Knight and Matt Worman]
   @version $Revision: .6 $ $Date: 2020/25/04 14:38:25 $

**/

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class UserPage extends JFrame {

	private JPanel contentPane;
	private String libraryCard;
	private JTextField searchText;
	private JTextField FirstField;
	private JTextField LastField;
	private JTextField PhoneField;
	private JTextField AddressField;
	private JTextField EmailField;
	private String uid;

	private String currentEmail = "";
	private boolean emailChanged = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserPage frame = new UserPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void passCard(String card) {
		libraryCard = card;
		JLabel LibCard = new JLabel(libraryCard);
		LibCard.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LibCard.setBounds(123, 11, 138, 40);
		contentPane.add(LibCard);

	}

	Connection conn = null;
	private JTable table;

	/**
	 * Create the frame.
	 * 
	 */
	public UserPage() {

		conn = sqliteConnection.dbConnect();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton exitBtn = new JButton("Logout");
		exitBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Login user = new Login();
				user.frame.setVisible(true);
				Logging.Log("4", "SUCCESSFUL_LOGOUT", "Logged out successfully with username: " + libraryCard);
				dispose();
			}
		});
		exitBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		exitBtn.setBounds(509, 11, 117, 40);
		contentPane.add(exitBtn);

		JLabel LibCardLabel = new JLabel("Library Card #");
		LibCardLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LibCardLabel.setBounds(10, 11, 103, 40);
		contentPane.add(LibCardLabel);

		searchText = new JTextField();
		searchText.setBounds(10, 78, 473, 35);
		contentPane.add(searchText);
		searchText.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 124, 616, 258);
		contentPane.add(scrollPane);

		// Performs the search based on what is inside the Search textfield
		JButton SearchBtn = new JButton("Search");
		SearchBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					// Queries and finds number of rows
					String query = "select * from Media where MediaName like ? or ISBN like ? or Author like ? ";
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.setString(1, "%" + searchText.getText() + "%");
					stmt.setString(2, "%" + searchText.getText() + "%");
					stmt.setString(3, "%" + searchText.getText() + "%");
					ResultSet rs = stmt.executeQuery();

					int rowCount = 0;
					while (rs.next()) 
					{
						rowCount++;
					}

					// Actual search query
					String query2 = "select * from Media where MediaName like ? or ISBN like ? or Author like ? ";
					PreparedStatement stmt2 = conn.prepareStatement(query2);
					stmt2.setString(1, "%" + searchText.getText() + "%");
					stmt2.setString(2, "%" + searchText.getText() + "%");
					stmt2.setString(3, "%" + searchText.getText() + "%");
					ResultSet rs2 = stmt2.executeQuery();

					// Adds table to scrollpane
					table = new JTable(rowCount, 6);
					scrollPane.setViewportView(table);

					// Sets table headers
					table.getColumnModel().getColumn(0).setHeaderValue("Media ID");
					table.getColumnModel().getColumn(1).setHeaderValue("ISBN");
					table.getColumnModel().getColumn(2).setHeaderValue("Media Name");
					table.getColumnModel().getColumn(3).setHeaderValue("Media Type");
					table.getColumnModel().getColumn(4).setHeaderValue("Media Author");
					table.getColumnModel().getColumn(5).setHeaderValue("Checked Out?");
					table.getTableHeader().resizeAndRepaint();

					// iterates through resultset and fills table
					int count = 0;
					while (rs2.next()) 
					{
						table.setValueAt(rs2.getString(1), count, 0);
						table.setValueAt(rs2.getString(2), count, 1);
						table.setValueAt(rs2.getString(3), count, 2);
						table.setValueAt(rs2.getString(4), count, 3);
						table.setValueAt(rs2.getString(5), count, 4);
						table.setValueAt(rs2.getString(7), count, 5);
						count++;
					}

					count = 0;
					rowCount = 0;
					stmt.close();
					stmt2.close();
					rs.close();
					rs2.close();

				} 
				catch (Exception d) 
				{
					d.printStackTrace();

				}
			}

		});
		SearchBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		SearchBtn.setBounds(500, 78, 126, 35);
		contentPane.add(SearchBtn);

		// Handles going to the account info page.
		JButton actInfoBtn = new JButton("Account Info");
		actInfoBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JLabel FirstLabel = new JLabel("First Name:");
				FirstLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				FirstLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
				FirstLabel.setBounds(10, 62, 139, 40);
				contentPane.add(FirstLabel);

				JLabel LastLabel = new JLabel("Last Name:");
				LastLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				LastLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
				LastLabel.setBounds(10, 113, 139, 40);
				contentPane.add(LastLabel);

				JLabel lblEmail = new JLabel("E-mail Address: ");
				lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
				lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblEmail.setBounds(10, 164, 139, 40);
				contentPane.add(lblEmail);

				JLabel lblAddress = new JLabel("Address:");
				lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
				lblAddress.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblAddress.setBounds(10, 215, 139, 40);
				contentPane.add(lblAddress);

				JLabel lblPhoneNumber = new JLabel("Phone Number:");
				lblPhoneNumber.setHorizontalAlignment(SwingConstants.RIGHT);
				lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblPhoneNumber.setBounds(10, 266, 139, 40);
				contentPane.add(lblPhoneNumber);

				// Displays text fields for account info
				FirstField = new JTextField();
				FirstField.setFont(new Font("Tahoma", Font.PLAIN, 14));
				FirstField.setBounds(153, 62, 223, 40);
				FirstField.setColumns(10);

				LastField = new JTextField();
				LastField.setFont(new Font("Tahoma", Font.PLAIN, 14));
				LastField.setColumns(10);
				LastField.setBounds(153, 113, 223, 40);

				PhoneField = new JTextField();
				PhoneField.setFont(new Font("Tahoma", Font.PLAIN, 14));
				PhoneField.setColumns(10);
				PhoneField.setBounds(153, 266, 339, 40);

				AddressField = new JTextField();
				AddressField.setFont(new Font("Tahoma", Font.PLAIN, 14));
				AddressField.setColumns(10);
				AddressField.setBounds(153, 215, 339, 40);

				EmailField = new JTextField();
				EmailField.setFont(new Font("Tahoma", Font.PLAIN, 14));
				EmailField.setColumns(10);
				EmailField.setBounds(153, 164, 339, 40);

				contentPane.add(FirstField);
				contentPane.add(EmailField);
				contentPane.add(AddressField);
				contentPane.add(PhoneField);
				contentPane.add(LastField);

				// Queries and retrieves account data, then updates the textfields
				try 
				{
					String query = "select * from Login where LibraryCard=? ";
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.setString(1, libraryCard);
					ResultSet rs = stmt.executeQuery();

					while (rs.next()) 
					{
						FirstField.setText(rs.getString(3));
						LastField.setText(rs.getString(4));
						PhoneField.setText(rs.getString(8));
						AddressField.setText(rs.getString(7));
						EmailField.setText(rs.getString(6));
					}
					currentEmail = EmailField.getText();
					stmt.close();
					rs.close();

				} 
				catch (Exception f) 
				{
					f.printStackTrace();

				}

				// Update button
				JButton updateBtn = new JButton("Update");
				updateBtn.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						try 
						{
							String query = "select * from Login where LibraryCard=? ";
							PreparedStatement stmt = conn.prepareStatement(query);
							stmt.setString(1, libraryCard);
							ResultSet rs = stmt.executeQuery();

							while (rs.next()) 
							{
								uid = rs.getString(1);
							}

							String query2 = "Update Login set FirstName=?,LastName=?,EmailAddress=?,Address=?,PhoneNumber=? where LibraryCard=?";

							PreparedStatement stmt2 = conn.prepareStatement(query2);
							stmt2.setString(1, FirstField.getText());
							stmt2.setString(2, LastField.getText());
							stmt2.setString(3, EmailField.getText());
							stmt2.setString(4, AddressField.getText());
							stmt2.setString(5, PhoneField.getText());
							stmt2.setString(6, libraryCard);

							if (!(EmailField.getText().equalsIgnoreCase(currentEmail))) 
							{
								emailChanged = true;
							}

							if (Validator.checkName(FirstField.getText()) && Validator.checkName(LastField.getText())
									&& Validator.checkEmailUpdate(EmailField.getText(), emailChanged)
									&& Validator.checkPhone(PhoneField.getText())) {
								stmt2.execute();
								JOptionPane.showMessageDialog(null, "Update made successfully.");
							} else {
								JOptionPane.showMessageDialog(null, "Please try again.");
							}

							String query3 = "select * from Login where LibraryCard=? ";
							PreparedStatement stmt3 = conn.prepareStatement(query3);
							stmt3.setString(1, libraryCard);
							ResultSet rs3 = stmt.executeQuery();

							while (rs3.next()) {
								FirstField.setText(rs3.getString(3));
								LastField.setText(rs3.getString(4));
								PhoneField.setText(rs3.getString(8));
								AddressField.setText(rs3.getString(7));
								EmailField.setText(rs3.getString(6));
							}
							stmt.close();
							stmt2.close();
							stmt3.close();
							rs.close();
							rs3.close();
						} 
						catch (Exception f) 
						{
							f.printStackTrace();

						}
					}

				});
				updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
				updateBtn.setBounds(427, 342, 199, 40);
				contentPane.add(updateBtn);

				// Change password button
				JButton ChngPass = new JButton("Change Password");
				ChngPass.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						UserChangePass user = new UserChangePass();
						user.passCard(libraryCard);
						user.setVisible(true);
						dispose();
					}
				});
				ChngPass.setFont(new Font("Tahoma", Font.PLAIN, 16));
				ChngPass.setBounds(10, 342, 199, 40);
				contentPane.add(ChngPass);

				SearchBtn.setVisible(false);
				scrollPane.setVisible(false);
				searchText.setVisible(false);
				actInfoBtn.setVisible(false);

				JButton SearchPane = new JButton("Search");
				SearchPane.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SearchBtn.setVisible(true);
						scrollPane.setVisible(true);
						searchText.setVisible(true);
						actInfoBtn.setVisible(true);
						updateBtn.setVisible(false);
						SearchPane.setVisible(false);
						FirstField.setVisible(false);
						LastField.setVisible(false);
						AddressField.setVisible(false);
						EmailField.setVisible(false);
						PhoneField.setVisible(false);
						FirstLabel.setVisible(false);
						LastLabel.setVisible(false);
						lblEmail.setVisible(false);
						lblAddress.setVisible(false);
						lblPhoneNumber.setVisible(false);
					}
				});
				SearchPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
				SearchPane.setBounds(362, 11, 137, 40);
				contentPane.add(SearchPane);

			}
		});
		actInfoBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		actInfoBtn.setBounds(362, 11, 137, 40);
		contentPane.add(actInfoBtn);

	}
}
