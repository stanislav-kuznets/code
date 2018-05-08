package ru.otus.skuznets.hw9;

import java.sql.*;

public class Main 
{
	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static void main(String[] args) throws Exception {
		
		Class.forName(DRIVER_NAME);
		UserDataSet user1 = new UserDataSet("Алексей", 30);
		UserDataSet user2 = new UserDataSet("Юрий", 35);
       
    	Executor executor = new Executor();
    	executor.createTable();
 //   	executor.save(user1);
 //   	executor.save(user2);
    	System.out.println(executor.load(1, UserDataSet.class));
    }
}
