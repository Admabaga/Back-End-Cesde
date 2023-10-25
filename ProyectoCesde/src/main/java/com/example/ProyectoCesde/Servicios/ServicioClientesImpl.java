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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
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
        clientes = repositorioClientes.save(clientes);
        Correo correo = new Correo();
        correo.setCorreo(clientes.getCorreoElectronico());
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
    public String enviarCorreoAClientes(CorreoDTO correoDTO) {
        List<String> correos = repositorioClientes.correosClientes();
        for (String correoClientes : correos) {
            Correo correo = new Correo();
                correo.setCorreo(correoClientes.toString());
                correo.setAsunto(correoDTO.getAsunto());
                correo.setCuerpoDelCorreo(correoDTO.getCuerpoDelCorreo());
                correo.setRemitente("admabaga@outlook.com");
                enviarCorreoTexto(correo);
                repositorioCorreo.save(correo);

        }
        return ("Correo enviado exitosamente!");
    }

    @Override
    public String enviarCorreosAdjuntos(CorreoDTO correoDTO) {
        Correo correo = CorreoConvertidor.dtoAEntidad(correoDTO);
        List<String> correosBaseDatos = repositorioClientes.correosClientes();
        for (String correoCLiente : correosBaseDatos) {

            correo.setCorreo(correoCLiente.toString());
            correo.setRemitente("admabaga@outlook.com");
            try {
//                correo.setArchivo(correoDTO.getArchivo().getBytes());
                correo.setNombreArchivo(correoDTO.getArchivo().getOriginalFilename());
                enviarCorreoAjunto(correo);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            repositorioCorreo.save(correo);

        }
        return null;
    }
//    EmailDetails emailDetails = new EmailDetails();
//        emailDetails.setFrom("tucorreo@gmail.com");
//        emailDetails.setTo(to);
//        emailDetails.setSubject(subject);
//        emailDetails.setText(text);
//        try {
//        emailDetails.setAttachment(file.getBytes());
//        emailDetails.setAttachmentName(file.getOriginalFilename());
//    } catch (IOException e) {
//        e.printStackTrace();
//    }

    public void enviarCorreoTexto(Correo correo){
        SimpleMailMessage correoAEnviar = new SimpleMailMessage();
        correoAEnviar.setFrom(correo.getRemitente());
        correoAEnviar.setTo(correo.getCorreo());
        correoAEnviar.setSubject(correo.getAsunto());
        correoAEnviar.setText(correo.getCuerpoDelCorreo());
        enviarCorreo.send(correoAEnviar);
    }

    public void enviarCorreoAjunto(Correo correo) throws MessagingException {
        MimeMessage mensaje = enviarCorreo.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
        helper.setFrom(correo.getRemitente());
        helper.setTo(correo.getCorreo());
        helper.setSubject(correo.getAsunto());
        helper.setText(correo.getCuerpoDelCorreo(), true);
        helper.addAttachment(correo.getNombreArchivo(), correo.getArchivo());
        enviarCorreo.send(mensaje);
    }
}



