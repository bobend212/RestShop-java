package com.example.shopcart.service;

import com.example.shopcart.controller.ItemDTO;
import lombok.*;

@Getter
@Builder
public class Item {
    private Long id;
    private Product product;

    static Item of(com.example.shopcart.repository.Item item) {
        return com.example.shopcart.service.Item.builder()
                //.pro(Product.of(item.getProduct()))
                .build();
    }

    public static ItemDTO toDto(Item item) {
        return ItemDTO.builder()
                .product(item.getProduct())
                .build();
    }
}
