package com.example.shopcart.controller;

import com.example.shopcart.service.Cart;
import com.example.shopcart.service.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDTO {
    private Long id;
    private Product product;
}
