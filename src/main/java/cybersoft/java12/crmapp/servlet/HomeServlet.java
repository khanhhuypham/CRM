package cybersoft.java12.crmapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.crmapp.util.JspUtils;
import cybersoft.java12.crmapp.util.ServletUtils;
import cybersoft.java12.crmapp.util.UrlUtils;

@WebServlet(name = ServletUtils.HOME, urlPatterns = {UrlUtils.HOME})
public class HomeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(JspUtils.HOME).forward(req, resp);
	}
}
