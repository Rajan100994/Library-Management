<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

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
margin: 10px 300px 10px 560px;
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
  margin: 10px 300px 10px 450px;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}
</style>
</head>
<body>
<div >
<form method="post" action="options.jsp">
<button  type="submit">Options</button>
</form>
<br>
    <form method="post" action ="Borrower_add" name="myform">
	
			<h1 id="title" align = center>New Borrower Details:<br>
			<i><b>Fill all the details to successfully add the new borrower!</b></i></h1>
                        		
			
			<input type="text" class="form-control" name="first_name" placeholder="First Name" required>
			<br/>
          
                        <label for="lastname"></label>
			<br/>
			
			<input type="text" class="form-control" name="last_name" placeholder="Last Name" required>
			<br/>
                        <label for="ssn"> </label>
			<br/>
			 
			<input type="text" class="form-control" name="ssn" placeholder="Social Security Number" required>
			<br/>
			 
			<label for="address"></label>
			<br/>
			<input type="text" class="form-control" name="address" placeholder="Address" required>
			<br/>
                        <label for="city"></label>
			<br/>
			<input type="text" class="form-control" name="city" placeholder="City" required>
			<br/>
                        <label for="state"> </label>
			<br/>
			
			<input type="text" class="form-control" name="state" placeholder="State" required>
			<br/>
                        <label for="phone"> </label>
			<br/>
		
			<input type="text" class="form-control" name="phone_number" placeholder="Phone Number" required>
			<br/>
			<button class="fsSubmitButton" type="submit" style="width:195px">Add</button>
			<br/>
<!--			<a href="#"><p class="small">Forgot your password?</p></a>-->
		
	
</form>
</div>
</body>
</html>