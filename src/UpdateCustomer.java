import java.awt.BorderLayout;
import java.awt.EventQueue;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
 
import net.proteanit.sql.DbUtils;
 
 
 
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Font;
 
public class UpdateCustomer extends JFrame {
 
	private JPanel contentPane;
	private JTable table;
	private JTextField customerNameField;
	private JTextField contactNameField;
	private JTextField phoneField;
	private JTextField addressField;
	private JTextField discountField;
	private JRadioButton RegularButton;
	private JRadioButton ValuedButton;
	private JRadioButton FlexibleButton;
	private JRadioButton FixedButton;
	private JRadioButton VariableButton;
	private String status;
	private String agreedDiscount;
	String CustomerID;
	Connection connection = null;

	/**
	 * Launch the application.
	 */
	//this is for test purposes 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCustomer frame = new UpdateCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	//Connection connection = null;
 
	/**
	 * Create the frame.
	 */
	public UpdateCustomer() {
 
 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1147, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//creating a button
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
 
		JLabel lblNewLabel = new JLabel("Update Customer Details");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 964, 14);
		contentPane.add(lblNewLabel);
 
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(413, 36, 708, 285);
		contentPane.add(scrollPane);
 
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Customer_id", "customer_name", "contact_name", "phone", "address", "status", "agreed_discount", "discount_rate"
			}
		));
		//same as updateAccount it fills the fields with values from the row you selected
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
				String tCustomerName = tblModel.getValueAt(table.getSelectedRow(), 1).toString();
				String tContactName= tblModel.getValueAt(table.getSelectedRow(), 2).toString();
				String tPhone= tblModel.getValueAt(table.getSelectedRow(), 3).toString();
				String tAddress = tblModel.getValueAt(table.getSelectedRow(),4).toString();
				String tStatus = tblModel.getValueAt(table.getSelectedRow(),5).toString();
				
				customerNameField.setText(tCustomerName);
				contactNameField.setText(tContactName);
				phoneField.setText(tPhone);
				addressField.setText(tAddress);
				if(tStatus.equals("Regular")) {
					RegularButton.setSelected(true);
					ValuedButton.setSelected(false);
				}
				if(tStatus.equals("Valued")) {
					ValuedButton.setSelected(true);
					RegularButton.setSelected(false);
				}	
	        }
	    });
		
		//creating a button that populates the table from the db
		JButton btnNewButton_2 = new JButton("Refresh");
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String query = "SELECT * FROM customer";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_2.setBounds(415, 327, 131, 23);
		contentPane.add(btnNewButton_2);
 
		JLabel lblNewLabel_1 = new JLabel("Customer Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 30, 103, 14);
		contentPane.add(lblNewLabel_1);
 
		customerNameField = new JTextField();
		customerNameField.setBackground(new Color(192, 192, 192));
		customerNameField.setBounds(147, 27, 167, 20);
		contentPane.add(customerNameField);
		customerNameField.setColumns(10);
 
		JLabel lblNewLabel_2 = new JLabel("Contact Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 70, 89, 14);
		contentPane.add(lblNewLabel_2);
 
		contactNameField = new JTextField();
		contactNameField.setBackground(new Color(192, 192, 192));
		contactNameField.setBounds(147, 67, 167, 20);
		contentPane.add(contactNameField);
		contactNameField.setColumns(10);
 
		JLabel lblNewLabel_3 = new JLabel("Phone");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(10, 110, 89, 14);
		contentPane.add(lblNewLabel_3);
 
		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(10, 150, 89, 14);
		contentPane.add(lblNewLabel_4);
 
		JLabel lblNewLabel_5 = new JLabel("Status");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(10, 190, 89, 14);
		contentPane.add(lblNewLabel_5);
 
		JLabel lblNewLabel_6 = new JLabel("Agreed Discount");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(10, 230, 103, 14);
		contentPane.add(lblNewLabel_6);
 
		JLabel lblNewLabel_7 = new JLabel("Discount Rate");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_7.setBounds(10, 270, 89, 14);
		contentPane.add(lblNewLabel_7);
 
		phoneField = new JTextField();
		phoneField.setBackground(new Color(192, 192, 192));
		phoneField.setBounds(147, 107, 167, 20);
		contentPane.add(phoneField);
		phoneField.setColumns(10);
 
		addressField = new JTextField();
		addressField.setBackground(new Color(192, 192, 192));
		addressField.setBounds(147, 147, 167, 20);
		contentPane.add(addressField);
		addressField.setColumns(10);
 
		discountField = new JTextField();
		discountField.setBackground(new Color(192, 192, 192));
		discountField.setBounds(140, 267, 86, 20);
		contentPane.add(discountField);
		discountField.setColumns(10);
		//creating a button where depending on regular or valued you get different fields to fill out for discount and stuff
		RegularButton = new JRadioButton("Regular");
		RegularButton.setBackground(new Color(192, 192, 192));
		RegularButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(RegularButton.isSelected()) {
					ValuedButton.setSelected(false);
					FlexibleButton.setVisible(false);
					FixedButton.setVisible(false);
					VariableButton.setVisible(false);
					lblNewLabel_6.setVisible(false);
					lblNewLabel_7.setVisible(false);
					discountField.setVisible(false);
					discountField.setText(null);
					FixedButton.setSelected(false);
					FlexibleButton.setSelected(false);
					VariableButton.setSelected(false);
					status = "Regular";
				}
			}
		});
		RegularButton.setBounds(144, 186, 109, 23);
		contentPane.add(RegularButton);
		//creating a button
		ValuedButton = new JRadioButton("Valued");
		ValuedButton.setBackground(new Color(192, 192, 192));
		ValuedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ValuedButton.isSelected()) {
					RegularButton.setSelected(false);
					FlexibleButton.setVisible(true);
					FixedButton.setVisible(true);
					VariableButton.setVisible(true);
					lblNewLabel_6.setVisible(true);
					lblNewLabel_7.setVisible(true);
					discountField.setVisible(true);
					status = "Valued";
				}
			}
		});
		ValuedButton.setBounds(255, 186, 109, 23);
		contentPane.add(ValuedButton);
		//creating a button
		FlexibleButton = new JRadioButton("Flexible");
		FlexibleButton.setBackground(new Color(192, 192, 192));
		FlexibleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(FlexibleButton.isSelected()) {
					VariableButton.setSelected(false);
					FixedButton.setSelected(false);
					agreedDiscount = "Flexible";
				}
			}
		});
		FlexibleButton.setBounds(140, 226, 69, 23);
		contentPane.add(FlexibleButton);
		//creating a button
		FixedButton = new JRadioButton("Fixed");
		FixedButton.setBackground(new Color(192, 192, 192));
		FixedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(FixedButton.isSelected()) {
					FlexibleButton.setSelected(false);
					VariableButton.setSelected(false);
					agreedDiscount = "Fixed";
				}
			}
		});
		FixedButton.setBounds(228, 226, 69, 23);
		contentPane.add(FixedButton);
		//creating a button
		VariableButton = new JRadioButton("Variable");
		VariableButton.setBackground(new Color(192, 192, 192));
		VariableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(VariableButton.isSelected()) {
					FixedButton.setSelected(false);
					FlexibleButton.setSelected(false);
					agreedDiscount = "Variable";
				}
			}
		});
		VariableButton.setBounds(319, 226, 69, 23);
		contentPane.add(VariableButton);
		//creating a button that takes the new values from the fields and then updates the db with it
		JButton btnNewButton_3 = new JButton("Update Details");
		btnNewButton_3.setBackground(new Color(192, 192, 192));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
			try {
				DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
				//selecting the customerID so it knows which row to update in the db using SQL
	        	CustomerID = tblModel.getValueAt(table.getSelectedRow(), 0).toString();
				String custName = customerNameField.getText();
				String contactName = contactNameField.getText();
				String phone = phoneField.getText();
				String address = addressField.getText();
				String Status = status;
				String AgreedDiscount = agreedDiscount;
				String DiscountRate = discountField.getText();
				String sql = "UPDATE `customer` SET `customer_name`= '" + custName + "' ,`contact_name`= '" + contactName + "', `phone` = '" + phone + "', `address` = '" + address +"', `status` = '"+ Status +"', `agreed_discount` = '" + AgreedDiscount +"', `discount_rate`= '"+ DiscountRate +"' WHERE `customer_id` = '" + CustomerID + "'";
				PreparedStatement pst = connection.prepareStatement(sql);
				pst.execute();
				UpdateCustomer thisFrame = new UpdateCustomer();
				thisFrame.setVisible(true);
				dispose();
				JOptionPane.showMessageDialog(null, "Updated");
			}catch(Exception E) {
				JOptionPane.showMessageDialog(null,E);
			}
			}
		});
		btnNewButton_3.setBounds(990, 327, 131, 23);
		contentPane.add(btnNewButton_3);
	}
}