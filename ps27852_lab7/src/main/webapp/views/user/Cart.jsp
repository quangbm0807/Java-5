<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Giỏ hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../layout/headerUser.jsp" /> <!-- Bao gồm header -->

<div class="container">
    <h1 class="mt-5">Giỏ hàng</h1>

    <c:choose>
        <c:when test="${not empty products}">
            <table class="table table-striped mt-3">
                <thead>
                <tr>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th>Danh mục</th> <!-- Hiển thị danh mục -->
                    <th>Tổng tiền</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <!-- Lặp qua các sản phẩm trong danh sách -->
                <c:forEach var="entry" items="${products.entrySet()}">
                    <tr>
                        <td>
                            <img src="${pageContext.request.contextPath}/products/${entry.value.image}"
                                 alt="${entry.value.name}" style="width: 50px; height: 50px;" />
                        </td>
                        <td>${entry.value.price}</td>
                        <td>
                            <form action="/user/updateCart/${entry.key}" method="post">
                                <input onchange="this.form.submit()" type="number" name="quantity" value="${entry.value.quantity}" min="1" />
                            </form>
                        </td>
                        <td>${entry.value.category}</td>
                        <td>${entry.value.price * entry.value.quantity}</td>
                        <td>
                            <a href="/user/deleteCart/${entry.key}" class="btn btn-sm btn-danger">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <!-- Hiển thị tổng số tiền -->
            <div class="mt-4 d-flex justify-content-between">
                <div class="tong-tien">
                    <strong>Tổng số tiền:</strong> $<fmt:formatNumber value="${totalPrice}" type="currency" />
                </div>
                    <a href="/user/ThanhToan" class="text-white btn btn-primary">Thanh toán</a>
            </div>
        </c:when>
        <c:otherwise>
            <p>Giỏ hàng của bạn hiện đang trống.</p>
        </c:otherwise>
    </c:choose>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
