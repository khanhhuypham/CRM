package cybersoft.java12.crmapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.util.ServletUtil;
import cybersoft.java12.crmapp.util.UrlUtils;

@WebServlet(name = ServletUtil.MONITOR, urlPatterns = {
		UrlUtils.HEALTH
})
public class MonitorServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch(path) {
			case UrlUtils.HEALTH:{
				if(MySqlConnection.getConnection() != null) {
					resp.getWriter().append("Database conenction has been established successfully!");
				}else {
					resp.getWriter().append("Database conenction could not be established!");
				}
				break;
			}
		}
	}
}
