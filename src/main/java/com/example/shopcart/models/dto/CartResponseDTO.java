package com.example.shopcart.models.dto;

import com.example.shopcart.common.OrderStatus;
import com.example.shopcart.models.Cart;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO {

    private Long id;

    private OrderStatus orderStatus;

    private Float totalPrice;

    public CartResponseDTO(Cart cart) {
        this.id = cart.getId();
        this.orderStatus = cart.getOrder_status();
        this.totalPrice = cart.getTotalPrice();
    }
}
