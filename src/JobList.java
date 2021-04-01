import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class JobList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JobList frame = new JobList();
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
	public JobList() {
		
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2-getWidth()/2, size.height/2 - getHeight()/2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Job List");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 762, 14);
		contentPane.add(lblNewLabel);
		//creating a button that takes you to the menu
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 762, 276);
		contentPane.add(scrollPane);
		
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"job_id", "customer_id", "date_created", "deadline", "urgency", "status", "special_instructions"
				}
			));
		//creating a button that loads the data into the table from the DB for the jobs
		JButton btnNewButton_2 = new JButton("Refresh");
		btnNewButton_2.setForeground(Color.BLUE);
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.addActionListener(new ActionListener() {
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
		btnNewButton_2.setBounds(683, 327, 89, 23);
		contentPane.add(btnNewButton_2);
		//allows me to delete jobs that are not needed anymore or that may be errors from
		//clicking on the specific row to delete
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
		        	String tjobid = tblModel.getValueAt(table.getSelectedRow(), 0).toString();
					String sql = "DELETE FROM `job` WHERE `job_id` = '" + tjobid + "'";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Deleted");
					JobList thisFrame = new JobList();
					thisFrame.setVisible(true);
					dispose();
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_1.setBounds(326, 327, 89, 23);
		contentPane.add(btnNewButton_1);
		
	}
}
