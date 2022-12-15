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
        if (updateCart.getStatus() != null) cart.setStatus((updateCart.getStatus()));
        if (updateCart.getTotalPrice() > -1) cart.setTotalPrice((updateCart.getTotalPrice()));
        cartService.clearCart(cart);
        return cart;
    }

}
