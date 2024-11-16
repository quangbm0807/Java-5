<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style>
    body {
        background-color: #f0f2f5; /* Màu nền sáng hơn */
        font-family: Arial, sans-serif;
    }

    h1 {
        color: #333; /* Màu chữ tiêu đề */
        margin-bottom: 30px;
    }

    .form-group label {
        font-weight: bold;
        color: #495057;
    }

    .btn-primary {
        background-color: #007bff; /* Màu nền cho nút */
        border-color: #007bff;
        transition: background-color 0.3s;
    }

    .btn-primary:hover {
        background-color: #0056b3; /* Màu nền khi hover */
        border-color: #0056b3;
    }

    .table {
        margin-top: 20px;
        border-radius: 0.5rem; /* Bo tròn góc cho bảng */
        overflow: hidden; /* Giúp bo tròn góc bảng */
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Tạo bóng cho bảng */
    }

    .thead-dark th {
        background-color: #343a40; /* Màu nền cho tiêu đề bảng */
        color: white; /* Màu chữ tiêu đề bảng */
    }

    .img-thumbnail {
        border-radius: 0.25rem; /* Bo tròn hình ảnh */
    }

    .pagination .page-item.active .page-link {
        background-color: #007bff; /* Màu nền cho trang hiện tại */
        border-color: #007bff; /* Màu viền cho trang hiện tại */
    }

    .pagination .page-link {
        color: #007bff; /* Màu chữ cho các trang */
    }

    .pagination .page-link:hover {
        color: #0056b3; /* Màu chữ khi hover */
    }
</style>

<h1 class="text-center">Thống kê hàng tồn </h1>

<form action="/product/selectCategory" method="post">
    <div class="row d-flex">
        <div class="col">
            <div class="form-group">
                <label for="name">Danh mục sản phẩm tồn kho:</label>
                <select name="categoryId" class="form-control" onchange="this.form.submit()">
                    <option value="null">Tất cả danh mục</option>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}" ${category.id == categoryId ? 'selected' : ''}>${category.name}</option>
                    </c:forEach>
                </select>
                </div>
        </div>
    </div>
</form>
<table class="table table-striped">
    <thead class="thead-dark">
    <tr>
        <th><a href="/product/inventoryByCategory?field=o.category.name" class="text-light text-decoration-none">Loại hàng</a></th>
        <th><a href="/product/inventoryByCategory?field=totalPrice" class="text-light text-decoration-none">Tổng giá</a></th>
        <th><a href="/product/inventoryByCategory?field=totalQuantity" class="text-light text-decoration-none">Số lượng sản phẩm</a></th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${not empty products}">
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.group}</td>
                <td>
                    <fmt:formatNumber value="${product.sum}" type="currency" />
                </td>
                <td>${product.count}</td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>


<div class="row">
    <div class="col d-flex justify-content-center">
        <nav aria-label="Page navigation ">
            <ul class="pagination d-flex justify-content-center ">
                <!-- Kiểm tra totalPages > 0 trước khi lặp -->
                <c:if test="${totalPages > 0}">
                    <c:forEach begin="0"  end="${totalPages - 1}" var="i">
                        <li class="page-item text-dark ${currentPage == i ? 'active' : ''}" >
                            <a  style="color:#343a40 " class="page-link" href="/product/inventoryByCategory?page=${i}&size=${size}">${i + 1}</a>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
        </nav>
    </div>
</div>
