package sv.edu.arrupelee.arrupelee.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sv.edu.arrupelee.arrupelee.model.admin.Grado;
import sv.edu.arrupelee.arrupelee.repositories.admin.GradoRepository;

@Controller
public class GradoController {
    
    @Autowired
    GradoRepository gradoRepository;
    
    @RequestMapping("/grados")
    public String grados(Model model) {
        model.addAttribute("grados", gradoRepository.findAll());
        
        return "admin/grados/vista_grados";
    }
    
    @RequestMapping("/agregar-grado")
    public String agregarGrado(Model model) {
        return "admin/grados/agregar_grado";
    }
    
    @RequestMapping("/guardar-grado")
    public String agregarGrado(@RequestParam String descripcion) {
        Grado grado = new Grado();
        grado.setDescripcion(descripcion);
        gradoRepository.save(grado);
        
        return "redirect:/grados";
    }
    
    @RequestMapping("/editar-grado/{id}")
    public String editarGrado(@PathVariable Long id, Model model) {
        model.addAttribute("grado", gradoRepository.findById(id).orElse(null));
        
        return "admin/grados/editar_grado";
    }
    
    @RequestMapping("/actualizar-grado")
    public String actualizarGrado(@RequestParam Long id, @RequestParam String descripcion) {
        Grado grado = gradoRepository.findById(id).orElse(null);
        grado.setDescripcion(descripcion);
        gradoRepository.save(grado);
        
        return "redirect:/grados";
    }
    
}
