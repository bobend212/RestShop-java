package com.example.shopcart.response;

import com.example.shopcart.models.Cart;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CartResponse {

    private Long id;

    private String status;

    private Float totalPrice;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.status = cart.getStatus();
        this.totalPrice = cart.getTotalPrice();
    }
}
