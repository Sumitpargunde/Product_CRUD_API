package com.example.product_category_crud_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.product_category_crud_api.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
