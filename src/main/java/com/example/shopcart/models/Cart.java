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
    @Column(name = "cart_id")
    private Long id;

    private String status;

    private Float totalPrice;

    @OneToMany(mappedBy = "cart")
    private List<Product> products;

}
