package com.example.shopcart.models.dto;

import com.example.shopcart.models.Item;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private Long id;

    private String name;

    private Float price;

    public ProductResponseDTO(Item product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
