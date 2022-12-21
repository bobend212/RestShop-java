package com.example.shopcart.models.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.shopcart.common.OrderStatus;
import com.example.shopcart.models.Cart;
import com.example.shopcart.models.Item;

import lombok.Data;

@Data
public class CartDTO {

    private Long id;
    private OrderStatus orderStatus;
    private Float totalPrice;
    private Set<ProductDTO> items = new HashSet<>();

    public static CartDTO mapToCart(Cart cart) {
        CartDTO cartDto = new CartDTO();
        cartDto.setId(cart.getId());
        cartDto.setOrderStatus(cart.getOrderStatus());
        cartDto.setItems(cart.getItems().stream().map(ProductDTO::from).collect(Collectors.toSet()));
        cartDto.setTotalPrice(sumPrice(cart.getItems()));
        return cartDto;
    }

    private static Float sumPrice(List<Item> products) {
        Float result = 0f;
        for (Item product : products) {
            result += product.getProduct().getPrice();
        }
        return result;
    }
}
