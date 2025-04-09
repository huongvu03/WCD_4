package com.mytech.shopmgmt.filters;

import java.io.IOException;

import com.mytech.shopmgmt.helper.ServletHelper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class UsernameFilter
 */
//thêm dòng này
@WebFilter(urlPatterns = { "/users",
		"/login" }, initParams = @WebInitParam(name = "notAllowedName", value = "facebook,google,zalo"))

public class UsernameFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 8144985062131488599L;
	private String[] namesNotAllowed;

	public UsernameFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String username = request.getParameter("username");

		if (checkUsernameNotAllowed(username)) {
			httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
			httpRequest.setAttribute("message", "username is not allowed");
			ServletHelper.forward(httpRequest, httpResponse, "login");
		} else {
			chain.doFilter(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String notAllowedName = fConfig.getInitParameter("notAllowedName");
		namesNotAllowed = notAllowedName.split(",");
		System.out.println("Name are not allowed:" + namesNotAllowed);

	}

	// check username is not allowed
	public boolean checkUsernameNotAllowed(String username) {
		if (username == null) {
			return false; // nếu username null thì không bị cấm
		}
		for (String name : namesNotAllowed) {
			if (username.equalsIgnoreCase(name.trim())) {
				return true; // nếu username trùng với 1 tên bị cấm
			}
		}
		return false; // username hợp lệ
	}
}
