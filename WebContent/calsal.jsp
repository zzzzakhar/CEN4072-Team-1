<%@page import="model.Salary"%>
<%@page import="utilities.DBConnection"%>
<%@page import="model.TimeSheet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="css/styles.css">

 <link href="css/js-image-slider.css" rel="stylesheet" type="text/css" />
    <script src="JS/js-image-slider.js" type="text/javascript"></script>
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
<title>Approve Time Sheets</title>
</head>
<body>
<%@ include file="employer_menu.html" %>
<br>
<form name="a1" method="get" action="">



<%

Connection con=DBConnection.createConnection();
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("select max(date1) into @maxdate from salaries");
rs=st.executeQuery("select datediff(curdate(),@maxdate) days");
if(rs.next())
{
//	out.print(" days="+rs.getInt("days"));
int n=rs.getInt("days");
String str=rs.getString("days") ;


if(n>=5 || str==null)
{
	%>
	
<center>
<table>
	<tr><td> <h3>Calculates Pay of Employees whose time sheets are approved</br> This calculation should be made for every two weeks</h3>  </td></tr>
	  <tr><td align="center">
	  <input type="submit" name="submit" id="submit" value="Calculate" style="border-radius:10px;background:Aqua;color:white;width:120px;height:40px;font-size:25px">
	  </td></tr>
</table>
</center>
	
<%	
	
}
else
{%>

<center><h1>Can't calculate employees pay because 1 week not passed from last pay calculation</h1> </center> 
	<%
}
	
}



if(request.getParameter("submit")!=null)
{
// write the condition to cal salary for everty 2 weeks	
	Salary obj=new Salary();
	String result=obj.calculateSalary();
	//response.sendRedirect("error.jsp?msg="+result);
	out.print("<script language='javascript'>window.alert('Pay calculations completed');window.location.replace('emplrhome.jsp');</script>");
}%>

</form>
</body>
</html>
