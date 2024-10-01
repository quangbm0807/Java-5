<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<table border="1" style="width:100%">
<tr>
	<th>Id</th>
	<th>Name</th>
</tr>
<c:forEach var="item" items="${page.content}">
	<tr>
		<td>${item.id}</td>
		<td>${item.name}</td>
	</tr>
</c:forEach>
<nav aria-label="Page navigation example">
	<ul class="pagination justify-content-center">
		<li class="page-item"><a class="page-link" href="/category/page?page=0">First</a></li>
		<li class="page-item"><a class="page-link" href="/category/page?page=${page.number -1 }">Previous</a></li>
		<li class="page-item"><a class="page-link" href="/category/page?page=${page.number +1 }">Next</a></li>
		<li class="page-item"><a class="page-link" href="/category/page?page=${page.totalPages - 1 }">Last</a></li>
	</ul>
</nav>
<ul class="list-group">
	<li class="list-group-item">Số thực thể hiện tại: ${page.numberOfElements }</li>
	<li class="list-group-item">Trang số: ${page.number }</li>
	<li class="list-group-item">Kích thước trang: ${page.size }</li>
	<li class="list-group-item">Tổng số thực thể: ${page.totalElements }</li>
	<li class="list-group-item">Tổng số trang: ${page.totalPages }</li>
</ul>
</table>