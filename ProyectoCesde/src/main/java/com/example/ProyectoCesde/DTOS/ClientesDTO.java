package com.example.ProyectoCesde.DTOS;

import lombok.Data;

@Data
public class ClientesDTO {
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String telefono;
    private String mensaje;
}
