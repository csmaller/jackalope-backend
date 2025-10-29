package com.jackalope.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jackalope.backend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * from product_inventory where product_name= ?1", nativeQuery = true)
    List<Product> findAllByProductName(String productName);
}
