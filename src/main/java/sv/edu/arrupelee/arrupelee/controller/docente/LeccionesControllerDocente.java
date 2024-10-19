package sv.edu.arrupelee.arrupelee.controller.docente;

import java.util.List;
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

@Controller
public class LeccionesControllerDocente {
    
    @Autowired
    LeccionesRepository leccionesRepository;
    
    @Autowired
    NivelEducativoRepository nivelEducativoRepository;
    
    @RequestMapping("/lecciones-docente")
    public String lecciones(Model model) {
        List<Lecciones> lecciones = leccionesRepository.findAll();
        
        for (Lecciones leccion : lecciones) {
            leccion.setNombreNivelEducativo(nivelEducativoRepository.findById(leccion.getIdNivel()).orElse(new NivelEducativo()).getNombre());
        }
        
        model.addAttribute("lecciones", leccionesRepository.findAll());
        
        return "/docente/lecciones/vista_lecciones";
    }
    
    @RequestMapping("/agregar-leccion-docente")
    public String agregarLeccion(Model model) {
        model.addAttribute("niveles", nivelEducativoRepository.findAll());
        return "/docente/lecciones/agregar_leccion";
    }
    
    @RequestMapping("/guardar-leccion-docente")
    public String guardarLeccion(Model model, @RequestParam Long idNivelEducativo, @RequestParam String estado, @RequestParam String nivelLiterario, @RequestParam String nombre, @RequestParam String imagenes) {
        Lecciones leccion = new Lecciones();
        leccion.setNombre(nombre);
        leccion.setEstado(estado);
        leccion.setContenido(imagenes);
        leccion.setIdNivel(idNivelEducativo);
        leccion.setNivelLiterario(nivelLiterario);
        leccionesRepository.save(leccion);
        
        return "redirect:/lecciones-docente";
    }
    
}
