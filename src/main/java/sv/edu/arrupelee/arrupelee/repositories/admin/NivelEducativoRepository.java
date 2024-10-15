package sv.edu.arrupelee.arrupelee.repositories.admin;

import sv.edu.arrupelee.arrupelee.model.admin.NivelEducativo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NivelEducativoRepository extends JpaRepository <NivelEducativo, Long> {
    NivelEducativo findByNombre(final String nombre);
}
