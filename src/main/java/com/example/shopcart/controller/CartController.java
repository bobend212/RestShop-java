package com.example.shopcart.controller;

import com.example.shopcart.enums.OrderStatus;
import com.example.shopcart.models.Cart;
import com.example.shopcart.models.Product;
import com.example.shopcart.models.dto.CartDTO;
import com.example.shopcart.models.dto.CartResponseDTO;
import com.example.shopcart.models.dto.ProductResponseDTO;
import com.example.shopcart.models.dto.ProductUpdateDTO;
import com.example.shopcart.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

}
