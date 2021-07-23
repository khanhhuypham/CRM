package cybersoft.java12.crmapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.crmapp.util.JspUtils;
import cybersoft.java12.crmapp.util.UrlUtils;



@WebServlet(name = "projectServlet", urlPatterns = {
		UrlUtils.PROJECT_DASHBOARD,
		UrlUtils.PROJECT_MANAGE,
		UrlUtils.PROJECT_ADD,
		UrlUtils.PROJECT_UPDATE,
		UrlUtils.PROJECT_DELETE,
		UrlUtils.PROJECT_STAFF,
		UrlUtils.PROJECT_STAFF_ADD,
		UrlUtils.PROJECT_STAFF_REMOVE
})
public class ProjectServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		
		switch(servletPath) {
		
			case UrlUtils.PROJECT_DASHBOARD:{
				getDashBoard(req, resp);
				break;
			}
			
			case UrlUtils.PROJECT_MANAGE: {
				break;
			}
			
			case UrlUtils.PROJECT_ADD: {
				break;
			}
			
			case UrlUtils.PROJECT_UPDATE: {
				break;
			}
			
			case UrlUtils.PROJECT_DELETE: {
				break;
			}
			
			case UrlUtils.PROJECT_STAFF: {
				break;
			}
			
			case UrlUtils.PROJECT_STAFF_ADD:{}
			case UrlUtils.PROJECT_STAFF_REMOVE:{}
			
			
			
		}
		
		
	}

	private void getDashBoard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(JspUtils.PROJECT_DASHBOARD).forward(req, resp);
		
	}
}

