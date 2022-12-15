package com.example.shopcart.controller;

import com.example.shopcart.models.Cart;
import com.example.shopcart.repository.CartRepository;
import com.example.shopcart.response.CartResponse;
import com.example.shopcart.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping()
    public ResponseEntity<List<CartResponse>> getAllCarts() {
        return new ResponseEntity<>(cartService.getAllCarts(), HttpStatus.OK);
    }

    @PostMapping()
    public Cart postCart(@RequestBody Cart cart) {
        return cartService.addCart(cart);
    }

}
