<%@page import="model.ModelFacade"%>
<%@page import="utilities.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees Details</title>
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

</head>
<body>
<%@ include file="employer_menu.html" %>
<br>
<center>
<table width="100%" border="1" ><caption><h1>Employees Details</h1></caption>
 <tr style="background-color:cyan"><th id="l">EMPID</th> <th id="l">FRIST NAME</th><th id="l">LAST NAME</th><th id="l">GENDER</th><th id="l">DOB</th><th id="l">JOB</th><th id="l">PHONE</th><th id="l">EMAIL ID</th><th id="l">ADDRESS</th><th id="l">ACCNO</th><th id="l">BANK NAME</th><th id="l">JOIN DATE</th></tr>

<%
String empid="";
if(request.getParameter("eid")!=null)
	empid=request.getParameter("eid");

ModelFacade obj=new ModelFacade();
ResultSet rs=obj.EmployeegetAllEmployees();
int i=1;
while(rs.next())
{
	if(i%2==0)
	{
%>

 <tr style="background-color:#D3D3D3">
 <%
	}
 else
 {
	 %>
 <tr>
 <%} %>
 
 <td> <%=rs.getString("emp_id") %></td>
 <td> <%=rs.getString("first_name") %></td>
 <td> <%=rs.getString("last_name") %></td>
 <td> <%=rs.getString("gender") %></td>
 <td> <%=rs.getString("dob") %></td>
 <td> <%=rs.getString("job") %></td>
 <td> <%=rs.getInt("phone") %></td>
 <td> <%=rs.getString("email_id") %></td>
 <td> <%=rs.getString("address") %></td>
 <td> <%=rs.getString("accno") %></td>
 <td> <%=rs.getString("bankname") %></td>
 <td> <%=rs.getString("joindate") %></td>
 </tr>
 
<%
i++;
}
%>
</table>
 </center>
</body>
</html>