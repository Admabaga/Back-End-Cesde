package com.example.ProyectoCesde.Servicios;

import com.example.ProyectoCesde.Convertidor.UsuarioConvertidor;
import com.example.ProyectoCesde.DTOS.UsuarioDTO;
import com.example.ProyectoCesde.Entidades.Usuario;
import com.example.ProyectoCesde.Repositorios.RepositorioUsuarios;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioRegistrarseImpl implements ServicioRegistrarse{

    private RepositorioUsuarios repositorioUsuarios;

    public ServicioRegistrarseImpl(RepositorioUsuarios repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
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
}
