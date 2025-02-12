package com.example.slstore.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @GetMapping("/login")
    public String login() {
        return "admin/common/login-form";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/common/dashboard";
    }
}
