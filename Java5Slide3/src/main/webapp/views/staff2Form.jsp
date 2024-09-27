<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Staff 2 Form</title>
</head>
<body>
<form:form action="/staff2/save" modelAttribute="staff2">
<br/>Position: <form:radiobuttons path="position" items="${positions }"  delimiter="" />
<br/>Hobbies: <form:checkboxes path="hobbies" items="${hobbies }" delimiter=""/>
<br/>Nationalities: 
<form:select path="country.id">
<form:options items="${nationalities }" itemValue="id" itemLabel="name"/>
</form:select>
</form:form>
</body>
</html>