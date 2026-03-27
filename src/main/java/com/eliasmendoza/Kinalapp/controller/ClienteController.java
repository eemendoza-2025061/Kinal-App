package com.eliasmendoza.Kinalapp.controller;

import com.eliasmendoza.Kinalapp.entity.Cliente;
import com.eliasmendoza.Kinalapp.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository repo;

    public ClienteController(ClienteRepository repo){
        this.repo = repo;
    }

    @GetMapping
    public  List<Cliente> listar(){
        return repo.findAll();
    }

    @PostMapping
    public Cliente guardar(@RequestBody Cliente c){
        return repo.save(c);
    }

    @PutMapping
    public Cliente actualizar(@RequestBody Cliente cliente){
        return repo.save(cliente);
    }
}
