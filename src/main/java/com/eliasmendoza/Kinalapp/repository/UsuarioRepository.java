package com.eliasmendoza.Kinalapp.repository;

import com.eliasmendoza.Kinalapp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,String> {
}
