package com.example.shopcart.models;

import com.example.shopcart.common.OrderStatus;
import com.example.shopcart.models.dto.CartDTO;
import lombok.*;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cart;

    private OrderStatus order_status;

    private Float total_price;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();;

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(Product product) {
        products.remove(product);
    }

    public static Cart mapFrom(CartDTO cartDto) {
        Cart cart = new Cart();
        cart.setId_cart(cartDto.getId_cart());
        cart.setOrder_status(cartDto.getOrder_status());
        // cart.setProducts(cartDto.getProductsDto().stream().map(ProductDTO::from).collect(Collectors.toList()));
        // cart.setTotal_price(sumPrice(cart.getProducts()));
        return cart;
    }
}
