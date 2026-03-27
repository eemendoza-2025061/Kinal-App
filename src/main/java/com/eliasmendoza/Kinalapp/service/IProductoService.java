package com.eliasmendoza.Kinalapp.service;

import com.eliasmendoza.Kinalapp.entity.Producto;
import java.util.List;
import java.util.Optional;

public interface IProductoService {
    List<Producto> listarTodos();
    Producto guardar(Producto producto);
    Optional<Producto> buscarPorCodigo(int codigo);
    Producto actualizar(int codigo, Producto producto);
    void eliminar(int codigo);
    boolean existePorCodigo(int codigo);
}