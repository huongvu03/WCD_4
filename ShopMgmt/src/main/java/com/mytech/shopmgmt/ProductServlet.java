package com.mytech.shopmgmt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.mytech.shopmgmt.dao.ProductDao;
import com.mytech.shopmgmt.helper.ServletHelper;
import com.mytech.shopmgmt.models.Product;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/products")
@MultipartConfig
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	private ProductJDBCDao productDao;
	private ProductDao productDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
//		productDao = new ProductJDBCDao();
		productDao = new ProductDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// update
		String action = request.getParameter("action");
		String code = request.getParameter("code");

		if ("update".equals(action)) {
			Product product = productDao.getProductByCode(code);
			if (code != null) {
				request.setAttribute("product", product);
				ServletHelper.forward(request, response, "productUpdate");
			} else {
				ServletHelper.forward(request, response, "error");
			}

			return;
		}

		else if ("add".equals(action)) {
			// Show Add Product Form
			ServletHelper.forward(request, response, "productAdd");
			return;
		} else if ("delete".equals(action) && code != null) {
			try {
				// Thực hiện xóa sản phẩm
				productDao.deleteProduct(code);
				request.setAttribute("message", "Product deleted successfully.");
			} catch (Exception e) {
				request.setAttribute("errorMessage", "Error while deleting product. Please try again.");
			}

			// Chuyển hướng lại danh sách sản phẩm
			response.sendRedirect("products");
			return;
		}

		else {

//		ArrayList<Product> listProducts = productDao.getProducts();
			List<Product> listProducts = productDao.getProducts();
			request.setAttribute("listProducts", listProducts);

			// cách 1: JSP Scriptlet để hiển thị listProducts
			// cách 2: sử dụng taglib JSTL để hiển thị listProducts

			ServletHelper.forward(request, response, "products");

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("addProduct".equals(action)) {
			String code = request.getParameter("code");
			String name = request.getParameter("name");
			String priceString = request.getParameter("price");
			double price = Double.parseDouble(priceString);
			// Lấy file hình ảnh từ form
			Part imagePart = request.getPart("image");
			
			String imagePath = saveImage(imagePart); // Lưu hình ảnh và lấy đường dẫn

			Product newProduct = new Product(code, name, price, imagePath);
			
			productDao.addProduct(newProduct); // Gọi phương thức addProduct để lưu vào DB

			response.sendRedirect("products"); // Quay lại danh sách sản phẩm
		} else if ("update".equals(action)) {
			String code = request.getParameter("code");
			String name = request.getParameter("name");
			double price = Double.parseDouble(request.getParameter("price"));

			// Lấy thông tin sản phẩm hiện tại
			Product existingProduct = productDao.getProductByCode(code);
			String oldImagePath = existingProduct.getImagePath(); // Ảnh cũ từ DB
			String imagePath = oldImagePath; // Mặc định giữ ảnh cũ

			// Lấy hình ảnh mới từ form (nếu có)
			Part imagePart = request.getPart("image");
			try {
				if (imagePart != null && imagePart.getSize() > 0) {
					// Xóa hình cũ trước khi lưu hình mới
					if (oldImagePath != null && !oldImagePath.isEmpty()) {
						deleteOldImage(oldImagePath);
					}
					imagePath = saveImage(imagePart); // Lưu ảnh mới
				}

				Product updatedProduct = new Product(code, name, price, imagePath);
				productDao.updateProduct(updatedProduct);

				response.sendRedirect("products"); // Quay lại danh sách sản phẩm
			} catch (IllegalArgumentException e) {
				// Catch the error for invalid file type or size
				request.setAttribute("errorMessage", e.getMessage());
				request.setAttribute("product", existingProduct); // Keep existing product details
				ServletHelper.forward(request, response, "productUpdate"); // Show the form again with error
			}
		} else {
			doGet(request, response);
		}
	}

	// Phương thức để lưu hình ảnh vào thư mục và trả về đường dẫn
	private String saveImage(Part imagePart) throws IOException {
		if (imagePart != null && imagePart.getSize() > 0) {
			// Kiểm tra loại tệp
			String contentType = imagePart.getContentType();
			if (contentType == null || !contentType.startsWith("image/")) {
				// Nếu không phải là hình ảnh, thông báo lỗi
				throw new IllegalArgumentException("File must be an image.");
			}

			// Kiểm tra kích thước tệp (ví dụ 5MB)
			long maxSize = 5 * 1024 * 1024; // 5MB
			if (imagePart.getSize() > maxSize) {
				throw new IllegalArgumentException("File size exceeds the maximum limit of 5MB.");
			}

			String imageName = imagePart.getSubmittedFileName();

			// thêm biến tránh trùng lặp tên
			String fileExtension = imageName.substring(imageName.lastIndexOf("."));
			String baseName = imageName.substring(0, imageName.lastIndexOf("."));

			// Lấy đường dẫn thư mục lưu trữ
			String uploadPath = getServletContext().getRealPath("/uploads");
			File uploadDir = new File(uploadPath);
			// tạo thư mục mới nếu chưa có
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			// in đường dẫn
			System.out.println("Upload directory: " + uploadDir.getAbsolutePath());

			// Lưu file vào thư mục uploads
			File file = new File(uploadDir, imageName);

			// đổi tên nếu trùng
			int counter = 1;
			while (file.exists()) {
				// Nếu file đã tồn tại, thêm số vào tên file
				imageName = baseName + "_" + counter + fileExtension;
				file = new File(uploadDir, imageName);
				counter++;
			}
			String imagePath = "uploads/" + imageName; // Đường dẫn nơi lưu hình ảnh
			// Lưu tệp lên server bằng cách đọc dữ liệu từ imagePart và ghi ra file đích
			try (
					// Tạo luồng đọc dữ liệu từ file người dùng upload
					InputStream inputStream = imagePart.getInputStream();
					// Tạo luồng ghi dữ liệu ra file trên server (file là đối tượng File với đường
					// dẫn lưu trữ cụ thể)
					FileOutputStream outputStream = new FileOutputStream(file)) {

				// Tạo một bộ đệm 4KB để đọc và ghi từng phần của file (tránh chiếm nhiều bộ
				// nhớ)
				byte[] buffer = new byte[4096];
				// Biến để lưu số byte đọc được trong mỗi vòng lặp
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					// Ghi phần dữ liệu đã đọc vào file trên server
					outputStream.write(buffer, 0, bytesRead);
				}
			}

			return imagePath; // Trả về đường dẫn lưu trữ ảnh
		}
		return ""; // Nếu không có hình ảnh mới, trả về chuỗi rỗng
	}

	// xóa ảnh cũ khi update hình mới
	private void deleteOldImage(String imagePath) {
		String uploadPath = getServletContext().getRealPath("/") + imagePath; // Đường dẫn đầy đủ
		File file = new File(uploadPath);

		if (file.exists()) {
			file.delete(); // Xóa file cũ
			System.out.println("Đã xóa hình cũ: " + uploadPath);
		} else {
			System.out.println("Không tìm thấy hình cũ để xóa: " + uploadPath);
		}
	}

}
