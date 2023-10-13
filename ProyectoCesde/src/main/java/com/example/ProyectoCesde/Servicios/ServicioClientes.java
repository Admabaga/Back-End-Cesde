package com.example.ProyectoCesde.Servicios;

import com.example.ProyectoCesde.DTOS.ClientesDTO;
import com.example.ProyectoCesde.DTOS.CorreoDTO;
import jakarta.mail.MessagingException;

import java.util.List;

public interface ServicioClientes {

    ClientesDTO guardarCliente(ClientesDTO clienteDTO);

    List<ClientesDTO> traerClientes();

    String enviarCorreoAClientes(CorreoDTO correoDTO) ;
}
