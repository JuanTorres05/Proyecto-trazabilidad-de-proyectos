package com.gestorproyectos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad JPA que representa un Proyecto en el sistema.
 */
@Entity
@Table(name = "proyectos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProyecto;

    @Column(nullable = false, length = 200)
    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    // Relación ManyToOne: Muchos proyectos pueden tener un mismo gestor asignado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gestor_asignado_id")
    private Usuario gestorAsignado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoProyecto estadoActual;

    @Column(nullable = false)
    private LocalDateTime fechaRadicacion;

    @Column(nullable = false)
    private LocalDateTime fechaUltimaActualizacion;

    // Relación bidireccional: Un proyecto puede tener múltiples registros de
    // trazabilidad
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trazabilidad> trazabilidades;

    /**
     * Método que se ejecuta antes de persistir la entidad.
     * Inicializa las fechas y el estado inicial del proyecto.
     */
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.fechaRadicacion = now;
        this.fechaUltimaActualizacion = now;
        if (this.estadoActual == null) {
            this.estadoActual = EstadoProyecto.RADICADO;
        }
    }
}
