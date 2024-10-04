<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search class by min & max students</title>
</head>
<body>
<form action="/clazz/search" method="post">
	<input type="input" name="minStudent"/>
	<input type="input" name="maxStudent"/>
	<hr>
	<input type="submit" value="Search">
</form>
<table border="1" style="width:100%">
<tr>
	<th>Id</th>
	<th>Name</th>
	<th>Semester</th>
	<th># Students</th>
	<th>Create date</th>
	
</tr>
<c:forEach var="item" items="${items}">
	<tr>
		<td>${item.id}</td>
		<td>${item.name}</td>
		<td>${item.semester}</td>
		<td>${item.numberOfStudents}</td>
		<td><fmt:formatDate pattern="dd-MM-yyyy" value="${item.createDate}" /></td>
		
		
	</tr>
</c:forEach>
</table>
</body>
</html>