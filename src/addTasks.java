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
	private JLabel id_label;
	String job_id;
	String status = "incomplete";

	/**
	 * Launch the application.
	 */
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
		setBounds(100, 100, 900, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 60, 667, 249);
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
				//CustomerID = (int) tblModel.getValueAt(table.getSelectedRow(), 0);
				id_label.setText(job_id);
	        }
	    });
		
		jobidField = new JTextField();
		jobidField.setBounds(111, 29, 86, 20);
		contentPane.add(jobidField);
		jobidField.setColumns(10);
		
		customeridField = new JTextField();
		customeridField.setBounds(464, 29, 86, 20);
		contentPane.add(customeridField);
		customeridField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("job id search");
		lblNewLabel.setBounds(40, 32, 61, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("customer id search");
		lblNewLabel_1.setBounds(362, 35, 92, 14);
		contentPane.add(lblNewLabel_1);
		
		comboBox = new JComboBox();
		comboBox.setBounds(758, 85, 80, 22);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("search job_id");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String name = jobidField.getText();
					String query = "SELECT * FROM job WHERE `job_id` = '" +name + "'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton.setBounds(207, 26, 106, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("search cutomer_id");
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
		btnNewButton_1.setBounds(561, 28, 121, 23);
		contentPane.add(btnNewButton_1);
		
		alljobs = new JButton("All jobs");
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
		alljobs.setBounds(618, 327, 89, 23);
		contentPane.add(alljobs);
		
		btnNewButton_2 = new JButton("finish");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menuFrame = new Menu();
				menuFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(10, 327, 89, 23);
		contentPane.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Add Task");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String myString = comboBox.getSelectedItem().toString();
					int taskID = Integer.parseInt(myString);
					//Integer taskID = (Integer) comboBox.getSelectedItem();
					String query = "INSERT INTO `task_job`(`job_id`, `task_id`, `status`) VALUES (?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, job_id);
					pst.setInt(2, taskID);
					pst.setString(3, status);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "task added");
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_3.setBounds(785, 327, 89, 23);
		contentPane.add(btnNewButton_3);
		
		id_label = new JLabel();
		id_label.setBounds(775, 60, 46, 14);
		contentPane.add(id_label);
		Fillcombo();
	}
}
