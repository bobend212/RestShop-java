package com.example.shopcart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.shopcart.models.Product;
import com.example.shopcart.models.dto.ProductCreateDTO;
import com.example.shopcart.models.dto.ProductResponseDTO;
import com.example.shopcart.models.dto.ProductUpdateDTO;
import com.example.shopcart.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productDataService;

    public ProductController(ProductService productDataService) {
        this.productDataService = productDataService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productDataService.getAllProducts(),
                HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable Long productId) {
        Product productData = productDataService.getSingleProduct(productId);
        return new ResponseEntity<>(productData, HttpStatus.OK);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@Validated @RequestBody ProductCreateDTO productDto) {
        return productDataService.createProduct(
                Product.builder()
                        .name(productDto.name())
                        .price(productDto.price())
                        .build());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        return productDataService.deleteProduct(productId) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductResponseDTO> updateProduct(@Validated @RequestBody ProductUpdateDTO updateProductDto) {
        productDataService.updateProduct(updateProductDto.getId(), updateProductDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
