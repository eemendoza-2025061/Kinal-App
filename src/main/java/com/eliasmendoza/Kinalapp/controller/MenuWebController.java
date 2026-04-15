package com.eliasmendoza.Kinalapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  // Marca esta clase como un bean de Spring MVC.
@RequestMapping("/web")  // Todas las rutas de este controlador empezarán con /web
public class MenuWebController {


    @GetMapping("/menu")  // Asocia este método a GET /web/menu
    public String menu() {
        return "menu";  // Vista que muestra opciones de navegación (clientes, productos, ventas, etc.)
    }
}