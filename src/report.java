import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.swing.JOptionPane;

public class report {
	Connection conn = null;
    Statement s = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
	
    public void CustomerReport(String custID, String StartDate, String EndDate) throws IOException {
    	try {
    		File file1 = new File("Customer"+custID+".txt");
    		FileWriter fw = new FileWriter(file1);
    		PrintWriter pw = new PrintWriter(fw);
    		 // connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bapersdb", "root", "");
            System.out.println("Connection established.");

            // set transaction level
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            // individual customer report
            //prepare statement
            String sqlIndividualCustomerReport = "SELECT " +
                    "  customer_id AS 'Customer', " +
                    "  tj.job_id AS 'Job', " +
                    "  t.task_id AS 'Task', " +
                    "  t.price AS 'Price', " +
                    "  department AS 'Department', " +
                    "  task_date AS 'Task date', " +
                    "  start_time AS 'Start time', " +
                    "  time_taken AS 'Time taken (min)', " +
                    "  completed_by AS 'Completed by' " +
                    "FROM task t, task_job tj, job j " +
                    "WHERE t.task_id = tj.task_id " +
                    "AND j.job_id = tj.job_id " +
                    "AND j.customer_id = ? " +
                    "AND (tj.task_date BETWEEN ? AND ?) " +
                    "ORDER BY tj.job_id, start_time";
            ps = conn.prepareStatement(sqlIndividualCustomerReport);
            // set the parameters
            ps.setString(1, custID);
            ps.setString(2, StartDate);
            ps.setString(3, EndDate);
            // execute SQL query
            rs = ps.executeQuery();
            // display the result set
            while(rs.next()) {
                // retrieve by column name
                String id1 = rs.getString("Customer");
                String job1 = rs.getString("Job");
                String task1 = rs.getString("Task");
                String price1 = rs.getString("Price");
                String department1 = rs.getString("Department");
                String task_date1 = rs.getString("Task date");
                String start_time1 = rs.getString("Start time");
                String time_taken1 = rs.getString("Time taken (min)");
                String completed_by1 = rs.getString("Completed by");
                // display values
                pw.write("\nCustomer: " + id1 + "\n");
                pw.write("Job: " + job1 + "\n");
                pw.write("Task: " + task1 + "\n");
                pw.write("Price: " + price1 + "\n");
                pw.write("Department: " + department1 + "\n");
                pw.write("Task date: " + task_date1 + "\n");
                pw.write("Start time: " + start_time1 + "\n");
                pw.write("Time taken (min): " + time_taken1 + "\n");
                pw.write("Completed by: " + completed_by1 + "\n");
                pw.close();
                System.out.println("\nCustomer: " + id1);
                System.out.println("Job: " + job1);
                System.out.println("Task: " + task1);
                System.out.println("Price: " + price1);
                System.out.println("Department: " + department1);
                System.out.println("Task date: " + task_date1);
                System.out.println("Start time: " + start_time1);
                System.out.println("Time taken (min): " + time_taken1);
                System.out.println("Completed by: " + completed_by1);
            }
    	}catch (SQLException se) {
            // handle errors for JDBC
            se.printStackTrace();
            //JOptionPane.showMessageDialog(null, se);
        }
    }
    
