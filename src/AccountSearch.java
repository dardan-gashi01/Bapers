import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class AccountSearch extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountSearch frame = new AccountSearch();
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
	public AccountSearch() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(217, 60, 106, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Role");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(217, 105, 89, 17);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(304, 67, 181, 17);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(304, 108, 181, 17);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Search Account");
		btnNewButton.setBounds(538, 109, 136, 20);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Select Account");
		btnNewButton_1.setBounds(538, 296, 136, 20);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Cancel");
		btnNewButton_2.setBounds(10, 327, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 143, 472, 173);
		contentPane.add(panel);
	}
}
