package com.example.ProyectoCesde.Controlador;

import com.example.ProyectoCesde.DTOS.ClientesDTO;
import com.example.ProyectoCesde.DTOS.CorreoDTO;
import com.example.ProyectoCesde.Servicios.ServicioClientes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/sendmail")
    public ResponseEntity<String> sendMail(@RequestBody CorreoDTO correoDTO) {

        servicioClientes.enviarCorreosAdjuntos(correoDTO);
        return ResponseEntity.ok("Mail sent successfully");
    }
}
