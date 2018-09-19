package com.qianfeng.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.dao.Resources;

/**
 * Servlet Filter implementation class PrivilegeFilter
 */
public class PrivilegeFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PrivilegeFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		String uri = req.getRequestURI();
		if(uri.contains(".jsp") || uri.contains("Login")){
			chain.doFilter(request, response);
		}else{
			// 从session中获取权限数据
			List<Resources> attribute = (List<Resources>)req.getSession().getAttribute("privilege");
			int flag = 0;
			for (Resources res : attribute) {
				if(uri.contains(res.getUrl())){
					flag = 1;
					break;
				}
			}
			if(flag == 1){
				chain.doFilter(request, response);
			}else{
				// 无权访问对应资源
				resp.sendRedirect(req.getContextPath() + "/error.jsp");
			}
			
		
		}
		
		// pass the request along the filter chain
//		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
