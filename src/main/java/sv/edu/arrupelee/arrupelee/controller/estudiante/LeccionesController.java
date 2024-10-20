package sv.edu.arrupelee.arrupelee.controller.estudiante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.SessionAttribute;
import java.util.List;
import sv.edu.arrupelee.arrupelee.model.estudiante.Lecciones;
import sv.edu.arrupelee.arrupelee.repositories.estudiante.LeccionesRepository;
import sv.edu.arrupelee.arrupelee.model.admin.NivelEducativo;
import sv.edu.arrupelee.arrupelee.repositories.admin.NivelEducativoRepository;
import sv.edu.arrupelee.arrupelee.model.estudiante.ProgresoEstudiante;
import sv.edu.arrupelee.arrupelee.repositories.estudiante.ProgresoEstudianteRepository;
import sv.edu.arrupelee.arrupelee.model.estudiante.LeccionesPruebas;
import sv.edu.arrupelee.arrupelee.repositories.estudiante.LeccionesPruebasRepository;
import sv.edu.arrupelee.arrupelee.model.admin.Usuario;
import sv.edu.arrupelee.arrupelee.repositories.admin.UsuarioRepository;

@Controller
@SessionAttributes({"usuario", "ID", "correo", "carnet", "nivelEducativo", "promedioLiteral", "promedioInferencial", "promedioCritico"})
public class LeccionesController {

    @Autowired
    LeccionesRepository leccionesRepository;

    @Autowired
    NivelEducativoRepository nivelEducativoRepository;

    @Autowired
    ProgresoEstudianteRepository progresoEstudianteRepository;
    
    @Autowired
    LeccionesPruebasRepository leccionesPruebasRepository;
    
    @Autowired
    UsuarioRepository usuarioRepository;

    // Navegacion para acceder al portal del estudiante (Desde donde se cargaran las lecciones)
    @RequestMapping("/portal-estudiante")
    public String portalEstudiante(Model model, @SessionAttribute("nivelEducativo") Long idNivelEducativo, @SessionAttribute("ID") Long idUsuario) {
        // Obtener el objeto del Nivel Educativo
        NivelEducativo nivelEducativo = nivelEducativoRepository.findById(idNivelEducativo).orElse(null);

        // Calcular promedio para cada nivel
        double promedioLiteral = calcularPromedioNivel("LITERAL", idNivelEducativo, idUsuario);
        double promedioInferencial = calcularPromedioNivel("INFERENCIAL", idNivelEducativo, idUsuario);
        double promedioCritico = calcularPromedioNivel("CRITICO", idNivelEducativo, idUsuario);

        // Agregar los promedios al modelo
        model.addAttribute("promedioLiteral", Math.round(promedioLiteral * 100.0) / 100.0);
        model.addAttribute("promedioInferencial", Math.round(promedioInferencial * 100.0) / 100.0);
        model.addAttribute("promedioCritico", Math.round(promedioCritico * 100.0) / 100.0);

        return "estudiante/estudiante_portal";
    }

    // Navegacion para acceder a los certificados
    @RequestMapping("/certificados")
    public String certificados(Model model, @SessionAttribute("nivelEducativo") Long idNivelEducativo, @SessionAttribute("ID") Long idUsuario, @SessionAttribute("promedioLiteral") double promedioLiteral, @SessionAttribute("promedioInferencial") double promedioInferencial, @SessionAttribute("promedioCritico") double promedioCritico) {
        return "estudiante/certificados/vista_certificados";
    }

    // Navegacion para ir al certificado
    @RequestMapping("/certificado/{nivelLiterario}")
    public String certificado(Model model, @SessionAttribute("usuario") String usuario, @SessionAttribute("carnet") String carnet, @PathVariable int nivelLiterario) {
        /*
        
        Verificar el nivelLiterario que se envia:
        1- Literal
        2- Inferencia
        3- Critico
        
         */

        if (nivelLiterario == 1) {
            model.addAttribute("nivelLiterario", "Nivel Literal");
            model.addAttribute("imgCertificado", "/images/nivelLiteral.png");
        } else if (nivelLiterario == 2) {
            model.addAttribute("nivelLiterario", "Nivel Inferencial");
            model.addAttribute("imgCertificado", "/images/nivelInferencial.png");
        } else {
            model.addAttribute("nivelLiterario", "Nivel Crítico");
            model.addAttribute("imgCertificado", "/images/nivelCritico.png");
        }
        
        return "estudiante/certificados/certificado";
    }

