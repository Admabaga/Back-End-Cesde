package com.example.ProyectoCesde.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Entity
public class Correo {
    @Id
    @GeneratedValue
    private Long id;
    private String remitente;
    private String correo;
    private String asunto;
    private String cuerpoDelCorreo;
    private String nombreArchivo;
    private MultipartFile archivo;


    public Correo(Long id, String remitente, String correo, String asunto, String cuerpoDelCorreo, String nombreArchivo, MultipartFile archivo) {
        this.id = id;
        this.remitente = remitente;
        this.correo = correo;
        this.asunto = asunto;
        this.cuerpoDelCorreo = cuerpoDelCorreo;
        this.nombreArchivo = nombreArchivo;
        this.archivo = archivo;
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

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public MultipartFile getArchivo() {
        return archivo;
    }

    public void setArchivo(MultipartFile archivo) {
        this.archivo = archivo;
    }

    @Override
    public String toString() {
        return "Correo{" +
                "id=" + id +
                ", asunto='" + asunto + '\'' +
                ", cuerpoDelCorreo='" + cuerpoDelCorreo + '\'' +
                ", correo='" + correo + '\'' +
                ", remitente='" + remitente + '\'' +
                '}';
    }
}
