<%@page import="utilities.DBConnection"%>
<%@page import="model.TimeSheet"%>
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


<%
TimeSheet obj=new TimeSheet();
ResultSet rs=obj.getTimeSheetApprovedEmpIds();



%>

<center>
<form name="a1" method="get" action="">
<table width="400"><caption><h1> Employee IDS</h1></caption>
 <tr><td id="l">Employee ID :</td>
          <td>
          <select name="eid">
          <option>-select id-</option>
        <%
        if(rs!=null)
        {
        while(rs.next())
			{
          %>
          <option><%=rs.getString("emp_id") %></option>
          <%
          }
          }%>
          <option>All</option>
          </select>

</td>
          
           <td>
                    <input type="submit" name="submit" id="submit" value="Get Details" style="border-radius:10px;background:Aqua;color:white;width:120px;height:35px;font-size:20px"></td></tr>
</table>
</form>
</center>
<br>
<br>


<%
if(request.getParameter("submit")!=null)
{

	String empid=request.getParameter("eid");
	
TimeSheet obj1=new TimeSheet();
ResultSet rs1=obj1.getTimeSheetApproved(empid);

%>
<center>
<form name="a2" method="post" action="Edit_Timesheet_Control">
    <table width="100%" border="1"> <tr style="background-color:cyan">
	<td width="">TS_ID</td>
	<td width="">EMPID</td>
	<td width="">DAY</td>
	<td width="">DATE</td>
	<td width="">IN TIME</td>
	<td width="">LUNCH OUT</td>
	<td width="">LUNCH IN</td>
	<td width="">OUT TIME</td>
	<td width="">TOTAL HOURS</td>
	<td width="">POSTED ON</td>
	
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
	<td width=""><input type="text" value="<%=rs1.getString("ets_id")%>" name="etsid<%=i%>" readonly></td>
	  <td width=""><%=rs1.getString("emp_id")%></td>
	  <td width=""><%=rs1.getString("day")%></td>
	  <td width=""><%=rs1.getString("wdate")%></td>
	  
	 <td width=""><input type="text" value="<%=rs1.getString("intime")%>" name="intime<%=i%>"></td>
	 <td width=""><input type="text" value="<%=rs1.getString("lunch_out")%>" name="lunchout<%=i%>"></td>
	 <td width=""><input type="text" value="<%=rs1.getString("lunch_in")%>" name="lunchin<%=i%>"></td>
	 <td width=""><input type="text" value="<%=rs1.getString("outtime")%>" name="outtime<%=i%>"></td>
	 <td width=""><input type="text" value="<%=rs1.getString("total_hours")%>" name="thours<%=i%>"></td>
	 <td width=""><input type="text" value="<%=rs1.getString("date1")%>" name="date<%=i%>"></td>
	 
	</tr>

		<%
		i++;
	  }%>
	  
	   
	  
	 <% 
	}
}
	%>
</table>
<br><br>
<input type="submit" name="submit" id="submit" value="Update Time Sheet" style="border-radius:10px;background:Aqua;color:white;width:220px;height:35px;font-size:20px"></td></tr>

</center>

</form>
</body>
</html>
