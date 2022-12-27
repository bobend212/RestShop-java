package com.example.shopcart.service;

import com.example.shopcart.controller.ItemDTO;
import lombok.*;

@Getter
@Setter
@Builder
public class Item {
    private Long id;
    private com.example.shopcart.repository.Product product;

    static Item of(com.example.shopcart.repository.Item item) {
        return Item.builder()
                .id(item.getId())
                .product(item.getProduct())
                .build();
    }

    public static ItemDTO toDto(Item item) {
        return ItemDTO.builder()
                .productName(item.getProduct().getName())
                .productPrice(item.getProduct().getPrice())
                .build();
    }
}
