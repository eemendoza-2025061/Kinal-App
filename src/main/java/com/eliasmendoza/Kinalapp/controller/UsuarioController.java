package com.eliasmendoza.Kinalapp.controller;

import com.eliasmendoza.Kinalapp.entity.Usuario;
import com.eliasmendoza.Kinalapp.service.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }



    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Usuario>> listarRest() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{codigo}")
    @ResponseBody
    public ResponseEntity<Usuario> buscarPorCodigo(@PathVariable Long codigo) {
        return usuarioService.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{estado}")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorEstado(@PathVariable int estado) {
        List<Usuario> usuarios = usuarioService.buscarPorEstado(estado);
        return usuarios.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(usuarios);
    }


    @PostMapping
    @ResponseBody
    public ResponseEntity<?> guardarRest(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(usuarioService.guardar(usuario), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{codigo}")
    @ResponseBody
    public ResponseEntity<Void> eliminarRest(@PathVariable Long codigo) {
        if (!usuarioService.existePorCodigo(codigo)) return ResponseEntity.notFound().build();
        usuarioService.eliminar(codigo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{codigo}")
    @ResponseBody
    public ResponseEntity<?> actualizarRest(@PathVariable Long codigo, @RequestBody Usuario usuario) {
        try {
            if (!usuarioService.existePorCodigo(codigo)) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(usuarioService.actualizar(codigo, usuario));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/web")
    public String listarWeb(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/list";
    }


    @GetMapping("/web/nuevo")
    public String nuevoFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/form";
    }

    @PostMapping("/web/guardar")
    public String guardarWeb(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.guardar(usuario);
            redirectAttributes.addFlashAttribute("success", "Usuario guardado correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/usuarios/web";
    }

    @GetMapping("/web/editar/{codigo}")
    public String editarFormulario(@PathVariable Long codigo, Model model, RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = usuarioService.buscarPorCodigo(codigo)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
            model.addAttribute("usuario", usuario);
            return "usuarios/form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/usuarios/web";
        }
    }

    @PostMapping("/web/actualizar/{codigo}")
    public String actualizarWeb(@PathVariable Long codigo, @ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.actualizar(codigo, usuario);
            redirectAttributes.addFlashAttribute("success", "Usuario actualizado correctamente.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/usuarios/web";
    }

    @GetMapping("/web/eliminar/{codigo}")
    public String eliminarWeb(@PathVariable Long codigo, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error",
                "❌ No se permite eliminar usuarios. Los registros primarios no pueden ser borrados.");
        return "redirect:/usuarios/web";
    }
}
