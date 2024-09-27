<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Staff Form</title>
</head>
<body>
<form:form action="/staff/save" modelAttribute="staff">
Staff ID: <form:input path="id"/>
<br/>Full Name: <form:input path="fullName"/>
<br/> Email: <form:input path="email"/>
<br/> Salary: <form:input path="salary"/>$
<br/> Gender: <form:radiobutton path="gender" value="true" label="Male"/>
<form:radiobutton path="gender" value="false" label="Female"/>
<br/>Position: <form:select path="position">
<form:option value="CEO">Chief Executive Officer </form:option>
<form:option value="DIR">Director </form:option>
<form:option value="MAN">Manager </form:option>
<form:option value="EMP">Employee </form:option>
</form:select>
<br/><button>Save</button>
</form:form>
</body>
</html>