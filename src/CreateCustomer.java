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
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.awt.event.ActionEvent;

public class CreateCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField cname;
	private JTextField address;
	private JTextField phone;
	private JTextField discount;
	private JRadioButton regularCustomer;
	private JRadioButton valuedCustomer;
	private JRadioButton flexibleDiscount;
	private JRadioButton fixedDiscount;
	private JRadioButton variableDiscount;
	private String status;
	private String agreedDiscount;
	Connection connection = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateCustomer frame = new CreateCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
		
		
	
	public CreateCustomer() {
		
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2-getWidth()/2, size.height/2 - getHeight()/2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create Customer");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 664, 14);
		contentPane.add(lblNewLabel);
		
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
		
		JButton btnNewButton_1 = new JButton("Confirm Details");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String query = "INSERT INTO `customer`(`customer_name`, `contact_name`, `phone`, `address`, `status`, `agreed_discount`, `discount_rate`) VALUES (?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,name.getText());
					pst.setString(2,cname.getText());
					pst.setString(3,phone.getText());
					pst.setString(4,address.getText());
					pst.setString(5, status);
					pst.setString(6, agreedDiscount);
					pst.setString(7,discount.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Customer registered");
					CreateCustomer thisframe = new CreateCustomer();
					thisframe.setVisible(true);
					dispose();
					}
				catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
				
			}
		});
		btnNewButton_1.setBounds(550, 327, 124, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(117, 70, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contact Name");
		lblNewLabel_2.setBounds(117, 100, 89, 14);
		contentPane.add(lblNewLabel_2);
		
		name = new JTextField();
		name.setBounds(230, 67, 194, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setBounds(117, 130, 89, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Phone");
		lblNewLabel_4.setBounds(117, 160, 89, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Status");
		lblNewLabel_5.setBounds(117, 190, 89, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Agreed Discount");
		lblNewLabel_6.setBounds(117, 220, 89, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Discount Rate");
		lblNewLabel_7.setBounds(117, 250, 89, 14);
		contentPane.add(lblNewLabel_7);
		
		cname = new JTextField();
		cname.setBounds(230, 97, 194, 20);
		contentPane.add(cname);
		cname.setColumns(10);
		
		address = new JTextField();
		address.setBounds(230, 127, 194, 20);
		contentPane.add(address);
		address.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(230, 157, 194, 20);
		contentPane.add(phone);
		phone.setColumns(10);
		
		regularCustomer = new JRadioButton("Regular");
		regularCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(regularCustomer.isSelected()) {
					valuedCustomer.setSelected(false);
					status = "Regular";
				}
			}
		});
		regularCustomer.setBounds(229, 186, 109, 23);
		contentPane.add(regularCustomer);
		
		valuedCustomer = new JRadioButton("Valued");
		valuedCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(valuedCustomer.isSelected()) {
					regularCustomer.setSelected(false);
					status = "Valued";
				}
			}
		});
		valuedCustomer.setBounds(362, 186, 109, 23);
		contentPane.add(valuedCustomer);
		
		flexibleDiscount = new JRadioButton("Flexible");
		flexibleDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flexibleDiscount.isSelected()) {
					fixedDiscount.setSelected(false);
					variableDiscount.setSelected(false);
					agreedDiscount = "Flexible";
				}
			}
		});
		flexibleDiscount.setBounds(230, 216, 109, 23);
		contentPane.add(flexibleDiscount);
		
		fixedDiscount = new JRadioButton("Fixed");
		fixedDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fixedDiscount.isSelected()) {
					flexibleDiscount.setSelected(false);
					variableDiscount.setSelected(false);
					agreedDiscount = "Fixed";
				}
			}
		});
		fixedDiscount.setBounds(362, 216, 109, 23);
		contentPane.add(fixedDiscount);
		
		variableDiscount = new JRadioButton("Variable");
		variableDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(variableDiscount.isSelected()) {
					fixedDiscount.setSelected(false);
					flexibleDiscount.setSelected(false);
					agreedDiscount = "Variable";
				}
			}
		});
		variableDiscount.setBounds(473, 216, 109, 23);
		contentPane.add(variableDiscount);
		
		discount = new JTextField();
		discount.setBounds(230, 247, 86, 20);
		contentPane.add(discount);
		discount.setColumns(10);
	}
}
