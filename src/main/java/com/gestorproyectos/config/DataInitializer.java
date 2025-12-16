package com.gestorproyectos.config;

import com.gestorproyectos.dao.ProyectoDAO;
import com.gestorproyectos.dao.UsuarioDAO;
import com.gestorproyectos.model.EstadoProyecto;
import com.gestorproyectos.model.Proyecto;
import com.gestorproyectos.model.RolUsuario;
import com.gestorproyectos.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private ProyectoDAO proyectoDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioDAO.count() == 0) {
            System.out.println("No se encontraron usuarios. Iniciando carga de datos de prueba...");

            // Crear Usuarios
            Usuario admin = new Usuario();
            admin.setNombre("Administrador");
            admin.setApellidos("Sistema");
            admin.setCorreo("admin@gestor.com");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setRol(RolUsuario.ADMIN);
            admin.setTelefono("555-0001");
            usuarioDAO.save(admin);

            Usuario gestorAna = new Usuario();
            gestorAna.setNombre("Ana");
            gestorAna.setApellidos("García");
            gestorAna.setCorreo("ana.gestor@gestor.com");
            gestorAna.setPassword(passwordEncoder.encode("1234"));
            gestorAna.setRol(RolUsuario.GESTOR);
            gestorAna.setTelefono("555-0002");
            usuarioDAO.save(gestorAna);

            Usuario gestorCarlos = new Usuario();
            gestorCarlos.setNombre("Carlos");
            gestorCarlos.setApellidos("López");
            gestorCarlos.setCorreo("carlos.gestor@gestor.com");
            gestorCarlos.setPassword(passwordEncoder.encode("1234"));
            gestorCarlos.setRol(RolUsuario.GESTOR);
            gestorCarlos.setTelefono("555-0003");
            usuarioDAO.save(gestorCarlos);

            // Crear Proyectos
            crearProyecto("Implementación ERP Corporativo",
                    "Proyecto para migrar el sistema contable a SAP S/4HANA.",
                    gestorAna, EstadoProyecto.EN_PROCESO);

            crearProyecto("Auditoría de Seguridad IT",
                    "Evaluación anual de vulnerabilidades en infraestructura crítica.",
                    gestorAna, EstadoProyecto.FINALIZADO);

            crearProyecto("Desarrollo App Móvil Clientes",
                    "Nueva aplicación iOS y Android para autogestión de usuarios.",
                    gestorCarlos, EstadoProyecto.RADICADO);

            crearProyecto("Actualización de Servidores",
                    "Renovación de hardware en el data center principal.",
                    gestorCarlos, EstadoProyecto.RECHAZADO);

            System.out.println("Datos de prueba cargados exitosamente.");
        } else {
            System.out
                    .println("La base de datos ya contiene usuarios. Actualizando contraseñas de cuentas de prueba...");
            actualizarPasswordSiExiste("admin@gestor.com", "1234");
            actualizarPasswordSiExiste("ana.gestor@gestor.com", "1234");
            actualizarPasswordSiExiste("carlos.gestor@gestor.com", "1234");
        }
    }

    private void actualizarPasswordSiExiste(String correo, String rawPassword) {
        usuarioDAO.findByCorreo(correo).ifPresent(usuario -> {
            usuario.setPassword(passwordEncoder.encode(rawPassword));
            usuarioDAO.save(usuario);
            System.out.println("Password actualizado para: " + correo);
        });
    }

    private void crearProyecto(String nombre, String descripcion, Usuario gestor, EstadoProyecto estado) {
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(nombre);
        proyecto.setDescripcion(descripcion);
        proyecto.setGestorAsignado(gestor);
        proyecto.setEstadoActual(estado);
        proyecto.setFechaRadicacion(LocalDateTime.now());
        proyecto.setFechaUltimaActualizacion(LocalDateTime.now());
        proyectoDAO.save(proyecto);
    }
}
