package com.example.ProyectoCesde.Servicios;

import com.example.ProyectoCesde.Entidades.Usuario;
import com.example.ProyectoCesde.Repositorios.RepositorioUsuarios;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeguridadUsuarios implements UserDetailsService {
    private RepositorioUsuarios repositorioUsuarios;

    public SeguridadUsuarios(RepositorioUsuarios repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        nombreExisteEnBaseDeDatos(username);
        Usuario usuario = this.repositorioUsuarios.traerUsuarioPorNombre(username);

        return User.builder()
                .username(usuario.getNombreUsuario())
                .password(usuario.getPassword())
                .build();
    }

    private String[] getAuthorities(String role) {
        if ("ADMIN".equals(role) || "CUSTOMER".equals(role)) {
            return new String[] {"random_order"};
        }

        return new String[] {};
    }

    private List<GrantedAuthority> grantedAuthorities(String[] roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

        for (String role: roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

            for (String authority: this.getAuthorities(role)) {
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }

        return authorities;
    }
    public void nombreExisteEnBaseDeDatos(String username) {
        if (!repositorioUsuarios.nombreExiste(username)) {
            throw new RuntimeException("El usuario " + username + " no existe");
        }

    }

}
