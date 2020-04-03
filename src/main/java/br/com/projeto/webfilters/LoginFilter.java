package br.com.projeto.webfilters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "/LoginFilter", urlPatterns = "/index.jsp")
public class LoginFilter implements Filter {

    public LoginFilter() {
    	
    }

	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession session = httpRequest.getSession(false);

		String loginURI = httpRequest.getContextPath() + "/login.jsp";

		boolean loggedIn = session != null && session.getAttribute("email") != null;
		boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);
		
		if (loggedIn || loginRequest) {
			chain.doFilter(httpRequest, httpResponse);
		} else {
			httpResponse.sendRedirect(loginURI);
		}
		
		
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
