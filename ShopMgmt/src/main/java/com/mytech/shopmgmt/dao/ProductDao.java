package com.mytech.shopmgmt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mytech.shopmgmt.db.DbConnector;
import com.mytech.shopmgmt.models.Product;

//Data Access Object
public class ProductDao {
	private final String SELECT_ALL_PRODUCT = "SELECT * FROM products;";
	private final String GET_PRODUCT_BY_CODE = "SELECT * FROM products WHERE code=?;";
	private final String INSERT_PRODUCT = "INSERT INTO products (code, name, price, imagePath) values (?,?,?,?)";
	private final String UPDATE_PRODUCT_BY_CODE = "UPDATE products SET name=?, price=?, imagePath=? WHERE code=?";
	private final String DELETE_PRODUCT_BY_CODE = "DELETE FROM products WHERE code=?";

	public ArrayList<Product> getProducts() {
		ArrayList<Product> listProducts = new ArrayList<Product>();

		Connection connection = DbConnector.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String code = resultSet.getString("code");
				String name = resultSet.getString("name");
				Double price = resultSet.getDouble("price");
				String imagePath = resultSet.getString("imagePath");

				Product product = new Product(code, name, price, imagePath);
				listProducts.add(product);
				for (Product products : listProducts) {
					System.out.println(products.toString());
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listProducts;
	}

	public Product getProductByCode(String code) {
		Connection connection = DbConnector.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_CODE);
			preparedStatement.setString(1, code);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				Double price = resultSet.getDouble("price");
				String imagePath = resultSet.getString("imagePath");

				Product product = new Product(code, name, price, imagePath);
			}
		} catch (Exception e) {
			System.out.println("getProductByCode::exception->" + e.toString());
		}
		return null;
	}

	public boolean addProductByCode(Product product) {
		Connection connection = DbConnector.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT);
			preparedStatement.setString(1, product.getCode());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.setString(4, product.getImagePath());

		} catch (Exception e) {
			System.out.println("addProductByCode::exception->" + e.toString());
			return false;
		}
		return true;
	}

	public boolean updateProductByCode(Product product) {
		Connection connection = DbConnector.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_BY_CODE);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setDouble(2, product.getPrice());
			preparedStatement.setString(3, product.getImagePath());
			preparedStatement.setString(4, product.getCode());

		} catch (Exception e) {
			System.out.println("updateProductByCode::exception->" + e.toString());
			return false;
		}
		return true;
	}

	public boolean deleteProductByCode(String code) {
		Connection connection = DbConnector.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_CODE);
			preparedStatement.setString(1, code);

		} catch (Exception e) {
			System.out.println("deleteProductByCode::exception->" + e.toString());
			return false;
		}
		return true;
	}
}
