package com.example.shopcart.controller;

import org.junit.jupiter.api.BeforeEach;
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
    void shouldFindProductById() throws Exception {

        ProductCreateDTO newProduct = new ProductCreateDTO(
                "NewProduct",
                10f);

        mockMvc.perform(post("/products")
                .content(objectMapper.writeValueAsString(newProduct))
                .contentType("application/json"))
                .andExpect(status().isCreated());

        MvcResult mvcResult = mockMvc.perform(get("/products/1")
                .contentType("application/json"))
                .andExpect(status().isOk()).andReturn();

        var product = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Product.class);

        Assertions.assertEquals("Test", product.getName());
    }

}
