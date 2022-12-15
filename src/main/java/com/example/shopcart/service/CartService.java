package com.example.shopcart.service;

import com.example.shopcart.models.Cart;
import com.example.shopcart.repository.CartRepository;
import com.example.shopcart.response.CartResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public List<CartResponse> getAllCarts() {
        List<CartResponse> cartResponses = new ArrayList<>();
        cartRepository.findAll().forEach(cart -> cartResponses.add(new CartResponse(cart)));
        return cartResponses;
    }

    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }
}
