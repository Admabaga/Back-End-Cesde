package com.example.ProyectoCesde.Entidades;

import jakarta.activation.DataSource;
import jakarta.mail.util.ByteArrayDataSource;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.io.File;
import java.time.LocalDateTime;


@Entity
public class Correo {
    @Id
    @GeneratedValue
    private Long id;
    private String asunto;
    private String cuerpoDelCorreo;
    private String correo;
    private String remitente;
    private LocalDateTime fechaYHora;


    public Correo(Long id, String asunto, String cuerpoDelCorreo, String correo, String remitente, LocalDateTime fechaYHora) {
        this.id = id;
        this.asunto = asunto;
        this.cuerpoDelCorreo = cuerpoDelCorreo;
        this.correo = correo;
        this.remitente = remitente;
        this.fechaYHora = fechaYHora;
    }

    public Correo() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpoDelCorreo() {
        return cuerpoDelCorreo;
    }

    public void setCuerpoDelCorreo(String cuerpoDelCorreo) {
        this.cuerpoDelCorreo = cuerpoDelCorreo;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    @Override
    public String toString() {
        return "Correo{" +
                "id=" + id +
                ", asunto='" + asunto + '\'' +
                ", cuerpoDelCorreo='" + cuerpoDelCorreo + '\'' +
                ", correo='" + correo + '\'' +
                ", remitente='" + remitente + '\'' +
                ", fechaYHora=" + fechaYHora +
                '}';
    }
}
