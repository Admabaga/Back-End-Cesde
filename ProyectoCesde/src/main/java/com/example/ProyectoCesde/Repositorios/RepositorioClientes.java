package com.example.ProyectoCesde.Repositorios;

import com.example.ProyectoCesde.Entidades.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositorioClientes extends JpaRepository<Clientes, Long> {
    @Query("SELECT c.correoElectronico FROM Clientes c")
    List<String> correosClientes();

}
