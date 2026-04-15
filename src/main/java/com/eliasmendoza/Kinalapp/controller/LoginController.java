package com.eliasmendoza.Kinalapp.controller;

import com.eliasmendoza.Kinalapp.entity.Usuario;
import com.eliasmendoza.Kinalapp.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class LoginController {

    private final IUsuarioService usuarioService;

    public LoginController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")  // Asocia este método a peticiones HTTP GET en la ruta /login
    public String mostrarLogin() {
        return "login";  // Spring buscará login.html en src/main/resources/templates/
    }

    @PostMapping("/login")  // Maneja POST en /login
    public String procesarLogin(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {

        Optional<Usuario> usuarioOpt = usuarioService.login(username, password);

        if (usuarioOpt.isPresent()) {
            session.setAttribute("usuarioLogueado", usuarioOpt.get());
            return "redirect:/web/menu";
        } else {

            redirectAttributes.addFlashAttribute("error", "Usuario o contraseña incorrectos.");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();  // Destruye la sesión, eliminando todos sus atributos.
        redirectAttributes.addFlashAttribute("success", "Sesión cerrada correctamente.");
        return "redirect:/login";
    }
}