package com.mytech.shopmgmt.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ShopCarts")
public class ShopCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

//    @Column(nullable = false)
//    private BigDecimal total = BigDecimal.ZERO; // Thay thế Double bằng BigDecimal để tính toán chính xác hơn

    @OneToMany(mappedBy = "shopCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartLine> cartLines = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Ghi lại thời gian khi giỏ hàng được tạo
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Thêm sản phẩm vào giỏ hàng
    public void addCartLine(CartLine cartLine) {
        cartLines.add(cartLine);
//        cartLine.setShopCart(this);
        recalculateTotal();
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public void removeCartLine(CartLine cartLine) {
        cartLines.remove(cartLine);
        cartLine.setShopCart(null);
        recalculateTotal();
    }

    // Cập nhật tổng tiền giỏ hàng
    public void recalculateTotal() {
//        total = cartLines.stream()
//                .map(CartLine::getSubtotal)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Getters và Setters
    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

//    public BigDecimal getTotal() {
//        return total;
//    }

    public List<CartLine> getCartLines() {
        return cartLines;
    }

    public void setCartLines(List<CartLine> cartLines) {
        this.cartLines = cartLines;
        recalculateTotal();
    }
}
