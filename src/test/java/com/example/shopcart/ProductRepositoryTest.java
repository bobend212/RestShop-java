package com.example.shopcart;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.shopcart.repository.Product;
import com.example.shopcart.repository.ProductRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void cleanUp() {
        productRepository.deleteAll();
    }

    @Test
    void checkIfProductExistByProductName() {
        // given
        String productName = "Water";
        Product product = Product.builder()
                .name(productName)
                .price(10f)
                .build();

        productRepository.save(product);

        // when
        // var findProduct = productRepository.findBy()
        // then
        assertThat(productRepository.findById(product.getId())).isNotNull();

    }
}
