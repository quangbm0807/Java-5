<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">

<style>
    body {
        background-color: #f8f9fa; /* Màu nền cho toàn bộ trang */
    }

    .container {
        background-color: #ffffff; /* Màu nền cho khung biểu mẫu */
        border-radius: 8px; /* Bo tròn các góc */
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Đổ bóng cho khung biểu mẫu */
        padding: 20px; /* Khoảng cách bên trong */
    }

    h2 {
        color: #007bff; /* Màu chữ cho tiêu đề */
        text-align: center; /* Căn giữa tiêu đề */
    }

    .form-control {
        border: 1px solid #007bff; /* Đổi màu viền cho các trường input */
    }

    .form-control:focus {
        border-color: #0056b3; /* Đổi màu viền khi trường input được chọn */
        box-shadow: 0 0 5px rgba(0, 91, 155, 0.5); /* Thêm hiệu ứng bóng khi focus */
    }

    .btn-success {
        background-color: #28a745; /* Màu nền cho nút gửi */
        border-color: #218838; /* Màu viền cho nút gửi */
    }

    .btn-success:hover {
        background-color: #218838; /* Màu nền cho nút gửi khi hover */
        border-color: #1e7e34; /* Màu viền cho nút gửi khi hover */
    }
</style>

<div class="container">
    <form enctype="multipart/form-data" action="/mailer/send" method="post" class="w-75 mx-auto">
        <h2>Gửi email</h2>

        <div class="mb-3">
            <label for="txtTo" class="form-label">To</label>
            <input type="email" class="form-control" id="txtTo" name="txtTo" placeholder="Nhập email người nhận..." required>
        </div>

        <div class="mb-3">
            <label for="txtCC" class="form-label">CC</label>
            <input type="text" class="form-control" id="txtCC" name="txtCC" placeholder="Nhập email CC, cách nhau dấu ','">
        </div>

        <div class="mb-3">
            <label for="txtBCC" class="form-label">BCC</label>
            <input type="text" class="form-control" id="txtBCC" name="txtBCC" placeholder="Nhập email BCC, cách nhau dấu ','">
        </div>


        <div class="mb-3">
            <label for="txtSubject" class="form-label">Tiêu đề</label>
            <input type="text" class="form-control" id="txtSubject" name="txtSubject" placeholder="Nhập tiêu đề..." required>
        </div>

        <div id="editor" class="form-control" style="height: 200px;"></div>
        <div class="mb-3">
            <label for="files" class="form-label">File đính kèm</label>
            <input type="file" class="form-control" id="files" name="files" multiple>
        </div>
        <div>
            <label for="sendDate">Thời gian gửi (tùy chọn):</label>
            <input type="datetime-local" id="sendDate" name="sendDate">
        </div>

        <button type="submit" class="btn btn-success mt-3">Gửi</button>
    </form>
</div>

<script src="https://cdn.quilljs.com/1.3.6/quill.min.js"></script>
<script>
    // Khởi tạo Quill
    var quill = new Quill('#editor', {
        theme: 'snow',
        modules: {
            toolbar: [
                [{ 'header': [1, 2, false] }],
                ['bold', 'italic', 'underline'],
                [{ 'color': [] }, { 'background': [] }], // Thêm lựa chọn màu chữ và màu nền
                ['image', 'code-block'],
                ['clean']  // Nút xóa định dạng
            ]
        }
    });

    // Lấy nội dung khi gửi form
    document.querySelector('form').onsubmit = function() {
        var content = quill.root.innerHTML; // Lấy nội dung HTML
        var contentInput = document.createElement('input');
        contentInput.setAttribute('type', 'hidden');
        contentInput.setAttribute('name', 'txtContent'); // Tên của trường
        contentInput.value = content; // Gán nội dung vào trường ẩn
        this.appendChild(contentInput); // Thêm vào form
    };
</script>
