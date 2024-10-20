package sv.edu.arrupelee.arrupelee.repositories.estudiante;

import sv.edu.arrupelee.arrupelee.model.estudiante.ProgresoEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProgresoEstudianteRepository extends JpaRepository<ProgresoEstudiante, Long> {

    ProgresoEstudiante findByidLeccion(final Long idLeccion);

    // Query personalizada para encontrar el progreso que coincida con el idLeccion y el idUsuario
    @Query("SELECT pe FROM ProgresoEstudiante pe WHERE pe.idLeccion = :idLeccion AND pe.idUsuario = :idUsuario")
    ProgresoEstudiante findByIdLeccionAndIdUsuario(@Param("idLeccion") Long idLeccion, @Param("idUsuario") Long idUsuario);
    
    // Query personalizada para encontrar el promedio de todos los promedios de todas las lecciones
    @Query("SELECT AVG(pe.porcentajeCompletado) FROM ProgresoEstudiante pe")
    Double findAveragePorcentajeCompletado();
    
    // Query personalizada para encontrar el promedio del un nivel literario
    @Query("SELECT AVG(pe.porcentajeCompletado) FROM ProgresoEstudiante pe JOIN Lecciones l ON pe.idLeccion = l.id WHERE l.nivelLiterario = :nivelLiterario")
    Double findAveragePorcentajeCompletadoByNivelLiterario(@Param("nivelLiterario") String nivelLiterario);
    
}
