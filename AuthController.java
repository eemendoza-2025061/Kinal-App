package com.eliasmendoza.Kinalapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
        }
        if (logout != null) {
            model.addAttribute("message", "Has cerrado sesión exitosamente");
        }
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/user/perfil")
    public String userProfile() {
        return "user/perfil";
    }

    @GetMapping("/admin/panel")
    public String adminPanel() {
        return "admin/panel";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}