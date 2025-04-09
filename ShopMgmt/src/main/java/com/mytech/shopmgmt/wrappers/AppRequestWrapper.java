package com.mytech.shopmgmt.wrappers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class AppRequestWrapper extends HttpServletRequestWrapper {
	HttpServletRequest request;
	public AppRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		System.out.println("Get parameter logger .... key:"+ name + "value: " + value);
		
		return super.getParameter(name);
	}
	
}
