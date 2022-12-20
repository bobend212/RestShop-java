package com.example.shopcart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shopcart.common.OrderStatus;
import com.example.shopcart.models.Cart;
import com.example.shopcart.models.Product;
import com.example.shopcart.repository.CartRepository;
import com.example.shopcart.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getSingleCart(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow();
    }

    public Cart createEmptyCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Boolean deleteCart(Long cartId) {
        return cartRepository.findById(cartId).map(cart -> {
            cartRepository.delete(cart);
            return true;
        }).orElse(false);
    }

    public Boolean clearCart(Long cartId) {
        return cartRepository.findById(cartId).map(cartToUpdate -> {
            deleteAllProductsByCardId(cartToUpdate.getId());
            cartRepository.save(cartToUpdate);
            return true;
        }).orElse(false);

    }

    private void deleteAllProductsByCardId(Long cardId) {
        var findProducts = productRepository.findAll().stream().filter(x -> x.getCart().getId() == cardId).toList();
        List<Long> ids = new ArrayList<Long>();
        for (Product product : findProducts) {
            ids.add(product.getId());
        }
        productRepository.deleteAllById(ids);
    }

    @Transactional
    public Cart addProductToCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        cart.setTotalPrice(product.getPrice());
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

}
