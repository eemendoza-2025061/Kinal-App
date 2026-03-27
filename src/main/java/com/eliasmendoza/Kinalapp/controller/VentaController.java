package com.eliasmendoza.Kinalapp.controller;

import com.eliasmendoza.Kinalapp.entity.Venta;
import com.eliasmendoza.Kinalapp.repository.VentaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaRepository repo;

    public VentaController(VentaRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Venta> listar() { return repo.findAll(); }

    @PostMapping
    public Venta guardar(@RequestBody Venta v) { return repo.save(v); }

    @PutMapping
    public Venta actualizar(@RequestBody Venta venta) { return repo.save(venta); }
}