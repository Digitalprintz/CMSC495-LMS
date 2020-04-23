/**
   [Handles the add media functionality of LibFunc.java]
   
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
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;

import java.awt.Color;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class LibFuncAddMedia extends JFrame 
{

	//Database Connection
	Connection conn = null;
		
	//Other Stuff
	private JPanel contentPane;
	private JTextField MediaNameText;
	private JTextField MediaTypeText;
	private JTextField AuthorText;
	private JTextField DateText;
	private JTextField IsbnText;

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
					LibFuncAddMedia frame = new LibFuncAddMedia();
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
	public LibFuncAddMedia() 
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
		JLabel lblType = new JLabel("Librarian Functionality Add Media");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblType.setBounds(10, 11, 314, 80);
		contentPane.add(lblType);
		
		//Add Media Panel creation
		JPanel addMediaPanel = new JPanel();
		addMediaPanel.setBounds(10, 81, 616, 300);
		contentPane.add(addMediaPanel);
		addMediaPanel.setLayout(null);
		
		//Handles returning to the LibFunc page
		JButton addMediaBackBtn = new JButton("Back");
		addMediaBackBtn.setBounds(382, 11, 117, 40);
		addMediaBackBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				LibFunc user = new LibFunc();
				user.setVisible(true);
				dispose();
				
			}
		});
		addMediaBackBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(addMediaBackBtn);

		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblIsbn.setBounds(10, 11, 141, 22);
		addMediaPanel.add(lblIsbn);
		
		JLabel lblMediaName = new JLabel("Media Name:");
		lblMediaName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMediaName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMediaName.setBounds(10, 44, 141, 22);
		addMediaPanel.add(lblMediaName);
		
		JLabel lblMediaType = new JLabel("Media Type:");
		lblMediaType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMediaType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMediaType.setBounds(10, 77, 141, 22);
		addMediaPanel.add(lblMediaType);
		
		JLabel lblAuthor = new JLabel("Author:");
		lblAuthor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAuthor.setBounds(10, 110, 141, 22);
		addMediaPanel.add(lblAuthor);
		
		JLabel lblPublicationDate = new JLabel("Publication Date:");
		lblPublicationDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPublicationDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPublicationDate.setBounds(10, 143, 141, 22);
		addMediaPanel.add(lblPublicationDate);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAmount.setBounds(10, 176, 141, 22);
		addMediaPanel.add(lblAmount);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 30, 1));
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		spinner.setBounds(151, 175, 116, 26);
		((DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
		addMediaPanel.add(spinner);
		
		IsbnText = new JTextField();
		IsbnText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		IsbnText.setColumns(10);
		IsbnText.setBounds(151, 10, 455, 26);
		addMediaPanel.add(IsbnText);
		
		MediaNameText = new JTextField();
		MediaNameText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		MediaNameText.setColumns(10);
		MediaNameText.setBounds(151, 43, 455, 26);
		addMediaPanel.add(MediaNameText);
		
		MediaTypeText = new JTextField();
		MediaTypeText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		MediaTypeText.setColumns(10);
		MediaTypeText.setBounds(151, 76, 455, 26);
		addMediaPanel.add(MediaTypeText);
		
		AuthorText = new JTextField();
		AuthorText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		AuthorText.setColumns(10);
		AuthorText.setBounds(151, 109, 455, 26);
		addMediaPanel.add(AuthorText);
		
		DateText = new JTextField();
		DateText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		DateText.setColumns(10);
		DateText.setBounds(151, 142, 455, 26);
		addMediaPanel.add(DateText);
		
		//handles adding the media to the database
		JButton btnAddMedia = new JButton("Add Media");
		btnAddMedia.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				try 
				{
					for(int i = (int)spinner.getValue(); i > 0; i--)
					{
						String query = "insert into Media (ISBN,MediaName,MediaType,Author,PublicationDate,CheckedOut,CheckedBy) values (?,?,?,?,?,?,?)";
						PreparedStatement stmt = conn.prepareStatement(query);
						stmt.setString(1, IsbnText.getText());
						stmt.setString(2, MediaNameText.getText());
						stmt.setString(3, MediaTypeText.getText());
						stmt.setString(4, AuthorText.getText());
						stmt.setString(5, DateText.getText());
						stmt.setString(6, "No");
						stmt.setString(7, "");
						
						stmt.execute();
					}
					
					//Declares it was added, logs it being added, and resets values
					JOptionPane.showMessageDialog(null, "Media Added.");
					Logging.Log("6", "MEDIA_ADDED", "Media added with ISBN: " + IsbnText.getText());
					IsbnText.setText("");
					MediaNameText.setText("");
					MediaTypeText.setText("");
					AuthorText.setText("");
					DateText.setText("");
					spinner.setValue(1);
						
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnAddMedia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddMedia.setBackground(Color.GREEN);
		btnAddMedia.setBounds(460, 179, 146, 40);
		addMediaPanel.add(btnAddMedia);
		
		
	}
}
