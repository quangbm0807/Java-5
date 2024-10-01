<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<table border="1" style="width:100%">
<tr>
	<th><a href="/category1/sort?field=id">Id</a></th>
	<th><a href="/category1/sort?field=name">Name</a></th>
</tr>
<c:forEach var="item" items="${items}">
	<tr>
		<td>${item.id}</td>
		<td>${item.name}</td>
		
	</tr>
</c:forEach>
</table>