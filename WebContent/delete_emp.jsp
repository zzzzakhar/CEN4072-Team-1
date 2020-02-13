<%@page import="model.ModelFacade"%>
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
<title>Delete Employee</title>
</head>
<body>
<%@ include file="employer_menu.html" %>
<br>



<form name="f1" method="get" action="deleteempcontroller">
<br><br>
<center>
<table>
<tr><th colspan="2" align="center">
<h1>Delete Employee</h1></th></tr>
 <tr><td id="l">Employee ID :</td>
          <td><input type="text" name="eid" plaeholder="Employee ID"  id="c" required></td></tr>
          <tr><td colspan="2" align="center"> <br><input type="submit" name="submit" id="submit" value="Delete" style="border-radius:10px;background:Aqua;color:white;width:120px;height:35px;font-size:25px"></td></tr>
</table>
<br><br>
<%
	if(request.getParameter("submit")!=null)
	{
		
		if(request.getParameter("eid")!=null)
		{
			
			ModelFacade obj=new ModelFacade();
    		String result=obj.EmployeedeleteEmp(request.getParameter("eid"));
    		if(result.equals("success"))
    		{
    			out.print("<script language='javascript'>window.alert('Employee  Deleted');window.location.replace('delete_emp.jsp');</script>");
    		}
    		else
    		{
    			out.print("<script language='javascript'>window.alert('Empid not found');window.location.replace('delete_emp.jsp');</script>");
    		}
		}
	}	
%>
</center>
</form>
</body>
</html>