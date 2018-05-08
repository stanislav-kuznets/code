package ru.otus.skuznets.hw9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.Statement;

public class Executor {
	
	String CONNECTION_STRING = "jdbc:mysql://localhost/web?user=root&password=demo";
	String QUERY_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS user(id BIGINT AUTO_INCREMENT, name VARCHAR(255), age INT(3), PRIMARY KEY(id))";
	String QUERY_SAVE = "INSERT INTO user (name, age) VALUES (?, ?)";
	String QUERY_LOAD = "SELECT * FROM user WHERE ID = (?)";
	String name;
	int age;
	
	public <T extends DataSet> void save(T user) throws Exception {
		try(Connection connection = DriverManager.getConnection(CONNECTION_STRING)) {
			PreparedStatement cmd = connection.prepareStatement(QUERY_SAVE);
				for(Field field: user.getClass().getDeclaredFields()) {
					if(field.getName().equals("name")) {
						cmd.setString(1, (String) field.get(user));
					} else if(field.getName().equals("age")) {
						cmd.setInt(2, (Integer) field.get(user));
					}
				}
			cmd.execute();
			cmd.close();
		}
	}

	public <T extends DataSet> T load(long id, Class<T> clazz) throws Exception {
		try(Connection connection = DriverManager.getConnection(CONNECTION_STRING)) {
			PreparedStatement cmd = connection.prepareStatement(QUERY_LOAD);
			cmd.setLong(1, id);
			ResultSet result = cmd.executeQuery();
			while(result.next()) {
				name = result.getString(("name"));
				age = Integer.parseInt(result.getString("age"));
			}
			UserDataSet user = (UserDataSet) clazz.newInstance();
			user.setName(name);
			user.setAge(age);
			cmd.close();
			return (T) user;
		}	
	}
	
	public void createTable() throws Exception {
		try(Connection connection = DriverManager.getConnection(CONNECTION_STRING)) {
			Statement statement = connection.createStatement();
			statement.execute(QUERY_CREATE_TABLE);
			statement.close();
		}	
	}
}
