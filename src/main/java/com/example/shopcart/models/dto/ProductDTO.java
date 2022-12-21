package com.example.shopcart.models.dto;

import com.example.shopcart.models.Item;
import com.example.shopcart.models.Product;
import lombok.Data;

@Data
public class ProductDTO {
    private Long id;

    private String name;

    private Float price;

    public static ProductDTO from(Item item) {
        ProductDTO productDto = new ProductDTO();
        productDto.setId(item.getId());
        productDto.setName(item.getProduct().getName());
        productDto.setPrice(item.getProduct().getPrice());
        return productDto;
    }
}
