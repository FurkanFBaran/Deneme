package com.inventory_system.service;



import com.inventory_system.entity.Product;
import com.inventory_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> searchByName(String keyword) {
        return productRepository.findByProductNameContaining(keyword);
    }

    public List<Product> filterByBrand(String brand) {
        return productRepository.findByBrandName(brand);
    }

    public List<Product> filterByCategory(String categoryName) {
        return productRepository.findByCategory_CategoryName(categoryName);
    }
}

