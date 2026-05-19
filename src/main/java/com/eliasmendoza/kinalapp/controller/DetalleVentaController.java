package com.eliasmendoza.kinalapp.controller;

import com.eliasmendoza.kinalapp.entity.DetalleVenta;
import com.eliasmendoza.kinalapp.service.IDetalleVentaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleventas")
public class DetalleVentaController {

    private final IDetalleVentaService detalleVentaService;

    public DetalleVentaController(IDetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping
    public List<DetalleVenta> listar() {
        return detalleVentaService.listarTodos();
    }

    @PostMapping
    public DetalleVenta guardar(@RequestBody DetalleVenta detalleVenta) {
        return detalleVentaService.guardar(detalleVenta);
    }

    @GetMapping("/{id}")
    public DetalleVenta buscar(@PathVariable Long id) {
        return detalleVentaService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        detalleVentaService.eliminar(id);
    }
}
