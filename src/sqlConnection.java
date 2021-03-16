import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class sqlConnection {
	static Connection connection = null;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bapersdb", "root", "");
			JOptionPane.showMessageDialog(null, "Connection Successful");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,e);
		}
		return connection;
	}
}
