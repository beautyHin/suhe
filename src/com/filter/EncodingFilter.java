package com.filter;


import java.io.IOException;
import java.io.PrintStream;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {

	private String encoding = "UTF-8";

	public EncodingFilter() {
	}
	
	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws ServletException, IOException {
		
		req.setCharacterEncoding(this.encoding);
		resp.setCharacterEncoding(this.encoding);
				
		HttpServletRequest request = (HttpServletRequest) req;
		
		String path = request.getContextPath();
	    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	    request.setAttribute("basePath", basePath);
		chain.doFilter(req, resp);
	}

	public void destroy() {
	}
}