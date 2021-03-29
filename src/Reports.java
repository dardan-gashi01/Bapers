import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Reports extends JFrame {

	private JPanel contentPane;
	private JRadioButton CustomerReportBTN;
	private JRadioButton SummaryReportBTN;
	private JRadioButton PersonalReportBTN;
	private JTextField CustIDField;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField StartDateField;
	private JTextField EndDateField;
	private JTextField StartTimeField;
	private JTextField EndTimeField;
	private JTextField StaffNameField;
	Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reports frame = new Reports();
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
	public Reports() {
		
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2-getWidth()/2, size.height/2 - getHeight()/2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
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
		
		JLabel lblNewLabel = new JLabel("Reports");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 664, 14);
		contentPane.add(lblNewLabel);
		
		CustomerReportBTN = new JRadioButton("Customer Report");
		CustomerReportBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CustomerReportBTN.isSelected()) {
					SummaryReportBTN.setSelected(false);
					PersonalReportBTN.setSelected(false);
					StartTimeField.setText(null);
					StartTimeField.setVisible(false);
					EndTimeField.setVisible(false);
					EndTimeField.setText(null);
					StaffNameField.setText(null);
					StaffNameField.setVisible(false);
					CustIDField.setVisible(true);
				}
			}
		});
		CustomerReportBTN.setBounds(20, 32, 139, 23);
		contentPane.add(CustomerReportBTN);
		
		SummaryReportBTN = new JRadioButton("Summary Report");
		SummaryReportBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(SummaryReportBTN.isSelected()) {
					PersonalReportBTN.setSelected(false);
					CustomerReportBTN.setSelected(false);
					StaffNameField.setText(null);
					StaffNameField.setVisible(false);
					CustIDField.setText(null);
					CustIDField.setVisible(false);
					StartTimeField.setVisible(true);
					EndTimeField.setVisible(true);
				}
			}
		});
		SummaryReportBTN.setBounds(255, 32, 143, 23);
		contentPane.add(SummaryReportBTN);
		
		PersonalReportBTN = new JRadioButton("individual Performance Report");
		PersonalReportBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(PersonalReportBTN.isSelected()) {
					SummaryReportBTN.setSelected(false);
					CustomerReportBTN.setSelected(false);
					StartTimeField.setText(null);
					StartTimeField.setVisible(false);
					EndTimeField.setVisible(false);
					EndTimeField.setText(null);
					CustIDField.setText(null);
					CustIDField.setVisible(false);
					StartDateField.setVisible(false);
					StartDateField.setText(null);
					StaffNameField.setVisible(true);
				}
			}
		});
		PersonalReportBTN.setBounds(450, 32, 204, 23);
		contentPane.add(PersonalReportBTN);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				report r =new report();
				if(CustomerReportBTN.isSelected()) {
					r.CustomerReport(CustIDField.getText(),StartDateField.getText(), EndDateField.getText() );
				}else if(SummaryReportBTN.isSelected()) {
					r.SummaryReport(StartTimeField.getText(), EndTimeField.getText(), StartDateField.getText(), EndDateField.getText());
				}else if (PersonalReportBTN.isSelected()) {
					r.individualReport(StaffNameField.getText(), EndDateField.getText());
				}
			}
		});
		btnNewButton_1.setBounds(585, 327, 89, 23);
		contentPane.add(btnNewButton_1);
		
		CustIDField = new JTextField();
		CustIDField.setBounds(312, 96, 56, 20);
		contentPane.add(CustIDField);
		CustIDField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Customer id");
		lblNewLabel_1.setBounds(216, 99, 74, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Start date");
		lblNewLabel_2.setBounds(216, 135, 74, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("End date");
		lblNewLabel_3.setBounds(216, 169, 74, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Start time");
		lblNewLabel_4.setBounds(216, 194, 74, 14);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("End Time");
		lblNewLabel_5.setBounds(216, 227, 74, 14);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Staff Name");
		lblNewLabel_6.setBounds(216, 254, 74, 14);
		contentPane.add(lblNewLabel_6);
		
		StartDateField = new JTextField();
		StartDateField.setBounds(312, 132, 127, 20);
		contentPane.add(StartDateField);
		StartDateField.setColumns(10);
		
		EndDateField = new JTextField();
		EndDateField.setBounds(312, 166, 127, 20);
		contentPane.add(EndDateField);
		EndDateField.setColumns(10);
		
		StartTimeField = new JTextField();
		StartTimeField.setBounds(312, 194, 127, 20);
		contentPane.add(StartTimeField);
		StartTimeField.setColumns(10);
		
		EndTimeField = new JTextField();
		EndTimeField.setBounds(312, 224, 127, 20);
		contentPane.add(EndTimeField);
		EndTimeField.setColumns(10);
		
		StaffNameField = new JTextField();
		StaffNameField.setBounds(312, 251, 127, 20);
		contentPane.add(StaffNameField);
		StaffNameField.setColumns(10);
	}
}