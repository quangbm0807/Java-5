<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Phones Management</title>
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
                    <li class="nav-item">
                        <a class="nav-link active" href="/products/management">Phone Management</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/products/list">PhoneList</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
	<div class="container mt-5">
		<h2 class="mb-4">Product List</h2>
		<button type="button" class="btn btn-primary mb-3"
			data-mdb-toggle="modal" data-mdb-target="#addProductModal">
			Add New Product</button>
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Price</th>
						<th>Image</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${products}" var="product">
						<tr>
							<td>${product.id}</td>
							<td>${product.name}</td>
							<td>${product.price}</td>
							<td><img src="/proxy-image?id=${product.imageUrl}"
								width="100" class="img-thumbnail"></td>
							<td>
								<button type="button" class="btn btn-sm btn-warning"
									data-mdb-toggle="modal"
									data-mdb-target="#editProductModal${product.id}">Edit
								</button> <a href="/products/delete/${product.id}"
								class="btn btn-sm btn-danger">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<!-- Add Product Modal -->
	<div class="modal fade" id="addProductModal" tabindex="-1"
		aria-labelledby="addProductModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="addProductModalLabel">Add New
						Product</h5>
					<button type="button" class="btn-close" data-mdb-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="/products/create" method="post"
						enctype="multipart/form-data">
						<div class="mb-3">
							<label for="name" class="form-label">Name</label> <input
								type="text" class="form-control" id="name" name="name" required>
						</div>
						<div class="mb-3">
							<label for="price" class="form-label">Price</label> <input
								type="number" class="form-control" id="price" name="price"
								required>
						</div>
						<div class="mb-3">
							<label for="image" class="form-label">Image</label> <input
								type="file" class="form-control" id="image" name="image"
								required>
						</div>
						<button type="submit" class="btn btn-primary">Add Product</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Edit Product Modals -->
	<c:forEach items="${products}" var="product">
		<div class="modal fade" id="editProductModal${product.id}"
			tabindex="-1" aria-labelledby="editProductModalLabel${product.id}"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="editProductModalLabel${product.id}">Edit
							Product</h5>
						<button type="button" class="btn-close" data-mdb-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form action="/products/update/${product.id}" method="post"
							enctype="multipart/form-data">
							<div class="mb-3">
								<label for="name${product.id}" class="form-label">Name</label> <input
									type="text" class="form-control" id="name${product.id}"
									name="name" value="${product.name}" required>
							</div>
							<div class="mb-3">
								<label for="price${product.id}" class="form-label">Price</label>
								<input type="number" class="form-control"
									id="price${product.id}" name="price" value="${product.price}"
									required>
							</div>
							<div class="mb-3">
								<img src="/proxy-image?id=${product.imageUrl}"
									alt="${product.name}" class="img-thumbnail" width="100">
							</div>
							<div class="mb-3">
								<label for="image${product.id}" class="form-label">New
									Image (Optional)</label> <input type="file" class="form-control"
									id="image${product.id}" name="image">
							</div>
							<button type="submit" class="btn btn-primary">Update
								Product</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>


	<!-- MDB -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.2.0/mdb.min.js"></script>
</body>
</html>