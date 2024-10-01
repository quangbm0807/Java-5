<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form action="/category/index" modelAttribute="item">
		
	<form:input path="id" placeholder="Category Id?"/>
	<form:input path="name" placeholder="Category Name?"/>
	<hr>
	 
	<button formaction="/category/create">Create</button>
	<button formaction="/category/update">Update</button>
	<a href="/category/delete/${item.id}">Delete</a>
	<a href="/category/index">Reset</a>
</form:form>
