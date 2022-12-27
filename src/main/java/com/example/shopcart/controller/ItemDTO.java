package com.example.shopcart.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDTO {
    //private Long id;
    private String productName;
    private Float productPrice;

}
