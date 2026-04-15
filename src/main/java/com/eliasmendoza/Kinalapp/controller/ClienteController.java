package com.eliasmendoza.Kinalapp.controller;

import com.eliasmendoza.Kinalapp.entity.Cliente;
import com.eliasmendoza.Kinalapp.service.IClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @RequestMapping("/clientes/web")

        @GetMapping
        public String listarClientes(Model model) {
            List<Cliente> clientes = new ArrayList<>();

            Cliente cliente1 = new Cliente();
            cliente1.setDpiCliente(123456789L);
            cliente1.setNombreCliente("Juan");
            cliente1.setApellidoCliente("Perez");
            cliente1.setDireccion("Guatemala");
            cliente1.setEstado(1L);
            clientes.add(cliente1);

            Cliente cliente2 = new Cliente();
            cliente2.setDpiCliente(987654321L);
            cliente2.setNombreCliente("Maria");
            cliente2.setApellidoCliente("Lopez");
            cliente2.setDireccion("Antigua");
            cliente2.setEstado(1L);
            clientes.add(cliente2);

            model.addAttribute("clientes", clientes);
            return "clientes/list";
        }


    private final IClienteService clienteService;

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Cliente>> listarRest() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> guardarRest(@RequestBody Cliente cliente) {
        try {
            return new ResponseEntity<>(clienteService.guardar(cliente), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{dpi}")
    @ResponseBody
    public ResponseEntity<?> actualizarRest(@PathVariable Long dpi, @RequestBody Cliente cliente) {
        try {
            if (!clienteService.existePorDPI(dpi)) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(clienteService.actualizar(dpi, cliente));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{dpi}")
    @ResponseBody
    public ResponseEntity<?> eliminarRest(@PathVariable Long dpi) {
        if (!clienteService.existePorDPI(dpi)) return ResponseEntity.notFound().build();
        try {
            clienteService.eliminar(dpi);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/web")
    public String listarWeb(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "clientes/list";
    }

    @GetMapping("/web/nuevo")
    public String nuevoFormulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/form";
    }

    @PostMapping("/web/guardar")
    public String guardarWeb(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        try {
            clienteService.guardar(cliente);
            redirectAttributes.addFlashAttribute("success", "Cliente guardado correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/clientes/web";
    }

    @GetMapping("/web/editar/{dpi}")
    public String editarFormulario(@PathVariable Long dpi, Model model, RedirectAttributes redirectAttributes) {
        try {
            Cliente cliente = clienteService.buscarPorDPI(dpi)
                    .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
            model.addAttribute("cliente", cliente);
            return "clientes/form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/clientes/web";
        }
    }

    @PostMapping("/web/actualizar/{dpi}")
    public String actualizarWeb(@PathVariable Long dpi, @ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        try {
            clienteService.actualizar(dpi, cliente);
            redirectAttributes.addFlashAttribute("success", "Cliente actualizado correctamente.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/clientes/web";
    }

    @GetMapping("/web/eliminar/{dpi}")
    public String eliminarWeb(@PathVariable Long dpi, RedirectAttributes redirectAttributes) {
        try {
            clienteService.eliminar(dpi);
            redirectAttributes.addFlashAttribute("success", "Cliente eliminado correctamente.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/clientes/web";
    }
}