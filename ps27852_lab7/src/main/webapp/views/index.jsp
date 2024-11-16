<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="layout/header.jsp"/>
<div style="margin-left: 185px; padding: 20px;"> <!-- Nội dung chính sẽ đẩy sang phải -->
    <jsp:include page="${content}"/> <!-- Hiển thị nội dung tùy theo tham số content -->
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
