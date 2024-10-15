package sv.edu.arrupelee.arrupelee.repositories.admin;

import sv.edu.arrupelee.arrupelee.model.admin.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeccionRepository extends JpaRepository <Seccion, Long> {
    Seccion findByNombre(final String nombre);
}
