package com.example.shopcart.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    EMPTY("EMPTY"), // default state, nothing in the cart
    PENDING("PENDING"), // when products are in the cart already
    PROCESSING("PROCESSING"), // when payment has been confirmed
    COMPLETE("COMPLETE"), // purchase has been delivered successfully
    CANCELED("CANCELED"); // purchase canceled during payment phase

    private final String value;
}
