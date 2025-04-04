package com.mytech.shopmgmt.dao;

import com.mytech.shopmgmt.db.DbConnector;
import com.mytech.shopmgmt.models.Customer;
import com.mytech.shopmgmt.models.Product;

import jakarta.persistence.EntityManager;

public class ShopCartDao {
	public boolean addShopCart(Customer customer, Product product, int quantity) {
		EntityManager entityManager = DbConnector.getEntityManager();
		//1. lấy giỏ hàng của customer
		//2. tạo cartline từ product với quantity
		//3. thêm CartLine vào ShopCart
		//4. lưu ShopCart
		
		return true;
	}
}
