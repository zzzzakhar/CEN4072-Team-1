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
height:40px;
width:300px;
}
#l{
color:blue;
font-size:20px;
text-align:right;
}

</style>
<title>Forgot Password</title>

</head>
<body>
<%@ include file="homemenu.html" %>
<br>
<form name="f1" action="forgotpasswordcontroller" method="post">
<table>
<tr><td colspan="2" align="center"><h1>Forgot Password</h1></td></tr>
<tr><td id="l">Employee ID :</td>
          <td><input type="text" name="eid" placeholder="Employee ID"  id="c"></td></tr>    
          <tr><td id="l">User Name :</td>
          <td><input type="text" name="uid" placeholder="User Name"  id="c"></td></tr>
          
          <tr><td id="l">Sequrity Question 1:</td>
          <td><select name="s1" id='c'>
          <option>Select Question</option>
          <option>Favorate Color?</option>
          <option>First PEt Name?</option>
          </select></td>
          
          </tr>
          <tr><td id="l">Answer 1 :</td>
          <td><input type="text" name="a1" placeholder="Answer"  id="c"></td></tr>
          <tr><td id="l">Sequrity Question 2:</td>
          <td><select name="s2" id='c'>
          <option>Select Question</option>
          <option>Favorate Color?</option>
          <option>First PEt Name?</option>
          </select></td>
           </td></tr>
           <tr>
           <td id="l">Answer 2 :</td><td><input type="text" name="a2" placeholder="Answer"  id="c">
           </tr>
          <tr><td id="l">Sequrity Question 3:</td>
          <td><select name="s3" id='c'>
          <option>Select Question</option>
          <option>Favorate Color?</option>
          <option>First PEt Name?</option>
          </select></td>
          </tr>
          <tr>
           <td id="l">Answer 3 :</td><td><input type="text" name="a3" placeholder="Answer"  id="c">
           </tr>
                    <tr><td colspan="2" align="center">
                    <input type="submit" name="submit" id="submit" value="Get Password" style="border-radius:10px;background:Aqua;color:white;width:220px;height:40px;font-size:25px"></td></tr>
                    </table>

</form>


</body>
</html>