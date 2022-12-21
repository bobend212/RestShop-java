package com.example.shopcart.common;

public enum OrderStatus {
    EMPTY("EMPTY"), // default state, nothing in the cart
    PENDING("PENDING"), // when products are in the cart already
    PROCESSING("PROCESSING"), // when payment has been confirmed
    COMPLETE("COMPLETE"), // purchase has beed delivered sucsessfully
    CANCELED("CANCELED"); // purchase canceled during payment phase

    private String statusOrder;

    OrderStatus(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getStatusOrder() {
        return statusOrder;
    }
}
