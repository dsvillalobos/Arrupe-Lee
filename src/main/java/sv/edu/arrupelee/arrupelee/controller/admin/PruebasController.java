package sv.edu.arrupelee.arrupelee.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sv.edu.arrupelee.arrupelee.model.estudiante.Lecciones;
import sv.edu.arrupelee.arrupelee.repositories.estudiante.LeccionesRepository;
import sv.edu.arrupelee.arrupelee.model.admin.NivelEducativo;
import sv.edu.arrupelee.arrupelee.repositories.admin.NivelEducativoRepository;
import sv.edu.arrupelee.arrupelee.model.estudiante.LeccionesPruebas;
import sv.edu.arrupelee.arrupelee.repositories.estudiante.LeccionesPruebasRepository;

@Controller
public class PruebasController {
    
    @Autowired
    LeccionesRepository leccionesRepository;
    
    @Autowired
    NivelEducativoRepository nivelEducativoRepository;
    
    @Autowired
    LeccionesPruebasRepository leccionesPruebasRepository;
    
    @RequestMapping("/agregar-prueba")
    public String agregarPrueba(Model model) {
        model.addAttribute("niveles", nivelEducativoRepository.findAll());
        return "admin/pruebas/agregar_prueba";
    }
    
    @RequestMapping("/pruebas-admin")
    public String pruebasAdmin(Model Model) {
        return "admin/pruebas/vista_pruebas";
    }
    
    @RequestMapping("/vincular-prueba")
    public String vincularPrueba(Model model) {
        model.addAttribute("lecciones", leccionesRepository.findAll());
        return "admin/pruebas/vincular_prueba";
    }
    
    @RequestMapping("/guardar-vinculacion")
    public String guardarVinculacion(Model mode, @RequestParam Long idLeccion, @RequestParam Long idPrueba) {
        LeccionesPruebas leccionesPruebas = new LeccionesPruebas();
        leccionesPruebas.setIdLeccion(idLeccion);
        leccionesPruebas.setIdPrueba(idPrueba);
        leccionesPruebasRepository.save(leccionesPruebas);
        
        return "redirect:/pruebas-admin";
    }
    
}
