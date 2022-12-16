package com.example.shopcart.service;

import com.example.shopcart.enums.OrderStatus;
import com.example.shopcart.models.Cart;
import com.example.shopcart.models.Product;
import com.example.shopcart.models.dto.CartResponseDTO;
import com.example.shopcart.repository.CartRepository;
import com.example.shopcart.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public List<CartResponseDTO> getAllCarts() {
        List<CartResponseDTO> carts = new ArrayList<>();
        cartRepository.findAll().forEach(cart -> carts.add(new CartResponseDTO(cart)));
        return carts;
    }

    public Optional<CartResponseDTO> getSingleCart(Long cartId) {
        return cartRepository.findById(cartId).map(CartResponseDTO::new);
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

        // if(Objects.nonNull(item.getCart())){
        // throw new ItemIsAlreadyAssignedException(itemId,
        // item.getCart().getId());
        // }
        cart.setTotal_price(product.getPrice());
        cart.setOrder_status(OrderStatus.IN_PROGRESS);
        cart.addProduct(product);
        product.setCart(cart);
        return cart;
    }
}
