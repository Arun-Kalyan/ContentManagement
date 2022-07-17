package cms.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cms.model.FileDetails;
import cms.service.FilterService;

@WebServlet("/filterServlet")
public class FilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FilterService filterService = new FilterService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FilterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pagecheck = request.getParameter("page");
		String page = pagecheck == null ? "1" : pagecheck;
		System.out.println("Servlet called............." + page);
		int minimum = 0, maximum = 0;
		String s = request.getParameter("files");
		String se = request.getParameter("search");
		String sli = request.getParameter("slide");
		String g = request.getParameter("grade");
		
		int records = 0;
		int pages = 0;
		int currentPage = 1;
		if (s != null)
			System.out.println("file type: " + s);
		if (se != null)
			System.out.println("file search: " + se);
		System.out.println("slide value:" + sli);
		if (g != null && g.equals("Between")) {
			minimum = Integer.parseInt(request.getParameter("min"));
			maximum = Integer.parseInt(request.getParameter("max"));
		}

		int valuet = 0;
		try {
			valuet = Integer.parseInt(request.getParameter("single"));
			System.out.println(minimum);
		} catch (NumberFormatException nfe) {
			valuet = 0;
			System.out.println("NumberFormat Exception: invalid input string");
		}

		int pageid = 1;
		String temp = "select * from files where ";
		if (s != null && !s.equals("all")) {
			temp += "type='" + s + "' and ";
			pageid = 1;
		}
		if (se != null && !se.equals("")) {
			temp += "file_name like '" + se + "%' and ";
			pageid = 1;
		}
		if (g != null && !g.equals("None") && !g.equals("Between")) {
			temp += " size" + g + valuet;
			pageid = 1;
		}
		if (g != null && g.equals("Between") && maximum != 0) {
			temp += " size between " + minimum + " and " + maximum;
			pageid = 1;
		}
		if (temp.endsWith("where ")) {
			temp = "select * from files";
			pageid = 1;
		}
		if (temp.endsWith("and ")) {
			temp = temp.substring(0, temp.length() - 4);
			pageid = 1;
		}
		System.out.println("query: " + temp);
		int checktotal = 0;
		temp += " order by id desc";
		System.out.println("query: " + temp);
		List<FileDetails> filtered = filterService.filter(temp);
		System.out.println(filtered);
		List<String> fileTypes = filterService.getFileTypes();
		Map<String, String> sizeTypes = filterService.getSizeFilter();
		List<String> courses = filterService.getCourses();
		System.out.println(fileTypes);

		request.setAttribute("filesList", filtered);
		request.setAttribute("typesList", fileTypes);
		request.setAttribute("currentPage", page);
		request.setAttribute("message", "hello");
		request.setAttribute("selectedSizeType", g);
		request.setAttribute("sizeTypeList", sizeTypes);
		request.setAttribute("courses", courses);
		if(s==null) s="all";
		request.setAttribute("selectedFileType", s);

		RequestDispatcher view = request.getRequestDispatcher("display.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
