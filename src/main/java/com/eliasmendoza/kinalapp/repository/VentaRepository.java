package com.eliasmendoza.kinalapp.repository;

import com.eliasmendoza.kinalapp.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}