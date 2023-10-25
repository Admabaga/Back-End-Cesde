package com.example.ProyectoCesde.DTOS;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class CorreoDTO {
    private Long id;
    private String asunto;
    private String cuerpoDelCorreo;
    private String remitente;
    private String nombreArchivo;
    private MultipartFile archivo;

}
