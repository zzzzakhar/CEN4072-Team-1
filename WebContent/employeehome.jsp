

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
     <script type="text/javascript">
        imageSlider.thumbnailPreview(function (thumbIndex) { return "<img src='images/thumb" + (thumbIndex + 1) + ".jpg' style='width:100px;height:60px;' />"; });
    </script>
   
<title>Employee Home</title>
</head>
<body>
<%@ include file="employee_menu.html" %>

<br>
<div id="sliderFrame">
        <div id="slider">
            <img src="images/i1.jpg" alt="Welcome To PMS" />
            <img src="images/i2.png" alt="Welcome To PMS" />
            <img src="images/i3.jpg" alt="Welcome To PMS" />
            <img src="images/i4.png" alt="Welcome To PMS" />
            
        </div>
       
    </div>

</body>
</html>