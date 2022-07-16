package cms;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/filemanagement", "root", "Arunkalyan@9");
		} catch (Exception e) {
			System.out.println("Connection error  :" + e.getMessage());
		}
		return con;
	}
}