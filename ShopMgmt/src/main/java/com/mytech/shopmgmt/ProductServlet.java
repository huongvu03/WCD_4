package com.mytech.shopmgmt;

import java.io.IOException;
import java.util.ArrayList;

import com.mytech.shopmgmt.dao.ProductDao;
import com.mytech.shopmgmt.helper.ServletHelper;
import com.mytech.shopmgmt.models.Product;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/products")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
		productDao = new ProductDao();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Product> listProducts = productDao.getProducts();
		request.setAttribute("listProducts", listProducts);
		
		//cách 1: JSP Scriptlet để hiển thị listProducts
		//cách 2: sử dụng taglib JSTL để hiển thị listProducts
		
		ServletHelper.forward(request, response, "products");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
