<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table border="1" style="width:100%">
<tr>
	<th>Id</th>
	<th>Name</th>
	<th>Semester</th>
	<th># Students</th>
	<th>Create date</th>
	<th></th>
</tr>
<c:forEach var="item" items="${items}">
	<tr>
		<td>${item.id}</td>
		<td>${item.name}</td>
		<td>${item.semester}</td>
		<td>${item.numberOfStudents}</td>
		<td><fmt:formatDate pattern="dd/MM/yyyy" value="${item.createDate}" /></td>
		
		<td>
			<a href="/clazz/edit/${item.id}">Edit</a>
		</td>
	</tr>
</c:forEach>
</table>