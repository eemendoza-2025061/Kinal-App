package com.eliasmendoza.kinalapp.repository;

import com.eliasmendoza.kinalapp.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}