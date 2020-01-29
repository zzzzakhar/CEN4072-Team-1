<%@page import="utilities.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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


#l2{
color:red;
font-size:16px;
text-align:right;
}


#l3{
font-size:18px;

}
</style>
<title>Add Time Sheet</title>
</head>
<body>
<%@ include file="employee_menu.html" %>
<br>
<center>
<%
	Connection con=DBConnection.createConnection();
	Statement st=con.createStatement();
	String empid=(String)session.getAttribute("empid");
	String useid=(String)session.getAttribute("userid");
	String qry="select * from employees where emp_id='"+empid+"'";
	ResultSet rs=st.executeQuery(qry);
	if(rs.next())
	{

%>

<form name='ts' action="Timesheet_Control" method="get">
<table cellspacing="10px" width="60%">
<tr ><td id="l2">Employee Name :</td> <td id="l3"><%=rs.getString("first_name") %> &nbsp; <%=rs.getString("last_name") %> </td> 
<td id="l2">Employee ID :</td> <td id="l3"><%=rs.getString("emp_id") %></td></tr>
<tr><td colspan="4">&nbsp</td></tr>
<tr><td id="l2">Job Title :</td> <td id="l3"><%=rs.getString("job") %></td><td id="l2">Phone :</td> <td id="l3"><%=rs.getString("phone") %></td></tr>
</table>
<%
}
st.close();
con.close();
%>
  
</table>
<table border='1'><caption><h1>Add Time Sheet</h1></caption>

<tr id='l'><th>Day</th><th>Date</th><th>In Time</th><th>Lunch Out</th><th>Lunch In</th><th>Check Out</th><th>Total Hours Worked</th></tr>

<%
for(int i=1;i<=5;i++)
{
out.print("<tr id='c'><td><select name='day"+i+"' id='c'>");

out.print("<option>Monday</option>");
out.print("<option>Tuesday</option>");
out.print("<option>Wednesday</option>");
out.print("<option>Thursday</option>");
out.print("<option>Friday</option>");
out.print("<option>Saturday</option>");
out.print("<option>Sunday</option>");
out.print("</select></td>");
out.print("<td><input type='date' name='dt"+i+"' placeholder='Date'  id='c' ></td>");
out.print("<td><input type='text' name='in"+i+"' placeholder='In Time'  id='c' ></td>");
out.print("<td><input type='text' name='lout"+i+"' placeholder='Lunch Out Time'  id='c' ></td>");
out.print("<td><input type='text' name='lin"+i+"' placeholder='Lunch In Time'  id='c' ></td>");
out.print("<td><input type='text' name='cout"+i+"' placeholder='Check Out Time'  id='c' ></td>");
out.print("<td><input type='text' name='total"+i+"' placeholder='Hours Worked'  id='c' readonly></td></tr>");
}
%>


           <tr><td  align="center" colspan='7'>
           <input type="submit" name="submit" id="submit" value="Save" style="border-radius:10px;background:Aqua;color:white;width:120px;height:40px;font-size:25px">
           <input type="reset" name="reset" id="reset" value="Clear" style="border-radius:10px;background:Aqua;color:white;width:120px;height:40px;font-size:25px"></td></tr>
           

</table>

<br> <h3>Want to Submit the Saved Time Sheet</h3>           
           
           <input type="button" name="button" id="submit" value="submit" onClick="window.location='Search_Employee?empid=<%=empid %>'" style="border-radius:10px;background:Aqua;color:white;width:120px;height:40px;font-size:25px">
</form>


 
 
 
 

</center>
</body>
</html>