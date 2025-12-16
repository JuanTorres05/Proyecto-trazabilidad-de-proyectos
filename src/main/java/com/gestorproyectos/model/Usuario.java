package com.gestorproyectos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entidad JPA que representa un Usuario en el sistema.
 */
@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(nullable = false, unique = true, length = 150)
    private String correo;

    @Column(length = 20)
    private String telefono;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RolUsuario rol;

    // Relación bidireccional: Un usuario puede ser gestor de varios proyectos
    @OneToMany(mappedBy = "gestorAsignado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proyecto> proyectosAsignados;

    // Relación bidireccional: Un usuario puede realizar varios registros de
    // trazabilidad
    @OneToMany(mappedBy = "usuarioQueRealiza", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trazabilidad> trazabilidadesRealizadas;
}
