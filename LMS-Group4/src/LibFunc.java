/**
   [Initial page draws the GUI and connects to the SQLite Database]
   
   [Notes]
   
   @author <A HREF="mailto:[christophera1997@yahoo.com]">[Christopher Hammond]</A>
   @version $Revision: .2 $ $Date: 2020/16/04 07:51:25 $

**/


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import java.sql.PreparedStatement;

@SuppressWarnings("serial")
public class LibFunc extends JFrame {

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
	
	//Database Connection
	Connection conn = null;
	
	/**
	 * Create the frame.
	 */
	public LibFunc() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
				JLabel lblType = new JLabel("Librarian Functionality");
				lblType.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblType.setBounds(10, 11, 314, 80);
				contentPane.add(lblType);
	}

}
