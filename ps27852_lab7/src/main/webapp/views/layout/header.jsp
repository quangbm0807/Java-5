<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    .vertical-navbar {
        height: 100%; /* Chiều cao đầy đủ của trang */
        width: 200px; /* Độ rộng của menu */
        background-color: #f8f9fa; /* Màu nền của menu */
        position: fixed; /* Đặt menu cố định bên trái */
        padding-top: 20px; /* Khoảng cách trên cùng */
    }

    .vertical-navbar a {
        padding: 10px 15px; /* Khoảng cách bên trong các mục menu */
        text-decoration: none; /* Bỏ gạch chân */
        font-size: 18px; /* Kích thước font chữ */
        color: #000; /* Màu chữ */
        display: block; /* Hiển thị mỗi mục menu thành khối */
    }

    .vertical-navbar a:hover {
        background-color: #ddd; /* Màu nền khi di chuột qua */
    }

    .vertical-navbar a.active {
        background-color: #007bff; /* Màu nền cho mục đang hoạt động */
        color: white; /* Màu chữ cho mục đang hoạt động */
    }
</style>
<header>
    <div class="vertical-navbar">
        <a href="/home" class="<c:if test='${activeUri == "/home"}'>active</c:if>">Trang chủ</a>
        <a href="/admin" class="<c:if test='${activeUri == "/admin"}'>active</c:if>">Quản lý admin</a>
        <a href="/category" class="<c:if test='${activeUri == "/category"}'>active</c:if>">Danh mục</a>
        <a href="/product" class="<c:if test='${activeUri == "/product"}'>active</c:if>">Sản phẩm</a>
        <a href="/product/RepostProuctByprice" class="<c:if test='${activeUri == "/product/RepostProuctByprice"}'>active</c:if>">Thống kê theo giá sản phẩm</a>
        <a href="/product/RepostProuctByName" class="<c:if test='${activeUri == "/product/RepostProuctByName"}'>active</c:if>">Thống kê theo tên </a>
        <a href="/product/inventoryByCategory" class="<c:if test='${activeUri == "/product/inventoryByCategory"}'>active</c:if>">Thống kê hàng tồn </a>
        <a href="/mailer" class="<c:if test='${activeUri == "/mailer"}'>active</c:if>">Gửi mail</a>
        <a href="/logout">Thoát</a>
    </div>
</header>
