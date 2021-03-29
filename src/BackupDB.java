import java.io.File;
import java.security.CodeSource;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class BackupDB {
	public void backup() {
		//I watched a youtube video to understand how to do this code here
		try {
			
	        CodeSource codeSource = BackupDB.class.getProtectionDomain().getCodeSource();
	        File jarFile = new File(codeSource.getLocation().toURI().getPath());
	        String jarDir = jarFile.getParentFile().getPath();
	        
	        
	        //database name and username but because I am using XAMPP there is no password and the username is just root
	        String dbName = "bapersdb";
	        String dbUser = "root";

	        String folderPath = jarDir + "\\backup";
	        //this created the backup folder if it does not exist already so I would usually remove this but for source code reasons I am keeping it here
	        File f1 = new File(folderPath);
	        f1.mkdir();
	        //adding the date to the save path to be able to recognise when the save was made
	        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	        //creating the save path
	        String savePath = "\"" + jarDir + "\\backup\\" + "backup_" + date +".sql\"";
	        //this executes the command so that it backs up the DB
	        String executeCmd = "C:/xampp/mysql/bin/mysqldump -u" + dbUser + " --database " + dbName + " -r " + savePath;

	        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
	        int processComplete = runtimeProcess.waitFor();
	        //this just lets me know if the DB has backed up or not
	        if (processComplete == 0) {
	        	JOptionPane.showMessageDialog(null, "Backup Complete");
	        } else {
	            JOptionPane.showMessageDialog(null, "Backup Failure");
	        }
		}catch(Exception E) {
			E.printStackTrace();
		}
	}
}
