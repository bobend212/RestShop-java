package com.example.shopcart.models;

import jakarta.persistence.*;
import lombok.*;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_product;

    private String name;

    private Float price;

    @ManyToOne
    @JoinColumn(name = "id_cart")
    Cart cart;
}
