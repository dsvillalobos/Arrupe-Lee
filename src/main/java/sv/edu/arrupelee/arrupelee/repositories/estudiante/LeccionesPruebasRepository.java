package sv.edu.arrupelee.arrupelee.repositories.estudiante;

import sv.edu.arrupelee.arrupelee.model.estudiante.LeccionesPruebas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LeccionesPruebasRepository extends JpaRepository <LeccionesPruebas, Long> {
    LeccionesPruebas findByEstado(final String estado);
    LeccionesPruebas findByIdLeccion(final Long idLeccion);
    
    // Query personalizada para obtener el idLeccion basado en el idPrueba
    @Query("SELECT l.idLeccion FROM LeccionesPruebas l WHERE l.idPrueba = :idPrueba")
    Long findIdLeccionByIdPrueba(@Param("idPrueba") Long idPrueba);
}
