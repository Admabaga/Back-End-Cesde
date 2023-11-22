package com.example.ProyectoCesde.Repositorios;

import com.example.ProyectoCesde.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface RepositorioUsuarios extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
    Usuario traerUsuarioPorNombre(@Param("nombreUsuario") String nombreUsuario);
    @Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
    boolean nombreExiste(@Param("nombreUsuario") String nombreUsuario);
}
