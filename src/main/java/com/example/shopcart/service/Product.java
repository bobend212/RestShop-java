package com.example.shopcart.service;

import lombok.*;

@Getter
@Setter
@Builder
public class Product {
    private Long id;
    private String name;
    private Float price;

    static Product of(com.example.shopcart.repository.Product product) {
        return com.example.shopcart.service.Product.builder()
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
