<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
  <%@page import="java.util.*"%>
  <%@page import="cms.model.FileDetails"%>
  <%@page import="cms.service.FileDao"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
  href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
  rel="stylesheet"
  integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
  crossorigin="anonymous">
<script type="text/javascript">
	function hideDiv(elem) {

		console.log(elem.value);
		console.log(typeof elem.value);
		if (elem.value.valueOf() == new String(">=").valueOf()) {
			hideDiv2(elem);
			return;
		}
		if (elem.value.valueOf() == new String("<=").valueOf()) {
			hideDiv2(elem);
			return;
		}
		if (elem.value.valueOf() == new String("=").valueOf()) {
			hideDiv2(elem);
			return;
		}

		if (elem.value != "Between") {
			document.getElementById('hideDiv').style.display = "none";
			document.getElementById('p1').style.display = "none";

		} else {
			document.getElementById('hideDiv').style.display = 'block';
			document.getElementById('p1').style.display = "block";

		}
	}

	function closeDiv() {
		document.getElementById('hideDiv').style.display = "none";
		document.getElementById('p1').style.display = "none";
	}

	function hideDiv2(elem) {
		console.log(elem.value);
		if (elem.value == "all" && elem.value == "Between") {
			document.getElementById('hideDiv2').style.display = "none";
			document.getElementById('p2').style.display = "none";

		} else {
			document.getElementById('hideDiv2').style.display = 'block';
			document.getElementById('p2').style.display = "block";

		}
	}

	function closeDiv2() {
		document.getElementById('hideDiv2').style.display = "none";
		document.getElementById('p2').style.display = "none";
	}
</script>
<style>
#users {
	font-family: Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#users td, #users th {
	border: 1px solid #ddd;
	padding: 8px;
}

#users tr:nth-child(even) {
	background-color: #f2f2f2;
}

#users tr:hover {
	background-color: #ddd;
}

#users th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #800000;
	color: white;
}

a {
	color: #800000;
}

.mb10 {
	margin-bottom: 10px;
}

.pd {
	padding: 20px;
}

.filter {
	margin-bottom: 10px;
	height: 100px;
	overflow-y: auto;
	overflow-x: hidden;
}

.demo {
	margin-top: 20px;
}

.sample {
	-webkit-appearance: none; /* Override default CSS styles */
	appearance: none;
	width: 100%; /* Full-width */
	height: 25px; /* Specified height */
	background: #d3d3d3; /* Grey background */
	outline: none; /* Remove outline */
	opacity: 0.7; /* Set transparency (for mouse-over effects on hover) */
	-webkit-transition: .2s; /* 0.2 seconds transition on hover */
	transition: opacity .2s;
	accent-color: #F55050;
}

.card {
	/* Add shadows to create the "card" effect */
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	transition: 0.3s;
	height: 40px;
}

/* On mouse-over, add a deeper shadow */
.card:hover {
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}

/* Add some padding inside the card container */
.container {
	padding: 5px 14px;
}

.prize {
	height: 280px;
	width: 500px;
	border: 1px solid #4CAF50;
	margin-top: 10px;
	position: fixed;
	top: 25%;
	display: none;
	left: 33%;
	background-color: white;
	z-index: 4;
}

.prize-div {
	height: 100%;
	width: 100%;
	position: fixed;
	top: 0;
	display: none;
	left: 0;
	background-color: rgba(0, 0, 0, 0.4);
	z-index: 3;
}

.first {
	margin: 10px;
}

.prize2 {
	height: 200px;
	width: 500px;
	border: 1px solid #4CAF50;
	margin-top: 10px;
	position: fixed;
	top: 25%;
	display: none;
	left: 33%;
	background-color: white;
	z-index: 4;
}

.prize-div2 {
	height: 100%;
	width: 100%;
	position: fixed;
	top: 0;
	display: none;
	left: 0;
	background-color: rgba(0, 0, 0, 0.4);
	z-index: 3;
}

.footer {
	margin: 10px;
}

.alignRight {
	float: left;
}

