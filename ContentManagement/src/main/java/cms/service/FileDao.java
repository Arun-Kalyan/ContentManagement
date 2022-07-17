package cms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cms.DbConnection;
import cms.model.FileDetails;

public class FileDao {

	private static int noOfRecords;

	public FileDao() {
	}

	public static List<FileDetails> viewAllRecords(int start, int total) {
	
		List<FileDetails> list = new ArrayList<FileDetails>();
		FileDetails fileDetail = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DbConnection.getConnection();
			String sql = "select * from files limit " + (start - 1) + "," + total;
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				fileDetail = new FileDetails();
				fileDetail.setId(rs.getInt(1));
				fileDetail.setFirst_name(rs.getString(2));
				fileDetail.setFile_name(rs.getString(3));
				fileDetail.setType(rs.getString(5));
				fileDetail.setLocation(rs.getString(6));
				fileDetail.setSize(rs.getLong(7));
				fileDetail.setTime(rs.getTimestamp(8));
				fileDetail.setCourses(rs.getString(9));

				list.add(fileDetail);
			}

			rs.close();
			rs = stmt.executeQuery("SELECT FOUND_ROWS()");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return list;
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}

	public static List<FileDetails> getRecords(List<FileDetails> filtered, int start, int total) {
		List<FileDetails> list = new ArrayList<FileDetails>();
		FileDetails fileDetail = null;
		int temp = start - 1;
		System.out.println("Starting: " + temp + "Ending: " + total);
		total = Math.min(filtered.size(), temp + total);
		System.out.println("Starting: " + temp + "Ending: " + total);
		for (int i = temp; i < filtered.size(); i++) {
			if (temp <= total)
				list.add(filtered.get(i));
			temp++;
		}
		return list;
	}

}
