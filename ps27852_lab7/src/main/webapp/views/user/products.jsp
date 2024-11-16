<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Products</title>
    <style>
        /* CSS cho hiệu ứng hover */
        .product-card {
            transition: transform 0.3s;
            border: 1px solid #dee2e6; /* Thêm đường viền cho card */
            border-radius: 5px; /* Bo tròn góc cho card */
            overflow: hidden; /* Ẩn phần thừa */
        }
        .product-card:hover {
            transform: scale(1.05); /* Hiệu ứng phóng to khi hover */
        }
        .card-body {
            text-align: center; /* Căn giữa nội dung trong card */
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../layout/headerUser.jsp" />

<div class="container mt-5">
    <h1 class="text-center">Danh Sách Sản Phẩm</h1>
    <div class="row">
        <c:forEach var="product" items="${products}">
            <div class="col-md-4 mb-4">
                <div class="card product-card"> <!-- Thêm lớp product-card vào thẻ card -->
                    <img src="${pageContext.request.contextPath}/products/${product.image}" width="200" height="200" class="card-img-top" alt="${product.name}">
                    <div class="card-body">
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text">Giá: <fmt:formatNumber value="${product.price}" type="currency" /></p>
                        <a href="/user/details/${product.id}" class="btn btn-primary">Chi Tiết</a>
                        <a href="/user/saveCart/${product.id}" class="btn btn-success">Đặt Hàng</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
