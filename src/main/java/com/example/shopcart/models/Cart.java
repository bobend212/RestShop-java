package com.example.shopcart.models;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

import com.example.shopcart.enums.OrderStatus;

@Table
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cart;

    private OrderStatus order_status;

    private Float total_price;

    @OneToMany(mappedBy = "cart")
    private List<Product> products;

    public void addProduct(Product product) {
        products.add(product);
    }

}
