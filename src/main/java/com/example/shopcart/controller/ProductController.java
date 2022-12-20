package com.example.shopcart.controller;

import java.util.List;

import com.example.shopcart.models.dto.ProductDTO;
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
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts().stream().map(ProductDTO::from).toList(),
                HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getSingleProduct(@PathVariable Long productId) {
        Product product = productService.getSingleProduct(productId);
        return new ResponseEntity<>(ProductDTO.from(product), HttpStatus.OK);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@Validated @RequestBody ProductCreateDTO productDto) {
        return productService.createProduct(
                Product.builder()
                        .name(productDto.name())
                        .price(productDto.price())
                        .build());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductResponseDTO> updateProduct(@Validated @RequestBody ProductUpdateDTO updateProductDto) {
        productService.updateProduct(updateProductDto.getId(), updateProductDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
