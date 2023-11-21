package com.example.ProyectoCesde.Servicios;

import com.example.ProyectoCesde.DTOS.ClientesDTO;
import com.example.ProyectoCesde.DTOS.CorreoDTO;
import com.example.ProyectoCesde.Entidades.Correo;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ServicioClientes {

    ClientesDTO guardarCliente(ClientesDTO clienteDTO);

    List<ClientesDTO> traerClientes();

    List<CorreoDTO> enviarCorreoAClientes(CorreoDTO correoDTO) throws MessagingException, IOException;
}
