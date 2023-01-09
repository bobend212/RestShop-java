package com.example.shopcart.controller;

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
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        return new ResponseEntity<>(cartService.getAllCarts().stream().map(com.example.shopcart.service.Cart::toDto)
                .toList(), HttpStatus.OK);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getSingleCart(@PathVariable Long cartId) {
        com.example.shopcart.service.Cart cart = cartService.getSingleCart(cartId);
        return new ResponseEntity<>(com.example.shopcart.service.Cart.toDto(cart), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CartDTO> createEmptyCart() {
        var newCart = cartService.createEmptyCart();
        return new ResponseEntity<>(com.example.shopcart.service.Cart.toDto(newCart), HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Boolean> deleteCart(@PathVariable Long cartId) {
        return cartService.deleteCart(cartId) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{cartId}/clear")
    public ResponseEntity<CartDTO> clearCart(@PathVariable Long cartId) {
        return cartService.clearCart(cartId) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{cartId}/add-product/{productId}")
    public ResponseEntity<Void> addProductToCart(@PathVariable final Long cartId, @PathVariable final Long productId) {
        cartService.addProductToCart(cartId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{cartId}/add-products")
    public ResponseEntity<Void> addProductsToCart(@PathVariable final Long cartId, @RequestBody List<Long> productIds) {
        cartService.addProductsToCart(cartId, productIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
