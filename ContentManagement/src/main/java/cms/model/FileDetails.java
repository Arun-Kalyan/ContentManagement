package cms.model;

import java.sql.Timestamp;

public class FileDetails {
	private int id;

	public FileDetails() {
		super();
	}

	private String first_name;
	private String file_name;
	private String type;
	private String courses;
	private String location;
	private long size;
	private Timestamp time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getType() {
		return type;
	}
	

	public String getCourses() {
		return courses;
	}

	public void setCourses(String courses) {
		this.courses = courses;
	}

	public FileDetails(int id, String first_name, String file_name, String type, String location, long size,
			Timestamp time, String courses) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.file_name = file_name;
		this.type = type;
		this.location = location;
		this.size = size;
		this.time = time;
		this.courses = courses;
	}
	
	public FileDetails(int id, String first_name, String file_name, String type, String location, long size,
			Timestamp time) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.file_name = file_name;
		this.type = type;
		this.location = location;
		this.size = size;
		this.time = time;
	}
	

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
