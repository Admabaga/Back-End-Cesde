package com.example.ProyectoCesde.Servicios;

import com.example.ProyectoCesde.ConfiguracionSeguridad.Tokens;
import com.example.ProyectoCesde.Convertidor.UsuarioConvertidor;
import com.example.ProyectoCesde.DTOS.LogDTO;
import com.example.ProyectoCesde.DTOS.UsuarioDTO;
import com.example.ProyectoCesde.Entidades.Usuario;
import com.example.ProyectoCesde.Repositorios.RepositorioUsuarios;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioUsuarioImpl implements ServicioUsuario {

    private RepositorioUsuarios repositorioUsuarios;
    private AuthenticationManager authenticationManager;
    private Tokens tokens;

    public ServicioUsuarioImpl(RepositorioUsuarios repositorioUsuarios, AuthenticationManager authenticationManager, Tokens token) {
        this.repositorioUsuarios = repositorioUsuarios;
        this.authenticationManager = authenticationManager;
        this.tokens = token;
    }

    @Override
    public UsuarioDTO crearusuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioConvertidor.dtoAEntidad(usuarioDTO);
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
        nombreExisteEnBaseDeDatos(logDTO.getNombreUsuario());
        Usuario usuario = repositorioUsuarios.traerUsuarioPorNombre(logDTO.getNombreUsuario());
        if (usuario == null){
            throw new RuntimeException("Ingrese el usuario y la contraseña");
        }
        if (usuario.getPassword().equals(logDTO.getPassword())){
            throw new RuntimeException("Contraseña incorrecta");

        }
        generarToken(logDTO);
        return logDTO.getToken();
    }

    public void nombreExisteEnBaseDeDatos(String nombreUsuario){
        if (!repositorioUsuarios.nombreExiste(nombreUsuario)) {
            throw new RuntimeException("El usuario ingresado no existe");
        }
    }

    public void generarToken(LogDTO logDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(logDTO.getNombreUsuario(), logDTO.getPassword()));
        String token = tokens.crearToken(authentication);
        logDTO.setToken(token);
    }
}
