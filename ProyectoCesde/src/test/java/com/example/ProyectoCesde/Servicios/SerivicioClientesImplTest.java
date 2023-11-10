package com.example.ProyectoCesde.Servicios;

import com.example.ProyectoCesde.DTOS.CorreoDTO;
import com.example.ProyectoCesde.Entidades.Correo;
import com.example.ProyectoCesde.Repositorios.RepositorioClientes;
import com.example.ProyectoCesde.Repositorios.RepositorioCorreo;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mail.javamail.JavaMailSender;


public class SerivicioClientesImplTest {
    private RepositorioClientes repositorioClientesMock = Mockito.mock(RepositorioClientes.class);
    private RepositorioCorreo repositorioCorreoMock = Mockito.mock(RepositorioCorreo.class);
    private JavaMailSender enviarCorreoMock = Mockito.mock(JavaMailSender.class);
    private ServicioClientes servicioClientes = new ServicioClientesImpl(repositorioClientesMock, repositorioCorreoMock,enviarCorreoMock);

//    @Test
//    public void enviarCorreoAClientes(){
//        //Given
//        Correo correo = new Correo();
//
//        When("se envía el correo electrónico");
//        emailService.sendEmail(email);
//
//        Then("el correo electrónico se envía correctamente");
//        Mockito.verify(emailSenderMock).sendEmail(email);
//
//    }
}
