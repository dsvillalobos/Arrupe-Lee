package sv.edu.arrupelee.arrupelee.controller.docente;

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
public class PruebasControllerDocente {
    
    @Autowired
    LeccionesRepository leccionesRepository;
    
    @Autowired
    NivelEducativoRepository nivelEducativoRepository;
    
    @Autowired
    LeccionesPruebasRepository leccionesPruebasRepository;
    
    @RequestMapping("/agregar-prueba-docente")
    public String agregarPrueba(Model model) {
        model.addAttribute("niveles", nivelEducativoRepository.findAll());
        return "docente/pruebas/agregar_prueba";
    }
    
    @RequestMapping("/pruebas-docente")
    public String pruebasAdmin(Model Model) {
        return "docente/pruebas/vista_pruebas";
    }
    
    @RequestMapping("/vincular-prueba-docente")
    public String vincularPrueba(Model model) {
        model.addAttribute("lecciones", leccionesRepository.findAll());
        return "docente/pruebas/vincular_prueba";
    }
    
    @RequestMapping("/guardar-vinculacion-docente")
    public String guardarVinculacion(Model mode, @RequestParam Long idLeccion, @RequestParam Long idPrueba, @RequestParam String estado) {
        LeccionesPruebas leccionesPruebas = new LeccionesPruebas();
        leccionesPruebas.setIdLeccion(idLeccion);
        leccionesPruebas.setIdPrueba(idPrueba);
        leccionesPruebas.setEstado(estado);
        leccionesPruebasRepository.save(leccionesPruebas);
        
        return "redirect:/pruebas-docente";
    }
    
}
