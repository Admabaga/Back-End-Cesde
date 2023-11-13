package com.example.ProyectoCesde.Servicios;

import com.example.ProyectoCesde.Convertidor.ClientesConvertidor;
import com.example.ProyectoCesde.Convertidor.CorreoConvertidor;
import com.example.ProyectoCesde.DTOS.ClientesDTO;
import com.example.ProyectoCesde.DTOS.CorreoDTO;
import com.example.ProyectoCesde.Entidades.Clientes;
import com.example.ProyectoCesde.Entidades.Correo;
import com.example.ProyectoCesde.Repositorios.RepositorioClientes;
import com.example.ProyectoCesde.Repositorios.RepositorioCorreo;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class SerivicioClientesImplTest {
    private RepositorioClientes repositorioClientesMock = Mockito.mock(RepositorioClientes.class);
    private RepositorioCorreo repositorioCorreoMock = Mockito.mock(RepositorioCorreo.class);
    private JavaMailSender enviarCorreoMock = Mockito.mock(JavaMailSender.class);
    private ServicioClientes servicioClientes = new ServicioClientesImpl(repositorioClientesMock, repositorioCorreoMock,enviarCorreoMock);

//    @Test
//    public void enviarCorreoAClientesEnBd(){
//        //Given
//        CorreoDTO correoDTO = new CorreoDTO();
//        correoDTO.setCorreos(null);
//        correoDTO.setCuerpoDelCorreo("Probando testing");
//        correoDTO.setRemitente("admabaga@outlook.com");
//        correoDTO.setFechaYHora(null);
//        correoDTO.setId(2L);
//        correoDTO.setCorreo(null);
//        correoDTO.setAsunto("Buenas noches");
//        Correo correo = CorreoConvertidor.dtoAEntidad(correoDTO);
//
//        Set<String> correosBaseDatos = new HashSet<>();
//        correosBaseDatos.add("varmencino@gmail.com");
//        correosBaseDatos.add("varmencino@hotmail.com");
//        correosBaseDatos.add("admabaga@outlook.com");
//        correosBaseDatos.add("admabaga@outlook.com");
//
//        List<Correo> correosEnviados = Arrays.asList(
//                new Correo(5L, "Buenas noches", "Probando testing", "varmencino@gmail.com", "admabaga@outlook.com", null),
//                new Correo(6L, "Buenas noches", "Probando testing", "varmencino@hotmail.com", "admabaga@outlook.com", null),
//                new Correo(7L, "Buenas noches", "Probando testing", "admabaga@outlook.com", "admabaga@outlook.com", null)
//        );
//        SimpleMailMessage correoAEnviar = new SimpleMailMessage();
//        correoAEnviar.setFrom(correo.getRemitente());
//        correoAEnviar.setTo(correo.getCorreo());
//        correoAEnviar.setSubject(correo.getAsunto());
//        correoAEnviar.setText(correo.getCuerpoDelCorreo());
////        When("se envía el correo electrónico");
//        emailService.sendEmail(email);
//
//        // Configura el comportamiento esperado del mock
//        when(repositorioClientesMock.correosClientes()).thenReturn(correosBaseDatos);
//        when(repositorioCorreoMock.save(any(Correo.class))).thenReturn(correo);
//        enviarCorreoMock.;
//
//        // Llama al método que envía el correo
//        List<CorreoDTO> correoDTOList = servicioClientes.enviarCorreoAClientes(correoDTO);
//
//        // Verifica que se haya llamado al método con los parámetros correctos
//        verify(emailSender).sendEmail("destinatario@example.com", "Asunto", "Cuerpo del correo");
//
//        // Verifica el resultado de la llamada al método
//        assertTrue(result);
//        assertTrue(correoDTOList.size() > 0);
//        assertEquals(correoDTOList.size(), correosEnviados.size());
//
//    }
    @Test
    public void guardarCliente(){
        //Given
        ClientesDTO clientesDTO = new ClientesDTO();
        clientesDTO.setId(5L);
        clientesDTO.setMensaje("Buenas tardes");
        clientesDTO.setNombre("Adrian Barrera");
        clientesDTO.setCorreoElectronico("varmencino@gmail.com");
        clientesDTO.setTelefono("313826348");
        Clientes clientes = ClientesConvertidor.dtoAEntidad(clientesDTO);
        when(repositorioClientesMock.save(ArgumentMatchers.any(Clientes.class))).thenReturn(clientes);
        //when
        ClientesDTO respuesta = servicioClientes.guardarCliente(clientesDTO);
        //then
        verify(repositorioClientesMock, times(1)).save(ArgumentMatchers.any(Clientes.class));
        assertEquals(respuesta.getCorreoElectronico(), clientesDTO.getCorreoElectronico());
        assertEquals(respuesta.getMensaje(),clientesDTO.getMensaje());
        assertEquals(respuesta.getNombre(),clientesDTO.getNombre());
        assertEquals(respuesta.getTelefono(),clientesDTO.getTelefono());

    }

    @Test
    public void traerClientes() {
        // given
        List<Clientes> clientes = Arrays.asList(
                new Clientes("Andres Garcia", "varmencino@gmail.com", "3148633210","Buenas noches",15L),
                new Clientes("Adrian Barrera", "admabaga@outlook.com", "3218854756","Dejo mi info",5L)
        );
        List<ClientesDTO> clientesDTOS = clientes.stream()
                .map(cliente -> ClientesConvertidor.entidadADto(cliente))
                .collect(Collectors.toList());

        when(repositorioClientesMock.findAll()).thenReturn(clientes);

        // when
        List<ClientesDTO> clientesDTOList = servicioClientes.traerClientes();

        // then
        assertTrue(clientesDTOList.size() > 0);
        assertEquals(clientesDTOList.size(), clientesDTOS.size());
    }
}
