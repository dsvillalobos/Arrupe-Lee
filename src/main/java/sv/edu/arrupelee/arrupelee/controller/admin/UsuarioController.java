package sv.edu.arrupelee.arrupelee.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sv.edu.arrupelee.arrupelee.model.admin.Usuario;
import sv.edu.arrupelee.arrupelee.repositories.admin.UsuarioRepository;
import sv.edu.arrupelee.arrupelee.model.admin.Rol;
import sv.edu.arrupelee.arrupelee.repositories.admin.RolRepository;
import sv.edu.arrupelee.arrupelee.model.admin.Grado;
import sv.edu.arrupelee.arrupelee.repositories.admin.GradoRepository;
import sv.edu.arrupelee.arrupelee.model.admin.Seccion;
import sv.edu.arrupelee.arrupelee.repositories.admin.SeccionRepository;
import sv.edu.arrupelee.arrupelee.model.admin.NivelEducativo;
import sv.edu.arrupelee.arrupelee.repositories.admin.NivelEducativoRepository;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    GradoRepository gradoRepository;

    @Autowired
    SeccionRepository seccionRepository;

    @Autowired
    NivelEducativoRepository nivelEducativoRepository;

    @RequestMapping("/usuarios")
    public String usuarios(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();

        for (Usuario usuario : usuarios) {
            usuario.setRolNombre(rolRepository.findById(usuario.getIdRol()).orElse(new Rol()).getDescripcion());
            usuario.setGradoNombre(gradoRepository.findById(usuario.getIdGrado()).orElse(new Grado()).getDescripcion());
            usuario.setSeccionNombre(seccionRepository.findById(usuario.getIdSeccion()).orElse(new Seccion()).getNombre());
            usuario.setNivelEducativoNombre(nivelEducativoRepository.findById(usuario.getIdNivelEducativo()).orElse(new NivelEducativo()).getNombre());
        }
        model.addAttribute("usuarios", usuarios);

        return "admin/usuarios/vista_usuarios";
    }

    @RequestMapping("/agregar-usuario")
    public String agregaUsuario(Model model) {
        // Para mostrarlos en el Dropdown (Select)
        model.addAttribute("roles", rolRepository.findAll());
        model.addAttribute("grados", gradoRepository.findAll());
        model.addAttribute("secciones", seccionRepository.findAll());
        model.addAttribute("niveles", nivelEducativoRepository.findAll());
        return "admin/usuarios/agregar_usuario";
    }

    @RequestMapping("/guardar-usuario")
    public String guardarUsuario(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String carnet, @RequestParam String correo, @RequestParam String contrasena, @RequestParam String numero, @RequestParam String estado, @RequestParam Long idRol, @RequestParam Long idGrado, @RequestParam Long idSeccion, @RequestParam Long idNivelEducativo) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCarnet(carnet);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);
        usuario.setNumero(numero);
        usuario.setEstado(estado);
        usuario.setIdRol(idRol);
        usuario.setIdGrado(idGrado);
        usuario.setIdSeccion(idSeccion);
        usuario.setIdNivelEducativo(idNivelEducativo);
        usuarioRepository.save(usuario);

        return "redirect:/usuarios";
    }

    @RequestMapping("/editar-usuario/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioRepository.findById(id).orElse(null));
        // Para mostrarlos en el Dropdown (Select)
        model.addAttribute("roles", rolRepository.findAll());
        model.addAttribute("grados", gradoRepository.findAll());
        model.addAttribute("secciones", seccionRepository.findAll());
        model.addAttribute("niveles", nivelEducativoRepository.findAll());

        return "admin/usuarios/editar_usuario";
    }

    @RequestMapping("/detalle-usuario/{id}")
    public String detalleUsuario(Model model, @PathVariable Long id) {
        Usuario detalleUsuario = usuarioRepository.findById(id).orElse(null);
        detalleUsuario.setRolNombre(rolRepository.findById(detalleUsuario.getIdRol()).orElse(new Rol()).getDescripcion());
        detalleUsuario.setGradoNombre(gradoRepository.findById(detalleUsuario.getIdGrado()).orElse(new Grado()).getDescripcion());
        detalleUsuario.setSeccionNombre(seccionRepository.findById(detalleUsuario.getIdSeccion()).orElse(new Seccion()).getNombre());
        detalleUsuario.setNivelEducativoNombre(nivelEducativoRepository.findById(detalleUsuario.getIdNivelEducativo()).orElse(new NivelEducativo()).getNombre());
        
        model.addAttribute("usuario", detalleUsuario);

        return "admin/usuarios/detalle_usuario";
    }

    @RequestMapping("/actualizar-usuario")
    public String actualizarUsuario(@RequestParam Long id, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String carnet, @RequestParam String correo, @RequestParam String contrasena, @RequestParam String numero, @RequestParam String estado, @RequestParam Long idRol, @RequestParam Long idGrado, @RequestParam Long idSeccion, @RequestParam Long idNivelEducativo) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCarnet(carnet);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);
        usuario.setNumero(numero);
        usuario.setEstado(estado);
        usuario.setIdRol(idRol);
        usuario.setIdGrado(idGrado);
        usuario.setIdSeccion(idSeccion);
        usuario.setIdNivelEducativo(idNivelEducativo);
        usuarioRepository.save(usuario);

        return "redirect:/usuarios";
    }
    
    @RequestMapping("/buscar-usuario")
    public String buscarUsuario(Model model) {
        // Para mostrarlos en el Dropdown (Select)
        model.addAttribute("roles", rolRepository.findAll());
        model.addAttribute("grados", gradoRepository.findAll());
        model.addAttribute("secciones", seccionRepository.findAll());
        model.addAttribute("niveles", nivelEducativoRepository.findAll());
        return "admin/usuarios/buscar_usuario";
    }
    
    @RequestMapping("/agregar-usuarios-csv")
    public String agregarUsuariosCSV(Model model) {
        return "admin/usuarios/agregar_usuarios_csv";
    }

}
