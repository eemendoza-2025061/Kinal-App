package com.eliasmendoza.Kinalapp.controller;

import com.eliasmendoza.Kinalapp.entity.Usuario;
import com.eliasmendoza.Kinalapp.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioRepository rep;

    public UsuarioController(UsuarioRepository rep) {
        this.rep = rep;
    }

    @GetMapping
    public List<Usuario> listar() {
        return rep.findAll();
    }

    @PostMapping
    public Usuario guardar(@RequestBody Usuario u) {
        return rep.save(u);
    }

    @PutMapping
    public Usuario actualizar(@RequestBody Usuario usuario) {
        return rep.save(usuario);
    }
}