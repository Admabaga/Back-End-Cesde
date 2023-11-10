package com.example.ProyectoCesde.Servicios;

import com.example.ProyectoCesde.Entidades.Usuario;
import com.example.ProyectoCesde.Repositorios.RepositorioUsuarios;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        Usuario usuario = repositorioUsuarios.traerUsuarioPorNombre(username);



        return User.builder()
                .username(usuario.getNombreUsuario())
                .password(usuario.getPassword())
                .build();
    }
    public void nombreExisteEnBaseDeDatos(String nombreUsuario){
        if (!repositorioUsuarios.nombreExiste(nombreUsuario)) {
            throw new UsernameNotFoundException("El usuario " + nombreUsuario + " no existe");
        }
    }



}
