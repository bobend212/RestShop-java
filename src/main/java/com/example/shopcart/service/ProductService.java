package com.example.shopcart.service;

import java.util.List;

import com.example.shopcart.controller.ProductCreateDTO;
import org.springframework.stereotype.Service;

import com.example.shopcart.controller.ProductUpdateDTO;
import com.example.shopcart.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<com.example.shopcart.service.Product> getAllProducts() {
        return productRepository.findAll().stream().map(com.example.shopcart.service.Product::of).toList();
    }

    public com.example.shopcart.service.Product getSingleProduct(Long productId) {
        return productRepository.findById(productId).map(com.example.shopcart.service.Product::of)
                .orElseThrow(() -> new RuntimeException("Product does not exist."));
    }

    public com.example.shopcart.service.Product createProduct(ProductCreateDTO newProduct) {
        return com.example.shopcart.service.Product.of(productRepository.save(
                com.example.shopcart.repository.Product.builder()
                        .name(newProduct.name())
                        .price(newProduct.price())
                        .build()));
    }

    public Boolean deleteProduct(Long productId) {
        return productRepository.findById(productId).map(productEntity -> {
            productRepository.delete(productEntity);
            return true;
        }).orElse(false);
    }

    //todo: Fix updateProduct
    public Product updateProduct(Long productId, ProductUpdateDTO productUpdateDTO) {
        return productRepository.findById(productId).map(productEntity -> {
            productEntity.setName(productUpdateDTO.getName());
            productEntity.setPrice(productUpdateDTO.getPrice());
            return productRepository.save(productEntity);
        }).orElseThrow(() -> new RuntimeException("Product does not exist."));
    }
}