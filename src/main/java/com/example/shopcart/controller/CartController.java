package com.example.shopcart.controller;

import com.example.shopcart.common.OrderStatus;
import com.example.shopcart.models.Cart;
import com.example.shopcart.models.dto.CartDTO;
import com.example.shopcart.models.dto.CartResponseDTO;
import com.example.shopcart.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping()
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        return new ResponseEntity<>(cartService.getAllCarts().stream().map(CartDTO::mapFrom).toList(), HttpStatus.OK);
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

    @DeleteMapping("/{cartId}/delete-product/{productId}")
    public ResponseEntity<CartDTO> deleteProductFromCart(@PathVariable final Long cartId,
            @PathVariable final Long productId) {
        Cart cart = cartService.deleteProductFromCart(cartId, productId);
        return new ResponseEntity<>(CartDTO.mapFrom(cart), HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<CartResponseDTO> deleteCart(@PathVariable Long cartId) {
        return cartService.deleteCart(cartId) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/clear")
    public Cart clearCart(@RequestBody Cart cart) {
        Cart newCart = new Cart();
        newCart.setId_cart(cart.getId_cart());
        newCart.setOrder_status(cart.getOrder_status());
        newCart.setTotal_price(cart.getTotal_price());
        newCart.setProducts(cart.getProducts());
        return cartService.clearCart(newCart);
    }

}
