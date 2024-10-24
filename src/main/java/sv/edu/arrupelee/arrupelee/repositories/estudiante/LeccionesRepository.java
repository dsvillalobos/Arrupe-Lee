package sv.edu.arrupelee.arrupelee.repositories.estudiante;

import sv.edu.arrupelee.arrupelee.model.estudiante.Lecciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LeccionesRepository extends JpaRepository <Lecciones, Long> {
    /*
    
    Al estudiante se le mostraran las lecciones por:
        - ESTADO = "HABILITADO"
        - NIVEL_LITERARIO
        - ID_NIVEL
    
    */
    
    @Query("SELECT l FROM Lecciones l WHERE l.estado = :estado AND l.nivelLiterario = :nivelLiterario AND l.idNivel = :idNivel")
    List <Lecciones> findByEstadoAndNivelLiterarioAndIdNivel(@Param("estado") String estado, @Param("nivelLiterario") String nivelLiterario, @Param("idNivel") Long idNivel);
    
}
