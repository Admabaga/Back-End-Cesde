package com.example.ProyectoCesde.Controlador;

import com.example.ProyectoCesde.DTOS.ClientesDTO;
import com.example.ProyectoCesde.DTOS.CorreoDTO;
import com.example.ProyectoCesde.Servicios.ServicioClientes;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping(value = "/clientes/correos", consumes = "multipart/form-data")
    public List<CorreoDTO> enviarMensaje(
            @RequestParam("remitente") String remitente,
            @RequestParam("correos") List<String> correos,
            @RequestParam("asunto") String asunto,
            @RequestParam("cuerpoDelCorreo") String cuerpoDelCorreo,
            @RequestPart(name = "file", required = false) MultipartFile adjunto) throws MessagingException, IOException {
        CorreoDTO correoDTO = new CorreoDTO();
        if (adjunto != null){
            String nombreArchivo = adjunto.getOriginalFilename();
            correoDTO.setNombreArchivo(nombreArchivo);
            correoDTO.setArchivoAdjunto(adjunto);
        }
        correoDTO.setRemitente(remitente);
        correoDTO.setAsunto(asunto);
        correoDTO.setCorreos(correos);
        correoDTO.setCuerpoDelCorreo(cuerpoDelCorreo);
        return servicioClientes.enviarCorreoAClientes(correoDTO);
    }
}
