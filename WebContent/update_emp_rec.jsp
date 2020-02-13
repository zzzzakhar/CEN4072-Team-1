<%@page import="model.ModelFacade"%>
<%@page import="utilities.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
  <style>
#c{
border-radius:8px;
height:30px;
width:200px;
}
#l{
color:blue;
font-size:16px;
text-align:right;
}
</style>

</head>
<body>
<%@ include file="employer_menu.html" %>
<br>


<%
String empid="";
if(request.getParameter("eid")!=null)
	empid=request.getParameter("eid");

ModelFacade obj=new ModelFacade();
ResultSet rs=obj.EmployeegetEmployee(empid);
if(rs.next())
{
%>
<form name="f1" method="post" action="Get_Profile">
<table>
<tr><th colspan="2" align="center">
<h1>Employee Details</h1></th></tr>
 <tr><td id="l">Employee ID :</td>
          <td><input type="text" name="eid" placeholder="Employee ID"  id="c" value="<%=rs.getString("emp_id")%>" required readonly></td></tr>
          <tr><td id="l">First Name :</td>
          <td><input type="text" name="fname" placeholder="First Name"  id="c" value="<%=rs.getString("first_name")%>" required></td></tr>
          <tr><td id="l">Last Name :</td>
          <td><input type="text" name="lname" placeholder="Last Name"  id="c" value="<%=rs.getString("last_name")%>" required></td></tr>
          
          <tr><td id="l">Gender :</td>
          <td >
          <select name="gen" id="c">
          <option selected="selected"><%=rs.getString("gender")%></option>
          <option>Male</option>
          <option>FeMale</option>
          </select>
          
          </td></tr>
          <tr><td id="l">Date of Birth :</td>
          <td><input type="date" name="dob" placeholder="Date of Birth"  id="c" value="<%=rs.getString("dob")%>" required></td></tr>
          <tr><td id="l">Job Role :</td>
          <td><input type="text" name="job" placeholder="Job Role"  id="c" value="<%=rs.getString("job")%>" required></td></tr>
          <tr><td id="l">Contact :</td>
          <td><input type="number" name="contact" placeholder="Contact"  id="c" value="<%=rs.getInt("phone")%>" required></td></tr>
          <tr><td id="l">Email :</td>
          <td><input type="email" name="email" placeholder="Email"  id="c" value="<%=rs.getString("email_id")%>" required></td></tr>
          <tr><td id="l">Address :</td>
          <td><textarea rows='15' name="addr" columns='100' id="c">
          <%=rs.getString("address")%>
          </textarea></td></tr>
          <tr><td id="l">Account Number:</td>
          <td><input type="number" name="accno" placeholder="A/C Number"  id="c" value="<%=rs.getString("accno")%>" required></td></tr>
          <tr><td id="l">Bank Name :</td>
          <td><input type="text" name="bname" placeholder="Bank Name"  id="c" value="<%=rs.getString("bankname")%>" required></td></tr>
           <tr align="center"><td colspan="2">
                    <input type="submit" name="submit" id="submit" value="update" style="border-radius:10px;background:Aqua;color:white;width:120px;height:35px;font-size:25px"></td></tr>
</table>
</form>
<%
}
else
{
	out.println("<h1> Empid not found.");
}
%>

</body>
</html>