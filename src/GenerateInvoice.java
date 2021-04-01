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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;

public class GenerateInvoice extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection connection = null;
	String job_id;
	int customer_id;
	float amount;
	String job_completed;
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

	
	
	/**
	 * Create the frame.
	 */
	//this is for test purposes 
	public GenerateInvoice() {
		//date format that suits the DB
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(new Date());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 864, 280);
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
		
		
		//takes you to menu
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setForeground(Color.BLUE);
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
		//loads all jobs in the DB to select 
		JButton btnNewButton_1 = new JButton("All Jobs");
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setBackground(new Color(192, 192, 192));
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
		//sets values for some variables depending on the column selected
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
		//this generates the invoice into the table invoice
		JButton btnNewButton_2 = new JButton("Generate");
		btnNewButton_2.setForeground(Color.BLUE);
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					//this is for the values so they are to 2dp and no more because it is money
					DecimalFormat decimalformat = new DecimalFormat("0.00");
					String sql = "SELECT * FROM customer WHERE customer_id ='"+ customer_id +"'";
					PreparedStatement ps = connection.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						discount = rs.getDouble("discount_rate");
						discount_type = rs.getString("agreed_discount");
						String customerT = rs.getString("status");
						//this checks if they are values to see if they need discounts
						if(customerT.equals("Valued")) {
							//applies fixed discount but couldn't complete the rest
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
					//formats the final price to 2dp
					double d = Double.parseDouble(decimalformat.format(discount_price));
					pst.setString(1,job_id);
					pst.setInt(2,customer_id);
					pst.setFloat(3, amount);
					pst.setString(4, job_completed);
					pst.setString(5, sdf.format(cal.getTime()));
					pst.setDouble(6, d);
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
