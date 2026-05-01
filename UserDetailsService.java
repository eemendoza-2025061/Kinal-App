package com.eliasmendoza.Kinalapp.service;

import com.eliasmendoza.Kinalapp.entity.Usuario;
import com.eliasmendoza.Kinalapp.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public <GrantedAuthority> User loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException());

        Set<GrantedAuthority> authorities = usuario.getRol().trim()
                .matches(rol -> {
                    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + rol.getNombre());
                    SimpleGrantedAuthority simpleGrantedAuthority1 = simpleGrantedAuthority;
                    return simpleGrantedAuthority1;
                })
                .collect(Collectors.toSet());

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(),
                true, true, true, authorities);
    }

    private class UserDetails {
    }

    private class UsernameNotFoundException extends Exception {
    }

    private record SimpleGrantedAuthority(String s) {
    }

    private record User<GrantedAuthority>(String username, String password, boolean enabled, boolean b, boolean b1, boolean b2,
                                          Set<GrantedAuthority> authorities) {
    }
}