import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

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
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

public class Payment extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection connection = null;
	double Amount;
	private JLabel lblNewLabel_2;
	String invoice_id;
	String type;
	private JRadioButton CashBTN;
	private JRadioButton CardBTN;
	private JTextField L4DField;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JRadioButton DebitBTN;
	private JRadioButton CreditBTN;
	private JDateChooser dateChooser;
	String cardType;
	private JTextField AmountField;
	private JLabel lblNewLabel_5;
	String date;
	String CardNumber;
	String CVC;
	private JTextField CVCField;
	private JLabel lblNewLabel_6;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment frame = new Payment();
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
	public Payment() {
		
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2-getWidth()/2, size.height/2 - getHeight()/2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Payment");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 664, 14);
		contentPane.add(lblNewLabel);
		//creating a button
		JButton btnNewButton_2 = new JButton("Cancel");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menuFrame = new Menu();
				menuFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(22, 327, 89, 23);
		contentPane.add(btnNewButton_2);
		
		
		
		
		JButton btnNewButton_3 = new JButton("Confirm Payment");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String query = "INSERT INTO `payment`(`invoice_id`,`type`, `amount`, `card_type`,`CardNumber`,`CVC` ) VALUES (?, ?,?, ?,?, ?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,invoice_id);
					pst.setString(2,type);
					pst.setDouble(3, Amount);
					pst.setString(4, cardType);
					//pst.setString(5,date);
					pst.setString(5, L4DField.getText());
					pst.setString(6, CVCField.getText());
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
		
		
		btnNewButton_3.setBounds(752, 327, 122, 23);
		contentPane.add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 36, 543, 269);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"invoice_id", "job_id", "customer_id", "amount", "jobCompleted", "invoiceDate", "discountedPrice"
				}
			));
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
	        	invoice_id = tblModel.getValueAt(table.getSelectedRow(), 0).toString();
				Amount = (double) tblModel.getValueAt(table.getSelectedRow(), 6);
				String s = Float.toString((float)Amount);
				lblNewLabel_2.setText(s);
			
	        }
	    });
		
		JButton btnNewButton_4 = new JButton("load invoices");
		btnNewButton_4.addActionListener(new ActionListener() {
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
		btnNewButton_4.setBounds(451, 327, 112, 23);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_1 = new JLabel("Amount to Pay: ");
		lblNewLabel_1.setBounds(646, 43, 97, 14);
		contentPane.add(lblNewLabel_1);
		
		
		
		
		
		lblNewLabel_2 = new JLabel("£");
		lblNewLabel_2.setBounds(765, 43, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		CashBTN = new JRadioButton("Cash");
		CashBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CashBTN.isSelected()) {
					CardBTN.setSelected(false);
					lblNewLabel_3.setVisible(false);
					lblNewLabel_4.setVisible(false);
					DebitBTN.setVisible(false);
					CreditBTN.setVisible(false);
					L4DField.setVisible(false);
					CVCField.setVisible(false);
					dateChooser.setVisible(false);
					AmountField.setVisible(true);
					lblNewLabel_5.setVisible(true);
					lblNewLabel_6.setVisible(false);
					type = "Cash";
					System.out.println(type);
					date = "NULL";
					cardType = "NULL";
					CardNumber = "NULL";
					CVC = "NULL";
				}
			}
		});
		CashBTN.setBounds(609, 64, 109, 23);
		contentPane.add(CashBTN);
		
		CardBTN = new JRadioButton("Card");
		CardBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CardBTN.isSelected()) {
					CashBTN.setSelected(false);
					lblNewLabel_3.setVisible(true);
					lblNewLabel_4.setVisible(true);
					DebitBTN.setVisible(true);
					CreditBTN.setVisible(true);
					L4DField.setVisible(true);
					CVCField.setVisible(true);
					dateChooser.setVisible(true);
					AmountField.setVisible(false);
					lblNewLabel_5.setVisible(false);
					lblNewLabel_6.setVisible(true);
					type = "Card";
					date = dateChooser.getDate().toString();
					CardNumber = L4DField.getText();
					CVC = CVCField.getText();
				}
			}
		});
		CardBTN.setBounds(734, 64, 109, 23);
		contentPane.add(CardBTN);
		
		L4DField = new JTextField();
		L4DField.setBounds(679, 127, 109, 20);
		contentPane.add(L4DField);
		L4DField.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Card Number");
		lblNewLabel_3.setBounds(599, 130, 70, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("ExpDate");
		lblNewLabel_4.setBounds(599, 165, 70, 14);
		contentPane.add(lblNewLabel_4);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(679, 165, 70, 20);
		contentPane.add(dateChooser);
		
		DebitBTN = new JRadioButton("Debit Card");
		DebitBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DebitBTN.isSelected()) {
					CreditBTN.setSelected(false);
					cardType = "Debit";
				}
			}
		});
		DebitBTN.setBounds(599, 97, 109, 23);
		contentPane.add(DebitBTN);
		
		CreditBTN = new JRadioButton("Credit Card");
		CreditBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CreditBTN.isSelected()) {
					DebitBTN.setSelected(false);
					cardType = "Credit";
				}
			}
		});
		CreditBTN.setBounds(710, 97, 109, 23);
		contentPane.add(CreditBTN);
		
		AmountField = new JTextField();
		AmountField.setBounds(733, 224, 86, 20);
		contentPane.add(AmountField);
		AmountField.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Amount Paid in Cash");
		lblNewLabel_5.setBounds(599, 227, 119, 14);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("CVC");
		lblNewLabel_6.setBounds(599, 202, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		CVCField = new JTextField();
		CVCField.setBounds(679, 193, 86, 20);
		contentPane.add(CVCField);
		CVCField.setColumns(10);
		
		
		
		
		
		
	}
}

