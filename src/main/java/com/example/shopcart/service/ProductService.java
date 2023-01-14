package com.example.shopcart.service;

import java.util.List;

import com.example.shopcart.controller.ProductCreateDTO;
import com.example.shopcart.exception.ApiRequestException;
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
                .orElseThrow(() -> new ApiRequestException("Product does not exist."));
    }

    public com.example.shopcart.repository.Product createProduct(com.example.shopcart.repository.Product product) {
        return productRepository.save(product);
    }

    public Boolean deleteProduct(Long productId) {
        return productRepository.findById(productId).map(productEntity -> {
            productRepository.delete(productEntity);
            return true;
        }).orElse(false);
    }

    public Boolean updateProduct(Long productId, ProductUpdateDTO productUpdateDTO) {
        return productRepository.findById(productId).map(productEntity -> {
            productEntity.setName(productUpdateDTO.getName());
            productEntity.setPrice(productUpdateDTO.getPrice());
            productRepository.save(productEntity);
            return  true;
        }).orElse(false);
    }
}