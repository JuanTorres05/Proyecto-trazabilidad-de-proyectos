package com.gestorproyectos.controller;

import com.gestorproyectos.dao.ProyectoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoDAO proyectoDAO;

    @Autowired
    private com.gestorproyectos.dao.UsuarioDAO usuarioDAO;

    @GetMapping
    public String listarProyectos(Model model) {
        model.addAttribute("proyectos", proyectoDAO.findAll());
        return "proyectos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("proyecto", new com.gestorproyectos.model.Proyecto());
        model.addAttribute("gestores", usuarioDAO.findAll()); // Idealmente filtrar solo por rol GESTOR
        return "proyectos/formulario";
    }

    @org.springframework.web.bind.annotation.PostMapping("/guardar")
    public String guardarProyecto(com.gestorproyectos.model.Proyecto proyecto) {
        proyectoDAO.save(proyecto);
        return "redirect:/proyectos";
    }
}
