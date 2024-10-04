<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>LOGIN</h3>
	<b><i>${message}${param.error}</i></b>
	<form action="/account/login" method="post">
		<input name="username" placeholder="Username?"> <input
			name="password" placeholder="Password?">
		<button>Login</button>
	</form>
</body>
</html>