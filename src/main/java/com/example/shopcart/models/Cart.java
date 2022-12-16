package com.example.shopcart.models;

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
    private Long id_cart;

    private String order_status;

    private Float total_price;

    @OneToMany(mappedBy = "cart")
    private List<Product> products;

}
