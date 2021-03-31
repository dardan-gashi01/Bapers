import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;

public class CreateAccount extends JFrame {

	private JPanel contentPane;
	private JTextField NameTextField;
	private JTextField EmailTextField;
	private JRadioButton ReceptionistBTN;
	private JRadioButton ShiftManagerBTN;
	private JRadioButton TechnicianBTN;
	private JRadioButton OfficeManagerBTN;
	String role;
	private JPasswordField passwordField;
	Connection connection = null;
	String password;
	private JPasswordField confirmField;

	/**
	 * Launch the application.
	 */
	//this is for test purposes 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount frame = new CreateAccount();
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
	public CreateAccount() {
		
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
		
		JLabel lblNewLabel = new JLabel("Create Account");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 664, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(75, 70, 109, 14);
		contentPane.add(lblNewLabel_1);
		//creating a button that takes you back to the menu page
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menuFrame = new Menu();
				menuFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(10, 327, 89, 23);
		contentPane.add(btnNewButton);
		//creating a button that adds the account to the account table
		JButton btnNewButton_1 = new JButton("Confirm Details");
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					/*
					takes the info from the fields and adds them to the DB table as the columns
					 */
					String query = "INSERT INTO `account`(`name`, `email`, `role`, `password`) VALUES (?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,NameTextField.getText());
					pst.setString(2,EmailTextField.getText());
					pst.setString(3,role);
					pst.setString(4,passwordField.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Account registered");
					CreateAccount thisframe = new CreateAccount();
					thisframe.setVisible(true);
					dispose();
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_1.setBounds(549, 327, 125, 23);
		contentPane.add(btnNewButton_1);
		
		NameTextField = new JTextField();
		NameTextField.setBackground(new Color(192, 192, 192));
		NameTextField.setBounds(194, 66, 322, 23);
		contentPane.add(NameTextField);
		NameTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(75, 127, 109, 14);
		contentPane.add(lblNewLabel_2);
		
		EmailTextField = new JTextField();
		EmailTextField.setBackground(new Color(192, 192, 192));
		EmailTextField.setBounds(194, 123, 322, 23);
		contentPane.add(EmailTextField);
		EmailTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Role");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(75, 184, 109, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(75, 246, 109, 14);
		contentPane.add(lblNewLabel_4);
		//creating a radio button to select the role of the person you are creating
		ReceptionistBTN = new JRadioButton("Receptionist");
		ReceptionistBTN.setBackground(new Color(192, 192, 192));
		ReceptionistBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//this is so if one is selected the others are unselected and the String
				// role is set to role to add to the DB easily
				if(ReceptionistBTN.isSelected()) {
					TechnicianBTN.setSelected(false);
					ShiftManagerBTN.setSelected(false);
					OfficeManagerBTN.setSelected(false);
					role = "Receptionist";
				}
			}
		});
		ReceptionistBTN.setBounds(194, 180, 109, 23);
		contentPane.add(ReceptionistBTN);
		//same as above
		TechnicianBTN = new JRadioButton("Technician");
		TechnicianBTN.setBackground(new Color(192, 192, 192));
		TechnicianBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(TechnicianBTN.isSelected()) {
					ReceptionistBTN.setSelected(false);
					ShiftManagerBTN.setSelected(false);
					OfficeManagerBTN.setSelected(false);
					role = "Technician";
				}
			}
		});
		TechnicianBTN.setBounds(305, 180, 94, 23);
		contentPane.add(TechnicianBTN);
		//same as above
		ShiftManagerBTN = new JRadioButton("Shift Manager");
		ShiftManagerBTN.setBackground(new Color(192, 192, 192));
		ShiftManagerBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ShiftManagerBTN.isSelected()) {
					OfficeManagerBTN.setSelected(false);
					TechnicianBTN.setSelected(false);
					ReceptionistBTN.setSelected(false);
					role = "Shift Manager";
				}
			}
		});
		ShiftManagerBTN.setBounds(407, 180, 109, 23);
		contentPane.add(ShiftManagerBTN);
		//same as above
		OfficeManagerBTN = new JRadioButton("Office Manager");
		OfficeManagerBTN.setBackground(new Color(192, 192, 192));
		OfficeManagerBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(OfficeManagerBTN.isSelected()) {
					TechnicianBTN.setSelected(false);
					ReceptionistBTN.setSelected(false);
					ShiftManagerBTN.setSelected(false);
					role = "Office Manager";
				}
			}
		});
		OfficeManagerBTN.setBounds(518, 180, 125, 23);
		contentPane.add(OfficeManagerBTN);
		//password field that blocks the password for security reasons
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(192, 192, 192));
		passwordField.setBounds(194, 243, 322, 20);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_5 = new JLabel("Confirm Password");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(75, 289, 109, 14);
		contentPane.add(lblNewLabel_5);
		
		confirmField = new JPasswordField();
		confirmField.setBackground(new Color(192, 192, 192));
		confirmField.setBounds(194, 286, 322, 20);
		contentPane.add(confirmField);
	}
}
