package com.eliasmendoza.kinalapp.service;

import com.eliasmendoza.kinalapp.entity.DetalleVenta;

import java.util.List;

public interface IDetalleVentaService {

    List<DetalleVenta> listarTodos();

    DetalleVenta guardar(DetalleVenta detalleVenta);

    DetalleVenta buscarPorId(Long id);

    void eliminar(Long id);
}
