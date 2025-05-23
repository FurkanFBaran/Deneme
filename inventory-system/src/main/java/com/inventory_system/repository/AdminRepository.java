package com.inventory_system.repository;

import com.inventory_system.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByEmail(String email); // Login işlemi için
    boolean existsByEmail(String email);

}
