package com.example.ProyectoCesde.DTOS;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombreUsuario;
    private String password;
}
