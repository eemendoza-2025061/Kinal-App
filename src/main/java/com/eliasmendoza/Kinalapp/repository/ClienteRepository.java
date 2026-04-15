package com.eliasmendoza.Kinalapp.repository;

import com.eliasmendoza.Kinalapp.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Los Repositorios son la capa de acceso a datos (DAO).
 * Al extender de JpaRepository, Spring genera automáticamente la implementación
 * de los métodos CRUD (Create, Read, Update, Delete) en tiempo de ejecución.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
