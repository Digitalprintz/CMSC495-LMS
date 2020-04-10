import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class UserPage extends JFrame 
{

	private JPanel contentPane;
	private String libraryCard;

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
					UserPage frame = new UserPage();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public void passCard(String card) 
	{
		libraryCard = card;
		JLabel LibCard = new JLabel(libraryCard);
		LibCard.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LibCard.setBounds(123, 11, 138, 40);
		contentPane.add(LibCard);
		
	}
	
	/**
	 * Create the frame.
	 * 
	 */
	public UserPage() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("USERPAGE");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblNewLabel.setBounds(211, 106, 310, 168);
		contentPane.add(lblNewLabel);
		
		JButton exitBtn = new JButton("Logout");
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
		
		JLabel LibCardLabel = new JLabel("Library Card #");
		LibCardLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LibCardLabel.setBounds(10, 11, 103, 40);
		contentPane.add(LibCardLabel);
		
		
	}

}
