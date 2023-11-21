package com.example.ProyectoCesde.Convertidor;

import jakarta.activation.DataSource;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class TransformadorMultipartByArray {
    public static DataSource transformarMultipartFile(MultipartFile archivoAdjunto) throws IOException {
        byte[] bytesArchivo = archivoAdjunto.getBytes();
        ByteArrayDataSource dataSource = new ByteArrayDataSource(bytesArchivo, archivoAdjunto.getContentType());
        return dataSource;
    }
}
