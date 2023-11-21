package com.example.ProyectoCesde.Convertidor;

import com.example.ProyectoCesde.DTOS.CorreoDTO;
import com.example.ProyectoCesde.Entidades.Correo;

import java.io.IOException;
import java.time.LocalDateTime;

public class CorreoConvertidor {
    public static Correo dtoAEntidad(CorreoDTO correoDTO) throws IOException {
        Correo correo = new Correo();
        correo.setAsunto(correoDTO.getAsunto());
        correo.setCuerpoDelCorreo(correoDTO.getCuerpoDelCorreo());
        correo.setRemitente(correoDTO.getRemitente());
        correo.setFechaYHora(LocalDateTime.now());
        correo.setNombreArchivo(correoDTO.getNombreArchivo());
        return correo;
    }

    public static CorreoDTO entidadADto(Correo correo){
        CorreoDTO correoDTO = new CorreoDTO();
        correoDTO.setId(correo.getId());
        correoDTO.setAsunto(correo.getAsunto());
        correoDTO.setCuerpoDelCorreo(correo.getCuerpoDelCorreo());
        correoDTO.setRemitente(correo.getRemitente());
        correoDTO.setFechaYHora(correo.getFechaYHora());
        correoDTO.setCorreo(correo.getCorreo());
        correoDTO.setNombreArchivo(correo.getNombreArchivo());
        return correoDTO;
    }
}
