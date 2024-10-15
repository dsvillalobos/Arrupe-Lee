package sv.edu.arrupelee.arrupelee.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sv.edu.arrupelee.arrupelee.model.admin.Usuario;
import sv.edu.arrupelee.arrupelee.repositories.admin.InformeRepository;

@Controller
public class InformeController {

    @Autowired
    InformeRepository informeRepository;

    @RequestMapping("/informes")
    public String informes(Model model) {

        model.addAttribute("nEstudiantes", informeRepository.findByidRol(1L).size()); // 1 es el ID Rol del Estudiante
        model.addAttribute("nDocentes", informeRepository.findByidRol(2L).size()); // 2 es el ID Rol del Docente
        model.addAttribute("nAdmins", informeRepository.findByidRol(3L).size()); // 3 es el ID Rol del Docente
        return "admin/informes";
    }

}
