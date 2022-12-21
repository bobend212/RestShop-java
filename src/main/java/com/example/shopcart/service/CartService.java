package com.example.shopcart.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.shopcart.common.OrderStatus;
import com.example.shopcart.models.Cart;
import com.example.shopcart.models.Item;
import com.example.shopcart.repository.CartRepository;
import com.example.shopcart.repository.ItemRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getSingleCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart does not exist."));
    }

    public Cart createEmptyCart() {
        return cartRepository.save(Cart.builder().orderStatus(OrderStatus.EMPTY).build());
    }

    public Boolean deleteCart(Long cartId) {
        return cartRepository.findById(cartId).map(cart -> {
            cartRepository.delete(cart);
            return true;
        }).orElseThrow(() -> new RuntimeException("Cart does not exist."));
    }

    public Boolean clearCart(Long cartId) {
        return cartRepository.findById(cartId).map(cartToUpdate -> {
            deleteAllProductsByCardId(cartToUpdate.getId());
            cartToUpdate.setOrderStatus(OrderStatus.EMPTY);
            return true;
        }).orElse(false);

    }

    private void deleteAllProductsByCardId(Long cardId) {
        itemRepository.deleteAllById(
                itemRepository.findAll().stream().
                        filter(x -> Objects.equals(x.getCart().getId(), cardId))
                        .map(Item::getId).toList());
    }

//    @Transactional
//    public Cart addProductToCart(Long cartId, List<Long> productIds) {
//        Cart cart = cartRepository.findById(cartId).orElseThrow();
//        Product product = productRepository.findById(productId).orElseThrow();
//
//        cart.setTotalPrice(product.getPrice());
//        cart.setOrder_status(OrderStatus.PENDING);
//        cart.addProduct(product);
//        product.setCart(cart);
//        return cart;
//    }

    // @Transactional
    // public Cart deleteProductFromCart(Long cartId, Long productId) {
    // Cart cart = getSingleCart(cartId);
    // Product product = productService.getSingleProduct(productId);
    // cart.deleteProduct(product);
    // product.setCart(null);
    // return cart;
    // }

}
