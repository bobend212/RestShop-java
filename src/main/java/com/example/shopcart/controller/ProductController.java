package com.example.shopcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getSingleProduct(@PathVariable Long productId) {
        return productService.getSingleProduct(productId).map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public Product createProduct(@RequestBody ProductCreateDTO productDto) {
        Product product = new Product();
        product.setName(productDto.name());
        product.setPrice(productDto.price());
        return productService.createProduct(product);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductResponseDTO> updateProduct(@Validated @RequestBody ProductUpdateDTO updateProductDto) {
        return productService.updateProduct(updateProductDto)
                .map(product -> new ResponseEntity<>(new ProductResponseDTO(product), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
