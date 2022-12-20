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
    @Column(name = "cart_id")
    private Long id;

    private OrderStatus order_status;

    private Float totalPrice;

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
        cart.setId(cartDto.getId());
        cart.setOrder_status(cartDto.getOrderStatus());
        // cart.setProducts(cartDto.getProductsDto().stream().map(ProductDTO::from).collect(Collectors.toList()));
        // cart.setTotal_price(sumPrice(cart.getProducts()));
        return cart;
    }
}
