package sv.edu.arrupelee.arrupelee.controller.admin;

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
public class LeccionesControllerAdmin {
    
    @Autowired
    LeccionesRepository leccionesRepository;
    
    @Autowired
    NivelEducativoRepository nivelEducativoRepository;
    
    @RequestMapping("/lecciones")
    public String lecciones(Model model) {
        List<Lecciones> lecciones = leccionesRepository.findAll();
        
        for (Lecciones leccion : lecciones) {
            leccion.setNombreNivelEducativo(nivelEducativoRepository.findById(leccion.getIdNivel()).orElse(new NivelEducativo()).getNombre());
        }
        
        model.addAttribute("lecciones", leccionesRepository.findAll());
        
        return "/admin/lecciones/vista_lecciones";
    }
    
    @RequestMapping("/agregar-leccion")
    public String agregarLeccion(Model model) {
        model.addAttribute("niveles", nivelEducativoRepository.findAll());
        return "/admin/lecciones/agregar_leccion";
    }
    
    @RequestMapping("/guardar-leccion")
    public String guardarLeccion(Model model, @RequestParam Long idNivelEducativo, @RequestParam String estado, @RequestParam String nivelLiterario, @RequestParam String nombre, @RequestParam String imagenes) {
        Lecciones leccion = new Lecciones();
        leccion.setNombre(nombre);
        leccion.setEstado(estado);
        leccion.setContenido(imagenes);
        leccion.setIdNivel(idNivelEducativo);
        leccion.setNivelLiterario(nivelLiterario);
        leccionesRepository.save(leccion);
        
        return "redirect:/lecciones";
    }
    
    @RequestMapping("/estado-leccion-admin/{idLeccion}")
    public String estadoLeccionAdmin(Model model, @PathVariable Long idLeccion) {
        Lecciones leccionEstado = leccionesRepository.findById(idLeccion).orElse(null);
        model.addAttribute("idLeccion", leccionEstado.getId());
        model.addAttribute("nombreEstadoLeccion", leccionEstado.getNombre());
        return "admin/lecciones/estado_leccion";
    }
    
    @RequestMapping("/guardar-estado-leccion-admin")
    public String guardarEstadoLeccionAdmin(Model model, @RequestParam Long idLeccion, @RequestParam String estado) {
        Lecciones leccionEstado = leccionesRepository.findById(idLeccion).orElse(null);
        leccionEstado.setEstado(estado);
        leccionesRepository.save(leccionEstado);
        
        return "redirect:/lecciones";
    }
    
    @RequestMapping("/editar-leccion-admin/{idLeccion}")
    public String editarLeccion(Model model, @PathVariable Long idLeccion) {
        Lecciones leccion = leccionesRepository.findById(idLeccion).orElse(null);
        model.addAttribute("leccion", leccion);
        model.addAttribute("niveles", nivelEducativoRepository.findAll());
        
        return "admin/lecciones/editar_leccion";
    }
    
    @RequestMapping("/guardar-edicion-leccion-admin")
    public String guardarEdicionLeccionAdmin(Model model, @RequestParam Long idLeccion, @RequestParam String nombre, @RequestParam String nivelLiterario, @RequestParam String contenido, @RequestParam String estado, @RequestParam Long idNivelEducativo) {
        Lecciones leccion  = leccionesRepository.findById(idLeccion).orElse(null);
        leccion.setNombre(nombre);
        leccion.setEstado(estado);
        leccion.setContenido(contenido);
        leccion.setNivelLiterario(nivelLiterario);
        leccion.setIdNivel(idNivelEducativo);
        leccionesRepository.save(leccion);
        
        return "redirect:/lecciones";
    }
    
}
