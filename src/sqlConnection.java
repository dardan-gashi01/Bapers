import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class sqlConnection {
	static Connection conn = null;
	public static Connection dbConnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bapersdb", "root", "");
			JOptionPane.showMessageDialog(null, "Connection Successful");
			return conn;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,e);
			return null;
		}
	}
}
