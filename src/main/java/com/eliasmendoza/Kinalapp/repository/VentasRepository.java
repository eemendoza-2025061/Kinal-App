package com.eliasmendoza.Kinalapp.repository;

import com.eliasmendoza.Kinalapp.entity.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositorio para la entidad Ventas.
public interface VentasRepository extends JpaRepository<Ventas, Long> {
}
