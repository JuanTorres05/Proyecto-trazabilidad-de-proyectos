package com.gestorproyectos.dao;

import com.gestorproyectos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz DAO para operaciones CRUD sobre la entidad Usuario.
 */
@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
    java.util.Optional<Usuario> findByCorreo(String correo);
}
