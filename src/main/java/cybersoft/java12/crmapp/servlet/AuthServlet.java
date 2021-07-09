package cybersoft.java12.crmapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.util.JspUtils;
import cybersoft.java12.crmapp.util.ServletUtils;
import cybersoft.java12.crmapp.util.UrlUtils;

@WebServlet(name = ServletUtils.AUTH, urlPatterns = {
		UrlUtils.AUTH_LOGIN,
		UrlUtils.AUTH_SIGNUP,
		UrlUtils.AUTH_LOGOUT,
		UrlUtils.AUTH_FORGOT_PASSWORD
})
public class AuthServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
			case UrlUtils.AUTH_LOGIN:{
				Cookie[] cookies = req.getCookies();
				int numberOfCookie = cookies != null ? cookies.length : 0;
				for(int i = 0; i < numberOfCookie; i ++) {
					if(cookies[i].getName().equals("email")) {
						System.out.println(cookies[i].getName());
						req.setAttribute("email", cookies[i].getValue());
					}
				}
				
				String status = String.valueOf(req.getSession().getAttribute("status"));
				System.out.println(status);
				if(!status.equals("null")) {
					resp.sendRedirect(req.getContextPath() + UrlUtils.HOME);
				}else{
					req.getRequestDispatcher(JspUtils.AUTH_LOGIN).forward(req, resp);
				}
				
				break;
			}
			
			case UrlUtils.AUTH_LOGOUT:{
				req.getSession().invalidate();
				System.out.print("log out");
				resp.sendRedirect(req.getContextPath() + UrlUtils.AUTH_LOGIN);
				break;
			}
			
			default: {throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());}
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
			case UrlUtils.AUTH_LOGIN:{
				String email = req.getParameter("email");
				String password = req.getParameter("password");
				String rememberMe = req.getParameter("rememberUserName");
//				if(rememberMe != null){
//					Cookie cookie = new Cookie("email", email);
//					cookie.setMaxAge(60*60*24*30);
//					resp.addCookie(cookie);
//				}
				System.out.printf("Email: %s, Remember: %s\n", email, rememberMe);
					
				//logic dang nhap
				
				if(logInAuthentication(email, password)) {
					Cookie cookie = new Cookie("email", email);
					cookie.setMaxAge(60*60*24*30);
					resp.addCookie(cookie);
					
					HttpSession currentSession = req.getSession();
					currentSession.setAttribute("status", "Logged in successfully");
					resp.sendRedirect(req.getContextPath() + UrlUtils.HOME);
					
				}else {
					resp.sendRedirect(req.getContextPath() + UrlUtils.AUTH_LOGIN);
				}
				break;
			}
			
			case UrlUtils.AUTH_LOGOUT: {break;}
			
			default: throw new IllegalArgumentException("Unexpected value: " + req.getServletPath());
		}
	}
	
	public static boolean logInAuthentication(String email, String password) {
		Connection connection = MySqlConnection.getConnection();
		Statement statement;
		try {
			statement = connection.createStatement();
			String query = "SELECT email, password FROM crm.user";
			ResultSet resultSet= statement.executeQuery(query);
			while(resultSet.next()) {
				if(resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
					return true;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
}
