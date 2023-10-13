package com.example.ProyectoCesde.Convertidor;

import com.example.ProyectoCesde.DTOS.ClientesDTO;
import com.example.ProyectoCesde.Entidades.Clientes;

public class ClientesConvertidor {

    public static Clientes dtoAEntidad(ClientesDTO clientesDTO){
        Clientes clientes = new Clientes();
        clientes.setNombre(clientesDTO.getNombre());
        clientes.setCorreoElectronico(clientesDTO.getCorreoElectronico());
        clientes.setTelefono(clientesDTO.getTelefono());
        clientes.setMensaje(clientesDTO.getMensaje());
        return clientes;
    }

    public static ClientesDTO entidadADto(Clientes clientes){
        ClientesDTO clientesDTO = new ClientesDTO();
        clientesDTO.setId(clientes.getId());
        clientesDTO.setNombre(clientes.getNombre());
        clientesDTO.setCorreoElectronico(clientes.getCorreoElectronico());
        clientesDTO.setTelefono(clientes.getTelefono());
        clientesDTO.setMensaje(clientes.getMensaje());
        return clientesDTO;
    }
}
