package cms.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import cms.service.FileUploadDao;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FileUploadDao fileUploadDao;

	@Override
	public void init() {
		fileUploadDao = new FileUploadDao();
	}

	public static String getFileExtension(String fileName) {
		if (fileName == null) {
			throw new IllegalArgumentException("fileName must not be null!");
		}

		String extension = "";

		int index = fileName.lastIndexOf('.');
		if (index > 0) {
			extension = fileName.substring(index + 1);
		}

		return extension;
	}

	private String getFileName(Part p) {
		String header = p.getHeader("content-disposition");
		System.out.println("Header is: " + header);
		String filename = header.substring(header.indexOf("filename=\"")).split("\"")[1]; // getting filename
		return filename;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// gets values of text fields
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("fileName");
		String courses = request.getParameter("course");
		String path = "F:\\Zoho\\Task One\\";
		path += courses + "\\";
		System.out.println("Course is: " + courses);
		System.out.println("Path is:" + path);
		File f1 = new File(path);
		boolean bool2 = f1.mkdirs();
		if (bool2) {
			System.out.println("Folder is created successfully");
		} else {
			System.out.println("Error Found!");
		}

		String fileType = null;
		String fileName = null;

		String location = path;
		long size = 0;

		InputStream inputStream = null; // input stream of the upload file

		String message = null;
		String contentType = request.getContentType();
		if ((contentType != null) && contentType.startsWith("multipart/form-data")) {
			// obtains the upload file part in this multipart request
			Part filePart = request.getPart("photo");
			System.out.println(filePart);

			if (filePart != null) {
				// prints out some information for debugging
				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());
				fileName = filePart.getSubmittedFileName();
				System.out.println("File Name:" + fileName);
				inputStream = filePart.getInputStream();
				fileType = getFileExtension(fileName);
				size = filePart.getSize();
				size /= 1000;
				System.out.println("File Extension:" + fileType);

				// writing to local storage

				for (Part part : request.getParts()) {
					part.write(location + fileName);
				}

				System.out.println("File Uploaded Successfully!");

			}

			else {
				System.out.println("No file!");
			}

			// sends the statement to the database server
			int row = fileUploadDao.uploadFile(firstName, fileName, inputStream, fileType, location, size);
			if (row > 0) {
				message = "File uploaded and saved into database";
			}

			// sets the message in request scope
			request.setAttribute("Message", message);

			// forwards to the message page
			getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);

		}
	}

}
