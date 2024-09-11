<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fr"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<fr:form action="/category/save" modelAttribute="category">
		<fr:input path="id" placeholder="Category Id?" />
		<fr:input path="name" placeholder="Category Name?" />
		<hr>
		<button>Create</button>
		<button formaction="/category/update/${ category.id }">Update</button>
		<a href="/category/delete/${category.id}">Delete</a>
		<a href="/category/reset">Reset</a>
	</fr:form>

	<!-- JavaScript Bundle with Popper -->
	<script src="/js/jquery-3.2.1.slim.min.js"></script>
	<script src="/js/popper.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
</body>
</html>
