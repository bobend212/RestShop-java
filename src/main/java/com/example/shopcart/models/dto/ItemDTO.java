package com.example.shopcart.models.dto;

import com.example.shopcart.models.Item;

import com.example.shopcart.models.Product;
import lombok.Data;

@Data
public class ItemDTO {

    private Long id;
    private String name;
    private Float price;

    public static ItemDTO from(Product product) {
        ItemDTO productDto = new ItemDTO ();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
//        productDto.setPrice(product.getPrice());
        return productDto;
    }
}
