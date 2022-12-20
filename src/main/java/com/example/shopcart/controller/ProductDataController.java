package com.example.shopcart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.shopcart.models.ProductData;
import com.example.shopcart.models.dto.ProductCreateDTO;
import com.example.shopcart.models.dto.ProductResponseDTO;
import com.example.shopcart.models.dto.ProductUpdateDTO;
import com.example.shopcart.service.ProductDataService;

@RestController
@RequestMapping("/products")
public class ProductDataController {

    private final ProductDataService productDataService;

    public ProductDataController(ProductDataService productDataService) {
        this.productDataService = productDataService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductData>> getAllProducts() {
        return new ResponseEntity<>(productDataService.getAllProducts(),
                HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductData> getSingleProduct(@PathVariable Long productId) {
        ProductData productData = productDataService.getSingleProduct(productId);
        return new ResponseEntity<>(productData, HttpStatus.OK);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProductData createProduct(@Validated @RequestBody ProductCreateDTO productDto) {
        return productDataService.createProduct(
                ProductData.builder()
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
