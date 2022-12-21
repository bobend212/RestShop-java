package com.example.shopcart.models.dto;

import com.example.shopcart.models.Item;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private Long id;

    private Long productId;

    public ProductResponseDTO(Item product) {
        this.id = product.getId();
        this.productId = product.getProduct().getId();
    }
}
