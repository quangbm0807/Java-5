<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Phones List</title>
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
	<nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top">
		<div class="container">
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link"
						href="/products/management">Phone Management</a></li>
					<li class="nav-item"><a class="nav-link active"
						href="/products/list">Phone List</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container mt-5">
		 <h2 class="mb-4 text-center">
            Phone List 
            <a href="/cart" class="btn btn-primary">
                <i class="fas fa-shopping-cart"></i>
                <span class="badge rounded-pill badge-notification bg-danger">${cartSize}</span>
            </a>
        </h2>
        
        <!-- Search and Filter Form -->
        <form action="/products/list" method="get" class="mb-4">
            <div class="row g-3">
                <div class="col-md-4">
                    <input type="text" class="form-control" name="keyword" value="${keyword}" placeholder="Search by name">
                </div>
                <div class="col-md-2">
                    <input type="number" class="form-control" name="minPrice" value="${minPrice}" placeholder="Min Price">
                </div>
                <div class="col-md-2">
                    <input type="number" class="form-control" name="maxPrice" value="${maxPrice}" placeholder="Max Price">
                </div>
                <div class="col-md-2">
                    <select class="form-select" name="sort">
                        <option value="name" ${sort == 'name' ? 'selected' : ''}>Name</option>
                        <option value="price" ${sort == 'price' ? 'selected' : ''}>Price</option>
                    </select>
                </div>
                <div class="col-md-1">
                    <select class="form-select" name="dir">
                        <option value="asc" ${dir == 'asc' ? 'selected' : ''}>Asc</option>
                        <option value="desc" ${dir == 'desc' ? 'selected' : ''}>Desc</option>
                    </select>
                </div>
                <div class="col-md-1">
                    <button type="submit" class="btn btn-primary w-100">Filter</button>
                </div>
            </div>
        </form>

		<div class="row">
			<c:forEach items="${products}" var="product">
				<div class="col-lg-3 col-md-4 col-sm-6 mb-4">
					<div class="card h-100">
						<img src="/proxy-image?id=${product.imageUrl}"
							class="card-img-top" alt="${product.name}">
						<div class="card-body">
							<h5 class="card-title">${product.name}</h5>
							<p class="card-text">Price: $${product.price}</p>
						</div>
						<div class="card-footer text-center">
							<a href="/cart/add/${product.id}"
								class="btn btn-primary btn-block"> <i
								class="fas fa-shopping-cart"></i> Add to Cart
							</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<nav aria-label="Page navigation" class="mt-4">
			<ul class="pagination justify-content-center">
				<li class="page-item ${currentPage == 0 ? 'disabled' : ''}"><a
					class="page-link"
					href="/products/list?page=${currentPage - 1}&size=4&sort=${sort}&dir=${dir}&keyword=${keyword}&minPrice=${minPrice}&maxPrice=${maxPrice}">Previous</a>
				</li>
				<c:forEach begin="0" end="${totalPages - 1}" var="i">
					<li class="page-item ${currentPage == i ? 'active' : ''}"><a
						class="page-link"
						href="/products/list?page=${i}&size=4&sort=${sort}&dir=${dir}&keyword=${keyword}&minPrice=${minPrice}&maxPrice=${maxPrice}">${i + 1}</a>
					</li>
				</c:forEach>
				<li
					class="page-item ${currentPage == totalPages - 1 ? 'disabled' : ''}">
					<a class="page-link"
					href="/products/list?page=${currentPage + 1}&size=4&sort=${sort}&dir=${dir}&keyword=${keyword}&minPrice=${minPrice}&maxPrice=${maxPrice}">Next</a>
				</li>
			</ul>
		</nav>
	</div>

	<!-- MDB -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"></script>
</body>
</html>
