package com.example.shopcart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopcart.models.ProductData;

public interface ProductDataRepository extends JpaRepository<ProductData, Long> {
    Optional<ProductData> findById(Long id_product);
}
