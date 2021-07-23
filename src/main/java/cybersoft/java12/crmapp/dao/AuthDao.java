package cybersoft.java12.crmapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cybersoft.java12.crmapp.DTO.UserLoginDTO;
import cybersoft.java12.crmapp.dbconnection.MySqlConnection;

public class AuthDao {
	public int login(String email, String password) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT count(id) AS result FROM crm.user WHERE email = ? AND password = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			
			if(!resultSet.next()) {
				return 0;
			}
			System.out.println(resultSet.getInt(1));
			return resultSet.getInt(1);
			
		} catch (SQLException e) {
			System.out.println("Unable to connect database");
		}finally {
			connection.close();
		}
		
		return 0;
	}
	
	public UserLoginDTO findUserLogin(String email) throws SQLException {
		Connection connection = MySqlConnection.getConnection();
		String query = "SELECT email, password FROM user WHERE email = ?";
		UserLoginDTO dto = null;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				dto = new UserLoginDTO();
				dto.setEmail(resultSet.getString("email"));
				dto.setPassword(resultSet.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return dto;
		
	}
}
