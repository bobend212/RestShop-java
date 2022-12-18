package com.example.shopcart.service;

import com.example.shopcart.enums.OrderStatus;
import com.example.shopcart.models.Cart;
import com.example.shopcart.models.Product;
import com.example.shopcart.models.dto.CartDTO;
import com.example.shopcart.models.dto.CartResponseDTO;
import com.example.shopcart.models.dto.ProductUpdateDTO;
import com.example.shopcart.repository.CartRepository;
import com.example.shopcart.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }

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
        Cart cartToUpdate = cartRepository.findById(cart.getId_cart()).orElseThrow();
        return cartRepository.save(cartToUpdate);
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
        Cart cart = getSingleCart(cartId);
        Product product = productService.getSingleProduct(productId);
        cart.deleteProduct(product);
        product.setCart(null);
        return cart;
    }

    public boolean deleteCart(Long cartId) {
        return cartRepository.findById(cartId).map(cart -> {
            cartRepository.delete(cart);
            return true;
        }).orElse(false);
    }
}
