package sv.edu.arrupelee.arrupelee.repositories.admin;

import sv.edu.arrupelee.arrupelee.model.admin.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository <Rol, Long> {
    Rol findByDescripcion(final String descripcion);
}
