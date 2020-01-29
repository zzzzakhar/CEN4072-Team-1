<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="css/styles.css">



    <link href="ss/tooltip.css" rel="stylesheet" type="text/css" />
    <script src="JS/tooltip.js" type="text/javascript"></script>
    <link href="css/generic.css" rel="stylesheet" type="text/css" />
   
    <style>
#c{
border-radius:8px;
height:30px;
width:150px;
}
#l{
color:blue;
font-size:16px;
}
</style>
<title>Update Employee</title>
</head>
<body>
<%@ include file="employer_menu.html" %>
<br>



<form name="f1" method="get" action="update_emp_rec.jsp">
<center>
<br>
<br>
<table>
<tr><th colspan="2" align="center">
<h1>Search Employee</h1></th></tr>
 <tr><td id="l">Employee ID :</td>
          <td><input type="text" name="eid" placeholder="Employee ID"  id="c" required></td></tr>
          <tr><td colspan="2" align="center"> <input type="submit" name="submit" id="submit" value="Search" style="border-radius:10px;background:Aqua;color:white;width:120px;height:35px;font-size:25px">
          <br><br></td></tr>
</table>
<br><br>
</center>
</form>
</body>
</html>