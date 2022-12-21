package com.example.shopcart.controller;

import java.util.List;

import com.example.shopcart.common.OrderStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartDTO {

    private Long id;
    private OrderStatus orderStatus;
    private Float totalPrice;
    private List<ItemDTO> items;
}
