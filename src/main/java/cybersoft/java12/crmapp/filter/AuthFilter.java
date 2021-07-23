package cybersoft.java12.crmapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.crmapp.util.JspUtils;
import cybersoft.java12.crmapp.util.UrlUtils;

@WebFilter(urlPatterns = UrlUtils.ROOT)
public class AuthFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {	
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String servletPath = req.getServletPath();
		if(servletPath.startsWith(UrlUtils.ASSETS) || servletPath.startsWith(UrlUtils.AUTH_LOGIN) || servletPath.startsWith(UrlUtils.AUTH_SIGNUP)) {
			chain.doFilter(request, response);
		}else {	
			String status = (String) req.getSession().getAttribute("status");
			System.out.println("Status: " + status);
			if(status == null) {
				resp.sendRedirect(req.getContextPath() + UrlUtils.AUTH_LOGIN);
			}else {
				chain.doFilter(request, response);
			}	
		}
	}

}
