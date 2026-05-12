package com.eliasmendoza.Kinalapp.controller;

import com.eliasmendoza.Kinalapp.entity.DetalleVenta;
import com.eliasmendoza.Kinalapp.service.IDetalleVentaService;
import com.eliasmendoza.Kinalapp.service.IProductoService;
import com.eliasmendoza.Kinalapp.service.IVentasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/detalleventa")
public class DetalleVentaController {

    private final IDetalleVentaService detalleVentaService;
    private final IProductoService     productoService;
    private final IVentasService       ventasService;

    public DetalleVentaController(IDetalleVentaService detalleVentaService,
                                  IProductoService productoService,
                                  IVentasService ventasService) {
        this.detalleVentaService = detalleVentaService;
        this.productoService     = productoService;
        this.ventasService       = ventasService;
    }

    /**
     * @GetMapping sin ruta específica significa que responde a la raíz 
     * definida en el @RequestMapping de la clase.
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<DetalleVenta>> listarRest() {
        return ResponseEntity.ok(detalleVentaService.listarTodos());
    }

    @GetMapping("/{codigo}")
    @ResponseBody
    public ResponseEntity<DetalleVenta> buscarPorCodigo(@PathVariable Long codigo) {
        return detalleVentaService.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/venta/{codigoVenta}")
    @ResponseBody
    public ResponseEntity<List<DetalleVenta>> buscarPorVenta(@PathVariable Long codigoVenta) {
        List<DetalleVenta> detalles = detalleVentaService.buscarPorVenta(codigoVenta);
        return detalles.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(detalles);
    }

    @GetMapping("/producto/{codigoProducto}")
    @ResponseBody
    public ResponseEntity<List<DetalleVenta>> buscarPorProducto(@PathVariable Long codigoProducto) {
        List<DetalleVenta> detalles = detalleVentaService.buscarPorProducto(codigoProducto);
        return detalles.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(detalles);
    }

    @GetMapping("/estado/{estado}")
    @ResponseBody
    public ResponseEntity<List<DetalleVenta>> buscarPorEstado(@PathVariable int estado) {
        List<DetalleVenta> detalles = detalleVentaService.buscarPorEstado(estado);
        return detalles.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(detalles);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> guardarRest(@RequestBody DetalleVenta detalleVenta) {
        try {
            return new ResponseEntity<>(detalleVentaService.guardar(detalleVenta), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    @ResponseBody
    public ResponseEntity<Void> eliminarRest(@PathVariable Long codigo) {
        if (!detalleVentaService.existePorCodigo(codigo)) return ResponseEntity.notFound().build();
        detalleVentaService.eliminar(codigo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{codigo}")
    @ResponseBody
    public ResponseEntity<?> actualizarRest(@PathVariable Long codigo, @RequestBody DetalleVenta detalleVenta) {
        try {
            if (!detalleVentaService.existePorCodigo(codigo)) return ResponseEntity.notFound().build();
            detalleVenta.setCodigoDetalleVenta(codigo);
            return ResponseEntity.ok(detalleVentaService.actualizar(codigo, detalleVenta));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/web")
    public String listarWeb(Model model) {
        model.addAttribute("detalles", detalleVentaService.listarTodos());
        return "detalleventa/list";
    }

    @GetMapping("/web/nuevo")
    public String nuevoFormulario(Model model) {
        model.addAttribute("detalle", new DetalleVenta());
        model.addAttribute("productos", productoService.listarTodos());
        model.addAttribute("ventas", ventasService.listarTodos());
        return "detalleventa/form";
    }

    @PostMapping("/web/guardar")
    public String guardarWeb(@ModelAttribute DetalleVenta detalle, RedirectAttributes redirectAttributes) {
        try {
            detalleVentaService.guardar(detalle);
            redirectAttributes.addFlashAttribute("success", "Detalle guardado correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/detalleventa/web";
    }

    @GetMapping("/web/editar/{codigo}")
    public String editarFormulario(@PathVariable Long codigo, Model model, RedirectAttributes redirectAttributes) {
        try {
            DetalleVenta detalle = detalleVentaService.buscarPorCodigo(codigo)
                    .orElseThrow(() -> new IllegalArgumentException("Detalle no encontrado"));
            model.addAttribute("detalle", detalle);
            model.addAttribute("productos", productoService.listarTodos());
            model.addAttribute("ventas", ventasService.listarTodos());
            return "detalleventa/form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/detalleventa/web";
        }
    }

    @PostMapping("/web/actualizar/{codigo}")
    public String actualizarWeb(@PathVariable Long codigo, @ModelAttribute DetalleVenta detalle, RedirectAttributes redirectAttributes) {
        try {
            detalleVentaService.actualizar(codigo, detalle);
            redirectAttributes.addFlashAttribute("success", "Detalle actualizado correctamente.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/detalleventa/web";
    }

    @GetMapping("/web/eliminar/{codigo}")
    public String eliminarWeb(@PathVariable Long codigo, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error",
                "❌ No se permite eliminar detalles de venta. Eliminar registros contables constituye fraude.");
        return "redirect:/detalleventa/web";
    }
}
