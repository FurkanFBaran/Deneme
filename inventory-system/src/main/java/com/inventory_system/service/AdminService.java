package com.inventory_system.service;

import com.inventory_system.entity.Admin;
import com.inventory_system.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return adminRepository.existsByEmail(email);
    }

    public Admin save(Admin admin) {
        // You can hash the password here if needed in the future
        return adminRepository.save(admin);
    }

    public boolean login(String email, String password) {
        Admin admin = findByEmail(email);
        if (admin == null) return false;
        return admin.getPasswordHash().equals(password);
    }
}
