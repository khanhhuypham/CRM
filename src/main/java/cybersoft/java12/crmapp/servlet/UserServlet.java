package cybersoft.java12.crmapp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cybersoft.java12.crmapp.DTO.UserCreateDTO;
import cybersoft.java12.crmapp.model.User;
import cybersoft.java12.crmapp.service.UserService;
import cybersoft.java12.crmapp.util.JspUtils;
import cybersoft.java12.crmapp.util.UrlUtils;


@WebServlet (name = "userServlet", urlPatterns = {
		UrlUtils.USER_DASHBOARD,
		UrlUtils.USER_PROFILE,
		UrlUtils.USER_ADD,
		UrlUtils.USER_UPDATE,
		UrlUtils.USER_DELETE
		
})
public class UserServlet extends HttpServlet {
	private UserService service;
	
	@Override
	public void init() throws ServletException {
		super.init();
		service = new UserService();
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		switch(servletPath) {
			case UrlUtils.USER_DASHBOARD:{
				getUserDashBoard(req,resp);
				break;
			}
			
			case UrlUtils.USER_PROFILE: {
				getUserProfile(req,resp);
				break;	
			}
			case UrlUtils.USER_ADD: {
				getUserAdd(req, resp);
				break;
			}
			case UrlUtils.USER_UPDATE:{
				getUserUpdate(req,resp);
				break;
			}			
			case UrlUtils.USER_DELETE:{
				getUserDelete(req,resp);
				break;
			}
		
		}
	}
	
	
	private void getUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		UserCreateDTO userDTO = null;
		try {
			userDTO = service.findUserById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("UserDTO: " +userDTO.getRoleId());
		req.setAttribute("userDTO", userDTO);
		req.getRequestDispatcher(JspUtils.USER_UPDATE).forward(req, resp);
	}


	private void getUserDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		service.deleteById(id);
		resp.sendRedirect(req.getContextPath() + UrlUtils.USER_DASHBOARD);	
	}

	private void getUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(JspUtils.USER_ADD).forward(req, resp);
	}


	private void getUserProfile(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}
	
	private void getUserDashBoard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> users = service.findAll();
		if(users != null && !users.isEmpty()) {
			req.setAttribute("users", users);
		}
		req.getRequestDispatcher(JspUtils.USER_DASHBOARD).forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		
		switch(servletPath) {
			case UrlUtils.USER_DASHBOARD:{
	
				break;
			}
			
			case UrlUtils.USER_PROFILE: {
	
				break;
				
			}
			
			case UrlUtils.USER_UPDATE:{
				postUserUpdate(req,resp);
				break;
			}
			
			case UrlUtils.USER_ADD: {
				postUserAdd(req, resp);
				break;
			}
			
			case UrlUtils.USER_DELETE:{
				break;
			}
		}
	}


	private void postUserUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserCreateDTO dto = extractDTOFromReq(req);
		int id = Integer.parseInt(req.getParameter("id"));		
		service.updateByID(dto, id);
		resp.sendRedirect(req.getContextPath() + UrlUtils.USER_DASHBOARD);
	}

	private void postUserAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserCreateDTO dto = extractDTOFromReq(req);
		service.add(dto);
		resp.sendRedirect(req.getContextPath() + UrlUtils.USER_DASHBOARD);
	}
	

	private UserCreateDTO extractDTOFromReq(HttpServletRequest req) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		int roleId = Integer.parseInt(req.getParameter("role"));
		return new UserCreateDTO(email, password, name, address, phone, roleId);
	}
}
