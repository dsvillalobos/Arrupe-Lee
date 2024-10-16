package sv.edu.arrupelee.arrupelee.repositories.estudiante;

import sv.edu.arrupelee.arrupelee.model.estudiante.LeccionesPruebas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LeccionesPruebasRepository extends JpaRepository <LeccionesPruebas, Long> {
    LeccionesPruebas findByEstado(final String estado);
    LeccionesPruebas findByIdLeccion(final Long idLeccion);
}
