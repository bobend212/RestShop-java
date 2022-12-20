package com.example.shopcart.models.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.shopcart.common.OrderStatus;
import com.example.shopcart.models.Cart;
import com.example.shopcart.models.Product;

import lombok.Data;

@Data
public class CartDTO {

    private Long id;
    private OrderStatus orderStatus;
    private Float totalPrice;
    private List<ProductDTO> products = new ArrayList<>();

    public static CartDTO mapFrom(Cart cart) {
        CartDTO cartDto = new CartDTO();
        cartDto.setId(cart.getId());
        cartDto.setOrderStatus(cart.getOrder_status());
        cartDto.setProducts(cart.getProducts().stream().map(ProductDTO::from).collect(Collectors.toList()));
        cartDto.setTotalPrice(sumPrice(cart.getProducts()));
        return cartDto;
    }

    private static Float sumPrice(List<Product> products) {
        Float result = 0f;
        for (Product product : products) {
            result += product.getPrice();
        }
        return result;
    }
}
