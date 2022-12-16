package com.example.shopcart.repository;

import com.example.shopcart.models.Product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id_product);
}