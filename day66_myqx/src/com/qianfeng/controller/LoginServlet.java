package com.qianfeng.controller;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.qianfeng.dao.IResourcesDao;
import com.qianfeng.dao.IUserDao;
import com.qianfeng.dao.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SqlSessionFactory sessionFactory;
	static{
		// 读取配置文件，获取Reader对象
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("mybatis.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 获取SqlSessionFactory对象
		sessionFactory = new SqlSessionFactoryBuilder().build(reader);
					
	}  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		SqlSession session = sessionFactory.openSession();
		IUserDao userDao = session.getMapper(IUserDao.class);
		IResourcesDao resDao = session.getMapper(IResourcesDao.class);
		
		User user = userDao.findByName(name);
		if(user.getPassword().equals(password)){
			HttpSession session2 = request.getSession();
			session2.setAttribute("loginname", name);
			
			List<com.qianfeng.dao.Resources> resList = resDao.findByUname(name);
			// 用户的资源信息存入HttpSession
			session2.setAttribute("privilege", resList);
			// 登录成功跳转到主页
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return;
					
		}
		response.sendRedirect(request.getContextPath() + "/login.jsp");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
