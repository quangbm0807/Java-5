<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>LapTop.vn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/styles.css"> <!-- Thêm đường dẫn đến file CSS tùy chỉnh -->
    <style>
        /* CSS tùy chỉnh cho giao diện trang chủ */
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }
        .header-title {
            text-align: center;
            margin: 20px 0;
        }
        .footer {
            background-color: #343a40;
            color: white;
            text-align: center;
            padding: 20px 0;
            position: relative;
            bottom: 0;
            width: 100%;
        }
        /* Thêm CSS cho carousel */
        .carousel-inner img {
            height: 500px; /* Chiều cao của hình ảnh trong carousel */
        }
    </style>

</head>
<body>
<jsp:include page="../layout/headerUser.jsp"/>
<div class="container mt-5">
    <h1 class="header-title">Chào Mừng Đến Với LapTop.vn</h1>

    <!-- Slider sản phẩm nổi bật -->
    <div id="productCarousel" class="carousel slide mb-4" data-bs-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="${pageContext.request.contextPath}/products/01.jpg" class="d-block w-100" alt="Sản phẩm 1">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/products/02.jpg" class="d-block w-100" alt="Sản phẩm 2">
            </div>
            <div class="carousel-item">
                <img src="${pageContext.request.contextPath}/products/03.jpg" class="d-block w-100" alt="Sản phẩm 3">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#productCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#productCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</div>

<div class="footer">
    <p>© 2024 LapTop.vn. Tất cả các quyền được bảo lưu.</p>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
