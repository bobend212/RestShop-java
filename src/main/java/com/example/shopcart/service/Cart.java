package com.example.shopcart.service;

import com.example.shopcart.common.OrderStatus;
import com.example.shopcart.controller.CartDTO;
import com.example.shopcart.repository.Item;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
public class Cart {
    private Long id;
    private OrderStatus orderStatus;
    private Float totalPrice;
    private List<com.example.shopcart.service.Item> items;

    static Cart of(com.example.shopcart.repository.Cart cart) {
        return com.example.shopcart.service.Cart.builder()
                .orderStatus(cart.getOrderStatus())
                .totalPrice(sumPrice(cart.getItemEntities()))
                .items(cart.getItemEntities().stream().map(com.example.shopcart.service.Item::of).toList())
                .build();
    }

    public static CartDTO toDto(Cart cart) {
        return CartDTO.builder()
                .orderStatus(cart.getOrderStatus())
                .totalPrice(cart.totalPrice)
                .items(cart.getItems().stream().map(com.example.shopcart.service.Item::toDto).toList())
                .build();
    }

    private static Float sumPrice(List<Item> products) {
        Float result = 0f;
        for (var product : products) {
            //result += product.getProduct().getPrice();
        }
        return result;
    }

}
