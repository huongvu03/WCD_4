package com.mytech.shopmgmt.helper;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletHelper {

	public static void forward(HttpServletRequest request, HttpServletResponse response, 
			String page) throws ServletException, IOException{
		
		request.getRequestDispatcher("/WEB-INF/" +page +".jsp").forward(request, response);
	}
	
	public static void redirect(HttpServletRequest request, HttpServletResponse response, 
	String page) throws IOException{
		response.sendRedirect(request.getContextPath()+"/"+page);
	}
}
