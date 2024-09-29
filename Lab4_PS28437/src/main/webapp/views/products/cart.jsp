<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Shopping Cart</title>
<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	rel="stylesheet" />
<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
	rel="stylesheet" />
<!-- MDB -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.css"
	rel="stylesheet" />
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container">
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link"
						href="/products/management">Phone Management</a></li>
					<li class="nav-item"><a class="nav-link" href="/products/list">PhoneList</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container mt-5">
		<h2 class="mb-4">Shopping Cart</h2>
		<c:if test="${empty cartItems}">
			<p>Your cart is empty.</p>
		</c:if>
		<c:if test="${not empty cartItems}">
			<div class="row">
				<div class="col-8">
					<c:forEach items="${cartItems}" var="item">
						<div class="col-lg-3 col-md-4 col-sm-6 mb-4">
							<div class="card h-100">
								<img src="/proxy-image?id=${item.imageUrl}" class="card-img-top"
									alt="${item.name}">
								<div class="card-body">
									<h5 class="card-title">${item.name}</h5>
									<p class="card-text">Price: $${item.price}</p>
									<p class="card-text">Quantity: ${item.quantity}</p>
									<div class="d-flex justify-content-between align-items-center">
										<form action="/cart/decrease/${item.id}" method="post">
											<button type="submit" class="btn btn-secondary btn-sm">
												<i class="fas fa-minus"></i>
											</button>
										</form>
										<span>${item.quantity}</span>
										<form action="/cart/increase/${item.id}" method="post">
											<button type="submit" class="btn btn-secondary btn-sm">
												<i class="fas fa-plus"></i>
											</button>
										</form>
									</div>
								</div>
								<div class="card-footer text-center">
									<form action="/cart/remove/${item.id}" method="post">
										<button type="submit" class="btn btn-danger btn-sm">
											<i class="fas fa-trash"></i> Remove
										</button>
									</form>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="col-4">
					<div class="mt-4">
						<a href="/cart/clear" class="btn btn-warning">Clear Cart</a> <a
							href="/checkout" class="btn btn-success">Proceed to Checkout</a>
						<a href="/products/list" class="btn btn-primary mt-3">Continue
							Shopping</a>
					</div>
					<div class="mt-4">
						<h4>Cart Summary</h4>
						<p>
							Total: $
							<c:out value="${totalPrice}" />
						</p>
					</div>
				</div>
				<div class="mt-4"></div>
			</div>

		</c:if>

	</div>

	<!-- MDB -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"></script>
</body>
</html>