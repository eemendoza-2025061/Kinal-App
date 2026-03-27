package com.eliasmendoza.Kinalapp.repository;

import com.eliasmendoza.Kinalapp.entity.Usuario;
import com.eliasmendoza.Kinalapp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Anotación que registra un Bean como un Bean de Spring
// Indica que la clase contiene la lógica del negocio
@Service
// Por defecto todos los métodos de esta clase serán transaccionales
@Transactional
public class UsuarioService implements IUsuarioService {

    /* private: solo accesible dentro de la clase
       UsuarioRepository: Es el repositorio para acceder a la DB
       Inyección de Dependencias: Spring nos da el repositorio
    */
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listarTodos() {
        // Retorna la lista de todos los usuarios usando el repositorio
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        // Guarda el objeto usuario y lo retorna con sus datos persistidos
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorCodigo(String codigoUsuario) {
        // Optional evita errores de NullPointerException al buscar por la llave primaria String
        return usuarioRepository.findById(codigoUsuario);
    }

    @Override
    public Usuario actualizar(String codigoUsuario, Usuario usuario) {
        // Buscamos si el usuario existe por su código antes de actualizar
        return usuarioRepository.findById(codigoUsuario)
                .map(u -> {
                    // Seteamos los datos específicos de tu entidad Usuario
                    u.setNombreUsuario(usuario.getNombreUsuario());
                    u.setPasswordUsuario(usuario.getPasswordUsuario());
                    u.setEmailUsuario(usuario.getEmailUsuario());
                    u.setRolUsuario(usuario.getRolUsuario());
                    u.setEstado(usuario.getEstado());
                    return usuarioRepository.save(u);
                })
                .orElse(null); // Retorna null si el código no existe
    }

    @Override
    public void eliminar(String codigoUsuario) {
        // Método void que no retorna nada, elimina por el ID (codigoUsuario)
        usuarioRepository.deleteById(codigoUsuario);
    }

    @Override
    public boolean existePorCodigo(String codigoUsuario) {
        // Retorna true si el código existe en la BD, false si no
        return usuarioRepository.existsById(codigoUsuario);
    }
}