package sv.edu.arrupelee.arrupelee.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sv.edu.arrupelee.arrupelee.model.admin.Rol;
import sv.edu.arrupelee.arrupelee.repositories.admin.RolRepository;

@Controller
public class RolController {
    
    @Autowired
    RolRepository rolRepository;
    
    @RequestMapping("/roles")
    public String roles(Model model) {
        model.addAttribute("roles", rolRepository.findAll());
        
        return "admin/roles/vista_roles";
    }
    
    @RequestMapping("/agregar-rol")
    public String agregarRol(Model model) {
        return "admin/roles/agregar_rol";
    }
    
    @RequestMapping("/guardar-rol")
    public String guardarRol(@RequestParam String descripcion) {
        Rol rol = new Rol();
        rol.setDescripcion(descripcion);
        rolRepository.save(rol);
        
        return "redirect:/roles";
    }
    
    @RequestMapping("/editar-rol/{id}")
    public String editarRol(@PathVariable Long id, Model model) {
        model.addAttribute("rol", rolRepository.findById(id).orElse(null));
        
        return "admin/roles/editar_rol";
    }
    
    @RequestMapping("/actualizar-rol")
    public String actualizarRol(@RequestParam Long id, @RequestParam String descripcion) {
        Rol rol = rolRepository.findById(id).orElse(null);
        rol.setDescripcion(descripcion);
        rolRepository.save(rol);
        
        return "redirect:/roles";
    }
    
}
