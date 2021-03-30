import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class UpdateTask extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField JobIDField;
	String job_id;
	//String name;
	Connection connection = null;
	String newStatus = "Complete";
	private JTextField textField;
	int timeTaken;

	/**
	 * Launch the application.
	 */
	//this is for test purposes 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateTask frame = new UpdateTask();
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
	public UpdateTask() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(new Date());
	    Calendar current = Calendar.getInstance(); // creates calendar
	    current.setTime(new Date());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 57, 799, 236);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"job_id","customer_id","payment_id","urgency","status","special_instructions", "date_created", "deadline"
				}
			));
		
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
				job_id = tblModel.getValueAt(table.getSelectedRow(), 0).toString();
				
	        }
	    });
		
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
		
		JButton btnNewButton_1 = new JButton("Start Task");
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				
				try {
					
					DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
		        	String tJobId = tblModel.getValueAt(table.getSelectedRow(), 1).toString();
		        	int JobID = Integer.parseInt(tJobId);
		        	String tTaskId = tblModel.getValueAt(table.getSelectedRow(), 2).toString();
		        	int TaskID = Integer.parseInt(tTaskId);
		        	
		        	switch(TaskID) {
		        	case 1:
		        		cal.add(Calendar.MINUTE, 120);
		        		timeTaken = 120;
		        		break;
		        	case 2:
		        		cal.add(Calendar.MINUTE, 60);
		        		timeTaken = 60;
		        		break;
		        	case 3:
		        		cal.add(Calendar.MINUTE, 30);
		        		timeTaken = 30;
		        		break;
		        	case 4:
		        		cal.add(Calendar.MINUTE, 90);
		        		timeTaken = 90;
		        		break;
		        	case 5:
		        		cal.add(Calendar.MINUTE, 180);
		        		timeTaken = 180;
		        		break;
		        	case 6:
		        		cal.add(Calendar.MINUTE, 75);
		        		timeTaken = 75;
		        		break;
		        	case 7:
		        		cal.add(Calendar.MINUTE, 45);
		        		timeTaken = 45;
		        		break;
		        	}
					String sql = "UPDATE task_job SET status = '"+ newStatus +"', start_time = '"+sdf2.format(current.getTime()) +"', completed_by = '" + textField.getText() +"', time_taken = '"+ timeTaken +"', task_date = '" + sdf3.format(current.getTime())+"' WHERE job_id = '"+ JobID +"' AND task_id = '"+ TaskID +"'";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.execute();
					UpdateTask thisFrame = new UpdateTask();
					thisFrame.setVisible(true);
					dispose();
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_1.setBounds(759, 327, 115, 23);
		contentPane.add(btnNewButton_1);
		
		JobIDField = new JTextField();
		JobIDField.setBackground(new Color(192, 192, 192));
		JobIDField.setBounds(135, 26, 86, 20);
		contentPane.add(JobIDField);
		JobIDField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("jobID search");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(30, 29, 86, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("Search");
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String name = JobIDField.getText();
					String query = "SELECT * FROM task_job WHERE `job_id` = '" + name + "'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_2.setBounds(242, 23, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("All jobs");
		btnNewButton_3.setBackground(new Color(192, 192, 192));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String query = "SELECT * FROM task_job ";
					
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_3.setBounds(567, 327, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("active tasks left");
		btnNewButton_4.setBackground(new Color(192, 192, 192));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String query = "SELECT * FROM task_job WHERE status =  'incomplete'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_4.setBounds(325, 327, 151, 23);
		contentPane.add(btnNewButton_4);
		
		textField = new JTextField();
		textField.setBackground(new Color(192, 192, 192));
		textField.setBounds(646, 26, 183, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Completed by");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(524, 29, 95, 14);
		contentPane.add(lblNewLabel_1);
		
		
	}
}
