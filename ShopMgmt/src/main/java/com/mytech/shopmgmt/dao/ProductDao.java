package com.mytech.shopmgmt.dao;

import java.util.List;

import com.mytech.shopmgmt.db.DbConnector;
import com.mytech.shopmgmt.models.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class ProductDao {
	public List<Product> getProducts() {
		EntityManager entityManager = DbConnector.getEntityManager();

		Query query = entityManager.createNamedQuery("Product.findAll", Product.class);

		return query.getResultList();
	}

	public Product getProductByCode(String code) {
		EntityManager entityManager = DbConnector.getEntityManager();
		Query query = entityManager.createNamedQuery("Product.findByCode", Product.class);
		query.setParameter("code", code);
		return (Product) query.getSingleResult();
//		try {
//			return entityManager.createNamedQuery("Product.findByCode", Product.class).setParameter("code", code)
//					.getSingleResult();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
	}

	public Product getProductByName(String name) {
		EntityManager entityManager = DbConnector.getEntityManager();
		Query query = entityManager.createNamedQuery("Product.findByName", Product.class);
		query.setParameter("name", "%" + name + "%");
		return (Product) query.getSingleResult();
	}

	public void updateProduct(Product updatedProduct) {
		EntityManager entityManager = DbConnector.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			// Tìm sản phẩm cần cập nhật
			Product existingProduct = entityManager.find(Product.class, updatedProduct.getCode());

			if (existingProduct != null) {
				// Cập nhật thông tin sản phẩm
				existingProduct.setName(updatedProduct.getName());
				existingProduct.setPrice(updatedProduct.getPrice());
				existingProduct.setImagePath(updatedProduct.getImagePath());

				entityManager.merge(existingProduct); // Cập nhật vào DB
				entityManager.getTransaction().commit();
			} else {
				System.out.println("Product not found!");
			}
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	public boolean addProduct1(Product product) {
		EntityManager entityManager = DbConnector.getEntityManager();

		entityManager.persist(product);
		return true;
	}

	public boolean updateProductByCode1(Product product) {
		EntityManager entityManager = DbConnector.getEntityManager();

		Product updProduct = entityManager.find(Product.class, product.getCode());
		updProduct.setName(product.getName());
		updProduct.setPrice(product.getPrice());
		updProduct.setImagePath(product.getImagePath());
		entityManager.merge(updProduct);
		return true;
	}

	public boolean deleteProductByCode1(String code) {
		EntityManager entityManager = DbConnector.getEntityManager();

		Product delProduct = entityManager.find(Product.class, code);
		entityManager.remove(delProduct);
		return true;
	}

	// Phương thức thêm sản phẩm
	public void addProduct(Product newProduct) {
		EntityManager entityManager = DbConnector.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			// Thêm sản phẩm mới vào cơ sở dữ liệu
			entityManager.persist(newProduct);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	public void deleteProduct(String code) {
		EntityManager entityManager = DbConnector.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			Product product = entityManager.find(Product.class, code);

			if (product != null) {
				entityManager.remove(product);
				entityManager.getTransaction().commit();
				System.out.println("Product with code " + code + " deleted successfully.");
			} else {
				System.out.println("Product with code " + code + " not found!");
			}
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.err.println("Error while deleting product with code " + code);
			e.printStackTrace();

		} finally {
			entityManager.close();
		}
	}

}
