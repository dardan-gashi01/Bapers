import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class UpdateAccount extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField NameField;
	private JTextField EmailField;
	private JRadioButton ReceptionistBTN;
	private JRadioButton OfficeManagerBTN;
	private JRadioButton TechnicianBTN;
	private JRadioButton ShiftManagerBTN;
	private JTextField PasswordField;
	private JLabel lblNewLabel_4;
	private JButton DeleteBTN;
	Connection connection = null;
	String role;

	/**
	 * Launch the application.
	 */
	//this is for test purposes 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateAccount frame = new UpdateAccount();
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
	public UpdateAccount() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update Account Details");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 964, 14);
		contentPane.add(lblNewLabel);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(400, 35, 574, 286);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"account_id", "name", "email", "role", "password"
				}
			));
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
				String TUserName = tblModel.getValueAt(table.getSelectedRow(), 1).toString();
				String TEmail= tblModel.getValueAt(table.getSelectedRow(), 2).toString();
				String TRole= tblModel.getValueAt(table.getSelectedRow(), 3).toString();
				String TPassword = tblModel.getValueAt(table.getSelectedRow(),4).toString();
				NameField.setText(TUserName);
				EmailField.setText(TEmail);
				PasswordField.setText(TPassword);
				if(TRole.equals("Receptionist")) {
					ReceptionistBTN.setSelected(true);
					TechnicianBTN.setSelected(false);
					OfficeManagerBTN.setSelected(false);
					ShiftManagerBTN.setSelected(false);
				}
				if(TRole.equals("Technician")) {
					TechnicianBTN.setSelected(true);
					ReceptionistBTN.setSelected(false);
					OfficeManagerBTN.setSelected(false);
					ShiftManagerBTN.setSelected(false);
				}
				if(TRole.equals("Office Manager")) {
					OfficeManagerBTN.setSelected(true);
					ReceptionistBTN.setSelected(false);
					TechnicianBTN.setSelected(false);
					ShiftManagerBTN.setSelected(false);
				}
				if(TRole.equals("Shift Manager")) {
					ShiftManagerBTN.setSelected(true);
					ReceptionistBTN.setSelected(false);
					TechnicianBTN.setSelected(false);
					OfficeManagerBTN.setSelected(false);
				}
				
	        }
	    });
		//creating a button
		JButton btnNewButton_1 = new JButton("Refresh");
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String query = "SELECT * FROM account";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_1.setBounds(398, 327, 89, 23);
		contentPane.add(btnNewButton_1);
		//creating a button
		JButton UpdateDetailsBTN = new JButton("Update Details");
		UpdateDetailsBTN.setBackground(new Color(192, 192, 192));
		UpdateDetailsBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
		        	String taccountid = tblModel.getValueAt(table.getSelectedRow(), 0).toString();
					String Name = NameField.getText();
					String Email = EmailField.getText();
					String Password = PasswordField.getText();
					String Role = role;
					String sql = "UPDATE `account` SET `name`= '" + Name + "' ,`email`= '" + Email + "', `role` = '" + Role + "', `password` = '" + Password + "' WHERE `account_id` = '" + taccountid + "'";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Updated");
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		UpdateDetailsBTN.setBounds(823, 327, 151, 23);
		contentPane.add(UpdateDetailsBTN);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(30, 65, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		NameField = new JTextField();
		NameField.setBackground(new Color(192, 192, 192));
		NameField.setBounds(127, 62, 205, 20);
		contentPane.add(NameField);
		NameField.setColumns(10);
		
		EmailField = new JTextField();
		EmailField.setBackground(new Color(192, 192, 192));
		EmailField.setBounds(127, 110, 205, 20);
		contentPane.add(EmailField);
		EmailField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(30, 113, 46, 14);
		contentPane.add(lblNewLabel_2);
		//creating a button
		ReceptionistBTN = new JRadioButton("Receptionist");
		ReceptionistBTN.setBackground(new Color(192, 192, 192));
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
		ReceptionistBTN.setBounds(127, 157, 109, 23);
		contentPane.add(ReceptionistBTN);
		//creating a button
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
		TechnicianBTN.setBounds(127, 183, 109, 23);
		contentPane.add(TechnicianBTN);
		//creating a button
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
		OfficeManagerBTN.setBounds(235, 157, 131, 23);
		contentPane.add(OfficeManagerBTN);
		//creating a button
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
		ShiftManagerBTN.setBounds(235, 183, 109, 23);
		contentPane.add(ShiftManagerBTN);
		
		PasswordField = new JTextField();
		PasswordField.setBackground(new Color(192, 192, 192));
		PasswordField.setBounds(127, 231, 205, 20);
		contentPane.add(PasswordField);
		PasswordField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("New Password");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(30, 234, 87, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Role");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(30, 161, 46, 14);
		contentPane.add(lblNewLabel_4);
		//creating a button
		DeleteBTN = new JButton("Delete User");
		DeleteBTN.setBackground(new Color(192, 192, 192));
		DeleteBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
		        	String taccountid = tblModel.getValueAt(table.getSelectedRow(), 0).toString();
					String sql = "DELETE FROM `account` WHERE `account_id` = '" + taccountid + "'";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Deleted");
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		DeleteBTN.setBounds(631, 327, 110, 23);
		contentPane.add(DeleteBTN);
	}
}
