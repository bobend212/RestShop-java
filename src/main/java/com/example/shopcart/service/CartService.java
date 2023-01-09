package com.example.shopcart.service;

import com.example.shopcart.common.OrderStatus;
import com.example.shopcart.exception.ApiRequestException;
import com.example.shopcart.repository.Cart;
import com.example.shopcart.repository.CartRepository;
import com.example.shopcart.repository.ItemRepository;
import com.example.shopcart.repository.Product;
import com.example.shopcart.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;

    public List<com.example.shopcart.service.Cart> getAllCarts() {
        return cartRepository.findAll().stream().map(com.example.shopcart.service.Cart::of).toList();
    }

    public com.example.shopcart.service.Cart getSingleCart(Long cartId) {
        return cartRepository.findById(cartId).map(com.example.shopcart.service.Cart::of)
                .orElseThrow(() -> new ApiRequestException("Cart does not exist."));
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
    public void addProductToCart(Long cartId, Long productId) {
        var cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart does not exist."));

        com.example.shopcart.repository.Item newItem = createItem(productId);

        var cartItems = Optional.ofNullable(cart.getItems()).map(e -> {
            e.add(newItem);
            return e;
        }).orElse(List.of(newItem));

        itemRepository.save(newItem);
        cart.setItems(cartItems);
        cartRepository.save(cart);
  }

    @Transactional
    public void addProductsToCart(Long cartId, List<Long> productIds) {
        var cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart does not exist."));

        var items = productIds.stream()
                .map(this::createItem)
                .toList();

        var cartItems = Optional.ofNullable(cart.getItems()).map(e -> {
            e.addAll(items);
            return e;
        }).orElse(items);

        itemRepository.saveAll(cartItems);
        cart.setItems(cartItems);
        cartRepository.save(cart);
    }

    private com.example.shopcart.repository.Item createItem(Long productId) {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product does not exist."));

        return com.example.shopcart.repository.Item.builder()
                .product(product)
                .build();
    }


}
