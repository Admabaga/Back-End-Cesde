package com.example.ProyectoCesde.DTOS;

import lombok.Data;

@Data
public class CorreoDTO {
    Long id;
    String asunto;
    String cuerpoDelCorreo;
    String remitente;

}
