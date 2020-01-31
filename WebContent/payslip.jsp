<%@page import="model.Salary"%>
<%@page import="utilities.DBConnection"%>
<%@page import="model.TimeSheet"%> <!-- Another unnecessary import? -->
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
</style>
<title>Employees Payments</title>
</head>
<body>
<%@ include file="employer_menu.html" %>
<br>
<form name="a1" method="get" action="">

<%
Salary obj=new Salary();
ResultSet rs1=obj.getEmpPays();
%>

<center>
    <table width="100%" border="1"><tr align="center">
    <caption><h1>Employees Pay Details</h1></caption>
    <tr style="background-color:cyan">
	<th ">EMPID</th>
	<th  >FIRST NAME</th>
	<th  >LAST NAME</th>
	<th  >TOTAL HOURS WORKED</th>
	<th >GROSS PAY</th>
	<th  >TAX</th>
	<th  >NET PAY</th>
	<th  >DATE</th>
	</tr>
	<%
		
	
	
int i=1;
	if(rs1!=null)
	{
	 while(rs1.next())
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

<td>	 <%=rs1.getString("emp_id")%> </td>
	  <td width=""><%=rs1.getString("first_name")%></td>
	  <td width=""><%=rs1.getString("last_name")%></td>
	  <td width=""><%=rs1.getString("total_hours")%></td>
	 <td width=""><%=rs1.getString("gross_sal")%> $</td>
	 <td width=""><%=rs1.getString("tax")%> $</td>
	 <td width=""><%=rs1.getString("net_sal")%> $</td>
	 <td width=""><%=rs1.getString("date1")%></td>
	 	 
	</tr>

		<%
		i++;
	  }%>
	  
	 <% 
	}
	else
	{%>
	<h1> No pay details exist.</h1>
		<%
	}

	%>
</table>

</center>
</form>
</body>
</html>
