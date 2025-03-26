package com.mytech.shopmgmt;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Login page init...");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("Login page destroying...");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		PrintWriter outPrintWriter = response.getWriter();
		outPrintWriter.append("You loggin with:" +username + "::"+ password);
		//doGet(request, response);
		
		//kiem tra username = admin & password= 123456
		//neu dung chuyen sang trang dashboard
		//neu sai chuyen ve trang login
		 if ("admin".equals(username) && "123456".equals(password)) {
			 RequestDispatcher requestDispatcher = request.getRequestDispatcher("dashboard.jsp");
			 requestDispatcher.forward(request, response);
			 //response.sendRedirect("dashboard.jsp"); // Thay "dashboard.jsp" bằng URL thực tế
			 } else {
				 RequestDispatcher requestDispatcher = request.getRequestDispatcher("error.jsp");
				 requestDispatcher.forward(request, response);
			  }
		
		
	}

}
