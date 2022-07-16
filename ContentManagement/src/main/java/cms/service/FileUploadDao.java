package cms.service;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cms.DbConnection;

public class FileUploadDao {

	private static String location = "F:Zoho\\Task One\\Data";

	public int uploadFile(String firstName, String fileName, InputStream file, String fileType, String location,
			long size) {
		int row = 0;

		try {

			Connection connection = DbConnection.getConnection();
			String sql = "INSERT INTO files (first_name, file_name, type, location, size) val ues (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, fileName);
			preparedStatement.setString(3, fileType);
			preparedStatement.setString(4, location);
			preparedStatement.setLong(5, size);
			row = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}

		return row;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	public static String getLocation() {
		return location;
	}

	public static void setLocation(String location) {
		FileUploadDao.location = location;
	}
}