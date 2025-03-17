package com.thymeleaf.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/")
    public String home() {
        return "home";
    }

//    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/leaders")
    public String leaders() {
        return "leader-page";
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/systems")
    public String systems() {
        return "admin-page";
    }
}
