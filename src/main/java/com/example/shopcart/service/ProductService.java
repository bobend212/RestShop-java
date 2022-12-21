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

    private final ProductRepository productDataRepository;

    public List<Product> getAllProducts() {
        return productDataRepository.findAll();
    }

    public Product getSingleProduct(Long productId) {
        return productDataRepository.findById(productId).orElseThrow();
    }

    public Product createProduct(Product product) {
        return productDataRepository.save(product);
    }

    public Boolean deleteProduct(Long productId) {
        return productDataRepository.findById(productId).map(productData -> {
            productDataRepository.delete(productData);
            return true;
        }).orElse(false);
    }

    public Product updateProduct(Long productId, ProductUpdateDTO productUpdateDTO) {
        Product productFromDb = productDataRepository.findById(productId).get();
        productFromDb.setName(productUpdateDTO.getName());
        productFromDb.setPrice(productUpdateDTO.getPrice());
        return productDataRepository.save(productFromDb);
    }
}
