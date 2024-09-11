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
	<h3>CATEGORY MANAGEMENT</h3>
	<jsp:include page="_form.jsp" />
	<hr>
	<jsp:include page="_table.jsp" />

	<!-- JavaScript Bundle with Popper -->
	<script src="/js/jquery-3.2.1.slim.min.js"></script>
	<script src="/js/popper.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
</body>
</html>
