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

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class PaySystem extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection connection = null;
	String invoice_id;
	double Amount;
	private JRadioButton CashBTN;
	private JRadioButton CardBTN;
	private JRadioButton CreditBTN;
	private JRadioButton DebitBTN;
	String type;
	String card_type;
	private JTextField CardNumberField;
	private JTextField CVCField;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField DateField;
	private JLabel lblNewLabel_2;
	private JTextField AmountField;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	//this is for test purposes 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaySystem frame = new PaySystem();
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
	public PaySystem() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JButton btnNewButton_1 = new JButton("confirm Payment");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String query = "INSERT INTO `payment`(`invoice_id`,`type`, `amount`, `card_type`,`expiry_date`, `CardNumber`,`CVC` ) VALUES (?, ?,?, ?,?, ?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,invoice_id);
					pst.setString(2,type);
					pst.setDouble(3, Amount);
					pst.setString(4, card_type);
					pst.setString(5, DateField.getText());
					pst.setString(6, CardNumberField.getText());
					pst.setString(7, CVCField.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Payment Success");
					if(type.equals("Cash")) {
						String paid = AmountField.getText();
						double d = Double.valueOf(paid);
						double change = (d) - Amount;
						DecimalFormat decimalformat = new DecimalFormat("0.00");
						JOptionPane.showMessageDialog(null, decimalformat.format(change));
					}
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_1.setBounds(785, 327, 139, 23);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 30, 618, 286);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"invoice_id", "job_id", "customer_id", "amount", "job_completed","invoice_date","discountedPrice"
				}
			));
		
		JButton btnNewButton_2 = new JButton("Show invoice");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String query = "SELECT * FROM invoice";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_2.setBounds(444, 327, 154, 23);
		contentPane.add(btnNewButton_2);
		
		CashBTN = new JRadioButton("Cash");
		CashBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CashBTN.isSelected()) {
					CardBTN.setSelected(false);
					type = "Cash";
					card_type = "NULL";
					CreditBTN.setSelected(false);
					DebitBTN.setSelected(false);
					DebitBTN.setVisible(false);
					CreditBTN.setVisible(false);
					CardNumberField.setVisible(false);
					CardNumberField.setText(null);
					CVCField.setVisible(false);
					CVCField.setText(null);
					lblNewLabel.setVisible(false);
					lblNewLabel_1.setVisible(false);
					DateField.setVisible(false);
					DateField.setText(null);
					AmountField.setVisible(true);
					lblNewLabel_3.setVisible(true);
					lblNewLabel_2.setVisible(false);
				}
			}
		});
		CashBTN.setBounds(690, 33, 109, 23);
		contentPane.add(CashBTN);
		
		CardBTN = new JRadioButton("Card");
		CardBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CardBTN.isSelected()) {
					CashBTN.setSelected(false);
					type = "Card";
					DebitBTN.setVisible(true);
					CreditBTN.setVisible(true);
					CardNumberField.setVisible(true);
					CVCField.setVisible(true);
					lblNewLabel.setVisible(true);
					lblNewLabel_1.setVisible(true);
					AmountField.setVisible(false);
					AmountField.setText(null);
					lblNewLabel_3.setVisible(false);
					lblNewLabel_2.setVisible(true);
					DateField.setVisible(true);
				}
			}
		});
		CardBTN.setBounds(801, 33, 109, 23);
		contentPane.add(CardBTN);
		
		DebitBTN = new JRadioButton("Debit");
		DebitBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DebitBTN.isSelected()) {
					CreditBTN.setSelected(false);
					card_type = "Debit";
				}
			}
		});
		DebitBTN.setBounds(676, 67, 109, 23);
		contentPane.add(DebitBTN);
		
		CreditBTN = new JRadioButton("Credit");
		CreditBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CreditBTN.isSelected()) {
					DebitBTN.setSelected(false);
					card_type = "Credit";
				}
			}
		});
		CreditBTN.setBounds(815, 67, 109, 23);
		contentPane.add(CreditBTN);
		
		CardNumberField = new JTextField();
		CardNumberField.setBounds(770, 111, 154, 20);
		contentPane.add(CardNumberField);
		CardNumberField.setColumns(10);
		
		CVCField = new JTextField();
		CVCField.setBounds(838, 142, 86, 20);
		contentPane.add(CVCField);
		CVCField.setColumns(10);
		
		lblNewLabel = new JLabel("Card Number");
		lblNewLabel.setBounds(658, 114, 102, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("CVC");
		lblNewLabel_1.setBounds(714, 145, 117, 14);
		contentPane.add(lblNewLabel_1);
		
		DateField = new JTextField();
		DateField.setBounds(838, 184, 86, 20);
		contentPane.add(DateField);
		DateField.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Expiry Date");
		lblNewLabel_2.setBounds(714, 187, 117, 14);
		contentPane.add(lblNewLabel_2);
		
		AmountField = new JTextField();
		AmountField.setBounds(838, 227, 86, 20);
		contentPane.add(AmountField);
		AmountField.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Amount paid in cash");
		lblNewLabel_3.setBounds(714, 230, 117, 14);
		contentPane.add(lblNewLabel_3);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
	        	invoice_id = tblModel.getValueAt(table.getSelectedRow(), 0).toString();
				Amount = (double) tblModel.getValueAt(table.getSelectedRow(), 6);
				
				
			
	        }
	    });
	}
}
