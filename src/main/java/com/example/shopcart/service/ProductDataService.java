package com.example.shopcart.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.shopcart.models.ProductData;
import com.example.shopcart.models.dto.ProductUpdateDTO;
import com.example.shopcart.repository.ProductDataRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductDataService {

    private final ProductDataRepository productDataRepository;

    public List<ProductData> getAllProducts() {
        return productDataRepository.findAll();
    }

    public ProductData getSingleProduct(Long productId) {
        return productDataRepository.findById(productId).orElseThrow();
    }

    public ProductData createProduct(ProductData product) {
        return productDataRepository.save(product);
    }

    public Boolean deleteProduct(Long productId) {
        return productDataRepository.findById(productId).map(productData -> {
            productDataRepository.delete(productData);
            return true;
        }).orElse(false);
    }

    public ProductData updateProduct(Long productId, ProductUpdateDTO productUpdateDTO) {
        ProductData productFromDb = productDataRepository.findById(productId).get();
        productFromDb.setName(productUpdateDTO.getName());
        productFromDb.setPrice(productUpdateDTO.getPrice());
        return productDataRepository.save(productFromDb);
    }
}