    public void individualReport(String Name, String Date) throws IOException {
    	try {
    		File file1 = new File(Name + ".txt");
    		FileWriter fw = new FileWriter(file1);
    		PrintWriter pw = new PrintWriter(fw);
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bapersdb", "root", "");
    		// individual performance report
            // prepare statement
            String sqlIndividualPerformanceReport1 = "CREATE OR REPLACE VIEW view1 AS " +
                    "SELECT completed_by, t.task_id, department, task_date, start_time, time_taken " +
                    "FROM task t, task_job tj " +
                    "WHERE t.task_id = tj.task_id " +
                    "AND (tj.completed_by = ?) " +
                    "AND tj.task_date = ? " +
                    "ORDER BY completed_by;";
            ps = conn.prepareStatement(sqlIndividualPerformanceReport1);
            // set the parameters
            ps.setString(1, Name);
            ps.setString(2, Date);
            // execute SQL query
            ps.executeUpdate();

            // create statement
            String sqlIndividualPerformanceReport2 = "CREATE OR REPLACE VIEW view2 AS " +
                    "SELECT completed_by, SEC_TO_TIME(SUM(time_taken) * 60) AS total_time " +
                    "FROM view1 " +
                    "GROUP BY completed_by;";
            s = conn.createStatement();
            // execute SQL query
            s.executeUpdate(sqlIndividualPerformanceReport2);

            // create statement
            String sqlIndividualPerformanceReport3 = "CREATE OR REPLACE VIEW view3 AS " +
                    "SELECT task_date, SEC_TO_TIME(SUM(time_taken) * 60) AS total_effort " +
                    "FROM view1 " +
                    "ORDER BY task_date;";
            s = conn.createStatement();
            // execute SQL query
            s.executeUpdate(sqlIndividualPerformanceReport3);

            //prepare statement
            String sqlIndividualPerformanceReport = "SELECT " +
                    "  ir.completed_by AS Name, " +
                    "  task_id AS 'Task IDs', " +
                    "  department AS 'Department', " +
                    "  ir.task_date AS 'Task date', " +
                    "  start_time AS 'Start time', " +
                    "  time_taken AS 'Time taken (min)', " +
                    "  tr.total_time AS 'Total time'," +
                    "  te.total_effort AS 'Total effort' " +
                    "FROM view1 ir\n" +
                    "JOIN view2 tr ON ir.completed_by = tr.completed_by\n" +
                    "JOIN view3 te ON ir.task_date = te.task_date\n" +
                    "ORDER BY Name, 'Start time';";
            s = conn.createStatement();
            rs = s.executeQuery(sqlIndividualPerformanceReport);
            // display the result set
            while(rs.next()) {
                // retrieve by column name
                String completed_by2 = rs.getString("Name");
                String task_id2 = rs.getString("Task IDs");
                String department2 = rs.getString("Department");
                String task_date2 = rs.getString("Task date");
                String start_time2 = rs.getString("Start time");
                String time_taken2 = rs.getString("Time taken (min)");
                String total_time2 = rs.getString("Total time");
                String total_effort2 = rs.getString("Total effort");
                // display values
                pw.write("\nName: " + completed_by2 + "\n");
                pw.write("Task IDs: " + task_id2 + "\n");
                pw.write("Department: " + department2 + "\n");
                pw.write("Task date: " + task_date2 + "\n");
                pw.write("Start time: " + start_time2 + "\n");
                pw.write("Time taken (min): " + time_taken2 + "\n");
                pw.write("Total time: " + total_time2 + "\n");
                pw.write("Total effort: " + total_effort2 + "\n");
                pw.close();
                System.out.println("\nName: " + completed_by2);
                System.out.println("Task IDs: " + task_id2);
                System.out.println("Department: " + department2);
                System.out.println("Task date: " + task_date2);
                System.out.println("Start time: " + start_time2);
                System.out.println("Time taken (min): " + time_taken2);
                System.out.println("Total time: " + total_time2);
                System.out.println("Total effort: " + total_effort2);
            }

    	}catch (SQLException se) {
            // handle errors for JDBC
            se.printStackTrace();
            //JOptionPane.showMessageDialog(null, se);
        }
    }
    
    public void SummaryReport(String startTime, String endTime, String startDate, String endDate) throws IOException {
    	try {
    		
    		File file1 = new File("\\reports\\Summary.txt");
    		FileWriter fw = new FileWriter(file1);
    		PrintWriter pw = new PrintWriter(fw);
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bapersdb", "root", "");
    		 // summary performance report
            //prepare statement
            String sqlSummaryPerformanceReport = "SELECT " +
                    "task_date AS 'Date', " +
                    "department AS 'Department', " +
                    "SEC_TO_TIME(SUM(time_taken) * 60) AS 'Time taken' " +
                    "FROM task t, task_job tj " +
                    "WHERE t.task_id = tj.task_id " +
                    "AND (tj.start_time BETWEEN ? AND ?) " +
                    "AND (tj.task_date BETWEEN ? AND ?) " +
                    "GROUP BY task_date, department, time_taken;";
            ps = conn.prepareStatement(sqlSummaryPerformanceReport);
            // set the parameters
            ps.setString(1, startTime);
            ps.setString(2, endTime);
            ps.setString(3, startDate);
            ps.setString(4, endDate);
            // execute SQL query
            rs = ps.executeQuery();
            // display the result set
            while(rs.next()) {
                // retrieve by column name
                String date3 = rs.getString("Date");
                String department3 = rs.getString("Department");
                String time_taken3 = rs.getString("Time taken");
                // display values
                pw.write("\nDate: " + date3 + "\n");
                pw.write("Department: " + department3 + "\n");
                pw.write("Time taken: " + time_taken3 + "\n");
                pw.close();
                System.out.println("\nDate: " + date3 + "\n");
                System.out.println("Department: " + department3 + "\n");
                System.out.println("Time taken: " + time_taken3 + "\n");
            }
    	}catch (SQLException se) {
            // handle errors for JDBC
            se.printStackTrace();
            //JOptionPane.showMessageDialog(null, se);
        }
    }

    

}

