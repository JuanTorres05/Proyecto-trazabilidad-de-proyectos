package com.gestorproyectos.dao;

import com.gestorproyectos.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz DAO para operaciones CRUD sobre la entidad Proyecto.
 */
@Repository
public interface ProyectoDAO extends JpaRepository<Proyecto, Long> {
}
