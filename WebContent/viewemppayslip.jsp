<%@page import="model.Salary"%>
<%@page import="utilities.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="css/styles.css">

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
text-align:left;
}


#l4{
text-align:left;
}


</style>
<title>View Employee PaySlip</title>
</head>
<body>
<%@ include file="employee_menu.html" %>
<br>
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

<form name='ts' action="Edit_Timesheet_Control" method="get">
<center>
<table cellspacing="10px" width="60%">
<caption><h1>Employee Pay Details</h1></caption>
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
  
<%

Salary obj=new Salary();

ResultSet rs1=obj.getEmpPay(empid);
%>

<br><br>
    <table width="60%" border="1">
    <tr align="center">
    
    <tr style="background-color:cyan">
	<th id="l" >TOTAL HOURS WORKED</th>
	<th id="l" >GROSS PAY</th>
	<th id="l" >TAX</th>
	<th id="l" >NET PAY</th>
	<th id="l" >DATE</th>
	</tr>
	<%
		
	
	
int i=1;
	if(rs1!=null)
	{
	 while(rs1.next())
	  {%>
	  <tr align="center">
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
</form>

</body>
</html>