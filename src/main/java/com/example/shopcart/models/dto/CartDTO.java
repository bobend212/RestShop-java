package com.example.shopcart.models.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.shopcart.enums.OrderStatus;
import com.example.shopcart.models.Cart;
import com.example.shopcart.models.Product;

import lombok.Data;

@Data
public class CartDTO {

    private Long id_cart;
    private OrderStatus order_status;
    private Float total_price;
    private List<ProductDTO> productsDto = new ArrayList<>();

    public static CartDTO mapFrom(Cart cart) {
        CartDTO cartDto = new CartDTO();
        cartDto.setId_cart(cart.getId_cart());
        cartDto.setOrder_status(cart.getOrder_status());
        cartDto.setProductsDto(cart.getProducts().stream().map(ProductDTO::from).collect(Collectors.toList()));
        cartDto.setTotal_price(sumPrice(cart.getProducts()));
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
