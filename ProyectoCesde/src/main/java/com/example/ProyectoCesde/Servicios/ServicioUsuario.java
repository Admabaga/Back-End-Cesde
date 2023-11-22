package com.example.ProyectoCesde.Servicios;

import com.example.ProyectoCesde.DTOS.LogDTO;
import com.example.ProyectoCesde.DTOS.UsuarioDTO;

public interface ServicioUsuario {

    UsuarioDTO crearusuario(UsuarioDTO usuarioDTO);

    UsuarioDTO cambiarPassword(UsuarioDTO usuarioDTO, Long usuarioId);

    String log(LogDTO logDTO);

}
