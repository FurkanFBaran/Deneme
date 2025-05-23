package com.inventory_system.controller;



import com.inventory_system.entity.Admin;
import com.inventory_system.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // login.html sayfasını göster
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
    	if (adminService.login(email, password)) {
    	    System.out.println("Giriş başarılı!"); // DEBUG
    	    session.setAttribute("adminEmail", email);
    	    return "redirect:/products/products";
    	} else {
    	    System.out.println("Giriş başarısız!"); // DEBUG
    	    model.addAttribute("error", "Geçersiz kullanıcı adı veya şifre");
    	    return "login";
    	}
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // Show register.html
    }

    @PostMapping("/register")
    public String register(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String password,
                           Model model) {
        if (adminService.existsByEmail(email)) {
            model.addAttribute("error", "Email already in use!");
            return "register";
        }

        Admin admin = new Admin();
        admin.setName(name);
        admin.setEmail(email);
        admin.setPasswordHash(password); // NOTE: Add hashing in service layer!
        adminService.save(admin);
        return "redirect:/login";
    }

}