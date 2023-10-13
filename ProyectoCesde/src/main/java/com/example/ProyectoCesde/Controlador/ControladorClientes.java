package com.example.ProyectoCesde.Controlador;

import com.example.ProyectoCesde.DTOS.ClientesDTO;
import com.example.ProyectoCesde.DTOS.CorreoDTO;
import com.example.ProyectoCesde.Servicios.ServicioClientes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControladorClientes {
    private final ServicioClientes servicioClientes;

    public ControladorClientes(ServicioClientes servicioClientes) {
        this.servicioClientes = servicioClientes;
    }

    @PostMapping(value = "/clientes")
    public ClientesDTO guardarCLientes(@RequestBody ClientesDTO clientesDTO){
        return servicioClientes.guardarCliente(clientesDTO);
    }

    @GetMapping(value = "/clientes")
    public List<ClientesDTO> traerCLientes(){
        return servicioClientes.traerClientes();
    }

    @PostMapping(value ="/clientes/correos")
    public void enviarCorreos(@RequestBody CorreoDTO correoDTO) {
        servicioClientes.enviarCorreoAClientes(correoDTO);
    }
}
