package com.inventory_system.controller;

import com.inventory_system.entity.Product;
import com.inventory_system.entity.Category;
import com.inventory_system.entity.Admin;
import com.inventory_system.service.ProductService;
import com.inventory_system.service.CategoryService;
import com.inventory_system.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AdminService adminService;

    // Ürünleri listeleme
    @GetMapping("/products")
    public String viewProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    // Ürün ekleme formu
    @GetMapping("/create")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product-create"; // templates/product-create.html
    }

    // Ürün kaydetme
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product, HttpSession session) {
        String email = (String) session.getAttribute("adminEmail");
        Admin admin = adminService.findByEmail(email);
        
        product.setAdmin(admin);
        product.setCreationDate(LocalDate.now());

        productService.saveProduct(product);
        return "redirect:/products/products";
    }

    // Ürün silme
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:/products/products";
    }

    // Ürün düzenleme formu
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "edit-product";
    }

    // Ürün güncelleme
    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product, HttpSession session) {
        String email = (String) session.getAttribute("adminEmail");
        Admin admin = adminService.findByEmail(email);

        product.setAdmin(admin);
        product.setCreationDate(LocalDate.now());
        productService.saveProduct(product);
        
        return "redirect:/products/products";
    }

    // Ürün arama
    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        List<Product> results = productService.searchByName(keyword);
        model.addAttribute("products", results);
        return "products";
    }
}