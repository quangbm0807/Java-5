<%--
  Created by IntelliJ IDEA.
  User: votha
  Date: 9/24/2024
  Time: 11:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- layout/menu.html -->

<header>
    <nav class="navbar navbar-expand-md navbar-light bg-light">
        <div class="container-fluid">
            <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#main-nav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div id="main-nav" class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item">
                        <a href="/user/home" class="nav-link text-dark">Trang Chủ</a>
                    </li>
                    <li class="nav-item">
                        <a href="/user/products" class="nav-link text-dark">Sản Phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a href="/user/cart" class="nav-link text-dark">Giỏ Hàng</a>
                    </li>
                </ul>
                <a href="/logout" class="btn btn-outline-primary ms-auto">Thoát</a>
            </div>
        </div>
    </nav>
</header>