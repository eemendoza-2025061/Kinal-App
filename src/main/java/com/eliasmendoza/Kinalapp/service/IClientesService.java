package com.eliasmendoza.Kinalapp.service;

import com.eliasmendoza.Kinalapp.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClientesService {
    //Interfaz: Es un contrato que dice QUE metodos debe tener
    //cualquier servicio de Clientes, No tiene
    //implementacion, solo la definicion de los metodos

    //Metodo que vuleve una lista de todos los clientes
    List<Cliente> listarTodos();
    //List<Cliente> lo que hace es devolver una lista
    //de objetos de la entidad Clientes

    //Metodo que guarda un Cliente en al BD
    Cliente guardar(Cliente cLiente);
    //Parametros - Recibe un objeto Cliente con los datos a guardar

    //Optional - Contenedor que puede o no tener un valor
    //evita el error de NullPointException
    Optional<Cliente> buscarPorDPI(String dpi);

    //Metodo que actualiza un Cliente
    Cliente actualizar(String dpi, Cliente cliente);
    //Parametros - dpi: DPI del Cliente a actualizar
    //Cliente cliente: Objeto con los datos nuevos
    //Retona un objeto de tipo Cliente ya actualizado

    //Metodo de tipo void para eliminar a un Cliente
    //void: no retorna ningun dato
    //Elimina un Cliente por su DPI
    void eliminar(String dpi);

    //boolean - Retorna true si existe, false si no existe
    boolean existePorDPI(String dpi);





}
