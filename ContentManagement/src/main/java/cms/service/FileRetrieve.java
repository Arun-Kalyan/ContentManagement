package cms.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cms.DbConnection;
import cms.model.FileDetails;


public class FileRetrieve {
	
	Statement statement;
	ResultSet resultSet;
	
	public int getPagesCount()
	{
		int temptotal = 0;
		int temppages = 0;
		String countsql = "select count(*) from files;";
		int count=0;
		try {
			Connection connection = DbConnection.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(countsql);
			if (resultSet.next())
				count = resultSet.getInt(1);	
		}
		
		catch(Exception ex) {
			System.out.println(ex);
		}
		System.out.println("records:" + temptotal);
		return count;
	}
	
		public static void main(String[] args)
		{
	
		List<FileDetails> list = new ArrayList<>();
		List<FileDetails> filtered = new ArrayList<>();
		Set<String> fileTypes = new HashSet<>();
	
		try
		{

			Statement statement= null;
			ResultSet resultSet = null;
			Connection connection = DbConnection.getConnection();

			String sql = "select * from files order by id desc";

			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				fileTypes.add(resultSet.getString("type"));
				int serial = resultSet.getInt("id");
				String firstName = resultSet.getString("first_name");
				String fileName = resultSet.getString("file_name");
				String fileType = resultSet.getString("type");
				String locationValue = resultSet.getString("location");
				long fileSize = resultSet.getLong("size");
				Timestamp time = resultSet.getTimestamp("time");
				FileDetails f = new FileDetails(serial, firstName, fileName, fileType, locationValue, fileSize, time);
				filtered.add(f);
			}
			filtered = list;
			connection.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		

}}
