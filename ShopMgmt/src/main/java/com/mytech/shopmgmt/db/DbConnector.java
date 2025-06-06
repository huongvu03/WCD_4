package com.mytech.shopmgmt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

public class DbConnector {
	static String jdbcURL = "jdbc:mysql://localhost:3306/t3shop?useSSL=false";
	static String jdbcUsername = "root";
	static String jdbcPassword = "admin@1234";

	@PersistenceUnit
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ShopManagement");

	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	public static Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}
}