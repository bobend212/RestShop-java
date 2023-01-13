package com.example.shopcart.controller;

import lombok.Builder;

@Builder
public record ProductCreateDTO(String name, Float price) {
}
