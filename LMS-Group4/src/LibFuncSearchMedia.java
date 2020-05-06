/**
   [Handles the modify media functionality of LibFuncSearchMedia.java]
   
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
import javax.swing.SwingConstants;
import java.awt.Color;

@SuppressWarnings("serial")
public class LibFuncSearchMedia extends JFrame {
	// Database Connection
	Connection conn = null;

	// other stuff
	private JPanel contentPane;
	private JTextField searchText;
	private JTable table;
	private JTextField modifyMediaField;
	private JTextField mediaIDField;
	private JTextField ISBNField;
	private JTextField mediaNameField;
	private JTextField mediaTypeField;
	private JTextField authorField;
	private JTextField dateField;
	private JTextField checkedOutField;
	private JTextField checkedByField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibFuncSearchMedia frame = new LibFuncSearchMedia();
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
	public LibFuncSearchMedia() {
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
		JLabel lblType = new JLabel("Librarian Functionality Search/Modify Media");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblType.setBounds(10, 11, 362, 80);
		contentPane.add(lblType);

		// modifyPanel creation
		JPanel modifyPanel = new JPanel();
		modifyPanel.setBounds(10, 81, 616, 301);
		contentPane.add(modifyPanel);
		modifyPanel.setLayout(null);
		modifyPanel.setVisible(false);

		// modifyMediaPanel creation
		JPanel modifyMediaPanel = new JPanel();
		modifyMediaPanel.setBounds(10, 81, 616, 301);
		contentPane.add(modifyMediaPanel);
		modifyMediaPanel.setLayout(null);

		// Button creations
		// Handles going back to the LibFunc main page.
		JButton modifyMediaBackBtn = new JButton("Back");
		modifyMediaBackBtn.setBounds(382, 11, 117, 40);
		contentPane.add(modifyMediaBackBtn);
		modifyMediaBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LibFunc user = new LibFunc();
				user.setVisible(true);
				dispose();
			}
		});
		modifyMediaBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));

		// Handles going back to the LibFuncSearchMedia panel.
		JButton modifyBackBtn = new JButton("Back");
		modifyBackBtn.setBounds(382, 11, 117, 40);
		contentPane.add(modifyBackBtn);
		modifyBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyPanel.setVisible(false);
				modifyBackBtn.setVisible(false);
				modifyMediaPanel.setVisible(true);
				modifyMediaBackBtn.setVisible(true);
				modifyMediaField.setText("");
				mediaIDField.setText("");
				ISBNField.setText("");
				mediaNameField.setText("");
				mediaTypeField.setText("");
				authorField.setText("");
				dateField.setText("");
				checkedOutField.setText("");
				checkedByField.setText("");
			}
		});
		modifyBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		modifyBackBtn.setVisible(false);

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// modifyMedia Panel

		// modifyMediaPanel Stuff:
		searchText = new JTextField();
		searchText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchText.setBounds(10, 9, 457, 35);
		modifyMediaPanel.add(searchText);
		searchText.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 596, 182);
		modifyMediaPanel.add(scrollPane);

		// Performs the search based on what is inside the modifyMedia textfield
		JButton searchMediaBtn = new JButton("Find Media");
		searchMediaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Verifies that the searchText box is not empty and has a length greater then 1
					// (Makes sure they enter atleast 2 characters)
					if (searchText.getText().length() > 1) {
						// Queries and finds number of rows
						String query = "select * from Media where MediaName like ? or ISBN like ? or Author like ? ";
						PreparedStatement stmt = conn.prepareStatement(query);
						stmt.setString(1, "%" + searchText.getText() + "%");
						stmt.setString(2, "%" + searchText.getText() + "%");
						stmt.setString(3, "%" + searchText.getText() + "%");
						ResultSet rs = stmt.executeQuery();

						int rowCount = 0;
						while (rs.next()) {
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
						while (rs2.next()) {
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

					} else {
						JOptionPane.showMessageDialog(null, "Please enter information to search for media.");
					}

				} catch (Exception d) {
					d.printStackTrace();

				}
			}

		});
		searchMediaBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchMediaBtn.setBounds(480, 9, 126, 35);
		modifyMediaPanel.add(searchMediaBtn);

		modifyMediaField = new JTextField();
		modifyMediaField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		modifyMediaField.setColumns(10);
		modifyMediaField.setBounds(103, 255, 364, 35);
		modifyMediaPanel.add(modifyMediaField);

		JLabel lblMediaID = new JLabel("Media ID:");
		lblMediaID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMediaID.setBounds(10, 250, 83, 40);
		modifyMediaPanel.add(lblMediaID);

		// Handles changing to the Modify Media panel, allowing a librarian to modify
		// any media.
		JButton btnModifyMedia = new JButton("Modify");
		btnModifyMedia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyMediaPanel.setVisible(false);
				modifyMediaBackBtn.setVisible(false);

				modifyPanel.setVisible(true);
				modifyBackBtn.setVisible(true);
				mediaIDField.setText(modifyMediaField.getText());

				try {
					// Fills out the information in the text fields for the librarian to edit based
					// on entered library card #
					String mID = mediaIDField.getText();
					String query3 = "select * from Media where MID=?";
					PreparedStatement stmt3 = conn.prepareStatement(query3);
					stmt3.setString(1, mID);
					ResultSet rs3 = stmt3.executeQuery();

					// Runs the query and fills out text fields
					while (rs3.next()) {
						ISBNField.setText(rs3.getString(2));
						mediaNameField.setText(rs3.getString(3));
						mediaTypeField.setText(rs3.getString(4));
						authorField.setText(rs3.getString(5));
						dateField.setText(rs3.getString(6));
						checkedOutField.setText(rs3.getString(7));
						checkedByField.setText(rs3.getString(8));
					}

					stmt3.close();
					rs3.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnModifyMedia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnModifyMedia.setBounds(480, 255, 126, 35);
		modifyMediaPanel.add(btnModifyMedia);

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// modifyMedia Panel

		// Text fields and labels updated on creation
		JLabel lblMediaId = new JLabel("Media ID:");
		lblMediaId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMediaId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMediaId.setBounds(10, 11, 106, 20);
		modifyPanel.add(lblMediaId);

		mediaIDField = new JTextField();
		mediaIDField.setEditable(false);
		mediaIDField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mediaIDField.setColumns(10);
		mediaIDField.setBounds(121, 11, 485, 20);
		modifyPanel.add(mediaIDField);

		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIsbn.setBounds(10, 42, 106, 20);
		modifyPanel.add(lblIsbn);

		ISBNField = new JTextField();
		ISBNField.setHorizontalAlignment(SwingConstants.LEFT);
		ISBNField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ISBNField.setColumns(10);
		ISBNField.setBounds(121, 42, 485, 20);
		modifyPanel.add(ISBNField);

		JLabel lblMediaName = new JLabel("Media Name:");
		lblMediaName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMediaName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMediaName.setBounds(10, 73, 106, 20);
		modifyPanel.add(lblMediaName);

		mediaNameField = new JTextField();
		mediaNameField.setHorizontalAlignment(SwingConstants.LEFT);
		mediaNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mediaNameField.setColumns(10);
		mediaNameField.setBounds(121, 73, 485, 20);
		modifyPanel.add(mediaNameField);

		JLabel lblMediaType = new JLabel("Media Type:");
		lblMediaType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMediaType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMediaType.setBounds(10, 104, 106, 20);
		modifyPanel.add(lblMediaType);

		mediaTypeField = new JTextField();
		mediaTypeField.setHorizontalAlignment(SwingConstants.LEFT);
		mediaTypeField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mediaTypeField.setColumns(10);
		mediaTypeField.setBounds(121, 104, 485, 20);
		modifyPanel.add(mediaTypeField);

		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAuthor.setBounds(10, 135, 106, 20);
		modifyPanel.add(lblAuthor);

		authorField = new JTextField();
		authorField.setHorizontalAlignment(SwingConstants.LEFT);
		authorField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		authorField.setColumns(10);
		authorField.setBounds(121, 135, 485, 20);
		modifyPanel.add(authorField);

		JLabel lblPhone = new JLabel("Publish Date:");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhone.setBounds(10, 166, 106, 20);
		modifyPanel.add(lblPhone);

		dateField = new JTextField();
		dateField.setHorizontalAlignment(SwingConstants.LEFT);
		dateField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dateField.setColumns(10);
		dateField.setBounds(121, 166, 485, 20);
		modifyPanel.add(dateField);

		JLabel lblCheckedOut = new JLabel("Checked Out:");
		lblCheckedOut.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCheckedOut.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCheckedOut.setBounds(10, 197, 106, 20);
		modifyPanel.add(lblCheckedOut);

		checkedOutField = new JTextField();
		checkedOutField.setHorizontalAlignment(SwingConstants.LEFT);
		checkedOutField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		checkedOutField.setColumns(10);
		checkedOutField.setBounds(121, 197, 485, 20);
		modifyPanel.add(checkedOutField);

		JLabel lblCheckedBy = new JLabel("Checked By:");
		lblCheckedBy.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCheckedBy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCheckedBy.setBounds(10, 228, 106, 20);
		modifyPanel.add(lblCheckedBy);

		checkedByField = new JTextField();
		checkedByField.setHorizontalAlignment(SwingConstants.LEFT);
		checkedByField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		checkedByField.setColumns(10);
		checkedByField.setBounds(121, 228, 485, 20);
		modifyPanel.add(checkedByField);

		// Handles updating the media
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query2 = "Update Media set ISBN=?,MediaName=?,MediaType=?,Author=?,PublicationDate=?,CheckedOut=?,CheckedBy=? where MID=?";

					PreparedStatement stmt2 = conn.prepareStatement(query2);
					stmt2.setString(1, ISBNField.getText());
					stmt2.setString(2, mediaNameField.getText());
					stmt2.setString(3, mediaTypeField.getText());
					stmt2.setString(4, authorField.getText());
					stmt2.setString(5, dateField.getText());
					stmt2.setString(6, checkedOutField.getText());
					stmt2.setString(7, checkedByField.getText());
					stmt2.setString(8, mediaIDField.getText());
					stmt2.execute();

					String query3 = "select * from Media where MID=? ";
					PreparedStatement stmt3 = conn.prepareStatement(query3);
					stmt3.setString(1, mediaIDField.getText());
					ResultSet rs3 = stmt3.executeQuery();

					while (rs3.next()) {
						ISBNField.setText(rs3.getString(2));
						mediaNameField.setText(rs3.getString(3));
						mediaTypeField.setText(rs3.getString(4));
						authorField.setText(rs3.getString(5));
						dateField.setText(rs3.getString(6));
						checkedOutField.setText(rs3.getString(7));
						checkedByField.setText(rs3.getString(8));
					}

					stmt2.close();
					stmt3.close();
					rs3.close();
					
					JOptionPane.showMessageDialog(null, "Media Updated. Redirecting...");
					LibFuncSearchMedia user = new LibFuncSearchMedia();
					user.setVisible(true);
					dispose();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setBackground(Color.GREEN);
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(457, 260, 149, 30);
		modifyPanel.add(btnUpdate);

	}
}
