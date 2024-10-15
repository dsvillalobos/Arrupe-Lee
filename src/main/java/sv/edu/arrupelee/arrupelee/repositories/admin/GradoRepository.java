package sv.edu.arrupelee.arrupelee.repositories.admin;

import sv.edu.arrupelee.arrupelee.model.admin.Grado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradoRepository extends JpaRepository <Grado, Long> {
    Grado findByDescripcion(final String descripcion);
}
