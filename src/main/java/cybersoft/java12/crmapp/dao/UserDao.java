package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java12.crmapp.DTO.UserCreateDTO;
import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.model.Role;
import cybersoft.java12.crmapp.model.User;

public class UserDao {
	public List<User> findAll() throws SQLException {
		List<User> users = new LinkedList<>();
		List<Role> roles = new ArrayList<>();
		
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT u.id as id, u.name as name, u.email as email, "
				+ "u.phone as phone, u.address as address, r.id as role_id, r.name as role_name, r.description as role_description "
				+ "FROM user u, role r WHERE u.role_id = r.id;";
		  
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setPhone(resultSet.getString("phone"));
				user.setAddress(resultSet.getString("address"));
				int roleId = resultSet.getInt("role_id");
				Role role = getRoleFromList(roles, roleId);
				
				if(role == null) {
					role = new Role();
					role.setID(resultSet.getInt("role_id"));
					role.setName(resultSet.getString("role_name"));
					role.setDescription(resultSet.getString("role_description"));
					roles.add(role);
				}
				user.setRole(role);	
				users.add(user);
			}	
		} catch (SQLException e) {
			System.out.println("Unable to connect to database.");
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return users;
	}
	
	public void deleteById(int id) throws SQLException {
		String query = "DELETE FROM user WHERE id = ?;";
		String query2 = "(SELECT * FROM  crm.user JOIN\r\n"
				+ "crm.project ON (crm.user.id = crm.project.owner)\r\n"
				+ "WHERE crm.user.id = 2);";
		Connection connection = MySqlConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			System.out.println("Dao: Delete Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connection.close();
		}
	}

	private Role getRoleFromList(List<Role> roles, int roleId) {
		for(Role role : roles)
			if(role.getID() == roleId)
				return role;
		return null;
	}
	
	public void add(UserCreateDTO dto) throws SQLException {
		String query = "INSERT INTO user(email, password, name, address, phone, role_id)"
				+ " + VALUES(?,?,?,?,?,?)";
		Connection connection = MySqlConnection.getConnection();
		try {
			PreparedStatement statement	= connection.prepareStatement(query);
			statement.setNString(1, dto.getEmail());
			statement.setNString(2, dto.getPassword());
			statement.setNString(3, dto.getName());
			statement.setNString(4, dto.getAddress());
			statement.setNString(5, dto.getPhone());
			statement.setInt(5, dto.getRoleId());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connection.close();
		}
		
	}
	
	
	public User findUserById(int id) throws SQLException {
		User user = null;
		String query = "SELECT user.id, user.email, user.password, user.name, user.phone, user.role_id, role.id ,role.name "
				+ "FROM crm.user AS user, crm.role AS role WHERE user.id = ? AND role_id = role.id;";
		Connection connection = MySqlConnection.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("user.id"));
				user.setEmail(resultSet.getString("user.email"));
				user.setPassword(resultSet.getString("user.password"));
				user.setName(resultSet.getString("user.name"));
				user.setPhone(resultSet.getString("user.phone"));
				
				int roleId = resultSet.getInt("user.role_id");
				Role role = new Role();
				role.setID(resultSet.getInt("role.id"));
				role.setName(resultSet.getString("role.name"));
				user.setRole(role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return user;
	}
	
	
	public void updateByID(UserCreateDTO dto, int id) throws SQLException {
		String query = "UPDATE crm.user SET email = ?, password = ?, name = ?, address = ?, phone = ? WHERE id = ?;";
		Connection connection = MySqlConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, dto.getEmail());
			statement.setString(2, dto.getPassword());
			statement.setString(3, dto.getName());
			statement.setString(4, dto.getAddress());
			statement.setString(5, dto.getPhone());
			statement.setInt(6, id);
			statement.executeUpdate();
			System.out.println("Update successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connection.close();
		}	
	}
}
