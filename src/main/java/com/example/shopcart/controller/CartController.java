package com.example.shopcart.controller;

import com.example.shopcart.common.OrderStatus;
import com.example.shopcart.models.Cart;
import com.example.shopcart.models.dto.CartDTO;
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
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        return new ResponseEntity<>(cartService.getAllCarts().stream().map(CartDTO::mapFrom).toList(), HttpStatus.OK);
    }

    @GetMapping("/{cartId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CartDTO> getSingleCart(@PathVariable Long cartId) {
        Cart cart = cartService.getSingleCart(cartId);
        return new ResponseEntity<>(CartDTO.mapFrom(cart), HttpStatus.OK);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cart> createEmptyCart() {
        return new ResponseEntity<>(cartService.createEmptyCart(
                Cart.builder()
                        .orderStatus(OrderStatus.EMPTY)
                        .totalPrice(0f)
                        .build()),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> deleteCart(@PathVariable Long cartId) {
        return cartService.deleteCart(cartId) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{cartId}/clear")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Cart> clearCart(@PathVariable Long cartId) {
        return cartService.clearCart(cartId) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // @PostMapping("/{cartId}/add-product/{productId}")
    // public ResponseEntity<CartDTO> addProductToCart(@PathVariable final Long
    // cartId,
    // @PathVariable final Long productId) {
    // Cart cart = cartService.addProductToCart(cartId, productId);
    // return new ResponseEntity<>(CartDTO.mapFrom(cart), HttpStatus.OK);
    // }

    // @DeleteMapping("/{cartId}/delete-product/{productId}")
    // public ResponseEntity<CartDTO> deleteProductFromCart(@PathVariable final Long
    // cartId,
    // @PathVariable final Long productId) {
    // Cart cart = cartService.deleteProductFromCart(cartId, productId);
    // return new ResponseEntity<>(CartDTO.mapFrom(cart), HttpStatus.OK);
    // }

}
