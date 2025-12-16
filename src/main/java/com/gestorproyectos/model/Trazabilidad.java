package com.gestorproyectos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entidad JPA que representa un registro de Trazabilidad de un proyecto.
 */
@Entity
@Table(name = "trazabilidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trazabilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String observacion;

    // Relación ManyToOne: Múltiples trazabilidades pertenecen a un mismo proyecto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;

    // Relación ManyToOne: Múltiples trazabilidades pueden ser realizadas por un
    // mismo usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioQueRealiza;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoProyecto nuevoEstado;

    @Column(nullable = false)
    private LocalDateTime fecha;

    /**
     * Método que se ejecuta antes de persistir la entidad.
     * Inicializa la fecha del registro de trazabilidad.
     */
    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }
}
