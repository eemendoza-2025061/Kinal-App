package com.eliasmendoza.Kinalapp.repository;

import com.eliasmendoza.Kinalapp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository 
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca usuarios por username. Retorna List para evitar excepción si hay duplicados.
    List<Usuario> findByUsername(String username);

    // Verifica si ya existe un username (para validar registro)
    boolean existsByUsername(String username);

    // Verifica si ya existe un email (para evitar duplicados)
    boolean existsByEmail(String email);
}
