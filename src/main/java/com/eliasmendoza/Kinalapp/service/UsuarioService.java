package com.eliasmendoza.Kinalapp.service;

import com.eliasmendoza.Kinalapp.entity.Usuario;
import com.eliasmendoza.Kinalapp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorCodigo(String codigoUsuario) {
        return usuarioRepository.findById(codigoUsuario);
    }

    @Override
    public Usuario actualizar(String codigoUsuario, Usuario usuario) {
        // Buscamos si el usuario existe por su código antes de actualizar
        return usuarioRepository.findById(codigoUsuario)
                .map(u -> {
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
        usuarioRepository.deleteById(codigoUsuario);
    }

    @Override
    public boolean existePorCodigo(String codigoUsuario) {
        return usuarioRepository.existsById(codigoUsuario);
    }
}