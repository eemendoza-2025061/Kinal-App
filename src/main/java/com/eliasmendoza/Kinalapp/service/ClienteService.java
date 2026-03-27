package com.eliasmendoza.Kinalapp.service;

import com.eliasmendoza.Kinalapp.entity.Cliente;
import com.eliasmendoza.Kinalapp.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//Anotacion que registra un Bean(un modelo) como un Bean de Spring
//Que la clase contiene la logica del negocio
@Service
//Por defecto todos los metodos de esta clase seran
// transaccionales
//Una transaccion es que puede o no ocurrir algo
@Transactional
public class ClienteService implements IClientesService {
    /*private: solo accesible dentro de la clase
      ClienteRepository: Es el repositorio para acceder a la DB
      Inyeccion de Dependencias Spring nos da el repositorio
      
    */
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Override
    public List<Cliente> listarTodos() {
        return List.of();
    }

    @Override
    public Cliente guardar(Cliente cLiente) {
        return null;
    }

    @Override
    public Optional<Cliente> buscarPorDPI(String dpi) {
        return Optional.empty();
    }

    @Override
    public Cliente actualizar(String dpi, Cliente cliente) {
        return null;
    }

    @Override
    public void eliminar(String dpi) {

    }

    @Override
    public boolean existePorDPI(String dpi) {
        return false;
    }
}
