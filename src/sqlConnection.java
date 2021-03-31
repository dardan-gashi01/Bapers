import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class sqlConnection {
	static Connection connection = null;
	//creating a connection with the database so I can call it easier in the other classes instead of having to repeat it 
	public static Connection getConnection() {
		try {
			//getting the connection with the DB and using the password "" and username root 
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bapersdb", "root", "");
	 		}
		//this is the exception for if the connection fails it will show me the error to debug 
		catch(Exception e){
			JOptionPane.showMessageDialog(null,e);
		}
		return connection;
	}
}
