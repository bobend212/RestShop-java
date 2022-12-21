package com.example.shopcart.controller;

import java.util.List;

import com.example.shopcart.models.dto.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.shopcart.models.Product;
import com.example.shopcart.models.dto.ProductCreateDTO;
import com.example.shopcart.models.dto.ProductUpdateDTO;
import com.example.shopcart.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(),
                HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable Long productId) {
        Product product = productService.getSingleProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> createProduct(@Validated @RequestBody ProductCreateDTO newProductBody) {
        return new ResponseEntity<>(productService.createProduct(newProductBody), HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId,
                                                    @RequestBody ProductUpdateDTO updateProductBody) {
        productService.updateProduct(productId, updateProductBody);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
