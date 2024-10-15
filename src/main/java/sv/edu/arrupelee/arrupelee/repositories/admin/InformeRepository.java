package sv.edu.arrupelee.arrupelee.repositories.admin;

import sv.edu.arrupelee.arrupelee.model.admin.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InformeRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByidRol(final Long idRol);
}