#navigation li a.current {
	color: #ffffff;
	background: #f1d74c;
}
</style>
</head>
<body>
  <br>
  <br>
  <center>
    <h1>File Explorer!</h1>
  </center>
  <input type="button" class="btn btn-danger" value="Home"
    onclick="window.location='Upload.jsp'">
  <div class="list-group pd">
    <div class="filter">
      <form name="f1" method="post" action="filterServlet">
        <div class="row">
          <div class="col-md-3">
            <input type="text" class="form-control" id="searchid"
              placeholder="Search.." name="search">
          </div>
          <div class="col-md-1">
            <select name="files" class="form-control" id="files">
              <option value="all">all</option>
              <%
              List<String> fileTypes = (ArrayList<String>)request.getAttribute("typesList");
              for (String str : fileTypes) {
              %>
              <option value=<%=str%>><%=str%></option>
              <%
              }
              %>
            </select>
          </div>
          <div class="col-md-2">
            <select name="grade" class="form-control" id="grade"
              onchange="hideDiv(this)">
              <option value="all">None</option>
              <option value=">=">Greater than</option>
              <option value="<=">Lesser than</option>
              <option value="=">Equal to</option>
              <option value="Between">Between</option>
            </select>
            <div id="p1" class="prize-div">
              <div id="hideDiv" class="prize">
                <br>
                <h5>&nbsp;&nbsp;&nbsp;Enter the values:</h5>
                <br>
                <center>
                  <label for="fname">Minimum</label> <input type="text"
                    id="fname" class="first" name="min"><br>
                  <br> <label for="lname">Maximum</label> &nbsp;<input
                    type="text" id="lname" name="max"><br>
                  <br> <input type="button" class="btn btn-danger"
                    value="Submit" onClick="closeDiv()">
                </center>
              </div>
            </div>
          </div>
          <div id="p2" class="prize-div2">
            <div id="hideDiv2" class="prize2">
              <br>
              <center>
                <label for="fname">Enter the value:</label> <input
                  type="text" id="fname" class="first" name="single"><br>
                <br> <input type="button" class="btn btn-danger"
                  value="Submit" onClick="closeDiv2()"> <input
                  type="button" class="btn btn-danger" value="Cancel"
                  onClick="closeDiv2()">
              </center>
            </div>
          </div>
          <div class="col-md-3">
            <input type="submit" class="btn btn-primary" name="submit"
              value="Apply" /> <input type="submit" name="submit"
              class="btn btn-secondary" value="Clear" />
          </div>
          
        </div>
    </div>
    </form>
    
    <div>

      <% 
      List<FileDetails> filtered = (ArrayList<FileDetails>)request.getAttribute("filesList");
      int size = 0;
      size = filtered.size();
      if (size == 0) {
      %>

      <div class="demo">
        <br>
        <br>
        <center>
          <h3>Oops..No records found!</h3>
          <img src="nodataanim.gif" alt="No image found" width="200"
            height="200">
      </div>
      <%
      } 
      else {

      //String spageid = request.getParameter("page");
      int pageid =1;
      try {
         pageid = Integer.parseInt(request.getParameter("page"));
      } catch (NumberFormatException nfe) {
        pageid = 1;
        System.out.println("NumberFormat Exception: invalid input string");
      }
 
      int total = 10;
      System.out.println("old page id:" + pageid);
      if (pageid == 1) {
      } else {

        pageid = pageid - 1;
        pageid = (pageid * total) + 1;
      }
      System.out.println("filtered size:" + filtered.size());
      System.out.println("new page id:" + pageid);
      List<FileDetails> f2 = FileDao.viewAllEmployees(pageid, total);
      List<FileDetails> ans = FileDao.getRecords(filtered, pageid, total);
      System.out.println("ans oda size:" + ans.size());
      %>
      `
      <form name="f2" method="get" action="#">

        <table id="users" border="1">
          <table id="users">
            <tr>
              <th>Uploaded By</th>
              <th>File Name</th>
              <th>File Type</th>
              <th>Course</th>
              <th>Location</th>
              <th>Size</th>
              <th>Upload Time</th>

            </tr>
            <%
            for (FileDetails f : ans) {
                String coursePath = f.getCourses() != null ? f.getCourses().replace(" ", "%20") : "";
            	String downloadUrl =  "download?fileName=" + f.getFile_name()+"&courses=" +coursePath;
                System.out.println(downloadUrl);
            %>
            <tr>
              <td><%=f.getFirst_name()%></td>
              <td name="demo"><a href=<%=downloadUrl%> target="_blank"><%=f.getFile_name()%></a></td>
              <td><%=f.getType()%></td>
              <td><%=f.getCourses()%></td>
              <td name="loc"><%=f.getLocation()%></td>
              <td><%=f.getSize()%></td>
              <td><%=f.getTime()%></td>
            </tr>

            <%
            }
            %>
          </table>

          <div class="footer">
            <nav class="alignRight" aria-label="Page navigation example">

            <div id="navigation">
              <ul class="pagination">
           
                <%
                int count = ans.size();
                System.out.println("check total: " + count);
                int pages = filtered.size() / 10;
                if (filtered.size() % 10 != 0)
                  pages += 1;
                int currentPage = 1;
                try {
                    currentPage = Integer.parseInt((String)(request.getAttribute("currentPage")));
                 } catch (NumberFormatException nfe) {
     
                   System.out.println("NumberFormat Exception: invalid input string"+request.getParameter("currentPage"));
                 }
                System.out.println("Final total rows: " + ans.size() + " and given limit count is: " + 10);
                System.out.println("final pages:" + pages);
                for (int i = 1; i <= pages; i++) {
                  System.out.println("Current Page is:" + currentPage);
                  String currentClass = "page-item";
                  String tempClass = " active";
                  if (currentPage == i) {%>
                    <li class="page-item active"><a
                        href="filterServlet?page=<%=i%>" class="page-link" href="#"><%=i%></a></li>
                  <%}else{%>  
                    <li class="page-item"><a
                    href="filterServlet?page=<%=i%>" class="page-link" href="#"><%=i%></a></li>
                  <%}%>
                <%}%>
              </ul>
            </nav>
          </div>
          </div>


          <%
          }
          %>
        
</body>
    
</html>