    // Metodo para calcular el promedio de cada nivel literario
    // ----------------------------------------------------------------------------------------------------
    private double calcularPromedioNivel(String nivelLiterario, Long idNivelEducativo, Long idUsuario) {
        // Obtener todas las lecciones del nivel
        List<Lecciones> lecciones = leccionesRepository.findByEstadoAndNivelLiterarioAndIdNivel("HABILITADO", nivelLiterario, idNivelEducativo);

        if (lecciones.isEmpty()) {
            return 0.0;
        }

        double sumaProgresos = 0.0;

        // Calcular la suma de progresos
        for (Lecciones leccion : lecciones) {
            ProgresoEstudiante progreso = progresoEstudianteRepository.findByIdLeccionAndIdUsuario(leccion.getId(), idUsuario);

            // Si existe progreso, sumarlo; si no, sumar 0
            sumaProgresos += (progreso != null) ? progreso.getPorcentajeCompletado() : 0;
        }

        // Calcular y retornar el promedio
        return sumaProgresos / lecciones.size();
    }
    // ----------------------------------------------------------------------------------------------------

    // Navegacion para los distintos tipos de nivel literario
    @RequestMapping("/lecciones-literal")
    public String leccionesLiteral(Model model, @SessionAttribute("nivelEducativo") Long idNivelEducativo, @SessionAttribute("ID") Long idUsuario) {
        // Obtener el objeto del Nivel Educativo correspondiente (Para luego mostrar el nombre de este)
        NivelEducativo nivelEducativo = nivelEducativoRepository.findById(idNivelEducativo).orElse(null);

        // Sacar una lista con las lecciones usando la Query personalizada
        List<Lecciones> leccionesEstudiante = leccionesRepository.findByEstadoAndNivelLiterarioAndIdNivel("HABILITADO", "LITERAL", idNivelEducativo);

        // Crear una lista para almacenar los progresos de las lecciones
        List<Double> progresoLecciones = new ArrayList<>();

        // Iterar sobre las lecciones para obtener el prgreso del estudiante en cada una de ellas
        for (Lecciones leccion : leccionesEstudiante) {
            ProgresoEstudiante progreso = progresoEstudianteRepository.findByIdLeccionAndIdUsuario(leccion.getId(), idUsuario);

            // Si no hay progreso, establecer progreso en 0
            double valorProgreso = (progreso != null) ? progreso.getPorcentajeCompletado() : 0;

            progresoLecciones.add(valorProgreso);
        }

        // Mandar las lecciones y el nombre del nivel educativo de las lecciones y del estudiante
        model.addAttribute("lecciones", leccionesEstudiante);
        model.addAttribute("nivelEducativoNombre", nivelEducativo.getNombre());

        // Mandar el progreso de las lecciones
        model.addAttribute("progresoLecciones", progresoLecciones);

        return "estudiante/lecciones/lecciones_literal";
    }

    @RequestMapping("/lecciones-inferencial")
    public String leccionesInferencial(Model model, @SessionAttribute("nivelEducativo") Long idNivelEducativo, @SessionAttribute("ID") Long idUsuario) {
        // Obtener el objeto del Nivel Educativo correspondiente (Para luego mostrar el nombre de este)
        NivelEducativo nivelEducativo = nivelEducativoRepository.findById(idNivelEducativo).orElse(null);

        // Sacar una lista con las lecciones usando la Query personalizada
        List<Lecciones> leccionesEstudiante = leccionesRepository.findByEstadoAndNivelLiterarioAndIdNivel("HABILITADO", "INFERENCIAL", idNivelEducativo);

        // Crear una lista para almacenar los progresos de las lecciones
        List<Double> progresoLecciones = new ArrayList<>();

        // Iterar sobre las lecciones para obtener el prgreso del estudiante en cada una de ellas
        for (Lecciones leccion : leccionesEstudiante) {
            ProgresoEstudiante progreso = progresoEstudianteRepository.findByIdLeccionAndIdUsuario(leccion.getId(), idUsuario);

            // Si no hay progreso, establecer progreso en 0
            double valorProgreso = (progreso != null) ? progreso.getPorcentajeCompletado() : 0;

            progresoLecciones.add(valorProgreso);
        }

        // Mandar las lecciones y el nombre del nivel educativo de las lecciones y del estudiante
        model.addAttribute("lecciones", leccionesEstudiante);
        model.addAttribute("nivelEducativoNombre", nivelEducativo.getNombre());

        // Mandar el progreso de las lecciones
        model.addAttribute("progresoLecciones", progresoLecciones);

        return "estudiante/lecciones/lecciones_inferencial";
    }

