package com.example.ProyectoCesde.Controlador;

import com.example.ProyectoCesde.DTOS.LogDTO;
import com.example.ProyectoCesde.DTOS.UsuarioDTO;
import com.example.ProyectoCesde.Servicios.ServicioUsuario;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ControladorUsuario {

    private ServicioUsuario servicioUsuario;

    public ControladorUsuario(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    @PostMapping(value = "/usuarios")
    public UsuarioDTO crearUsuario (@RequestBody UsuarioDTO usuarioDTO){
        return servicioUsuario.crearusuario(usuarioDTO);
    }

    @PutMapping(value = "/usuarios/{usuarioId}")
     public UsuarioDTO cambiarPassword (@RequestBody UsuarioDTO usuarioDTO, @PathVariable Long usuarioId){
        return servicioUsuario.cambiarPassword(usuarioDTO, usuarioId);
    }

    @PostMapping(value = "/usuarios/login")
    public String login(@RequestBody LogDTO logDTO){
        return servicioUsuario.log(logDTO);
    }

}
