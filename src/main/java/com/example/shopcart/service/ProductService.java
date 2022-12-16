package com.example.shopcart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.example.shopcart.models.Product;
import com.example.shopcart.models.dto.ProductResponseDTO;
import com.example.shopcart.models.dto.ProductUpdateDTO;
import com.example.shopcart.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponseDTO> getAllProducts() {
        List<ProductResponseDTO> products = new ArrayList<>();
        productRepository.findAll().forEach(product -> products.add(new ProductResponseDTO(product)));
        return products;
    }

    public Optional<ProductResponseDTO> getSingleProduct(Long productId) {
        return productRepository.findById(productId).map(ProductResponseDTO::new);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public boolean deleteProduct(Long productId) {
        return productRepository.findById(productId).map(product -> {
            productRepository.delete(product);
            return true;
        }).orElse(false);
    }

    public Optional<Product> updateProduct(ProductUpdateDTO productDto) {
        return productRepository.findById(productDto.getId_product()).map(m -> updateProduct(m, productDto));
    }

    private Product updateProduct(Product product, ProductUpdateDTO productDto) {
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        return productRepository.save(product);
    }
}
