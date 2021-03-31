import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JButton PlaceJobBTN;
	private JButton TakePaymentBTN;
	private JButton CreateCustBTN;
	private JButton viewActiveJobBTN;
	private JButton viewAllJobsBTN;
	private JButton ReportsBTN;
	private JButton CreateAccountBTN;
	private JButton UpdateAccountBTN;
	private JButton ManageCustomersBTN;
	private JButton CreateBackupBTN;
	private JButton EditTasksBTN;
	private JButton UpdateTasksBTN;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	//this is for test purposes 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void AssignMenu(String role) {
		if(role.equals("Receptionist")) {
			CreateAccountBTN.setVisible(false);
			UpdateAccountBTN.setVisible(false);
			ManageCustomersBTN.setVisible(false);
			CreateBackupBTN.setVisible(false);
			ReportsBTN.setVisible(false);
			viewActiveJobBTN.setVisible(false);
			viewAllJobsBTN.setVisible(false);
			//UpdateJobStatusBTN.setVisible(false);
			EditTasksBTN.setVisible(false);
			UpdateTasksBTN.setVisible(false);
		}else if(role.equals("Shift Manager")) {
			CreateAccountBTN.setVisible(false);
			UpdateAccountBTN.setVisible(false);
			CreateBackupBTN.setVisible(false);
			ManageCustomersBTN.setVisible(false);
		}
		else if(role.equals("Technician")) {
			CreateAccountBTN.setVisible(false);
			UpdateAccountBTN.setVisible(false);
			CreateBackupBTN.setVisible(false);
			ManageCustomersBTN.setVisible(false);
			ReportsBTN.setVisible(false);
			EditTasksBTN.setVisible(false);
			PlaceJobBTN.setVisible(false);
			TakePaymentBTN.setVisible(false);
			CreateCustBTN.setVisible(false);
			btnNewButton_1.setVisible(false);
		}
	}
	

	/**
	 * Create the frame.
	 */
	
	public Menu() {
		
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2-getWidth()/2, size.height/2 - getHeight()/2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BAPERS MENU");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 695, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Office Manager");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(21, 60, 116, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Shift Manager");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(185, 60, 115, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Technician");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(382, 60, 67, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Receptionist");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(546, 60, 97, 14);
		contentPane.add(lblNewLabel_4);
		//creating a button
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login LoginFrame = new Login();
				LoginFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(505, 327, 187, 23);
		contentPane.add(btnNewButton);
		//creating a button
		PlaceJobBTN = new JButton("Place Job");
		PlaceJobBTN.setBackground(new Color(192, 192, 192));
		PlaceJobBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerSearch newjobFrame = new CustomerSearch();
				newjobFrame.setVisible(true);
				dispose();
			}
		});
		PlaceJobBTN.setBounds(505, 85, 187, 23);
		contentPane.add(PlaceJobBTN);
		//creating a button
		TakePaymentBTN = new JButton("Take Payment");
		TakePaymentBTN.setBackground(new Color(192, 192, 192));
		TakePaymentBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaySystem paymentFrame = new PaySystem();
				paymentFrame.setVisible(true);
				dispose();
			}
		});
		TakePaymentBTN.setBounds(505, 135, 187, 23);
		contentPane.add(TakePaymentBTN);
		//creating a button
		CreateCustBTN = new JButton("Create Customer");
		CreateCustBTN.setBackground(new Color(192, 192, 192));
		CreateCustBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateCustomer CCFrame = new CreateCustomer();
				CCFrame.setVisible(true);
				dispose();
			}
		});
		CreateCustBTN.setBounds(505, 185, 187, 23);
		contentPane.add(CreateCustBTN);
		//creating a button
		ReportsBTN = new JButton("Reports");
		ReportsBTN.setBackground(new Color(192, 192, 192));
		ReportsBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reports reportsFrame = new Reports();
				reportsFrame.setVisible(true);
				dispose();
			}
		});
		ReportsBTN.setBounds(185, 85, 125, 23);
		contentPane.add(ReportsBTN);
		//creating a button
		viewActiveJobBTN = new JButton("View Active Jobs");
		viewActiveJobBTN.setBackground(new Color(192, 192, 192));
		viewActiveJobBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActiveJobList activejobsFrame = new ActiveJobList();
				activejobsFrame.setVisible(true);
				dispose();
			}
		});
		viewActiveJobBTN.setBounds(331, 85, 149, 23);
		contentPane.add(viewActiveJobBTN);
		//creating a button
		CreateAccountBTN = new JButton("Create Account");
		CreateAccountBTN.setBackground(new Color(192, 192, 192));
		CreateAccountBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAccount createaccountFrame = new CreateAccount();
				createaccountFrame.setVisible(true);
				dispose();
			}
		});
		CreateAccountBTN.setBounds(10, 85, 151, 23);
		contentPane.add(CreateAccountBTN);
		//creating a button
		UpdateAccountBTN = new JButton("Update Account");
		UpdateAccountBTN.setBackground(new Color(192, 192, 192));
		UpdateAccountBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateAccount accountdetailsFrame = new UpdateAccount();
				accountdetailsFrame.setVisible(true);
				dispose();
			}
		});
		UpdateAccountBTN.setBounds(10, 135, 151, 23);
		contentPane.add(UpdateAccountBTN);
		//creating a button
		ManageCustomersBTN = new JButton("Manage Customers");
		ManageCustomersBTN.setBackground(new Color(192, 192, 192));
		ManageCustomersBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateCustomer customersearchFrame = new UpdateCustomer();
				customersearchFrame.setVisible(true);
				dispose();
			}
		});
		ManageCustomersBTN.setBounds(10, 185, 151, 23);
		contentPane.add(ManageCustomersBTN);
		//creating a button
		viewAllJobsBTN = new JButton("View All Jobs");
		viewAllJobsBTN.setBackground(new Color(192, 192, 192));
		viewAllJobsBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JobList alljobFrame = new JobList();
				alljobFrame.setVisible(true);
				dispose();
			}
		});
		viewAllJobsBTN.setBounds(331, 135, 149, 23);
		contentPane.add(viewAllJobsBTN);
		//creating a button
		CreateBackupBTN = new JButton("Create Backup");
		CreateBackupBTN.setBackground(new Color(192, 192, 192));
		CreateBackupBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BackupDB bdb = new BackupDB();
				bdb.backup();
			}
		});
		CreateBackupBTN.setBounds(10, 232, 151, 23);
		contentPane.add(CreateBackupBTN);
		//creating a button
		EditTasksBTN = new JButton("Edit Tasks");
		EditTasksBTN.setBackground(new Color(192, 192, 192));
		EditTasksBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateDeleteTask tasksFrame = new CreateDeleteTask();
				tasksFrame.setVisible(true);
				dispose();
			}
		});
		EditTasksBTN.setBounds(185, 135, 125, 23);
		contentPane.add(EditTasksBTN);
		
		UpdateTasksBTN = new JButton("Update Tasks");
		UpdateTasksBTN.setBackground(new Color(192, 192, 192));
		UpdateTasksBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateTask updatetaskFrame = new UpdateTask();
				updatetaskFrame.setVisible(true);
				dispose();
			}
		});
		UpdateTasksBTN.setBounds(331, 185, 149, 23);
		contentPane.add(UpdateTasksBTN);
		
		btnNewButton_1 = new JButton("Generate Invoice");
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerateInvoice invoiceFrame = new GenerateInvoice();
				invoiceFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(505, 232, 187, 23);
		contentPane.add(btnNewButton_1);
	}
}