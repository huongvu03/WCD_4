<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%-- <%@ taglib prefix="c" uri="jakarta.servlet.jsp.jstl" prefix="c" %> --%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Product Management</title>
<!-- Bootstrap CSS -->
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
	max-width: 1000px;
}

.table img {
	width: 50px;
	height: 50px;
	border-radius: 5px;
}

.btn-action {
	margin-right: 5px;
}
</style>
</head>
<body>
	<div class="product-container">
		<div class="mb-3">
			<a href="dashboard.jsp" class="btn btn-secondary">⬅ Back to
				Dashboard</a>
		</div>
		<h2 class="text-center text-warning mb-4">Product Management</h2>

		<!-- Search Product -->
		<div class="row mb-3">
			<div class="col-md-8">
				<input type="text" id="searchInput" class="form-control"
					placeholder="Search by name...">
			</div>
			<div class="col-md-4">
				<button class="btn btn-warning w-100" onclick="searchProduct()">Search</button>
			</div>
		</div>

		<!-- Add Product -->
		<div class="mb-3 text-end">
			<a href="add-product.jsp" class="btn btn-success">+ Add Product</a>
		</div>
		<!-- Product List Table -->
		<table class="table table-bordered table-hover">
			<thead class="table-dark">
				<tr>
					<th>Code</th>
					<th>Name</th>
					<th>Price</th>
					<th>Image</th>
					<th>Cart</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody id="productTable">
				<%-- cách 1: JSP Scriptlet để hiển thị listProducts
						cách 2: sử dụng taglib JSTL để hiển thị listProducts --%>
					<!-- cach 2: -->
				<c:forEach var="product" items="${listProducts}">
					<tr>
						<td>${product.code}</td>
						<td>${product.name}</td>
						<td>$${product.price}</td>
						<td><img src="${product.imagePath}" alt="${product.name}"></td>
						
						<td><a href="product?action=addCart?code=${product.code}"
							class="btn btn-primary btn-sm">Add to Cart</a></td>
						<td><a href="product?action=update?code=${product.code}"
							class="btn btn-warning btn-sm btn-action">Edit</a> <a
							href="product?action=delete?code=${product.code}"
							class="btn btn-danger btn-sm">Delete</a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		function searchProduct() {
			let input = document.getElementById("searchInput").value
					.toLowerCase();
			let table = document.getElementById("productTable");
			let rows = table.getElementsByTagName("tr");

			for (let i = 0; i < rows.length; i++) {
				let nameCol = rows[i].getElementsByTagName("td")[1];
				if (nameCol) {
					let name = nameCol.textContent || nameCol.innerText;
					rows[i].style.display = name.toLowerCase().includes(input) ? ""
							: "none";
				}
			}
		}
	</script>
</body>
</html>
