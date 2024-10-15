package sv.edu.arrupelee.arrupelee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sv.edu.arrupelee.arrupelee.model.admin.Usuario;
import sv.edu.arrupelee.arrupelee.repositories.AuthRepository;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({"usuario", "ID", "correo", "carnet", "nivelEducativo"})
@Controller
public class AuthController {

    @Autowired
    AuthRepository authRepository;

    @RequestMapping("/ingreso-usuario")
    public String ingresoUsuario(Model model, @RequestParam String carnet, @RequestParam String contrasena) {
        Usuario usuario = authRepository.findByCarnet(carnet);

        if (usuario != null) {
            if (usuario.getContrasena().equals(contrasena)) {
                model.addAttribute("usuario", (usuario.getNombre() + " " + usuario.getApellido()));
                model.addAttribute("ID", usuario.getId());
                model.addAttribute("correo", usuario.getCorreo());
                model.addAttribute("carnet", usuario.getCarnet());
                // Mandar el ID del nivel educativo (para el estudiante)
                model.addAttribute("nivelEducativo", usuario.getIdNivelEducativo());
                
                // Verificar el Rol
                if (usuario.getIdRol() == 1) { // Estudiante
                    return "redirect:/portal-estudiante";
                } else if (usuario.getIdRol() == 2) { // Docente
                    return "redirect:/auth-error";
                } else if (usuario.getIdRol() == 3) { // Admin
                    return "forward:/portal-admin";
                } else {
                    return "redirect:/auth-error";
                }
            } else {
                return "redirect:/auth-error";
            }
        } else {
            return "redirect:/auth-error";
        }
    }

}
