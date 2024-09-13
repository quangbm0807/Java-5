<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Kết quả đăng ký</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Thông tin đăng ký</h2>
        <div class="card">
            <div class="card-body">
                <p><strong>Họ:</strong> ${lastName}</p>
                <p><strong>Tên:</strong> ${firstName}</p>
                <p><strong>Tỉnh/Thành phố:</strong> ${province}</p>
                <p><strong>Quận/Huyện:</strong> ${district}</p>
                <p><strong>Phường/Xã:</strong> ${ward}</p>
                <p><strong>Tệp đã tải lên:</strong> ${fileName}</p>
                <c:if test="${not empty imagePath}">
                    <img src="${imagePath}" alt="Uploaded Image" class="img-fluid mt-3">
                </c:if>
            </div>
        </div>
        <a href="/" class="btn btn-primary mt-3">Quay lại trang đăng ký</a>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>