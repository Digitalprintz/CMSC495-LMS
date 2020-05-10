
/**
   [Handles the remove media functionality of LibFunc.java]
   
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
import java.sql.ResultSet;
import java.sql.SQLException;

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
public class LibFuncRemoveMedia extends JFrame {
	// Database Connection
	Connection conn = null;

	// other stuff
	private JTable table2;
	private JPanel contentPane;
	private JTextField removeTextField;
	private JTextField searchText2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibFuncRemoveMedia frame = new LibFuncRemoveMedia();
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
	public LibFuncRemoveMedia() {
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
		JLabel lblType = new JLabel("Librarian Functionality Remove Media");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblType.setBounds(10, 11, 314, 80);
		contentPane.add(lblType);

		// Remove Media Panel creation
		JPanel removeMediaPanel = new JPanel();
		removeMediaPanel.setBounds(10, 81, 616, 300);
		contentPane.add(removeMediaPanel);
		removeMediaPanel.setLayout(null);

		// Handles returning to the LibFunc page
		JButton removeMediaBackBtn = new JButton("Back");
		removeMediaBackBtn.setBounds(382, 11, 117, 40);
		removeMediaBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibFunc user = new LibFunc();
				user.setVisible(true);
				dispose();
			}
		});
		removeMediaBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(removeMediaBackBtn);

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Remove User Panel

		// panel Stuff:
		searchText2 = new JTextField();
		searchText2.setToolTipText("");
		searchText2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchText2.setBounds(10, 9, 457, 35);
		removeMediaPanel.add(searchText2);
		searchText2.setColumns(10);

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 55, 596, 182);
		removeMediaPanel.add(scrollPane2);

		// Performs the search based on what is inside the removeMedia search textfield
		JButton removeMediaSearchBtn = new JButton("Find Media");
		removeMediaSearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Verifies that the search box is not empty and has a length greater then 1
					// (Makes sure they enter atleast 2 characters)
					if (searchText2.getText().length() > 1) {
						// Queries and finds number of rows
						String query3 = "select * from Media where ISBN like ? or MediaName like ? or Author like ?";
						PreparedStatement stmt3 = conn.prepareStatement(query3);
						stmt3.setString(1, "%" + searchText2.getText() + "%");
						stmt3.setString(2, "%" + searchText2.getText() + "%");
						stmt3.setString(3, "%" + searchText2.getText() + "%");
						ResultSet rs3 = stmt3.executeQuery();

						int rowCount2 = 0;
						while (rs3.next()) {
							rowCount2++;
						}

						// Actual search query
						String query4 = "select * from Media where ISBN like ? or MediaName like ? or Author like ?";
						PreparedStatement stmt4 = conn.prepareStatement(query4);
						stmt4.setString(1, "%" + searchText2.getText() + "%");
						stmt4.setString(2, "%" + searchText2.getText() + "%");
						stmt4.setString(3, "%" + searchText2.getText() + "%");
						ResultSet rs4 = stmt4.executeQuery();

						// Adds table to scrollpane2
						table2 = new JTable(rowCount2, 6);
						scrollPane2.setViewportView(table2);

						// Sets table2 headers and sizing automatically
						table2.getColumnModel().getColumn(0).setHeaderValue("Media ID");
						table2.getColumnModel().getColumn(1).setHeaderValue("ISBN");
						table2.getColumnModel().getColumn(2).setHeaderValue("Media Name");
						table2.getColumnModel().getColumn(3).setHeaderValue("Media Type");
						table2.getColumnModel().getColumn(4).setHeaderValue("Author");
						table2.getColumnModel().getColumn(5).setHeaderValue("Publication Date");
						table2.getTableHeader().resizeAndRepaint();

						// iterates through resultset and fills table
						int count2 = 0;
						while (rs4.next()) {
							table2.setValueAt(rs4.getString(1), count2, 0);
							table2.setValueAt(rs4.getString(2), count2, 1);
							table2.setValueAt(rs4.getString(3), count2, 2);
							table2.setValueAt(rs4.getString(4), count2, 3);
							table2.setValueAt(rs4.getString(5), count2, 4);
							table2.setValueAt(rs4.getString(6), count2, 5);
							count2++;
						}

						table2.setEnabled(false);
						count2 = 0;
						rowCount2 = 0;
						stmt3.close();
						stmt4.close();
						rs3.close();
						rs4.close();

					} else {
						JOptionPane.showMessageDialog(null, "Please enter a value to search for media.");
					}
				} catch (Exception d) {
					d.printStackTrace();

				}
			}

		});
		removeMediaSearchBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		removeMediaSearchBtn.setBounds(480, 9, 126, 35);
		removeMediaPanel.add(removeMediaSearchBtn);

		// text field for the remove button
		removeTextField = new JTextField();
		removeTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		removeTextField.setColumns(10);
		removeTextField.setBounds(123, 248, 344, 35);
		removeMediaPanel.add(removeTextField);

		// remove button functionality
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// checks to make sure the field is not empty and the length is greater then 1.
					if (removeTextField.getText().length() > 0) {
						// query to delete user
						String query5 = "delete from Media where MID=?";
						PreparedStatement stmt5 = conn.prepareStatement(query5);
						stmt5.setString(1, removeTextField.getText());
						stmt5.execute();

						// query to check whether user was actually deleted.
						String query6 = "select * from Media where MID=?";
						PreparedStatement stmt6 = conn.prepareStatement(query6);
						stmt6.setString(1, removeTextField.getText());
						ResultSet rs6 = stmt6.executeQuery();

						int count6 = 0;
						while (rs6.next()) {
							count6++;

						}
						if (count6 == 1) {
							JOptionPane.showMessageDialog(null,
									"Removal unsuccessful, please verify Media ID(MID) and try again.");
						} else {
							JOptionPane.showMessageDialog(null, "Removal Successful.");
							Logging.Log("7", "REMOVE_MEDIA", "Deletion of Media: " + removeTextField.getText());
						}

						// closes everything out
						stmt5.close();
						stmt6.close();
						rs6.close();
						count6 = 0;

					} else {
						JOptionPane.showMessageDialog(null, "Please enter a value to search for Media.");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemove.setBounds(480, 248, 126, 35);
		removeMediaPanel.add(btnRemove);

		JLabel lblLibraryCard = new JLabel("Media ID:");
		lblLibraryCard.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLibraryCard.setBounds(10, 248, 103, 35);
		removeMediaPanel.add(lblLibraryCard);
	}

}
