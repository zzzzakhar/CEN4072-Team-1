<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
width:200px;
}
#l{
color:blue;
font-size:16px;
text-align:right;
}
</style>
<title>Adding Employees</title>
</head>
<body>
<%@ include file="employer_menu.html" %>
<br>
<form name="f1" method="get" action="Registration">
<table>
<tr><th colspan="2" align="center">
<h1>Employee Details</h1></th></tr>
 <tr><td id="l">Employee ID :</td>
          <td><input type="text" name="eid" placeholder="Employee ID"  id="c" required></td></tr>
          <tr><td id="l">First Name :</td>
          <td><input type="text" name="fname" placeholder="First Name"  id="c" required></td></tr>
          <tr><td id="l">Last Name :</td>
          <td><input type="text" name="lname" placeholder="Last Name"  id="c" required></td></tr>
          
          <tr><td id="l">Gender :</td>
          <td id="l"><input type="radio" name="gen" >Male<input type="radio" name="gen" id="l" >Female</td></tr>
          <tr><td id="l">Date of Birth :</td>
          <td><input type="date" name="dob" placeholder="Date of Birth"  id="c" required></td></tr>
          <tr><td id="l">Job Role :</td>
          <td><input type="text" name="job" placeholder="Job Role"  id="c" required></td></tr>
          <tr><td id="l">Contact :</td>
          <td><input type="number" name="contact" placeholder="Contact"  id="c" required></td></tr>
          <tr><td id="l">Email :</td>
          <td><input type="email" name="email" placeholder="Email"  id="c" required></td></tr>
          <tr><td id="l">Address :</td>
          <td><textarea rows='15' name="addr" columns='100' id="c"></textarea></td></tr>
          <tr><td id="l">Account Number:</td>
          <td><input type="number" name="accno" placeholder="A/C Number"  id="c" required></td></tr>
          <tr><td id="l">Bank Name :</td>
          <td><input type="text" name="bname" placeholder="Bank Name"  id="c" required></td></tr>
           <tr align="center"><td colspan="2">
                    <input type="submit" name="submit" id="submit" value="Add" style="border-radius:10px;background:Aqua;color:white;width:120px;height:35px;font-size:25px"></td></tr>
</table>
</center>
</form>
</body>
</html>