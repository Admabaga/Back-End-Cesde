package com.example.ProyectoCesde.Servicios;

import com.example.ProyectoCesde.DTOS.UsuarioDTO;

public interface ServicioRegistrarse {

    UsuarioDTO crearusuario(UsuarioDTO usuarioDTO);

    UsuarioDTO cambiarPassword(UsuarioDTO usuarioDTO, Long usuarioId);
}
