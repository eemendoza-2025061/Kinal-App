package com.eliasmendoza.Kinalapp.controller;

import com.eliasmendoza.Kinalapp.entity.DetalleVenta;
import com.eliasmendoza.Kinalapp.repository.DetalleVentaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalles-ventas")
public class DetalleVentaController {

    private final DetalleVentaRepository repo;

    public DetalleVentaController(DetalleVentaRepository repo) { this.repo = repo; }

    @GetMapping
    public List<DetalleVenta> listar() { return repo.findAll(); }

    @PostMapping
    public DetalleVenta guardar(@RequestBody DetalleVenta dv) { return repo.save(dv); }

    @PutMapping
    public DetalleVenta actualizar(@RequestBody DetalleVenta detalleVenta) { return repo.save(detalleVenta); }
}