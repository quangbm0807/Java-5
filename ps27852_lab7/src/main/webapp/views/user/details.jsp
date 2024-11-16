<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Chi tiết sản phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../layout/headerUser.jsp" />

<div class="container mt-5">
    <div class="row">
        <!-- Cột hiển thị hình ảnh sản phẩm -->
        <div class="col-md-3">
            <c:choose>
                <c:when test="${not empty product.image}">
                    <img src="${pageContext.request.contextPath}/products/${product.image}" alt="${product.name}" class="img-fluid">
                </c:when>
                <c:otherwise>
                    <img src="${pageContext.request.contextPath}/products/default-avatar.jpg" alt="Hình ảnh mặc định" class="img-fluid">
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Cột hiển thị thông tin sản phẩm -->
        <div class="col-md-9">
            <h1>${product.name}</h1>
            <p><strong>Giá:</strong> ${product.price} VND</p>

            <!-- Ngày sản xuất -->
            <p><strong>Ngày sản xuất:</strong> ${product.createDate}</p>

            <!-- Trạng thái có sẵn -->
            <p><strong>Tình trạng:</strong>
                <c:choose>
                    <c:when test="${product.available}">
                        Còn hàng
                    </c:when>
                    <c:otherwise>
                        Hết hàng
                    </c:otherwise>
                </c:choose>
            </p>

            <!-- Danh mục sản phẩm -->
            <p><strong>Danh mục:</strong> ${product.category.name}</p>

            <!-- Mô tả và thông tin mở rộng -->
            <p><strong>CPU:</strong> ${product.cpu}</p>
            <p><strong>RAM:</strong> ${product.ram}</p>
            <p><strong>Storage:</strong> ${product.storage}</p>
            <p><strong>Display:</strong> ${product.display}</p>
            <p><strong>Hệ điều hành:</strong> ${product.os}</p>
            <p><strong>Pin:</strong> ${product.battery}</p>

            <!-- Thêm nút mua hàng hoặc các nút liên quan -->
            <a href="/user/saveCart/${product.id}" class="btn btn-primary">Thêm vào giỏ hàng</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
