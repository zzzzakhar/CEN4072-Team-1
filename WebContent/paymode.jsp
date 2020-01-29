<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/styles.css">
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
<title>Adding Paymodes</title>
</head>
<body>
<%@ include file="employer_menu.html" %>
<br>
<form name="f1" method="get" action="Paycheck_Generator">
<br><br>
<table>
<tr><th colspan="2" align="center">
<h1>Add Pay Modes</h1></th></tr>
 <tr><td id="l">Actual Pay :</td>
          <td><input type="number" name="normal" placeholder="Normal Pay"  id="c" required></td></tr>
          <tr><td id="l">Extra Time Pay :</td>
          <td><input type="number" name="extra" placeholder="Extra Time Pay"  id="c" required></td></tr>
          <tr align="center"><td colspan="2">
          <input type="submit" name="submit" id="submit" value="Add" style="border-radius:10px;background:Aqua;color:white;width:120px;height:35px;font-size:25px"></td></tr>
</table>
</center>
</form>
</body>
</html>