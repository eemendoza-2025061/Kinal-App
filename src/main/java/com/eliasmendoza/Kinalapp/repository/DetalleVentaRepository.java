package com.eliasmendoza.Kinalapp.repository;

import com.eliasmendoza.Kinalapp.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {

}