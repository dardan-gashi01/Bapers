import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField pin;
	Connection connection = null;


	/**
	 * Launch the application.
	 */
	//this is for test purposes 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2-getWidth()/2, size.height/2 - getHeight()/2);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(195, 148, 73, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(195, 179, 66, 17);
		contentPane.add(lblNewLabel_1);
		
		username = new JTextField();
		username.setBackground(new Color(192, 192, 192));
		username.setBounds(298, 145, 136, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		pin = new JPasswordField();
		pin.setBackground(new Color(192, 192, 192));
		pin.setBounds(298, 176, 136, 20);
		contentPane.add(pin);
		//creating a button that logs the user in and checks for their details in the database to check if it is correct
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String sql = "Select role from account where name=? and password=?";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setString(1, username.getText());
					pst.setString(2, pin.getText());
					ResultSet rs = pst.executeQuery();
					
					
					//PreparedStatement pst2 = con.prepareStatement(role);
					//ResultSet rs2 = pst.executeQuery();
					
					if(rs.next()) {
						Menu menuFrame = new Menu();
						menuFrame.setVisible(true);
						dispose();
						menuFrame.AssignMenu(rs.getString(1));
					}
					else {
						JOptionPane.showMessageDialog(null, "Username and Pin dont match");
						username.setText("");
						pin.setText("");
					}
					connection.close();
				}
				catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton.setBounds(585, 327, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel(":");
		lblNewLabel_2.setBounds(257, 148, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel(":");
		lblNewLabel_2_1.setBounds(257, 180, 46, 14);
		contentPane.add(lblNewLabel_2_1);
		//creating a button that closes the whole application
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(10, 327, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("Login");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 11, 664, 14);
		contentPane.add(lblNewLabel_3);
		
	}
}