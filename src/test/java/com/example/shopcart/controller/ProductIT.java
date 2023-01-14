package com.example.shopcart.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.shopcart.repository.Product;
import com.example.shopcart.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Assertions;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.http.MediaType;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void cleanUp() {
        productRepository.deleteAll();
    }

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldCreateNewProductAndReturnCreated() throws Exception {
        // given
        Product product = Product.builder()
                .name("TestProduct")
                .price(10f)
                .build();

        // when
        ResultActions resultActions = mockMvc
                .perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)));
        // then
        resultActions.andExpect(status().isCreated());
        List<Product> products = productRepository.findAll();
        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals(product.getName(), products.get(0).getName());
    }

    @Test
    void whenUpdateShouldReturnNotFoundWhenProductDoesNotExist() throws Exception {
        //given
        ProductUpdateDTO productToUpdate = ProductUpdateDTO.builder()
                .name("ProductName")
                .price(10f)
                .build();

        //when //then
        mockMvc.perform(put("/products/999/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(productToUpdate)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldFindProductByProductId() throws Exception {
        // given
        Product product = Product.builder()
                .name("TestProduct")
                .price(10f)
                .build();

        // when
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)));

        List<Product> products = productRepository.findAll();

        long productId = products.stream()
                .filter(s -> s.getName().equals(product.getName()))
                .map(Product::getId)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException(
                                "product with name: " + product.getName() + " not found"));

        ResultActions mvcResult = mockMvc.perform(get("/products/" + productId));

        boolean isProductExist = productRepository.existsById(productId);

        //then
        mvcResult.andExpect(status().isOk());
        Assertions.assertEquals(true, isProductExist);
    }

}
