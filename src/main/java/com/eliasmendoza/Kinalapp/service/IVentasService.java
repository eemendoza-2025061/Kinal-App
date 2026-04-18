package com.eliasmendoza.Kinalapp.service;

import com.eliasmendoza.Kinalapp.entity.Ventas;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public interface IVentasService {

    @Transactional(readOnly = true)
    List<Ventas> listarTodos();
    // List<Ventas> lo que hace es devolver una lista de objetos de la entidad Ventas

    // Metodo que guarda una Venta en la BD
    Ventas guardar(Ventas ventas);

    // Optional - Contenedor que puede o no tener un valor evita el error de NullPointerException
    Optional<Ventas> buscarPorCodigo(Long codigo);

    @Transactional(readOnly = true)
    List<Ventas> buscarPorEstado(int estado);

    // Metodo que actualiza una Venta
    Ventas actualizar(Long codigo, Ventas ventas);

    // Metodo de tipo void para eliminar una Venta (no retorna ningún dato)
    void eliminar(Long codigo);

    // boolean - Retorna true si existe, false si no existe
    boolean existePorCodigo(Long codigo);
}
