<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Thông Báo Thanh Toán</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            margin-top: 50px;
            text-align: center;
        }

        h1 {
            color:
            <c:choose>
                    <c:when test="${status == 'success'}">#28a745</c:when>
                <c:otherwise>#dc3545</c:otherwise>
        </c:choose>;
            font-size: 2.5rem;
            font-weight: bold;
        }

        p {
            font-size: 1.2rem;
            margin-top: 20px;
        }

        .btn-primary {
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 1.1rem;
        }

        .status-icon {
            font-size: 5rem;
            color:
            <c:choose>
                    <c:when test="${status == 'success'}">#28a745</c:when>
                <c:otherwise>#dc3545</c:otherwise>
        </c:choose>;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<jsp:include page="../layout/headerUser.jsp" />

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <i class="status-icon bi
            <c:choose>
                <c:when test="${status == 'success'}">bi-check-circle-fill</c:when>
                <c:otherwise>bi-x-circle-fill</c:otherwise>
            </c:choose>"></i>
            <h1>${message}</h1>
            <p>Mã giao dịch: ${transactionId}</p>
            <a href="/user/home" class="btn btn-primary">Trở Về Trang Chủ</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
