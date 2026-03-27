package com.eliasmendoza.Kinalapp.repository;

import com.eliasmendoza.Kinalapp.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    // Método que devuelve una lista de todos los usuarios
    List<Usuario> listarTodos();

    // Método que guarda un Usuario en la BD
    Usuario guardar(Usuario usuario);

    // Buscamos por el campo @Id de tu entidad: codigoUsuario
    Optional<Usuario> buscarPorCodigo(String codigoUsuario);

    // Método que recibe el código y el objeto con nuevos datos
    Usuario actualizar(String codigoUsuario, Usuario usuario);

    // Elimina un Usuario por su código
    void eliminar(String codigoUsuario);

    // Verifica existencia por código
    boolean existePorCodigo(String codigoUsuario);
}