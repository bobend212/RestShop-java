package com.example.shopcart.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.shopcart.repository.Product;
import com.example.shopcart.repository.ProductRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    void checkIfProductExistByProductName() {
        // given
        String productName = "Tomato";
        Product product = Product.builder()
                .name(productName)
                .price(10f)
                .build();

        productRepository.save(product);

        // when
        boolean expected = productRepository.selectExistProduct(productName);

        // then
        assertThat(expected).isTrue();
    }


    @Test
    void checkIfProductDoesNotExistByProductName() {
        // given
        String productName = "Watermelon";

        // when
         var expectedProduct = productRepository.selectExistProduct(productName);

         // then
        assertThat(expectedProduct).isFalse();

    }

}
