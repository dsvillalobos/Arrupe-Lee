package sv.edu.arrupelee.arrupelee.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sv.edu.arrupelee.arrupelee.model.admin.Seccion;
import sv.edu.arrupelee.arrupelee.repositories.admin.SeccionRepository;

@Controller
public class SeccionController {
    
    @Autowired
    SeccionRepository seccionRepository;
    
    @RequestMapping("/secciones")
    public String secciones(Model model) {
        model.addAttribute("secciones", seccionRepository.findAll());
        
        return "/admin/secciones/vista_secciones";
    }
    
    @RequestMapping("/agregar-seccion")
    public String agregarSeccion(Model model) {
        return "/admin/secciones/agregar_seccion";
    }
    
    @RequestMapping("/guardar-seccion")
    public String guardarSeccion(@RequestParam String nombre) {
        Seccion seccion = new Seccion();
        seccion.setNombre(nombre);
        seccionRepository.save(seccion);
        
        return "redirect:/secciones";
    }
    
    @RequestMapping("/editar-seccion/{id}")
    public String editarSeccion(@PathVariable Long id, Model model) {
        model.addAttribute("seccion", seccionRepository.findById(id).orElse(null));
        
        return "/admin/secciones/editar_seccion";
    }
    
    @RequestMapping("actualizar-seccion")
    public String actualizarSeccion(@RequestParam Long id, @RequestParam String nombre) {
        Seccion seccion = seccionRepository.findById(id).orElse(null);
        seccion.setNombre(nombre);
        seccionRepository.save(seccion);
        
        return "redirect:/secciones";
    }
    
}
