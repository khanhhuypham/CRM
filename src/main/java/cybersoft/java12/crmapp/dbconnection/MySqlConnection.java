package cybersoft.java12.crmapp.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
	private static Connection connection = null;
	private static String url = "jdbc:mysql://localhost:3306/crm";
	private static String userName = "root";
	private static String password = "1234";
	
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, userName, password);
			System.out.print("Connect successfully");
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("Driver could not found.");
		} catch (SQLException e) {
			System.out.println("Database connection could not establish.");
			e.printStackTrace();
		}
		return connection;
	}	
}
