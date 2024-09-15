<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.3.0/mdb.min.css"
	rel="stylesheet" />
<div class="container mt-5">
	<h1 class="text-center mb-4">Product Form</h1>

	<c:if test="${not empty error}">
		<div class="alert alert-danger" role="alert">${error}</div>
	</c:if>

	<c:if test="${not empty message}">
		<div class="alert alert-success" role="alert">${message}</div>
	</c:if>

	<form id="paramForm" action="/product/save" method="post"
		enctype="multipart/form-data">
		<input type="hidden" name="id"
			value="${product.id != null ? product.id : ''}" />
		<div class="form-outline mb-4">
			<input type="text" id="xInput" name="name" class="form-control"
				value="${product.name != null ? product.name : ''}" required /> <label
				class="form-label" for="xInput">Enter Product Name</label>
		</div>

		<div class="form-outline mb-4">
			<input type="file" id="imgInput" name="imgFile" class="form-control" />
		</div>

		<div class="mb-4">
			<img id="previewImage"
				src="/img/${product.img}"
				alt="Image Preview" style="max-width: 200px; max-height: 200px;" />
		</div>

		<div class="form-outline mb-4">
			<input type="number" step="0.01" id="yInput" name="price"
				class="form-control"
				value="${product.price != null ? product.price : ''}" required /> <label
				class="form-label" for="yInput">Enter Price</label>
		</div>

		<button type="submit" class="btn btn-primary btn-block mb-4">${product.id != null ? 'Update' : 'Add New'}</button>
		<button type="reset" class="btn btn-secondary btn-block mb-4"
			onclick="resetForm()">Reset</button>
	</form>


	<h2>Product List</h2>
	<table class="table">
		<thead>
			<tr>
				<th>Name</th>
				<th>Price</th>
				<th>Image</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="product" items="${products}">
				<tr>
					<td>${product.name}</td>
					<td>${product.price}</td>
					<td><img src="/img/${product.img}" alt="Product Image"
						style="max-width: 100px; max-height: 100px;" /></td>
					<td><a href="/product/form?id=${product.id}"
						class="btn btn-warning btn-sm">Edit</a> <a
						href="/product/delete?id=${product.id}"
						class="btn btn-danger btn-sm">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.3.0/mdb.min.js"></script>
<script>
	document
			.getElementById('imgInput')
			.addEventListener(
					'change',
					function(event) {
						const file = event.target.files[0];
						if (file) {
							const reader = new FileReader();
							reader.onload = function(e) {
								document.getElementById('previewImage').src = e.target.result;
							};
							reader.readAsDataURL(file);
						}
					});

	function resetForm() {
		document.getElementById('paramForm').reset();
		document.querySelector('button[type="submit"]').textContent = 'Add New';
		document.getElementById('previewImage').src = 'default-image.png';
	}
</script>

