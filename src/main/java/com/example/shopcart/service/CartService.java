package com.example.shopcart.service;

import com.example.shopcart.enums.OrderStatus;
import com.example.shopcart.models.Cart;
import com.example.shopcart.models.Product;
import com.example.shopcart.models.dto.CartDTO;
import com.example.shopcart.models.dto.CartResponseDTO;
import com.example.shopcart.repository.CartRepository;
import com.example.shopcart.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public List<Cart> getAllCarts() {
        return StreamSupport
                .stream(cartRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Cart getSingleCart(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow();
    }

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart clearCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart addProductToCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        cart.setTotal_price(product.getPrice());
        cart.setOrder_status(OrderStatus.IN_PROGRESS);
        cart.addProduct(product);
        product.setCart(cart);
        return cart;
    }

    @Transactional
    public Cart deleteProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();
        cart.deleteProduct(product);
        return cart;
    }
}
