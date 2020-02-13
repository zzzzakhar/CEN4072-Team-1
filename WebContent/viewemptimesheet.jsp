<%@page import="utilities.DBConnection"%>
<%@page import="model.ModelFacade"%>
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
<title>Approve Time Sheets</title>
</head>
<body>
<%@ include file="employee_menu.html" %>
<br>
<form name="a1" method="get" action="">

<%
ModelFacade obj=new ModelFacade();
String empid=(String)session.getAttribute("empid");

ResultSet rs1=obj.TimeSheetgetEmpTimeSheetNotApproved(empid);
%>

<center>
    <table width="100%" border="1"><tr align="center" >
    <caption><h1>Employee Time Sheet</h1></caption>
    <tr style="background-color:cyan">
	<th id="l">TS_ID</th>
	<th id="l" >EMPID</th>
	<th id="l" >DAY</th>
	<th id="l" >DATE</th>
	<th id="l" >IN TIME</th>
	<th id="l" >LUNCH OUT</th>
	<th id="l" >LUNCH IN</th>
	<th id="l" >OUT TIME</th>
	<th id="l" >TOTAL HOURS</th>
	<th id="l" >POSTED ON</th>
	<th id="l" >STATUS</th>
	
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

	 <td><%=rs1.getString("ets_id")%> </td>
	  <td width=""><%=rs1.getString("emp_id")%></td>
	  <td width=""><%=rs1.getString("day")%></td>
	  <td width=""><%=rs1.getString("wdate")%></td>
	 <td width=""><%=rs1.getString("intime")%></td>
	 <td width=""><%=rs1.getString("lunch_out")%></td>
	 <td width=""><%=rs1.getString("lunch_in")%></td>
	 <td width=""><%=rs1.getString("outtime")%></td>
	 <td width=""><%=rs1.getString("total_hours")%></td>
	 <td width=""><%=rs1.getString("date1")%></td>
	 <td width=""><%=rs1.getString("status")%></td>
	 
	</tr>

		<%
		i++;
	  }
	
	  %>
	  <tr><td colspan="11" align="center">
	  <input type="submit" name="submit" id="submit" value="Submit" style="border-radius:10px;background:Aqua;color:white;width:120px;height:40px;font-size:25px">
	  </td></tr>
	   
	  
	 <% 
	}
	 else
	 {
		 out.print("resultset is null");
	 }

	%>
</table>

</center>
</form>
</body>
</html>
