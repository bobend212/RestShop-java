package com.example.shopcart.controller;

import com.example.shopcart.enums.OrderStatus;
import com.example.shopcart.models.Cart;
import com.example.shopcart.models.dto.CartDTO;
import com.example.shopcart.models.dto.CartResponseDTO;
import com.example.shopcart.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping()
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        List<CartDTO> cartsDto = carts.stream().map(CartDTO::mapFrom).collect(Collectors.toList());
        return new ResponseEntity<>(cartsDto, HttpStatus.OK);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getSingleCart(@PathVariable Long cartId) {
        Cart cart = cartService.getSingleCart(cartId);
        return new ResponseEntity<>(CartDTO.mapFrom(cart), HttpStatus.OK);
    }

    @PostMapping()
    public Cart createCart() {
        Cart cart = new Cart();
        cart.setOrder_status(OrderStatus.EMPTY);
        cart.setTotal_price(0f);
        return cartService.createCart(cart);
    }

    @PostMapping("/{cartId}/add-product/{productId}")
    public ResponseEntity<CartDTO> addProductToCart(@PathVariable final Long cartId,
            @PathVariable final Long productId) {
        Cart cart = cartService.addProductToCart(cartId, productId);
        return new ResponseEntity<>(CartDTO.mapFrom(cart), HttpStatus.OK);
    }

    @PostMapping("/{cartId}/delete-product/{productId}")
    public ResponseEntity<CartDTO> deleteProductFromCart(@PathVariable final Long cartId,
                                                    @PathVariable final Long productId) {
        Cart cart = cartService.deleteProductFromCart(cartId, productId);
        return new ResponseEntity<>(CartDTO.mapFrom(cart), HttpStatus.OK);
    }

    // @PatchMapping("/{cartId}/clear")
    // public Cart clearCart(@PathVariable Long cartId, @RequestBody Cart
    // updateCart) {
    // Optional<CartResponseDTO> cart = cartService.getSingleCart(cartId);
    // if (updateCart.getOrder_status() != null)
    // cart.setOrder_status((updateCart.getOrder_status()));
    // if (updateCart.getTotal_price() > -1)
    // cart.setTotal_price((updateCart.getTotal_price()));
    // cartService.clearCart(cart);
    // return cart;
    // }

}
