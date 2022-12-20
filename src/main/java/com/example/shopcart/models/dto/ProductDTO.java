package com.example.shopcart.models.dto;

import com.example.shopcart.models.Product;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private Float price;

    public static ProductDTO from(Product product) {
        ProductDTO productDto = new ProductDTO();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        return productDto;
    }
}
