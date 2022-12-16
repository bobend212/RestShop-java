package com.example.shopcart.models.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDTO {
    private Long id_product;
    private String name;
    private Float price;
}
