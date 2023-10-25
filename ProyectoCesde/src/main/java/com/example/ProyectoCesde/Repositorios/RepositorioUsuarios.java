package com.example.ProyectoCesde.Repositorios;

import com.example.ProyectoCesde.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioUsuarios extends JpaRepository<Usuario, Long> {
}
