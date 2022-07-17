package cms.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DownloadServlet extends HttpServlet {
  
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      response.setContentType("text/html");  
      			PrintWriter out = response.getWriter();
				String demo = request.getParameter("fileName");
				String demo2 = request.getParameter("courses");
				System.out.println("demo pls work: "+ demo);
				System.out.println("demo pls work part2: "+ demo2);
				String filename = demo;
				String filepath = "F:\\Zoho\\Task One\\";
				filepath+=demo2+"\\";
				System.out.println("file path is:"+filepath);
				response.setContentType("APPLICATION/OCTET-STREAM");   
				response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");
      
      //use inline if you want to view the content in browser, helpful for pdf file
      //response.setHeader("Content-Disposition","inline; filename=\"" + filename + "\"");
				FileInputStream fileInputStream = new FileInputStream(filepath + filename);    
				int i;   
				while ((i=fileInputStream.read()) != -1) {  
					out.write(i);   
				}   
				fileInputStream.close();   
				out.close();   
			}  
 
   
   }
