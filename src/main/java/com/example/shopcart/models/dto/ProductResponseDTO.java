package com.example.shopcart.models.dto;

import com.example.shopcart.models.Product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private Long id_product;

    private String name;

    private Float price;

    public ProductResponseDTO(Product product) {
        this.id_product = product.getId_product();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
