package cybersoft.java12.crmapp.service;

import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import cybersoft.java12.crmapp.DTO.UserCreateDTO;
import cybersoft.java12.crmapp.dao.UserDao;
import cybersoft.java12.crmapp.model.User;

public class UserService {
	private UserDao dao;
	public UserService() {
		dao = new UserDao();
	}
	
	public List<User> findAll(){
		List<User> users = null;
		try {
			users = dao.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
		
	public void deleteById(int id) {
		try {
			dao.deleteById(id);
			System.out.println("Service: Delete successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void add(UserCreateDTO dto) {
		String hashedPwd = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
		dto.setPassword(hashedPwd);
		try {
			dao.add(dto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void updateByID(UserCreateDTO dto, int id) {
		try {
			dao.updateByID(dto, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public UserCreateDTO findUserById(int id) throws SQLException {
		User user = dao.findUserById(id);
		/*
		 * cần xài hash password tại đây
		 *  
		 * */
		UserCreateDTO dto = new UserCreateDTO(user);
		if (user != null) {
			return dto;
		}
		System.out.println("user == null");
		return null;
	}
	
}
