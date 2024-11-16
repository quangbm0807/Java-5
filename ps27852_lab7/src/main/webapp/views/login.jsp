    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-image: linear-gradient(to bottom, #000000, #434343);
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0; /* Remove default margin */
            }
            .container {
                background-color: #ffffff;
                padding: 40px;
                border-radius: 10px;
                box-shadow: 0 4px 20px rgba(0, 0, 0, 0.5);
                width: 100%;
                max-width: 400px; /* Set a max width for the form */
            }
            .btn-primary {
                background-color: #007bff;
                border-color: #007bff;
            }
            .btn-primary:hover {
                background-color: #0056b3;
                border-color: #0056b3;
            }
            .form-label {
                font-weight: bold;
            }
            .login-header {
                text-align: center;
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
    <div class="container">
        <h2 class="login-header">Đăng nhập</h2>
        <form:form action="/loginForm" method="post" modelAttribute="account">
            <!-- Username input -->
            <div class="form-outline mb-4">
                <label class="form-label" for="form1Example1">Tên người dùng</label>
                <form:input path="username" type="text" id="form1Example1" class="form-control" />
            </div>

            <!-- Password input -->
            <div class="form-outline mb-4">
                <label class="form-label" for="form1Example2">Mật khẩu</label>
                <form:input path="password" type="password" id="form1Example2" class="form-control" />
            </div>

            <!-- Remember Me checkbox -->
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="rememberMe" name="rememberMe"
                 ${rememberMe == 'true' ? 'checked' : ''}/>
                <label class="form-check-label" for="rememberMe"> Ghi nhớ tài khoản! </label>
            </div>

            <!-- Submit button -->
            <button type="submit" class="btn btn-primary btn-block">Đăng nhập</button>
        </form:form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>
