<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Add Product</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background: linear-gradient(135deg, #1f4037, #99f2c8);
	font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
	margin: 0;
}

.product-container {
	background: rgba(255, 255, 255, 0.95);
	border-radius: 15px;
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
	padding: 30px;
	width: 95%;
	max-width: 600px;
}
</style>
</head>
<body>
	<div class="product-container">
		<h2 class="text-center text-success">Add New Product</h2>
		<form action="products" method="post" enctype="multipart/form-data">
			<input type="hidden" name="action" value="addProduct" />
			<div class="mb-3">
				<label>Code:</label> <input type="text" class="form-control"
					name="code" required>
			</div>
			<div class="mb-3">
				<label>Name:</label> <input type="text" class="form-control"
					name="name" required>
			</div>
			<div class="mb-3">
				<label>Price:</label> <input type="number" step="0.01"
					class="form-control" name="price" required>
			</div>
			<div class="mb-3">
				<label>Image URL:</label> <input type="file" class="form-control"
					name="image" accept="image/*" >
			</div>
			<button type="submit" class="btn btn-success">Add Product</button>
			<a href="products" class="btn btn-secondary">Cancel</a>
		</form>
	</div>
</body>
</html>
