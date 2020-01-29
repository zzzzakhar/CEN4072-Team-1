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
<title>Change Password</title>
</head>
<body>
<%@ include file="employee_menu.html" %>
<br>
<form name="f1" action="Authentication" method="post">
<br><br>
<table>
<tr><td colspan="2" align="center"><h1>Change Password</h1></td></tr>
<tr><td id="l">Employee ID :</td>
          <td><input type="text" name="eid" placeholder="Employee ID"  id="c"></td></tr>    
          <tr><td id="l">User Name :</td>
          <td><input type="text" name="uid" placeholder="User Name"  id="c"></td></tr>
          
          <tr><td id="l">Security Question 1:</td>
          <td><select name="s1" id='c'>
          <option>Select Question</option>
          <option>Favorite Color?</option>
          <option>First PEt Name?</option>
          <option>Favorite movie?</option>
          </select></td>
          
          </tr>
          <tr><td id="l">Answer 1 :</td>
          <td><input type="text" name="a1" placeholder="Answer"  id="c"></td></tr>
          <tr><td id="l">Security Question 2:</td>
          <td><select name="s2" id='c'>
          <option>Select Question</option>
          <option>Favorite Color?</option>
          <option>First PEt Name?</option>
          <option>Favorite movie?</option>

          </select></td>
           </td></tr>
           <tr>
           <td id="l">Answer 2 :</td><td><input type="text" name="a2" placeholder="Answer"  id="c">
           </tr>
          <tr><td id="l">Security Question 3:</td>
          <td><select name="s3" id='c'>
          <option>Select Question</option>
          <option>Favorite Color?</option>
          <option>First PEt Name?</option>
          <option>Favorite movie?</option>

          </select></td>
          </tr>
          <tr>
           <td id="l">Answer 3 :</td><td><input type="text" name="a3" placeholder="Answer"  id="c">
           </tr>
           
            <tr><td id="l">
          Old Password :</td>
          <td><input type="password" name="oldpass" placeholder="Old Password"  id="c"></td></tr>
          <tr><td id="l">
          New Password :</td>
          <td><input type="password" name="newpass" placeholder="New Password"  id="c"></td></tr>
          <tr><td id="l">
          Confirm-Password :</td>
          <td><input type="password" name="cpass" placeholder="Confirm-Password"  id="c"></td></tr>
           <tr><td colspan="2" align="center">
            <input type="submit" name="submit" id="submit" value="Change Password" style="border-radius:10px;background:Aqua;color:white;width:220px;height:40px;font-size:25px"></td></tr>
           </table>
</form>

</body>
</html>