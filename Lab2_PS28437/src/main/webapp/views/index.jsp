<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Nhập 3 cạnh</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12">
				<form action="/triangle" method="post">
					<div class="mb-3">
						<label for="canh1" class="form-label">Cạnh 1:</label> <input
							type="number" id="canh1" name="canh1" class="form-control">
					</div>
					<div class="mb-3">
						<label for="canh2" class="form-label">Cạnh 2:</label> <input
							type="number" id="canh2" name="canh2" class="form-control">
					</div>
					<div class="mb-3">
						<label for="canh3" class="form-label">Cạnh 3:</label> <input
							type="number" id="canh3" name="canh3" class="form-control">
					</div>
					<div class="mb-3">
					<h1 id="result">${result}</h1>
					<button type="submit" class="btn btn-primary">Xác định tam
						giác</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>