package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.model.Project;
import cybersoft.java12.crmapp.model.ProjectUser;
import cybersoft.java12.crmapp.model.User;
public class ProjectDao {
	private UserDao userDao;
	public List<Project> findAll(){
		List<Project> projects = new ArrayList<Project>();
		List<ProjectUser> projectUserList = new ArrayList<ProjectUser>();
		List<User> userList = null;
		try {
			userList = userDao.findAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT project.id, project.name, project.description, project.start_date, project.end_date, project.owner, projectUser.user_id, projectUser.role_description "
				+ "FROM crm.project AS project, crm.project_user AS projectUser WHERE project.id = projectUser.project_id";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Project project = new Project();
				project.setID(resultSet.getInt("project.id"));
				project.setName(resultSet.getString("project.name"));
				project.setDescription(resultSet.getString("project.description"));
				project.setStartDate(resultSet.getDate("project.start_date"));
				project.setEndDate(resultSet.getDate("project.end_date"));
				int ownerID = resultSet.getInt("project.owner");
				User owner = getUserFromList(userList, ownerID);
				project.setOwner(owner);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return projects;
		
	}
	
	
	private Project getProjectFromList(List<Project> projectList, int projectID) {
		for(Project project : projectList) {
			if(project.getID() == projectID) {
				return project;
			}
		}
		return null;
	}

	private User getUserFromList(List<User> userList, int ownerID) {
		for(User user: userList) {
			if(ownerID == user.getId()) {
				return user;
			}
		}
		return null;
	}
	
	
}
