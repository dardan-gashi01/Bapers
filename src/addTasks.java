import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class addTasks extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField jobidField;
	private JTextField customeridField;
	private JScrollPane scrollPane;
	private JComboBox comboBox;
	Connection connection = null;
	private JButton alljobs;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	String job_id;
	String status = "incomplete";

	/**
	 * Launch the application.
	 */
	//this is for test purposes 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addTasks frame = new addTasks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	this method fills the drag down box with the tasks in the table task
	 */
	private void Fillcombo() {
		connection = sqlConnection.getConnection();
		try {
			String sql = "SELECT task_id FROM task";
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("task_id");
				comboBox.addItem(id);
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}
	}
	/**
	 * Create the frame.
	 */
	public addTasks() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 60, 894, 249);
		contentPane.add(scrollPane);
		//creating a table with the columns labelled
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"job_id","customer_id","urgency","status","special_instructions", "date_created", "deadline"
				}
			));
		//selection model is so I can select rows and get values from each column for example hre I got the job_id in the first column
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
				job_id = tblModel.getValueAt(table.getSelectedRow(), 0).toString();
	        }
	    });
		//text fields to populate with text I will use a lot in this project
		jobidField = new JTextField();
		jobidField.setBounds(144, 29, 86, 20);
		contentPane.add(jobidField);
		jobidField.setColumns(10);
		
		customeridField = new JTextField();
		customeridField.setBounds(539, 29, 86, 20);
		contentPane.add(customeridField);
		customeridField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("job id search");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(40, 32, 94, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("customer id search");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(415, 32, 114, 14);
		contentPane.add(lblNewLabel_1);
		
		comboBox = new JComboBox();
		comboBox.setBackground(new Color(192, 192, 192));
		comboBox.setBounds(944, 63, 80, 22);
		contentPane.add(comboBox);

		//button that searches the table for a specific column and filter by job_id
		JButton btnNewButton = new JButton("search job_id");
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				so it takes the text from the field and uses it for the search in the query named
				name and populates the table with all jobs with the job id name
				 */
				connection = sqlConnection.getConnection();
				try {
					String name = jobidField.getText();
					String query = "SELECT * FROM job WHERE `job_id` = '" + name + "'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton.setBounds(240, 28, 124, 23);
		contentPane.add(btnNewButton);

		//button that searches by customer_ID like the one above but this way its for the customer_id instead of job_id if you want to filter for specific customer
		JButton btnNewButton_1 = new JButton("search cutomer_id");
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String name = customeridField.getText();
					String query = "SELECT * FROM job WHERE `customer_id` = '" +name + "'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_1.setBounds(635, 28, 140, 23);
		contentPane.add(btnNewButton_1);
		// this is a button that just shows all of the jobs in the DB
		alljobs = new JButton("All jobs");
		alljobs.setForeground(Color.BLUE);
		alljobs.setBackground(new Color(192, 192, 192));
		alljobs.addActionListener(new ActionListener() {
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
		alljobs.setBounds(686, 327, 89, 23);
		contentPane.add(alljobs);
		// this button means that you are done adding tasks to a specific job and then it takes you to the menu frame again
		btnNewButton_2 = new JButton("finish");
		btnNewButton_2.setForeground(Color.BLUE);
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menuFrame = new Menu();
				menuFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(10, 327, 89, 23);
		contentPane.add(btnNewButton_2);
		//this button adds tasks to a specific job
		btnNewButton_3 = new JButton("Add Task");
		btnNewButton_3.setForeground(Color.BLUE);
		btnNewButton_3.setBackground(new Color(192, 192, 192));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					// getting the specific columns from specific row and stores them to use later
					DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
					String tprice = tblModel.getValueAt(table.getSelectedRow(), 7).toString();
		        	float Price = Float.parseFloat(tprice);
					String myString = comboBox.getSelectedItem().toString();
					int taskID = Integer.parseInt(myString);
					int tJobId = (int) tblModel.getValueAt(table.getSelectedRow(), 0);
		        	
		        	String No = tblModel.getValueAt(table.getSelectedRow(), 8).toString();
		        	int number = Integer.parseInt(No);
		        	//these are if else statment that give add a price due to the task you add
					if(taskID == 1) {
						Price += (19*number);
					}else if(taskID == 2) {
						Price += (49.5*number);
					}else if(taskID == 3) {
						Price += (20*number);
					}else if(taskID == 4) {
						Price += (80*number);
					}else if(taskID == 5) {
						Price += (110.30*number);
					}else if(taskID == 6) {
						Price += (8.30*number);
					}else if(taskID == 7) {
						Price += (55.50*number);
					}
					/* adds the task and status into the table task_job and another that just updates
					the job price to give a total price at the end
					 */
					String query = "INSERT INTO `task_job`(`job_id`, `task_id`, `status`) VALUES (?,?,?)";
					String query2 = "UPDATE job SET Price = '" + Price + "' WHERE job_id = '" + tJobId +"'";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setInt(1, tJobId);
					pst.setInt(2, taskID);
					pst.setString(3, status);
					PreparedStatement pst2 = connection.prepareStatement(query2);
					pst.executeUpdate();
					pst2.executeUpdate();
					addTasks thisFrame = new addTasks();
					thisFrame.setVisible(true);
					dispose();
					JOptionPane.showMessageDialog(null, "task added");
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_3.setBounds(935, 327, 89, 23);
		contentPane.add(btnNewButton_3);
		Fillcombo();
	}
}
