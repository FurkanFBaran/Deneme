package com.inventory_system.repository;


import com.inventory_system.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByBrandName(String brandName);

    List<Product> findByProductNameContaining(String keyword); // Search işlemi için

    List<Product> findByCategory_CategoryName(String categoryName); // Kategoriye göre filtreleme

}
