<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
			<a href="products?action=add" class="btn btn-success">+ Add
				Product</a>
		</div>

		<!-- PHÂN TRANG -->
		<c:set var="productsPerPage" value="3" />
		<c:set var="totalProducts" value="${fn:length(listProducts)}" />
		<c:set var="totalPages"
			value="${(totalProducts / productsPerPage) + (totalProducts % productsPerPage > 0 ? 1 : 0)}" />
		<c:set var="currentPage"
			value="${param.page != null ? param.page : 1}" />
		<c:set var="startIndex" value="${(currentPage - 1) * productsPerPage}" />
		<c:set var="endIndex"
			value="${(startIndex + productsPerPage) > totalProducts ? totalProducts : (startIndex + productsPerPage)}" />

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
				<c:forEach var="i" begin="${startIndex}" end="${endIndex - 1}">
					<c:set var="product" value="${listProducts[i]}" />
					<tr>
						<td>${product.code}</td>
						<td>${product.name}</td>
						<td>$${product.price}</td>
						<td><img
							src="${pageContext.request.contextPath}/${product.imagePath}"
							alt="Product Image"></td>
						<td><a href="products?action=addCart&code=${product.code}"
							class="btn btn-primary btn-sm">Add to Cart</a></td>
						<td><a href="products?action=update&code=${product.code}"
							class="btn btn-warning btn-sm">Edit</a> <a
							href="products?action=delete&code=${product.code}"
							class="btn btn-danger btn-sm">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- Pagination -->
		<div class="text-center mt-4">
			<c:if test="${totalPages > 1}">
				<nav>
					<ul class="pagination justify-content-center">
						<!-- Previous Page -->
						<c:if test="${currentPage > 1}">
							<li class="page-item"><a class="page-link"
								href="products?page=${currentPage - 1}">Previous</a></li>
						</c:if>

						<!-- Display Page Numbers -->
						<c:set var="startPage"
							value="${currentPage - 1 > 0 ? currentPage - 1 : 1}" />
						<c:set var="endPage"
							value="${currentPage + 1 <= totalPages ? currentPage + 1 : totalPages}" />

						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<li class="page-item ${i == currentPage ? 'active' : ''}"><a
								class="page-link" href="products?page=${i}">${i}</a></li>
						</c:forEach>

						<!-- Next Page -->
						<c:if test="${currentPage < totalPages}">
							<li class="page-item"><a class="page-link"
								href="products?page=${currentPage + 1}">Next</a></li>
						</c:if>
					</ul>
				</nav>
			</c:if>
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
						rows[i].style.display = name.toLowerCase().includes(
								input) ? "" : "none";
					}
				}
			}
		</script>
</body>
</html>
