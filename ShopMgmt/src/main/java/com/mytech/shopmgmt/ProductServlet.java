package com.mytech.shopmgmt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
			String imagePath = existingProduct.getImagePath(); // Mặc định giữ ảnh cũ

			// Lấy hình ảnh mới từ form (nếu có)
			Part imagePart = request.getPart("image");
			if (imagePart != null && imagePart.getSize() > 0) {
				imagePath = saveImage(imagePart); // Lưu ảnh mới
			}

			Product updatedProduct = new Product(code, name, price, imagePath);
			productDao.updateProduct(updatedProduct);

			response.sendRedirect("products"); // Quay lại danh sách sản phẩm
		} else {
			doGet(request, response);
		}
	}

	// Phương thức để lưu hình ảnh vào thư mục và trả về đường dẫn
	private String saveImage(Part imagePart) throws IOException {
		if (imagePart != null && imagePart.getSize() > 0) {
			String imageName = imagePart.getSubmittedFileName();
			String imagePath = "uploads/" + imageName; // Đường dẫn nơi lưu hình ảnh

			// Tạo thư mục nếu chưa có
//			File uploadDir = new File(getServletContext().getRealPath("/uploads"));
//			if (!uploadDir.exists()) {
//				uploadDir.mkdirs(); // Tạo cả thư mục cha nếu cần
//			}
			String uploadPath = "D:/FPT_APTECH/HK4/1_WCD/uploads"; // Hoặc "/opt/uploads" trên Linux
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
			    uploadDir.mkdirs();
			}

			System.out.println("Upload directory: " + uploadDir.getAbsolutePath());
			// Lưu file vào thư mục uploads
			File file = new File(uploadDir, imageName);
			try (InputStream inputStream = imagePart.getInputStream();
					FileOutputStream outputStream = new FileOutputStream(file)) {
				byte[] buffer = new byte[4096];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
			}

			return imagePath; // Trả về đường dẫn lưu trữ ảnh
		}
		return ""; // Nếu không có hình ảnh mới, trả về chuỗi rỗng
	}

}
