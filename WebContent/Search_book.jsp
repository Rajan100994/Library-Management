<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    

<%@page import="java.util.ArrayList"%>
 <%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
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

<body>
<div>
<form method="post" action="options.jsp">
<button  type="submit">Options</button>
</form>
<form method="post" action ="SearchAbcd.jsp" name="form1">


<input type="text" name="search_val" placeholder="Please enter the name of the book/author/ISBN" required>
	
</br>

    
<input class="fsSubmitButton" type="submit" value="Search">
</form>
<c:choose>
    <c:when test="${requestScope.size1 > 0}">
       
<h3 align="center">Your search returned 
<c:out value="${requestScope.size1}" /> results.</h3>
</c:when>
 </c:choose>
<br>
<br>
<table border="1">
<c:choose>
    <c:when test="${requestScope.size1 > 0}">
	<tr><td>Sr. No.</td>
	<td align="center">Title</td>
	<td align="center">Author</td>
	<td align="center">ISBN</td>
	<td align="center">Availability</td></tr>
</c:when>
 </c:choose>	
<c:forEach items="${requestScope.res}" var="resulrVar" varStatus="loop"> 

 <tr><td align="center"><c:out value= "${loop.index + 1}" /></td>
 <td align="center"><c:out value="${resulrVar.title}" /></td>
 <td align="center"><c:out value="${resulrVar.authorname}" /></td>
 <td align="center"><c:out value="${resulrVar.isbn}" /></td>
 <td align="center"><c:out value="${resulrVar.availability}" /></td>
 <td> <object data="http://covers.openlibrary.org/b/isbn/${resulrVar.isbn}-M.jpg" width=100 height=150></object></td></tr>


</c:forEach>
 </table>
 
</div>
</body>
</html>