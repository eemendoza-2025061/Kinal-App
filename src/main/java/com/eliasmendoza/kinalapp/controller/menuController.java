package com.eliasmendoza.kinalapp.controller;

import com.eliasmendoza.kinalapp.entity.Usuario;
import com.eliasmendoza.kinalapp.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class menuController {

    private final UsuarioRepository usuarioRepository;

    public menuController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/menu")
    public String menu(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
            usuarioOpt.ifPresent(usuario -> model.addAttribute("usuario", usuario));
        }
        return "menu";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/menu";
    }
}
