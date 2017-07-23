package org.company.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.company.example.data.UserData;

public class DatabaseManager {

	private final String driver = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://db.ist.utl.pt/ist176370";
	private final String user = "ist176370";
	private final String pass = "lldx0788";

	public DatabaseManager() {
	}

	private Connection getDbConnection(){
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(URL, user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public Collection<UserData> selectAllUsers() {

		Connection dbConnection = getDbConnection();

		String query = "SELECT * FROM users";

		List<UserData> userList = new ArrayList<UserData>();

		try {
			Statement st = dbConnection.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next()) {
				UserData ud = new UserData();

				String nif = rs.getString("nif");
				ud.setNif(nif);
				String name = rs.getString("name");
				ud.setName(name);
				String address = rs.getString("address");
				ud.setAddress(address);
				String contact = rs.getString("contact");
				ud.setContact(contact);

				userList.add(ud);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;

	}
	
	public UserData selectUserByNif(String nifToSearch) {
		Connection dbConnection = getDbConnection();

		String query = "SELECT * FROM users WHERE nif = ?";
		
		UserData ud = null;
		try {
			PreparedStatement st = dbConnection.prepareStatement(query);
			
			st.setString(1, nifToSearch);

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery();
			// iterate through the java resultset
			while (rs.next()) {
				ud = new UserData();
				String nif = rs.getString("nif");
				ud.setNif(nif);
				String name = rs.getString("name");
				ud.setName(name);
				String address = rs.getString("address");
				ud.setAddress(address);
				String contact = rs.getString("contact");
				ud.setContact(contact);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ud;
	}
	
	public Collection<UserData> searchUsersByName(String nameToSearch) {
		Connection dbConnection = getDbConnection();

		String query = "SELECT * FROM users WHERE name = ?";
		
		List<UserData> usersList = new ArrayList<UserData>();
		
		try {
			PreparedStatement st = dbConnection.prepareStatement(query);
			
			st.setString(1, nameToSearch);

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery();
			// iterate through the java resultset
			while (rs.next()) {
				UserData ud = new UserData();
				String nif = rs.getString("nif");
				ud.setNif(nif);
				String name = rs.getString("name");
				ud.setName(name);
				String address = rs.getString("address");
				ud.setAddress(address);
				String contact = rs.getString("contact");
				ud.setContact(contact);
				usersList.add(ud);
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usersList;
	}

	public void createUser(UserData user) {
		Connection dbConnection = getDbConnection();
		
		String query = "INSERT INTO users (nif, name, address, contact) "
				+ "VALUES (?,?,?,?)";
		
		try {
			PreparedStatement st = dbConnection.prepareStatement(query);
			
			st.setString(1, user.getNif());
			st.setString(2, user.getName());
			st.setString(3, user.getAddress());
			st.setString(4, user.getContact());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(String nif) {
		Connection dbConnection = getDbConnection();
		
		String query = "DELETE FROM users WHERE nif = ?";
		
		try {
			PreparedStatement st = dbConnection.prepareStatement(query);
			
			st.setString(1, nif);
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
