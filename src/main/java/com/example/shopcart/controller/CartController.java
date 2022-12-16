package com.example.shopcart.controller;

import com.example.shopcart.models.Cart;
import com.example.shopcart.service.CartService;
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
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/{cartId}")
    public Cart getSingleCart(@PathVariable Long cartId) {
        return cartService.getSingleCart(cartId);
    }

    @PostMapping()
    public Cart postCart(@RequestBody Cart cart) {
        return cartService.addCart(cart);
    }

    @PatchMapping("/{cartId}/clear")
    public Cart clearCart(@PathVariable Long cartId, @RequestBody Cart updateCart) {
        Cart cart = cartService.getSingleCart(cartId);
        if (updateCart.getOrder_status() != null)
            cart.setOrder_status((updateCart.getOrder_status()));
        if (updateCart.getTotal_price() > -1)
            cart.setTotal_price((updateCart.getTotal_price()));
        cartService.clearCart(cart);
        return cart;
    }

}
