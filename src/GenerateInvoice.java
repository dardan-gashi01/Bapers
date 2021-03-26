import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class GenerateInvoice extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection connection = null;
	String job_id;
	int customer_id;
	float amount;
	String job_completed;
	private JLabel id_label;
	private JLabel CUSTOMERIDLABEL;
	private JLabel AMOUNTLABEL;
	private JLabel COMPLETEDLABEL;
	double discount;
	String discount_type;
	double discount_price;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateInvoice frame = new GenerateInvoice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/*public void applyDiscount() {
		connection = sqlConnection.getConnection();
		try {
			String query = "SELECT * FROM customer WHERE customer_id ='"+ customer_id +"'";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				discount = rs.getDouble("discount_rate");
				discount_type = rs.getString("agreed_discount");
				if(discount_type.equals("Fixed")){
					discount_price = amount * ((100-discount)/100);
				}else if(discount_type.equals("Variable")) {
					
				}else if(discount_type.equals("Flexible")) {
					
				}else {
					discount_price = amount;
				}
			}	
		}catch(Exception E) {
			JOptionPane.showMessageDialog(null,E);
		}
	}
	*/
	/**
	 * Create the frame.
	 */
	public GenerateInvoice() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(new Date());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 618, 280);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"job_id", "customer_id","urgency", "status", "instructions", "dateCreated", "deadline", "price"
				}
			));
		
		
		
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
		
		JButton btnNewButton_1 = new JButton("All Jobs");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String query = "SELECT * FROM job";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
	        	job_id = tblModel.getValueAt(table.getSelectedRow(), 0).toString();	
				customer_id = (int)tblModel.getValueAt(table.getSelectedRow(), 1);
				amount = (float) tblModel.getValueAt(table.getSelectedRow(), 7);
				job_completed = tblModel.getValueAt(table.getSelectedRow(), 6).toString();
				
	        }
	    });
		btnNewButton_1.setBounds(539, 326, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Job ID");
		lblNewLabel.setBounds(666, 42, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Customer ID");
		lblNewLabel_1.setBounds(666, 73, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Amount");
		lblNewLabel_2.setBounds(666, 108, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Date Finished");
		lblNewLabel_3.setBounds(666, 145, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		id_label = new JLabel();
		id_label.setBounds(750, 42, 46, 14);
		contentPane.add(id_label);
		
		CUSTOMERIDLABEL = new JLabel();
		CUSTOMERIDLABEL.setBounds(750, 73, 46, 14);
		contentPane.add(CUSTOMERIDLABEL);
		
		AMOUNTLABEL = new JLabel();
		AMOUNTLABEL.setBounds(750, 108, 46, 14);
		contentPane.add(AMOUNTLABEL);
		
		COMPLETEDLABEL = new JLabel();
		COMPLETEDLABEL.setBounds(750, 145, 46, 14);
		contentPane.add(COMPLETEDLABEL);
		
		JButton btnNewButton_2 = new JButton("Generate");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String sql = "SELECT * FROM customer WHERE customer_id ='"+ customer_id +"'";
					PreparedStatement ps = connection.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						discount = rs.getDouble("discount_rate");
						discount_type = rs.getString("agreed_discount");
						String customerT = rs.getString("status");
						if(customerT.equals("Valued")) {
							if(discount_type.equals("Fixed")){
								discount_price = amount * ((100-discount)/100);
							}else if(discount_type.equals("Variable")) {
								discount_price = amount;
							}else if(discount_type.equals("Flexible")) {
								discount_price = amount;
							}
						}else {
							discount_price = amount;
						}
					}	
					String query = "INSERT INTO `invoice`(`job_id` , `customer_id`,`amount`, `job_completed`, `invoice_date`, discountedPrice) VALUES (?, ?,?, ?, ?, ?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,job_id);
					pst.setInt(2,customer_id);
					pst.setFloat(3, amount);
					pst.setString(4, job_completed);
					pst.setString(5, sdf.format(cal.getTime()));
					pst.setDouble(6, discount_price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "invoice Generated");
					Menu menuFrame = new Menu();
					menuFrame.setVisible(true);
					dispose();
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_2.setBounds(785, 327, 89, 23);
		contentPane.add(btnNewButton_2);
	}
}
