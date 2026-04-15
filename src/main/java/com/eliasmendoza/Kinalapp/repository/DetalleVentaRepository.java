package com.eliasmendoza.Kinalapp.repository;

import com.eliasmendoza.Kinalapp.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositorio para DetalleVenta. Hereda métodos como save(), findAll(), deleteById().
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
    // Long corresponde al tipo de codigoDetalleVenta
}