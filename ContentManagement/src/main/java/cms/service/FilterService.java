package cms.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cms.DbConnection;
import cms.model.FileDetails;

public class FilterService {

	private static String location = "F:Zoho\\Task One\\Data";

	public List<FileDetails> filter(String query) {
		
		List<FileDetails> filtered = new ArrayList<>();
		List<FileDetails> list = new ArrayList<>();
		ResultSet resultSet = null;
		int total =0;
		try {
			
			Connection connection = DbConnection.getConnection();
			Statement statement = connection.createStatement();
			System.out.println("received query: "+query);
			resultSet = statement.executeQuery(query);
			System.out.println(resultSet);
			while (resultSet.next()) {
				int serial = resultSet.getInt("id");
				String firstName = resultSet.getString("first_name");
				String fileName = resultSet.getString("file_name");
				String fileType = resultSet.getString("type");
				String locationValue = resultSet.getString("location");
				long fileSize = resultSet.getLong("size");
				Timestamp time = resultSet.getTimestamp("time");
				String courses = resultSet.getString("courses");
				FileDetails f = new FileDetails(serial, firstName, fileName, fileType, locationValue, fileSize, time, courses);
				list.add(f);
				total+=1;
			}
			filtered = list;
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return filtered;
	}

	public List<String> getFileTypes() {
		List<String> fileTypes = new ArrayList<>();
		ResultSet resultSet = null;
		try {
			String query = "select distinct(type) as type from files";
			Connection connection = DbConnection.getConnection();
			Statement statement = connection.createStatement();
			System.out.println("received query: "+query);
			resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) {
				fileTypes.add(resultSet.getString("type"));
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("fileTypes1:" +fileTypes);
		return fileTypes;
	}
	
	public List<String> getCourses() {
		List<String> courses = new ArrayList<>();
		ResultSet resultSet = null;
		try {
			String query = "select distinct(courses) as courses from files";
			Connection connection = DbConnection.getConnection();
			Statement statement = connection.createStatement();
			System.out.println("received query: "+query);
			resultSet = statement.executeQuery(query);
			
			while (resultSet.next()) {
				courses.add(resultSet.getString("courses"));
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;
	}
	
	public Map<String,String> getSizeFilter() {
		Map<String,String> sizeTypes = new HashMap<>();
		sizeTypes.put("Lesser", "<=");
		sizeTypes.put("Greater", "greater");
		sizeTypes.put("Equals", "=");
		sizeTypes.put("Between", "Between");

		return sizeTypes;
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
	
}