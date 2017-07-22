package org.company.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
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

	private Connection getDbConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		return DriverManager.getConnection(URL, user, pass);
	}

	public List<UserData> selectAllUsers() {

		Connection dbConnection = null;
		try {
			dbConnection = getDbConnection();
		} catch (ClassNotFoundException e1) {
			System.out.println("----------------------------------------");
			System.out.println("erro class not found");
			System.out.println("----------------------------------------");
			return Collections.emptyList();
		} catch (SQLException e1) {
			System.out.println("----------------------------------------");
			System.out.println("erro sql");
			System.out.println("----------------------------------------");
			return Collections.emptyList();
		}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;

	}

	public static void main(String[] args) {
		System.out.println("trying");
		DatabaseManager db = new DatabaseManager();
		for (UserData ud : db.selectAllUsers()) {
			System.out.println(ud);
		}
	}

}
