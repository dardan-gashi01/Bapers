import java.awt.BorderLayout;
import java.util.Date;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class NewJob extends JFrame {
	java.util.Date date;
	java.sql.Date sqldate;
	private JPanel contentPane;
	private JTextField specialInstruction;
	private String payment = "Null";
	private String urgency;
	private JRadioButton normalButton;
	private JRadioButton urgentButton;
	private JDateChooser Date;
	private String Status = "Null";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewJob frame = new NewJob();
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
	public NewJob() {
		
		
		CustomerSearch cs = new CustomerSearch();
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2-getWidth()/2, size.height/2 - getHeight()/2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Job");
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
		
		JButton btnNewButton_1 = new JButton("Confirm Job");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Date currentDate = new Date();
					java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
					date = Date.getDate();
					sqldate = new java.sql.Date(date.getTime());
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bapersdb", "root", "");
					String query = "INSERT INTO `job`(`job_id` , `customer_id`,`payment_id`, `date_created`, `deadline`, `urgency`, `status`, `special_instructions`) VALUES (?, ?, ?,?,?, ?, ?,?)";
					PreparedStatement pst = con.prepareStatement(query);
					pst.setString(1,"abcd");
					pst.setInt(2,7);
					pst.setInt(3,1001);
					pst.setDate(4,sqlDate);
					pst.setDate(5, sqldate);
					pst.setString(6, urgency);
					pst.setString(7, Status);
					pst.setString(8,specialInstruction.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Job Created");
					NewJob thisframe = new NewJob();
					thisframe.setVisible(true);
					dispose();
					}
				catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_1.setBounds(585, 327, 89, 23);
		contentPane.add(btnNewButton_1);
	
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(99, 70, 107, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Deadline");
		lblNewLabel_3.setBounds(99, 95, 107, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Urgency Level");
		lblNewLabel_4.setBounds(99, 120, 107, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Special Instructions");
		lblNewLabel_5.setBounds(99, 145, 107, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel nameLabel = new JLabel("");
		nameLabel.setBounds(238, 70, 68, 14);
		contentPane.add(nameLabel);
		//nameLabel.setText(cs.getCustomerName());
		
		specialInstruction = new JTextField();
		specialInstruction.setBounds(238, 142, 253, 20);
		contentPane.add(specialInstruction);
		specialInstruction.setColumns(10);
		
		normalButton = new JRadioButton("Normal");
		normalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(normalButton.isSelected()) {
					urgentButton.setSelected(false);
					urgency = "normal";
				}
			}
		});
		normalButton.setBounds(238, 116, 109, 23);
		contentPane.add(normalButton);
		
		urgentButton = new JRadioButton("Urgent");
		urgentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(urgentButton.isSelected()) {
					normalButton.setSelected(false);
					urgency = "urgent";
				}
			}
		});
		urgentButton.setBounds(382, 116, 109, 23);
		contentPane.add(urgentButton);
		
		Date = new JDateChooser();
		Date.setBounds(238, 89, 94, 20);
		contentPane.add(Date);
		
		
		
		
		
		
	}
}
