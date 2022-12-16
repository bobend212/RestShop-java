package com.example.shopcart.models.dto;

import com.example.shopcart.enums.OrderStatus;
import com.example.shopcart.models.Cart;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO {

    private Long id_cart;

    private OrderStatus order_status;

    private Float total_price;

    public CartResponseDTO(Cart cart) {
        this.id_cart = cart.getId_cart();
        this.order_status = cart.getOrder_status();
        this.total_price = cart.getTotal_price();
    }
}
