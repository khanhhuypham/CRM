package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cybersoft.java12.crmapp.DTO.RoleDTO;
import cybersoft.java12.crmapp.dbconnection.MySqlConnection;
import cybersoft.java12.crmapp.model.Role;

public class RoleDao {
	public List<Role> findAll() throws SQLException{
		List<Role> roles = new ArrayList<Role>();
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT id, name, description FROM crm.role;";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Role role = new Role();
				role.setID(resultSet.getInt("id"));
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
				roles.add(role);
			}
			return roles;	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return roles;
		
	}
	
	public void updateByID(RoleDTO dto, int id) throws SQLException {
		String query = "UPDATE crm.role SET name = ?, description = ? WHERE id = ?;";
		Connection connection = MySqlConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, dto.getName());
			statement.setString(2, dto.getDescription());
			statement.setInt(3, id);
			statement.executeUpdate();
			System.out.println("Update successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connection.close();
		}	
	}
	
	public Role findRoleByID(int id) throws SQLException {
		Role role = null;
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT name, description FROM crm.role WHERE id = ?;";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				role = new Role();
				role.setID(id);
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getNString("description"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return role;
	}
	
	public void add(RoleDTO roleDTO) throws SQLException {
		String query = "INSERT INTO role(name, description)"
				+ " + VALUES(?,?)";
		Connection connection = MySqlConnection.getConnection();
		try {
			PreparedStatement statement	= connection.prepareStatement(query);
			statement.setNString(1, roleDTO.getName());
			statement.setNString(2, roleDTO.getDescription());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connection.close();
		}
	}
	
	
}
