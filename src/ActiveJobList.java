
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ActiveJobList extends JFrame {

	private JPanel contentPane;
	Connection connection = null;
	private JTable table;
	public String Status;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActiveJobList frame = new ActiveJobList();
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
	public ActiveJobList() {
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2-getWidth()/2, size.height/2 - getHeight()/2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Active Jobs List");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 664, 14);
		contentPane.add(lblNewLabel);
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
		
		//creating a button
		JButton btnNewButton_1 = new JButton("Update Job Status");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
					String JobID = tblModel.getValueAt(table.getSelectedRow(), 0).toString();
					String Status = "Complete";
					String sql = "UPDATE `job` SET `status` = '" + Status + "' WHERE `job_id` = '" + JobID +"'";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Updated");
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_1.setBounds(953, 327, 121, 23);
		contentPane.add(btnNewButton_1);
		//creating a button
		JButton RefreshJobs = new JButton("RefreshJobs");
		RefreshJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String query = "SELECT * FROM job WHERE status = 'NULL'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		RefreshJobs.setBounds(166, 327, 103, 23);
		contentPane.add(RefreshJobs);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 832, 261);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"job_id", "customer_id", "payment_id", "date_created", "deadline", "urgency", "status", "special_instructions"
				}
			));
		//creating a button
		JButton btnNewButton_2 = new JButton("New Job");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerSearch csFrame = new CustomerSearch();
				csFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(854, 327, 89, 23);
		contentPane.add(btnNewButton_2);
		
		
	}
}
