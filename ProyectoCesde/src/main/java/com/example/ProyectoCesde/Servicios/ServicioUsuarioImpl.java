package com.example.ProyectoCesde.Servicios;

import com.example.ProyectoCesde.ConfiguracionSeguridad.JwtUtil;
import com.example.ProyectoCesde.Convertidor.UsuarioConvertidor;
import com.example.ProyectoCesde.DTOS.LogDTO;
import com.example.ProyectoCesde.DTOS.UsuarioDTO;
import com.example.ProyectoCesde.Entidades.Usuario;
import com.example.ProyectoCesde.Repositorios.RepositorioUsuarios;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioUsuarioImpl implements ServicioUsuario {

    private RepositorioUsuarios repositorioUsuarios;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public ServicioUsuarioImpl(RepositorioUsuarios repositorioUsuarios, AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.repositorioUsuarios = repositorioUsuarios;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioDTO crearusuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioConvertidor.dtoAEntidad(usuarioDTO);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        repositorioUsuarios.save(usuario);
        return UsuarioConvertidor.entidadADto(usuario);
    }

    @Override
    public UsuarioDTO cambiarPassword(UsuarioDTO usuarioDTO, Long usuarioId) {
        Optional<Usuario> usuarioOptional = repositorioUsuarios.findById(usuarioId);
        if (!usuarioOptional.isPresent()){
            new RuntimeException("El usuario no existe");
        }
        Usuario usuario = usuarioOptional.get();
        usuario.setPassword(usuarioDTO.getPassword());
        repositorioUsuarios.save(usuario);
        return UsuarioConvertidor.entidadADto(usuario);
    }

    @Override
    public String log(LogDTO logDTO) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logDTO.getNombreUsuario(), logDTO.getPassword()));

            Usuario usuario = repositorioUsuarios.traerUsuarioPorNombre(logDTO.getNombreUsuario());
            String token = jwtUtil.create(usuario.getNombreUsuario());
            return token;
        }
}
