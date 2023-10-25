package com.example.ProyectoCesde.Convertidor;

import com.example.ProyectoCesde.DTOS.UsuarioDTO;
import com.example.ProyectoCesde.Entidades.Usuario;

public class UsuarioConvertidor {

    public static Usuario dtoAEntidad (UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
        usuario.setPassword(usuarioDTO.getPassword());
        return usuario;
    }

    public static UsuarioDTO entidadADto(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
        usuarioDTO.setPassword(usuario.getPassword());
        return usuarioDTO;
    }

}
