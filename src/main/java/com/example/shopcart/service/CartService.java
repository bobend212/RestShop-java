package com.example.shopcart.service;

import com.example.shopcart.models.Cart;
import com.example.shopcart.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getSingleCart(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow();
    }

    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart clearCart(Cart cart) {
        return cartRepository.save(cart);
    }
}
