package com.example.ProyectoCesde.DTOS;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CorreoDTO {
    Long id;
    String asunto;
    String cuerpoDelCorreo;
    String remitente;
    String correo;
    List<String> correos;
    LocalDateTime fechaYHora;
}
