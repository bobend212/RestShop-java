package com.example.shopcart.repository;

import com.example.shopcart.models.Item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}