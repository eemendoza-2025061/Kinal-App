package com.eliasmendoza.Kinalapp.controller;

import com.eliasmendoza.Kinalapp.entity.Producto;
import com.eliasmendoza.Kinalapp.repository.ProductoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoRepository repo;

    // Inyección por constructor
    public ProductoController(ProductoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Producto> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Producto guardar(@RequestBody Producto p) {
        return repo.save(p);
    }

    @PutMapping
    public Producto actualizar(@RequestBody Producto producto) {
        // En JPA, save() funciona para insertar (si el ID no existe)
        // o actualizar (si el ID ya existe en la DB).
        return repo.save(producto);
    }
}