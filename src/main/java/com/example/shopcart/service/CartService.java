package com.example.shopcart.service;

import com.example.shopcart.common.OrderStatus;
import com.example.shopcart.repository.Cart;
import com.example.shopcart.repository.CartRepository;
import com.example.shopcart.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    public List<com.example.shopcart.service.Cart> getAllCarts() {
        return cartRepository.findAll().stream().map(com.example.shopcart.service.Cart::of).toList();
    }

    public com.example.shopcart.service.Cart getSingleCart(Long cartId) {
        return cartRepository.findById(cartId).map(com.example.shopcart.service.Cart::of)
                .orElseThrow(() -> new RuntimeException("Cart does not exist."));
    }

    public com.example.shopcart.service.Cart createEmptyCart() {
        return com.example.shopcart.service.Cart.of(cartRepository.save(
                Cart.builder()
                        .items(Collections.emptyList())
                        .orderStatus(OrderStatus.EMPTY)
                        .build()));
    }

    public Boolean deleteCart(Long cartId) {
        return cartRepository.findById(cartId).map(cart -> {
            cartRepository.delete(cart);
            return true;
        }).orElse(false);
    }

    public Boolean clearCart(Long cartId) {
        return cartRepository.findById(cartId).map(cartToUpdate -> {
            itemRepository.deleteAllInBatch(cartToUpdate.getItems());
            cartToUpdate.setOrderStatus(OrderStatus.EMPTY);
            cartRepository.save(cartToUpdate);
            return true;
        }).orElse(false);

    }

    @Transactional
    public com.example.shopcart.service.Cart addProductsToCart(Long cartId) {
        com.example.shopcart.service.Cart cart = cartRepository.findById(cartId).map(com.example.shopcart.service.Cart::of)
                .orElseThrow(() -> new RuntimeException("Cart does not exist."));

        var itemsToAdd = Arrays.asList(1L, 2L);

        var items = itemRepository.findAll();

        //.setOrderStatus(OrderStatus.PENDING);
       // cart.setItems(items);

        return cart;
    }


}
