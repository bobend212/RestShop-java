package com.example.shopcart.service;

import java.util.List;
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

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public Product updateProduct(Long productId, ProductUpdateDTO productUpdateDTO) {
        Product productFromDb = productRepository.findById(productId).get();
        productFromDb.setName(productUpdateDTO.getName());
        productFromDb.setPrice(productUpdateDTO.getPrice());
        return productRepository.save(productFromDb);
    }
}
