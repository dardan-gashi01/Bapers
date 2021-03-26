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
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;

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

	/**
	 * Launch the application.
	 */
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create Account");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 664, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(138, 70, 46, 14);
		contentPane.add(lblNewLabel_1);
		//creating a button
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menuFrame = new Menu();
				menuFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(10, 327, 89, 23);
		contentPane.add(btnNewButton);
		//creating a button
		JButton btnNewButton_1 = new JButton("Confirm Details");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
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
		NameTextField.setBounds(194, 66, 322, 23);
		contentPane.add(NameTextField);
		NameTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(138, 127, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		EmailTextField = new JTextField();
		EmailTextField.setBounds(194, 123, 322, 23);
		contentPane.add(EmailTextField);
		EmailTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Role");
		lblNewLabel_3.setBounds(138, 184, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setBounds(138, 246, 46, 14);
		contentPane.add(lblNewLabel_4);
		//creating a button
		ReceptionistBTN = new JRadioButton("Receptionist");
		ReceptionistBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		//creating a button
		TechnicianBTN = new JRadioButton("Technician");
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
		//creating a button
		ShiftManagerBTN = new JRadioButton("Shift Manager");
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
		//creating a button
		OfficeManagerBTN = new JRadioButton("Office Manager");
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
		OfficeManagerBTN.setBounds(518, 180, 109, 23);
		contentPane.add(OfficeManagerBTN);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(194, 243, 322, 20);
		contentPane.add(passwordField);
	}
}
