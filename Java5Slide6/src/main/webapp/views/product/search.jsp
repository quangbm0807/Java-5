<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product searching</title>
</head>
<body>
<form action="/product/search" method="post">
	<input name="min" value="${param.min}" placeholder="Min Price?">
	<input name="max" value="${param.max}" placeholder="Max Price?">
	<button>Search</button>
</form>
<table border="1" style="width:100%">
<tr>
	<th>Id</th>
	<th>Name</th>
	<th>Price</th>
	<th>Create Date</th>
</tr>
<c:forEach var="item" items="${items}">
	<tr>
		<td>${item.id}</td>
		<td>${item.name}</td>
		<td>${item.price}</td>
		<td><fmt:formatDate pattern="dd/MM/yyyy" value="${item.createDate}" /></td>
	</tr>
</c:forEach>
</table>
</body>
</html>