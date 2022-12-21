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

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Item> items;

    public void addProduct(Item product) {
        items.add(product);
    }

    public void deleteProduct(Item product) {
        items.remove(product);
    }

    public static Cart mapFrom(CartDTO cartDto) {
        Cart cart = new Cart();
        cart.setId(cartDto.getId());
        cart.setOrderStatus(cartDto.getOrderStatus());
        return cart;
    }
}
