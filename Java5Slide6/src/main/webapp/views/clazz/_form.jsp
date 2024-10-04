<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form action="/clazz/index" modelAttribute="item">
	
	Class Name: <form:input path="name" placeholder="Class name"/>
	<br>Semester: <form:input path="semester" placeholder="Semester"/>
	<br>Number of students: <form:input path="numberOfStudents" placeholder="Number of student"/>
	<hr>
	<button formaction="/clazz/create">Create</button>
	<button formaction="/clazz/update">Update</button>
	<a href="/clazz/delete/${item.id}">Delete</a>
	<a href="/clazz/index">Reset</a>
</form:form>
