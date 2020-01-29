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
<center>
<h1> Approve Time Sheet</h1>
<form name="a1" method="get" action="">
<%
if(request.getParameter("submit")!=null)
{
		int j=1;
		int n=200;
		Connection con=DBConnection.createConnection();
		Statement st=con.createStatement();
		int count=0;
		while(j<=n)
		{
			if(request.getParameter("sub"+j)!=null)
			{
			 String tsid=request.getParameter("sub"+j);
				
				
				String qry2="update emp_ts set status='approved' where ets_id='"+tsid+"'";
				int q=st.executeUpdate(qry2);
				count++;
			}
			j++;
		}
}
%>





<%
TimeSheet obj=new TimeSheet();
ResultSet rs=obj.getTimeSheetNotApproved();




%>


    <table width="100%" border="1">
    <tr style="background-color:cyan">
	<td width="">TS_ID</td><td width="">EMPID</td>
	<td width="">DAY</td><td width="">DATE</td>
	<td width="">IN TIME</td>
	<td width="">LUNCH OUT</td>
	<td width="">LUNCH IN</td>
	<td width="">OUT TIME</td>
	<td width="">TOTAL HOURS</td>
	<td width="">POSTED ON</td>
	
	<td>APPROVAL</td>
	</tr>
	<%
		
	
	
int i=1;
	if(rs!=null)
	{
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

	<td> <%=rs.getString("ets_id")%> </td>
	  <td width=""><%=rs.getString("emp_id")%></td>
	  <td width=""><%=rs.getString("day")%></td>
	  <td width=""><%=rs.getString("wdate")%></td>
	 <td width=""><%=rs.getString("intime")%></td>
	 <td width=""><%=rs.getString("lunch_out")%></td>
	 <td width=""><%=rs.getString("lunch_in")%></td>
	 <td width=""><%=rs.getString("outtime")%></td>
	 <td width=""><%=rs.getString("total_hours")%></td>
	 <td width=""><%=rs.getString("date1")%></td>
	<td>
	<input type="checkbox" name="sub<%=i%>" value="<%=rs.getString("ets_id")%>"></td></tr>

		<%
		i++;
	  }%>
	  <tr><td colspan="11" align="center">
	  <input type="submit" name="submit" id="submit" value="Approve" style="border-radius:10px;background:Aqua;color:white;width:120px;height:40px;font-size:25px">
	  </td></tr>
	   
	  
	 <% 
	}
	%>
</table>

</center>
</form>
</body>
</html>
