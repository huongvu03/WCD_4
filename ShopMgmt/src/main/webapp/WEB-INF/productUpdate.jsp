<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Update Product</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background: linear-gradient(135deg, #1f4037, #99f2c8);
	font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
	height: 100vh;
	margin: 0;
	display: flex;
	justify-content: center;
	align-items: center;
}

.product-container {
	background: rgba(255, 255, 255, 0.95);
	border-radius: 15px;
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
	padding: 30px;
	width: 95%;
	max-width: 600px;
}

.form-label {
	font-weight: bold;
}
</style>
</head>
<body>
	<div class="product-container">
		<div class="mb-3">
			<a href="products" class="btn btn-secondary">⬅ Back to Products</a>
		</div>
		<h2 class="text-center text-warning mb-4">Update Product</h2>
		<form action="products?action=update" method="post"
			enctype="multipart/form-data">
			<div class="mb-3">
				<label class="form-label">Code:</label> <input type="text"
					class="form-control" name="code" value="${product.code}" readonly>
			</div>
			<div class="mb-3">
				<label class="form-label">Name:</label> <input type="text"
					class="form-control" name="name" value="${product.name}" required>
			</div>
			<div class="mb-3">
				<label class="form-label">Price:</label> <input type="number"
					step="0.01" class="form-control" name="price"
					value="${product.price}" required>
			</div>
			<div class="mb-3">
				<label class="form-label">Image URL:</label> <input type="file"
					class="form-control" name="image"> <br> <label>Current
					Image:</label> <img
					src="${pageContext.request.contextPath}/${product.imagePath}"
					alt="Product Image" width="200">
			</div>
			<!-- Error message for invalid image -->
			<c:if test="${not empty errorMessage}">
				<div class="alert alert-danger mt-2">${errorMessage}</div>
			</c:if>
			<div class="text-center">
				<button type="submit" class="btn btn-success">Update</button>
				<a href="products" class="btn btn-secondary">Cancel</a>
			</div>
		</form>
	</div>
</body>
</html>