package com.example.shopcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopcart.models.ProductData;

public interface ProductCatalogRepository extends JpaRepository<ProductData, Long> {

}
