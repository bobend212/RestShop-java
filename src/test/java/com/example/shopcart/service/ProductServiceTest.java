package com.example.shopcart.service;

import com.example.shopcart.controller.ProductCreateDTO;
import com.example.shopcart.repository.Product;
import com.example.shopcart.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);
    }

    @Test
    void canGetAllProducts() {
        //when
        productService.getAllProducts();
        //then
        verify(productRepository).findAll();
    }

    @Test
    @Disabled
    void getSingleProduct() {
    }

    @Test
    void canCreateProduct() {
        //given
        ProductCreateDTO product = new ProductCreateDTO(
                "NewProduct",
                10f
        );

        //when
        productService.createProduct(product);

        //then
        ArgumentCaptor<com.example.shopcart.repository.Product> productArgumentCaptor =
                ArgumentCaptor.forClass(com.example.shopcart.repository.Product.class);

        verify(productRepository)
                .save(productArgumentCaptor.capture());

        Product capturedProduct = productArgumentCaptor.getValue();

        assertThat(capturedProduct).isEqualTo(product);
    }

    @Test
    @Disabled
    void deleteProduct() {
    }

    @Test
    @Disabled
    void updateProduct() {
    }
}