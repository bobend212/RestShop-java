package com.example.shopcart.models;

import com.example.shopcart.common.OrderStatus;
import com.example.shopcart.models.dto.CartDTO;
import lombok.*;

import jakarta.persistence.*;

import java.util.List;

@Table
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    private OrderStatus orderStatus;

    private Float totalPrice;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Product> products;

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(Product product) {
        products.remove(product);
    }

    public static Cart mapFrom(CartDTO cartDto) {
        Cart cart = new Cart();
        cart.setId(cartDto.getId());
        cart.setOrderStatus(cartDto.getOrderStatus());
        return cart;
    }
}
