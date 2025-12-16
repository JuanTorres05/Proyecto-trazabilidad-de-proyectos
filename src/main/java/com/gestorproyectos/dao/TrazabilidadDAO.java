package com.gestorproyectos.dao;

import com.gestorproyectos.model.Trazabilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interfaz DAO para operaciones CRUD sobre la entidad Trazabilidad.
 * Incluye método custom para buscar trazabilidades por ID de proyecto.
 */
@Repository
public interface TrazabilidadDAO extends JpaRepository<Trazabilidad, Long> {

    /**
     * Encuentra todas las trazabilidades asociadas a un proyecto específico.
     * 
     * @param idProyecto ID del proyecto
     * @return Lista de trazabilidades del proyecto
     */
    List<Trazabilidad> findByProyectoIdProyecto(Long idProyecto);
}
