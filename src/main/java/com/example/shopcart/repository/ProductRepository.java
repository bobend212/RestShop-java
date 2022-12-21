package com.example.shopcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopcart.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
