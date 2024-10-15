package sv.edu.arrupelee.arrupelee.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sv.edu.arrupelee.arrupelee.model.admin.NivelEducativo;
import sv.edu.arrupelee.arrupelee.repositories.admin.NivelEducativoRepository;

@Controller
public class NivelEducativoController {
    
    @Autowired
    NivelEducativoRepository nivelEducativoRepository;
    
    @RequestMapping("/niveles-educativos")
    public String nivelesEducativos(Model model) {
        model.addAttribute("niveles", nivelEducativoRepository.findAll());
        
        return "admin/niveles-educativos/vista_niveles";
    }
    
    @RequestMapping("/agregar-nivel")
    public String agregarNivel(Model model) {
        return "admin/niveles-educativos/agregar_nivel";
    }
    
    @RequestMapping("/guardar-nivel")
    public String guardarNivel(@RequestParam String nombre) {
        NivelEducativo nivelEducativo = new NivelEducativo();
        nivelEducativo.setNombre(nombre);
        nivelEducativoRepository.save(nivelEducativo);
        
        return "redirect:/niveles-educativos";
    }
    
    @RequestMapping("/editar-nivel/{id}")
    public String editarNivel(@PathVariable Long id, Model model) {
        model.addAttribute("nivel", nivelEducativoRepository.findById(id).orElse(null));
        
        return "admin/niveles-educativos/editar_nivel";
    }
    
    @RequestMapping("/actualizar-nivel")
    public String actualizarNivel(@RequestParam Long id, @RequestParam String nombre, Model model) {
        NivelEducativo nivelEducativo = nivelEducativoRepository.findById(id).orElse(null);
        nivelEducativo.setNombre(nombre);
        nivelEducativoRepository.save(nivelEducativo);
        
        return "redirect:/niveles-educativos";
    }
    
}
