package sv.edu.arrupelee.arrupelee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"usuario", "ID", "correo", "carnet", "nivelEducativo"})
public class NavigationController {
    
    @RequestMapping("/ingresar")
    public String ingresar(Model model) {
        return "ingreso";
    }
    
    @RequestMapping("/auth-error")
    public String authError(Model model) {
        return "err/auth-error";
    }
    
    @RequestMapping("/portal-admin")
    public String portalAdmin(Model model) {
        return "admin/admin_portal";
    }
    
    @RequestMapping("/portal-docente")
    public String portalDocente(Model model) {
        return "docente/docente_portal";
    }
    
}
