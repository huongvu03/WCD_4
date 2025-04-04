package com.mytech.shopmgmt.models;

import jakarta.persistence.*;

@Entity
@Table(name = "CartLines")
public class CartLine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "shopCartId", nullable = false)
	private ShopCart shopCart;

	@ManyToOne
	@JoinColumn(name = "product_code", nullable = false)
	private Product product;

	@Column(name = "quantity")
	private int quantity;

	private Double price;

	public CartLine() {
		super();
	}

	public CartLine(Long id, Product product, int quantity, Double price) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public ShopCart getShopCart() {
		return shopCart;
	}

	public void setShopCart(ShopCart shopCart) {
		this.shopCart = shopCart;
	}

}
