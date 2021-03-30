import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

public class CreateDeleteTask extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection connection = null;
	private JTextField PriceField;
	private JTextField Department;
	private JTextField DurationField;

	/**
	 * Launch the application.
	 */
	//this is for test purposes 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateDeleteTask frame = new CreateDeleteTask();
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
	public CreateDeleteTask() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//creating a button
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
		//creating a button
		JButton btnNewButton_1 = new JButton("Create new Task");
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
			try {
				String query = "INSERT INTO `task`(`price`, `department`, `duration`) VALUES (?,?,?)";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1,PriceField.getText());
				pst.setString(2, Department.getText());
				pst.setString(3, DurationField.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Customer registered");
				CreateDeleteTask thisFrame = new CreateDeleteTask();
				thisFrame.setVisible(true);
				dispose();
				}
			catch(Exception E) {
				JOptionPane.showMessageDialog(null,E);
			}
			}
		});
		btnNewButton_1.setBounds(275, 327, 151, 23);
		contentPane.add(btnNewButton_1);
		//creating a button
		JButton btnNewButton_2 = new JButton("Delete Task");
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
		        	String tjobid = tblModel.getValueAt(table.getSelectedRow(), 0).toString();
					String sql = "DELETE FROM `task` WHERE `task_id` = '" + tjobid + "'";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Deleted");
					CreateDeleteTask thisFrame = new CreateDeleteTask();
					thisFrame.setVisible(true);
					dispose();
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_2.setBounds(853, 327, 106, 23);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(375, 30, 584, 286);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"task_id", "price", "department", "duration"
				}
			));
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
				String TPrice = tblModel.getValueAt(table.getSelectedRow(), 1).toString();
				String TDepartment= tblModel.getValueAt(table.getSelectedRow(), 2).toString();
				String TDuration= tblModel.getValueAt(table.getSelectedRow(), 3).toString();
				PriceField.setText(TPrice);
				Department.setText(TDepartment);
				DurationField.setText(TDuration);
	        }
	    });
		//creating a button
		JButton btnNewButton_3 = new JButton("Refresh");
		btnNewButton_3.setBackground(new Color(192, 192, 192));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String query = "SELECT * FROM task";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_3.setBounds(436, 327, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Price");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(41, 60, 58, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Department");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(41, 109, 78, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Duration");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(41, 162, 78, 14);
		contentPane.add(lblNewLabel_2);
		
		PriceField = new JTextField();
		PriceField.setBackground(new Color(192, 192, 192));
		PriceField.setBounds(146, 57, 153, 20);
		contentPane.add(PriceField);
		PriceField.setColumns(10);
		
		Department = new JTextField();
		Department.setBackground(new Color(192, 192, 192));
		Department.setBounds(146, 106, 153, 20);
		contentPane.add(Department);
		Department.setColumns(10);
		
		DurationField = new JTextField();
		DurationField.setBackground(new Color(192, 192, 192));
		DurationField.setBounds(146, 159, 153, 20);
		contentPane.add(DurationField);
		DurationField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(109, 11, 46, 14);
		contentPane.add(lblNewLabel_3);
		//creating a button
		JButton btnNewButton_4 = new JButton("Update Task");
		btnNewButton_4.setBackground(new Color(192, 192, 192));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
		        	String tJobId = tblModel.getValueAt(table.getSelectedRow(), 0).toString();
					String Price = PriceField.getText();
					String department = Department.getText();
					String Duration = DurationField.getText();
					String sql = "UPDATE `task` SET `price`= '" + Price + "' ,`department`= '" + department + "', `duration` = '" + Duration + "' WHERE `task_id` = '" + tJobId + "'";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Updated");
					CreateDeleteTask thisFrame = new CreateDeleteTask();
					thisFrame.setVisible(true);
					dispose();
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_4.setBounds(119, 327, 118, 23);
		contentPane.add(btnNewButton_4);
	}
}