    @RequestMapping("/lecciones-critico")
    public String leccionesCritico(Model model, @SessionAttribute("nivelEducativo") Long idNivelEducativo, @SessionAttribute("ID") Long idUsuario) {
        // Obtener el objeto del Nivel Educativo correspondiente (Para luego mostrar el nombre de este)
        NivelEducativo nivelEducativo = nivelEducativoRepository.findById(idNivelEducativo).orElse(null);

        // Sacar una lista con las lecciones usando la Query personalizada
        List<Lecciones> leccionesEstudiante = leccionesRepository.findByEstadoAndNivelLiterarioAndIdNivel("HABILITADO", "CRITICO", idNivelEducativo);

        // Crear una lista para almacenar los progresos de las lecciones
        List<Double> progresoLecciones = new ArrayList<>();

        // Iterar sobre las lecciones para obtener el prgreso del estudiante en cada una de ellas
        for (Lecciones leccion : leccionesEstudiante) {
            ProgresoEstudiante progreso = progresoEstudianteRepository.findByIdLeccionAndIdUsuario(leccion.getId(), idUsuario);

            // Si no hay progreso, establecer progreso en 0
            double valorProgreso = (progreso != null) ? progreso.getPorcentajeCompletado() : 0;

            progresoLecciones.add(valorProgreso);
        }

        // Mandar las lecciones y el nombre del nivel educativo de las lecciones y del estudiante
        model.addAttribute("lecciones", leccionesEstudiante);
        model.addAttribute("nivelEducativoNombre", nivelEducativo.getNombre());

        // Mandar el progreso de las lecciones
        model.addAttribute("progresoLecciones", progresoLecciones);

        return "estudiante/lecciones/lecciones_critico";
    }

    // Esta ruta literalmente es para mostrar las lecciones
    @RequestMapping("/leccion/{id}/{progresoLeccion}")
    public String mostrarLeccion(Model model, @PathVariable Long id, @PathVariable int progresoLeccion) {
        Lecciones leccion = leccionesRepository.findById(id).orElse(null);

        // Aquí separo de las comas las imagenes de la leccion
        String[] imagenesLeccion = leccion.getContenido().split(",");

        // Mandar las imagenes de la leccin a la vista
        model.addAttribute("imagenesLeccion", imagenesLeccion);

        // Mandar la leccion a la vista
        model.addAttribute("leccion", leccion);
        
        // Mandar el progreso de la leccion a la vista
        model.addAttribute("progresoLeccionVista", progresoLeccion);
        
        // Mandar a la vista el id de la prueba vinculado a la leccion
        // Verificar si es nulo, es decir, que no haya una prueba viculada a la leccion
        LeccionesPruebas leccionesPruebas = leccionesPruebasRepository.findByIdLeccion(id);
        
        if (leccionesPruebas == null) {
            model.addAttribute("idPrueba_leccion", null);
        } else {
            model.addAttribute("idPrueba_leccion", leccionesPruebas.getIdPrueba());
        }

        return "estudiante/lecciones/vista_leccion";
    }

    // Esta ruta es para guardar el progreso de las lecciones
    @RequestMapping("/guardar-progreso/{idLeccion}/{progreso}")
    public ResponseEntity<String> guardarProgreso(Model model, @PathVariable Double progreso, @PathVariable Long idLeccion, @SessionAttribute("ID") Long idUsuario) {
        // Verificar si ha habido progreso previo
        ProgresoEstudiante checkProgreso = progresoEstudianteRepository.findByIdLeccionAndIdUsuario(idLeccion, idUsuario);

        if (checkProgreso == null) {
            // Si no ha habido progreso previo
            ProgresoEstudiante progresoAGuardar = new ProgresoEstudiante();
            progresoAGuardar.setIdUsuario(idUsuario);
            progresoAGuardar.setIdLeccion(idLeccion);
            progresoAGuardar.setPorcentajeCompletado(progreso);

            progresoEstudianteRepository.save(progresoAGuardar);
        } else {
            // Si ha habido progreso previo
            checkProgreso.setPorcentajeCompletado(progreso); // Solamente modificar y establecer el nuevo progreso
            progresoEstudianteRepository.save(checkProgreso);
        }

        return ResponseEntity.ok("Progreso guardado exitosamente");
    }
    
    // Ruta para ir a la vista de las pruebas
    @RequestMapping("/prueba/{idLeccion}")
    public String prueba(Model model, @PathVariable Long idLeccion) {
        LeccionesPruebas leccionesPruebas = leccionesPruebasRepository.findByIdLeccion(idLeccion);
        model.addAttribute("idPrueba", leccionesPruebas.getIdPrueba());
        return "estudiante/pruebas/vista_prueba";
    }
    
    // Ruta para ir a a la vista del detalle de la prueba
    @RequestMapping("/resultado-prueba/{idPrueba}")
    public String resultadoPrueba(Model model, @PathVariable Long idPrueba, @SessionAttribute("ID") Long idUsuario) {
        model.addAttribute("idDetallePrueba", idPrueba);
        // Obtener el nombre del estudiante
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        model.addAttribute("nombreEstudianteResultadPrueba", usuario.getNombre() + " " + usuario.getApellido());
        return "estudiante/pruebas/resultado_prueba";
    }
    
}
