package com.example.ProyectoCesde.Controlador;

import com.example.ProyectoCesde.DTOS.UsuarioDTO;
import com.example.ProyectoCesde.Servicios.ServicioRegistrarse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ControladorRegistrarse {

    private ServicioRegistrarse servicioRegistrarse;

    public ControladorRegistrarse(ServicioRegistrarse servicioRegistrarse) {
        this.servicioRegistrarse = servicioRegistrarse;
    }

    @PostMapping(value = "/registrarse")
    UsuarioDTO crearUsuario (@RequestBody UsuarioDTO usuarioDTO){
        return servicioRegistrarse.crearusuario(usuarioDTO);
    }

    @PutMapping(value = "/registrarse/{usuarioId}")
    UsuarioDTO cambiarPassword (@RequestBody UsuarioDTO usuarioDTO, @PathVariable Long usuarioId){
        return servicioRegistrarse.cambiarPassword(usuarioDTO, usuarioId);
    }

}
