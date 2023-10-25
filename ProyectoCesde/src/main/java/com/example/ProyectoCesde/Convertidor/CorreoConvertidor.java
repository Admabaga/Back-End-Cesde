package com.example.ProyectoCesde.Convertidor;

import com.example.ProyectoCesde.DTOS.CorreoDTO;
import com.example.ProyectoCesde.Entidades.Correo;

public class CorreoConvertidor {
    public static Correo dtoAEntidad(CorreoDTO correoDTO){
        Correo correo = new Correo();
        correo.setAsunto(correoDTO.getAsunto());
        correo.setCuerpoDelCorreo(correoDTO.getCuerpoDelCorreo());
        correo.setArchivo(correoDTO.getArchivo());
        return correo;
    }

    public static CorreoDTO entidadADto(Correo correo){
        CorreoDTO correoDTO = new CorreoDTO();
        correoDTO.setId(correo.getId());
        correoDTO.setAsunto(correo.getAsunto());
        correoDTO.setCuerpoDelCorreo(correo.getCuerpoDelCorreo());
        correoDTO.setRemitente(correo.getRemitente());
        correoDTO.setNombreArchivo(correo.getNombreArchivo());
        return correoDTO;
    }
}
