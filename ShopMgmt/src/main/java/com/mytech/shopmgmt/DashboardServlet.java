package com.mytech.shopmgmt;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet({ "/dashboard" })
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Session
		HttpSession session = request.getSession();
		String usernameSession = (String) session.getAttribute("username");
		System.out.println();
		
		Cookie[] cookies = request.getCookies();
		
		String username ="";
		String loginDate ="";
		
		if (cookies != null) {
		for (Cookie cookie : cookies) {
			System.out.println("cookie:"+cookie.getName()+ cookie.getValue());
			
			if ("username".equals(cookie.getName())) {
				username = cookie.getValue();
			}else if("loginDate".equals(cookie.getName())) {
				loginDate = cookie.getValue();
			}
		}
		}
		request.setAttribute("username", username);
		request.setAttribute("loginDate", loginDate);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("dashboard.jsp");
		 requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
