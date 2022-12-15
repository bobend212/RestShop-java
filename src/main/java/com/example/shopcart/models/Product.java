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
    @Column(name = "product_id")
    private Long id;

    private String product_name;

    private Float price;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    Cart cart;
}
