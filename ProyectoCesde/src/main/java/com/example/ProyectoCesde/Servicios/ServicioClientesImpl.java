package com.example.ProyectoCesde.Servicios;

import com.example.ProyectoCesde.Convertidor.ClientesConvertidor;
import com.example.ProyectoCesde.Convertidor.CorreoConvertidor;
import com.example.ProyectoCesde.DTOS.ClientesDTO;
import com.example.ProyectoCesde.DTOS.CorreoDTO;
import com.example.ProyectoCesde.Entidades.Clientes;
import com.example.ProyectoCesde.Entidades.Correo;
import com.example.ProyectoCesde.Repositorios.RepositorioClientes;
import com.example.ProyectoCesde.Repositorios.RepositorioCorreo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicioClientesImpl implements ServicioClientes {
    private final RepositorioClientes repositorioClientes;
    private final RepositorioCorreo repositorioCorreo;
    private final JavaMailSender enviarCorreo;

    public ServicioClientesImpl(RepositorioClientes repositorioClientes, RepositorioCorreo repositorioCorreo, JavaMailSender enviarCorreo) {
        this.repositorioClientes = repositorioClientes;
        this.repositorioCorreo = repositorioCorreo;
        this.enviarCorreo = enviarCorreo;
    }


    @Override
    public ClientesDTO guardarCliente(ClientesDTO clienteDTO) {
        Clientes clientes = ClientesConvertidor.dtoAEntidad(clienteDTO);
        repositorioClientes.save(clientes);
        return ClientesConvertidor.entidadADto(clientes);
    }

    @Override
    public List<ClientesDTO> traerClientes() {
        List<Clientes> listaClientes = repositorioClientes.findAll();
        return listaClientes.stream()
                .map(cliente -> ClientesConvertidor.entidadADto(cliente))
                .collect(Collectors.toList());
    }

    @Override
    public List<CorreoDTO> enviarCorreoAClientes(CorreoDTO correoDTO) throws MessagingException, IOException {
        Set<String> correosBaseDeDatos = repositorioClientes.correosClientes();
        List<String> correos = correoDTO.getCorreos();
        List<Correo> correosEnviados = new ArrayList<>();
        if (!correos.isEmpty()){
                for (String correosIngresados : correos){
                correoDTO.setCorreo(correosIngresados);
                enviarCorreos(correoDTO);
                Correo correo = CorreoConvertidor.dtoAEntidad(correoDTO);
                correo.setCorreo(correosIngresados);
                correosEnviados.add(correo);
                repositorioCorreo.save(correo);
            }
        }else {
            for (String correoClientes : correosBaseDeDatos) {
                correoDTO.setCorreo(correoClientes.toString());
                enviarCorreos(correoDTO);
                Correo correo = CorreoConvertidor.dtoAEntidad(correoDTO);
                correo.setCorreo(correoClientes.toString());
                correosEnviados.add(correo);
                repositorioCorreo.save(correo);
            }
        }
        return correosEnviados.stream()
                .map(CorreoConvertidor::entidadADto)
                .collect(Collectors.toList());
    }

    public void enviarCorreos(CorreoDTO correoDTO) throws MessagingException {
        MimeMessage message = enviarCorreo.createMimeMessage();
        MimeMessageHelper correoAEnviar = new MimeMessageHelper(message, true);
        correoAEnviar.setFrom(correoDTO.getRemitente());
        correoAEnviar.setTo(correoDTO.getCorreo());
        correoAEnviar.setSubject(correoDTO.getAsunto());
        correoAEnviar.setText(correoDTO.getCuerpoDelCorreo());
        correoAEnviar.addAttachment(correoDTO.getNombreArchivo(), correoDTO.getArchivoAdjunto());
        enviarCorreo.send(message);
    }
}



