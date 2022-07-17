<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Task One!</title>
<link
  href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
  rel="stylesheet"
  integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
  crossorigin="anonymous">
<style>
body {
	background-image: url('bg.png');
}
</style>
</head>
<body>
  <div class="container col-lg-6">
    <br> <br> <br>
    <h1 class="text-center">Upload Section!</h1>
    <div class="card">
      <div class="card-body">
        <form method="post" class="form-group" action="uploadServlet" enctype="multipart/form-data">
          <br> <label><b>Course Instructor</b></label> <input
            type="text" class="form-control" id="searchid"
            name="firstName" placeholder="Your Name:" name="search">
          <div class="form-group">
            <br> <span class="label danger">Course Selection</span>
            <br> <br> <select name="courses"
              class="form-control" id="files">
              <option value="Android App Development">Android
                App Development</option>
              <option value="Web App Development">Web App
                Development</option>
              <option value="Game Development">Game Development</option>
              <option value="Data Structures and Algorithms">Data
                Structures and Algorithms</option>
            </select>
          </div>
          <br>
          <div class="form-group">
            <label for="Profile Photo">File:</label> <input type="file"
              name="file" size="50" />
          </div>
          <center>
            <input type="submit" value="Save" class="btn btn-danger"/>
          
          </center>
        </form>
        
        <center> <form method="post" class="form-group" action="filterServlet?page=1">
            <input type="submit" class="btn btn-danger" value="Load"/>
              </form></center>
      </div>
    </div>
  </div>
</body>
</html>