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

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField pin;

	/**
	 * Launch the application.
	 */
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(195, 148, 73, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(195, 179, 66, 17);
		contentPane.add(lblNewLabel_1);
		
		username = new JTextField();
		username.setBounds(298, 145, 136, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		pin = new JPasswordField();
		pin.setBounds(298, 176, 136, 20);
		contentPane.add(pin);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*String user = username.getText();
				String pas = pass.getText();
				
				if(user.equals("user") & pas.equals("12345")) {
					Menu menuFrame = new Menu();
					menuFrame.setVisible(true);
					dispose();
				}else {
					
					
				}*/
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bapers", "root", "");
					String sql = "Select * from account where account_id=? and pin=?";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setString(1, username.getText());
					pst.setString(2, pin.getText());
					ResultSet rs = pst.executeQuery();
					if(rs.next()) {
						Menu menuFrame = new Menu();
						menuFrame.setVisible(true);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Username and Pin dont match");
						username.setText("");
						pin.setText("");
					}
					con.close();
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
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(10, 327, 89, 23);
		contentPane.add(btnNewButton_1);
		
		
	}
}