<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
margin: 10px 300px 10px 630px;
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
<body>
<div>
<form method="post" action="options.jsp">
<button  type="submit">Options</button>
</form>
<br>
<form method="post" action="Fines">
	<br>
				<h2 align = center >Fines
				</h2>
			
			
			<input type="text" name="fines_val" placeholder="Enter Borrower ID" required>
			
			<button class="fsSubmitButton" type="submit">Get Fines</button>
			<br/>
			
			<center>
			 		<input type="radio" name="r1" value="Paid" required />Paid Fines
			 		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="r1" value="UnPaid" required />UnPaid Fines
                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="r1" value="Both" required />Both
                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="r1" value="OverDue" required/>OverDue
                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="r1" value="Settle" required/>Pay Fine
                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                     </form>
                     
			</center>
			</div>

<br>
<table border="1">


<c:choose>
    <c:when test="${requestScope.flistsize > 0}">
  	
	<tr><td>Sr. No.</td>
	<td align="center">Loan_ID</td>
	<td align="center">Card_ID</td>
	<td align="center">ISBN</td>
	<td align="center">Title</td>
	<td align="center">Amount</td>
	<td align="center">Payment Status</td></tr>
	
<c:forEach items="${requestScope.flist}" var="resulrVar" varStatus="loop"> 

 <tr><td align="center"><c:out value= "${loop.index + 1}" /></td>
 <td align="center"><c:out value="${resulrVar.loanid}" /></td>
 <td align="center"><c:out value="${resulrVar.cardid}" /></td>
 <td align="center"><c:out value="${resulrVar.isbn}" /></td>
 <td align="center"><c:out value="${resulrVar.title}" /></td>
 <td align="center"><c:out value="${resulrVar.amt}" /></td>
 <td align="center"><c:out value="${resulrVar.paid_unpaid}" /></td></tr>


</c:forEach>
</c:when>
</c:choose>


<c:choose>
    <c:when test="${requestScope.pay == 1}">
  	
	<tr><td>Sr. No.</td>
	<td align="center">Loan_ID</td>
	<td align="center">Card_ID</td>
	<td align="center">ISBN</td>
	<td align="center">Title</td>
	<td align="center">Amount</td>
	<td align="center">Settle</td></tr>
	
<c:forEach items="${requestScope.flist}" var="resulrVar" varStatus="loop"> 

 <tr><td align="center"><c:out value= "${loop.index + 1}" /></td>
 <td align="center"><c:out value="${resulrVar.loanid}" /></td>
 <td align="center"><c:out value="${resulrVar.cardid}" /></td>
 <td align="center"><c:out value="${resulrVar.isbn}" /></td>
 <td align="center"><c:out value="${resulrVar.title}" /></td>
 <td align="center"><c:out value="${resulrVar.amt}" /></td>
 <td><form method="post" action="Payfine"><button name="check" value="${resulrVar.loanid}">Pay</button></form></td></tr>
	

</c:forEach>
</c:when>
</c:choose>


<c:choose>
    <c:when test="${requestScope.od == 1}">
  
	<tr><td>Sr. No.</td>
	<td align="center">Loan_ID</td>
	<td align="center">Card_ID</td>
	<td align="center">ISBN</td>
	<td align="center">Title</td>
	<td align="center">Due Date</td></tr>
	
<c:forEach items="${requestScope.flist}" var="resulrVar" varStatus="loop"> 

 <tr><td align="center"><c:out value= "${loop.index + 1}" /></td>
 <td align="center"><c:out value="${resulrVar.loanid}" /></td>
 <td align="center"><c:out value="${resulrVar.cardid}" /></td>
 <td align="center"><c:out value="${resulrVar.isbn}" /></td>
 <td align="center"><c:out value="${resulrVar.title}" /></td>
  <td align="center"><c:out value="${resulrVar.authorname}" /></td></tr>


</c:forEach>
</c:when>
</c:choose>

 </table>
</body>
</html>