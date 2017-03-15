<!--
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action ="Add_b" name="myform">
                
					<input type="text" class="form-control" name="user_id" placeholder="USER ID" required>
   					 <input type="password" name="pass" class="form-control"  placeholder="PASSWORD" required>
                
            
	
							<input type="submit" class="btn btn-default" style="width:100px;" value="Login">
    			</form>

</body>
</html>-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta name="keywords" content="HTML,KWIC Architecture,CSS, JSP, Search Engine, Indexing">
<meta name="authors" content="Shail, Prince ,Rajan ">

<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover:not(.active) {
  background-color: #111;
}

.active {
  background-color: #4CAF50;
}
.fsSubmitButton
{
padding: 10px 15px 11px !important;
font-size: 18px !important;
background-color: #57d6c7;
font-weight: bold;
text-shadow: 1px 1px #57D6C7;
color: #ffffff;
margin: 10px 300px 10px 670px;
border-radius: 5px;
-moz-border-radius: 5px;
-webkit-border-radius: 5px;
border: 1px solid #57D6C7;
cursor: pointer;
box-shadow: 0 1px 0 rgba(255, 255, 255, 0.5) inset;
-moz-box-shadow: 0 1px 0 rgba(255, 255, 255, 0.5) inset;
-webkit-box-shadow: 0 1px 0 rgba(255, 255, 255, 0.5) inset;
}
div {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 25px;
}
input[type=text]{
  width: 30%;
  padding: 12px 20px;
  margin: 10px 300px 10px 500px;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

</style>

</head>

<title> Library Management System</title>

<body>


<ul>
<li><a class="active" href="">Login</a></li>


</ul>




<form method="post" action ="Add_b">
<div>

<input type="text" name="admin_login" placeholder="Please enter your Librarian_id" required>
<input type="text" name="admin_password" placeholder="PASSWORD" required>	
</br>
<input class="fsSubmitButton" type="submit" value="Login"></div>
</form>
</div>
</body>
</html>

