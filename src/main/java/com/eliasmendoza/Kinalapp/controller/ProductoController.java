package com.eliasmendoza.Kinalapp.controller;

import com.eliasmendoza.Kinalapp.entity.Producto;
import com.eliasmendoza.Kinalapp.service.IProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }


    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Producto>> listarRest() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> guardarRest(@RequestBody Producto producto) {
        try {
            Producto guardado = productoService.guardar(producto);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{codigo}")
    @ResponseBody
    public ResponseEntity<?> actualizarRest(@PathVariable Long codigo, @RequestBody Producto producto) {
        try {
            if (!productoService.existePorCodigo(codigo)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(productoService.actualizar(codigo, producto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{codigo}")
    @ResponseBody
    public ResponseEntity<?> eliminarRest(@PathVariable Long codigo) {
        if (!productoService.existePorCodigo(codigo)) {
            return ResponseEntity.notFound().build();
        }
        try {
            productoService.eliminar(codigo);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/web")
    public String listarWeb(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        return "productos/list";
    }

    @GetMapping("/web/nuevo")
    public String nuevoFormulario(Model model) {
        model.addAttribute("producto", new Producto());
        return "productos/form";
    }

    @PostMapping("/web/guardar")
    public String guardarWeb(@ModelAttribute Producto producto, RedirectAttributes redirectAttributes) {
        try {
            productoService.guardar(producto);
            // addFlashAttribute: el mensaje estará disponible solo en la siguiente petición (redirección)
            redirectAttributes.addFlashAttribute("success", "Producto guardado correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/productos/web";  // Redirige a la lista después de guardar.
    }

    @GetMapping("/web/editar/{codigo}")
    public String editarFormulario(@PathVariable Long codigo, Model model, RedirectAttributes redirectAttributes) {
        try {
            // Buscar el producto; si no existe, lanza excepción.
            Producto producto = productoService.buscarPorCodigo(codigo)
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
            model.addAttribute("producto", producto);
            return "productos/form";  // Reutilizamos la misma plantilla form.html
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/productos/web";
        }
    }

    @PostMapping("/web/actualizar/{codigo}")
    public String actualizarWeb(@PathVariable Long codigo, @ModelAttribute Producto producto, RedirectAttributes redirectAttributes) {
        try {
            productoService.actualizar(codigo, producto);
            redirectAttributes.addFlashAttribute("success", "Producto actualizado correctamente.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/productos/web";
    }

    @GetMapping("/web/eliminar/{codigo}")
    public String eliminarWeb(@PathVariable Long codigo, RedirectAttributes redirectAttributes) {
        try {
            productoService.eliminar(codigo);
            redirectAttributes.addFlashAttribute("success", "Producto eliminado correctamente.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/productos/web";
    }
}