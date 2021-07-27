package cybersoft.java12.crmapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java12.crmapp.model.Task;
import cybersoft.java12.crmapp.service.TaskService;
import cybersoft.java12.crmapp.util.JspUtils;
import cybersoft.java12.crmapp.util.UrlUtils;


@WebServlet(name = "taskServlet", urlPatterns = {
		UrlUtils.TASK_DASHBOARD,
		UrlUtils.TASK_ADD,
		UrlUtils.TASK_UPDATE,
		UrlUtils.TASK_DELETE})

public class TaskServlet extends HttpServlet {
	private TaskService service;
	
	@Override
	public void init() throws ServletException {
		service = new TaskService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathServlet = req.getServletPath();
		switch(pathServlet) {
			case UrlUtils.TASK_DASHBOARD:{
				getTaskDashBoard(req,resp);
				break;
			} 
		
		}
	}

	private void getTaskDashBoard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Task> tasks = service.findTask();
		if (tasks != null && !tasks.isEmpty())
			req.setAttribute("tasks", tasks);

		req.getRequestDispatcher(JspUtils.TASK_DASHBOARD).forward(req, resp);
	}

}
