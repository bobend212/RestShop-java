package com.example.shopcart.service;

import java.util.List;

import com.example.shopcart.models.dto.ProductCreateDTO;
import org.springframework.stereotype.Service;

import com.example.shopcart.models.Product;
import com.example.shopcart.models.dto.ProductUpdateDTO;
import com.example.shopcart.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getSingleProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    public Product createProduct(ProductCreateDTO newProduct) {
        return productRepository.save(Product.builder()
                .name(newProduct.name())
                .price(newProduct.price())
                .build());
    }

    public Boolean deleteProduct(Long productId) {
        return productRepository.findById(productId).map(product -> {
            productRepository.delete(product);
            return true;
        }).orElse(false);
    }

    public Product updateProduct(Long productId, ProductUpdateDTO productUpdateDTO) {
        return productRepository.findById(productId).map(product -> {
            product.setName(productUpdateDTO.getName());
            product.setPrice(productUpdateDTO.getPrice());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product does not exist."));
    }
}